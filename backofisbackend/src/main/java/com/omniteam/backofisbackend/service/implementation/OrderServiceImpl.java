package com.omniteam.backofisbackend.service.implementation;

import com.omniteam.backofisbackend.dto.PagedDataWrapper;
import com.omniteam.backofisbackend.dto.order.AddProductToCartRequest;
import com.omniteam.backofisbackend.dto.order.OrderDto;
import com.omniteam.backofisbackend.entity.*;
import com.omniteam.backofisbackend.repository.*;
import com.omniteam.backofisbackend.repository.customspecification.OrderSpec;
import com.omniteam.backofisbackend.requests.order.*;
import com.omniteam.backofisbackend.service.OrderService;
import com.omniteam.backofisbackend.shared.mapper.OrderDetailMapper;
import com.omniteam.backofisbackend.shared.mapper.OrderMapper;
import com.omniteam.backofisbackend.shared.result.DataResult;
import com.omniteam.backofisbackend.shared.result.Result;
import com.omniteam.backofisbackend.shared.result.SuccessDataResult;
import com.omniteam.backofisbackend.shared.result.SuccessResult;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    Job orderExporterJob;

    private final OrderDetailMapper orderDetailMapper;
    private final ProductPriceRepository productPriceRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, OrderDetailMapper orderDetailMapper, ProductPriceRepository productPriceRepository, OrderDetailRepository orderDetailRepository, CustomerRepository customerRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.orderDetailMapper = orderDetailMapper;
        this.productPriceRepository = productPriceRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public DataResult<OrderDto> getById(int orderId) {
        Optional<Order> optionalOrder = this.orderRepository.findById(orderId);
        OrderDto orderDto = this.orderMapper.toOrderDto(optionalOrder.orElse(null));
        return new SuccessDataResult<>(orderDto);
    }

    @Override
    public DataResult<PagedDataWrapper<OrderDto>> getAll(OrderGetAllRequest orderGetAllRequest) {
        Pageable pageable = PageRequest.of(orderGetAllRequest.getPage(), orderGetAllRequest.getSize());
        Page<Order> orderPage =
                this.orderRepository.findAll(
                        OrderSpec.getAllByFilter(
                                orderGetAllRequest.getCustomerId(),
                                orderGetAllRequest.getStatus(),
                                orderGetAllRequest.getStartDate(),
                                orderGetAllRequest.getEndDate()
                        ), pageable);

        List<OrderDto> orderDtoList = this.orderMapper.toOrderDtoList(orderPage.getContent());
        PagedDataWrapper<OrderDto> pagedDataWrapper = new PagedDataWrapper<>(
                orderDtoList,
                orderPage.getNumber(),
                orderPage.getSize(),
                orderPage.getTotalElements(),
                orderPage.getTotalPages(),
                orderPage.isLast()
        );

        return new SuccessDataResult<>(pagedDataWrapper);
    }

    @Override
    public DataResult<?> startOrderReportExport() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters parameters = new JobParametersBuilder().addParameter("proccess-key", new JobParameter(UUID.randomUUID().toString())).toJobParameters();
        this.jobLauncher.run(orderExporterJob, parameters);
        return DataResult.builder().build();
    }

    @Override
    @Transactional
    public DataResult<OrderDto> add(OrderAddRequest orderAddRequest) {
        Order order = this.orderMapper.toOrderFromOrderAddRequest(orderAddRequest);
        order.getOrderDetails().forEach(orderDetail -> {
            ProductPrice productPrice =
                    this.productPriceRepository.findFirstByProductAndIsActiveOrderByCreatedDateDesc(
                            orderDetail.getProduct(), true);
            orderDetail.setOrder(order);
            orderDetail.setProductPrice(productPrice);
        });
        this.orderRepository.save(order);
        this.orderDetailRepository.saveAll(order.getOrderDetails());
        OrderDto orderDto = this.orderMapper.toOrderDto(order);
        return new SuccessDataResult<>("ürün sepete eklendi", orderDto);
    }

    @Override
    @Transactional
    public DataResult<OrderDto> update(OrderUpdateRequest orderUpdateRequest) {
        Order orderToUpdate = this.orderRepository.getById(orderUpdateRequest.getOrderId());
        this.orderMapper.update(orderToUpdate, orderUpdateRequest);
        orderToUpdate.setModifiedDate(LocalDateTime.now());
        for (OrderDetail orderDetail : orderToUpdate.getOrderDetails()) {
            Optional<OrderDetailUpdateRequest> orderDetailUpdateRequest
                    = orderUpdateRequest.getOrderDetails()
                    .stream()
                    .filter(od -> od.getOrderDetailId().intValue() == orderDetail.getOrderDetailId().intValue())
                    .findFirst();
            if (orderDetailUpdateRequest.isPresent()) {
                this.orderDetailMapper.update(orderDetail, orderDetailUpdateRequest.get());
                orderDetail.setModifiedDate(LocalDateTime.now());
            }

        }
        this.orderRepository.save(orderToUpdate);
        this.orderDetailRepository.saveAll(orderToUpdate.getOrderDetails());

        OrderDto orderDto = this.orderMapper.toOrderDto(orderToUpdate);
        return new SuccessDataResult<>("Sipariş güncellendi", orderDto);
    }

    @Override
    @Transactional
    public Result delete(OrderDeleteRequest orderDeleteRequest) {
        Order orderToDelete = this.orderRepository.getById(orderDeleteRequest.getOrderId());
        orderToDelete.setStatus("DELETED");
        orderToDelete.setIsActive(false);
        orderToDelete.setModifiedDate(LocalDateTime.now());

        for (OrderDetail orderDetail : orderToDelete.getOrderDetails()) {
            orderDetail.setModifiedDate(LocalDateTime.now());
            orderDetail.setStatus("DELETED");
            orderDetail.setIsActive(false);
        }

        this.orderRepository.save(orderToDelete);
        this.orderDetailRepository.saveAll(orderToDelete.getOrderDetails());

        return new SuccessResult("Sipariş silindi");
    }

    @Override
    @Transactional
    public DataResult<OrderDto> addProductToCart(AddProductToCartRequest addProductToCartRequest) {
        Order order =
                this.orderRepository
                        .findFirstByStatusAndIsActiveAndCustomer_CustomerIdOrderByCreatedDateDesc("Waiting",true,addProductToCartRequest.getCustomerId());
        if(order == null){
            order = new Order();
            order.setStatus("Waiting");
            Customer customer = this.customerRepository.getById(addProductToCartRequest.getCustomerId());
            order.setCustomer(customer);
            //TODO inquire user from database by email in the jwttoken
            User user = this.userRepository.getById(1);
            order.setUser(user);
        }
        List<OrderDetail> orderDetails = new ArrayList<>();
        Product product = this.productRepository.getById(addProductToCartRequest.getProductId());
        ProductPrice currentPrice =
                this.productPriceRepository.findFirstByProductAndIsActiveOrderByCreatedDateDesc(product,true);
        for (int i=0; i<addProductToCartRequest.getCount();i++){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setStatus("Confirmed");
            orderDetail.setOrder(order);
            orderDetail.setProduct(product);
            orderDetail.setProductPrice(currentPrice);
            order.getOrderDetails().add(orderDetail);
            orderDetails.add(orderDetail);
        }


        this.orderRepository.save(order);
        this.orderDetailRepository.saveAll(orderDetails);
        DataResult<OrderDto> orderDtoDataResult = this.getById(order.getOrderId());
        return new SuccessDataResult<OrderDto>("Ürün sepete eklendi",orderDtoDataResult.getData());
    }
}
