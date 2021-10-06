package com.omniteam.backofisbackend.service.implementation;

import com.omniteam.backofisbackend.dto.PagedDataWrapper;
import com.omniteam.backofisbackend.entity.User;
import com.omniteam.backofisbackend.repository.UserRepository;
import com.omniteam.backofisbackend.shared.mapper.UserMapper;
import com.omniteam.backofisbackend.shared.result.DataResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Spy
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    private LogServiceImpl logService;
    @Mock
    private SecurityVerificationServiceImpl securityVerificationService;


    @Test
    void getAllUsersTest() {

        List<User> mockedUsers = new ArrayList<>();
        for (int i = 1; i <= 15; i++)
            mockedUsers.add(new User((i + 1)));

        Mockito.when(userRepository.findAll(Pageable.unpaged())).thenReturn(new PageImpl<>(mockedUsers));
        Mockito.when(userRepository.findAll((Pageable) null)).thenReturn(new PageImpl<>(mockedUsers));

        DataResult<PagedDataWrapper<User>> allUsers = userService.getAll(null);
        assertNotNull(allUsers);
        assertNotNull(allUsers.getData().getContent());

        for (int i = 1; i <= 15; i++)
            assertEquals(i, allUsers.getData().getContent().get(i - 1).getUserId());


    }


}
