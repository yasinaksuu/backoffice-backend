package com.omniteam.backofisbackend.service.implementation;

import com.omniteam.backofisbackend.base.annotions.LogMethodCall;
import com.omniteam.backofisbackend.dto.order.OrderDetailDto;
import com.omniteam.backofisbackend.entity.Order;
import com.omniteam.backofisbackend.entity.OrderDetail;
import com.omniteam.backofisbackend.enums.EnumLogIslemTipi;
import com.omniteam.backofisbackend.repository.OrderDetailRepository;
import com.omniteam.backofisbackend.service.OrderDetailService;
import com.omniteam.backofisbackend.shared.mapper.OrderDetailMapper;
import com.omniteam.backofisbackend.shared.result.DataResult;
import com.omniteam.backofisbackend.shared.result.ErrorDataResult;
import com.omniteam.backofisbackend.shared.result.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private SecurityVerificationServiceImpl securityVerificationService;

    @Autowired
    private  LogServiceImpl logService;

    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailMapper orderDetailMapper;

    @Autowired
    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository, OrderDetailMapper orderDetailMapper) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderDetailMapper = orderDetailMapper;
    }

    @LogMethodCall(value = "getByOrderId is started")
    @Override
    public DataResult<List<OrderDetailDto>> getByOrderId(int orderId) {
        if (orderId == 0) {
            return new ErrorDataResult<>("Lütfen detaylarını görmek istediğiniz siparişi belirtiniz", null);
        }
        Order order = new Order();
        order.setOrderId(orderId);
        List<OrderDetail> orderDetails = this.orderDetailRepository.getOrderDetailsByOrder(order);
        List<OrderDetailDto> orderDetailDtoList = this.orderDetailMapper.toOrderDetailDtoList(orderDetails);
        logService.loglama(EnumLogIslemTipi.GetOrderDetails,securityVerificationService.inquireLoggedInUser());
        Method m = new Object() {}
                .getClass()
                .getEnclosingMethod();

        LogMethodCall logMethodCall =  m.getAnnotation(LogMethodCall.class);
        return new SuccessDataResult<>(orderDetailDtoList);
    }
}
