package com.omniteam.backofisbackend.service.implementation;

import com.omniteam.backofisbackend.repository.RoleRepository;
import com.omniteam.backofisbackend.service.RoleService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.Random;

@ExtendWith(MockitoExtension.class)
public class RoleServiceImpTest {


    @Mock
    private RoleRepository roleRepository;

    private RoleService roleService=new RoleServiceImpl();

    @BeforeAll
    static void beforeClassForPrepare() {
        //prepare tests
    }


    @Test
    void getAllRolesTest() {
        //Mocking
//        Mockito.when(roleRepository.findAllByFilter(null, Pageable.unpaged())).thenReturn();

        Integer roleArraySize = new Random().nextInt(10);
        for (int i=1;i<=roleArraySize;i++)
        System.out.println("asdasd:"+i);
    }


    @Test
    void getAllRolesWithSearchKeywordTest() {
        System.out.println("qweqweqw");
    }


}
