package com.omniteam.backofisbackend.service.implementation;

import com.omniteam.backofisbackend.repository.UserRepository;
import com.omniteam.backofisbackend.shared.constant.ResultMessage;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SecurityVerificationServiceImplTest {

    @InjectMocks
    private SecurityVerificationServiceImpl securityVerificationService;

    @Mock
    private UserRepository userRepository;


    private User obtainTokenizedUser(Object principal) {
        return principal instanceof User ? (User) principal : null;
    }


    @Test
     public void inquireLoggedInUser() {
        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContextHolder securityContextHolder = Mockito.mock(SecurityContextHolder.class);

    //Mockito.when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(auth);
        com.omniteam.backofisbackend.entity.User user = new com.omniteam.backofisbackend.entity.User();
        user.setEmail("test@etiya");
        user.setPassword("123");
        user.setIsActive(true);


        UserDetails userDetails = new User("username", "password", true, true, true, true, auth.getAuthorities()  );
        assertNotNull(userDetails);

      //  Mockito.when(userRepository.findUserByEmailAndIsActive(user.getEmail(), user.getIsActive())).thenReturn(user);
        assertNotEquals(null,userDetails);
      //  assertEquals(user,userDetails);

     com.omniteam.backofisbackend.entity.User userService = securityVerificationService.inquireLoggedInUser();
        assertNull(userService);


   //   Assertions.assertThat(userrservice).isNotNull();
    }

    @Test
    void testObtainTokenizedUser() {
        Principal principal = new Principal() {
            @Override
            public String getName() {
                return null;
            }
        };
    User user = securityVerificationService.obtainTokenizedUser(principal);

    }
}

  /*  public void inquireLoggedInUser() {
        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext secCont = Mockito.mock(SecurityContext.class);


        // Mockito.when(secCont.getAuthentication()).thenReturn(auth);
        // UserDetails userDetails = auth != null ? obtainTokenizedUser(auth.getPrincipal()) : null;
        com.omniteam.backofisbackend.entity.User user =new com.omniteam.backofisbackend.entity.User();
        //   Mockito.when(userRepository.findUserByEmailAndIsActive("test@etiya.com",true)).thenReturn(user);
        assertNotNull(user);*/