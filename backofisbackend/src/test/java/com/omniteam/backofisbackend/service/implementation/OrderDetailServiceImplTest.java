package com.omniteam.backofisbackend.service.implementation;

import com.omniteam.backofisbackend.dto.order.OrderDetailDto;
import com.omniteam.backofisbackend.entity.Order;
import com.omniteam.backofisbackend.entity.OrderDetail;
import com.omniteam.backofisbackend.repository.OrderDetailRepository;
import com.omniteam.backofisbackend.service.OrderDetailService;
import com.omniteam.backofisbackend.shared.mapper.OrderDetailMapper;
import com.omniteam.backofisbackend.shared.result.DataResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OrderDetailServiceImplTest {
    @Mock
    private SecurityVerificationServiceImpl securityVerificationService;
    @Mock
    private  LogServiceImpl logService;
    @Mock
    private OrderDetailRepository orderDetailRepository;
    @Spy
    private OrderDetailMapper orderDetailMapper = Mappers.getMapper(OrderDetailMapper.class);
    @InjectMocks
    private OrderDetailServiceImpl orderDetailService;

    @Test
    public void getByOrderId_ReturnErrorDataResult_IfGivenOrderIdEqualZero() {
        DataResult<?> dataResult = this.orderDetailService.getByOrderId(0);
        Assertions.assertThat(dataResult.getData()).isNull();
        Assertions.assertThat(dataResult.isSuccess()).isFalse();
    }

    @Test
    public void getByOrderId() {
        List<OrderDetail> orderDetails = Arrays.asList(new OrderDetail(),new OrderDetail());
        Mockito.when(
                this.orderDetailRepository.getOrderDetailsByOrder(
                        Mockito.any(Order.class)
                )
        ).thenReturn(orderDetails);
        DataResult<List<OrderDetailDto>> dataResult = this.orderDetailService.getByOrderId(1);

        Assertions.assertThat(dataResult).isNotNull();
        Assertions.assertThat(dataResult.isSuccess()).isTrue();
        Assertions.assertThat(dataResult.getData()).hasSize(2);
    }
}