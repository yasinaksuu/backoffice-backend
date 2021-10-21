package com.omniteam.backofisbackend.service.implementation;


import com.omniteam.backofisbackend.repository.UserRepository;
import com.omniteam.backofisbackend.service.SecurityVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SecurityVerificationServiceImpl implements SecurityVerificationService {

    @Autowired
    private UserRepository userRepository;

    public User obtainTokenizedUser(Object principal) {
        return principal instanceof User ? (User) principal : null;
    }


    @Transactional
    public com.omniteam.backofisbackend.entity.User inquireLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = authentication != null ? obtainTokenizedUser(authentication.getPrincipal()) : null;
    if (userDetails != null)
            return userRepository.findUserByEmailAndIsActive(userDetails.getUsername(), true);
      else return null;

    }

}
