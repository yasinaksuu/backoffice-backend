package com.omniteam.backofisbackend.service.implementation;

import com.omniteam.backofisbackend.dto.order.OrderDto;
import com.omniteam.backofisbackend.entity.Order;
import com.omniteam.backofisbackend.repository.*;
import com.omniteam.backofisbackend.service.SecurityVerificationService;
import com.omniteam.backofisbackend.shared.mapper.OrderDetailMapper;
import com.omniteam.backofisbackend.shared.mapper.OrderMapper;
import com.omniteam.backofisbackend.shared.result.DataResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductPriceRepository productPriceRepository;
    @Mock
    private OrderDetailRepository orderDetailRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private LogServiceImpl logService;
    @Mock
    private SecurityVerificationService securityVerificationService;
    @Mock
    private JobLauncher jobLauncher;
    @Mock
    private Job orderExporterJob;
    @Spy
    private OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);
    @Spy
    private OrderDetailMapper orderDetailMapper= Mappers.getMapper(OrderDetailMapper.class);

    @InjectMocks
    private OrderServiceImpl orderService;
    @Test
    void getById() {
        Optional<Order> optionalOrder = Optional.of(new Order());
        Mockito.when(
                this.orderRepository.findById(Mockito.anyInt())
        ).thenReturn(optionalOrder);

        DataResult<OrderDto> result = this.orderService.getById(1);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.isSuccess()).isTrue();
        Assertions.assertThat(result.getData()).isNotNull();
    }
}