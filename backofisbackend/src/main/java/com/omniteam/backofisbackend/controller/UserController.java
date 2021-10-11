package com.omniteam.backofisbackend.controller;

import com.omniteam.backofisbackend.base.annotions.LogMethodCall;
import com.omniteam.backofisbackend.enums.EnumLogIslemTipi;
import com.omniteam.backofisbackend.requests.user.UserAddRequest;
import com.omniteam.backofisbackend.requests.user.UserUpdateRequest;
import com.omniteam.backofisbackend.service.UserService;
import com.omniteam.backofisbackend.service.implementation.LogServiceImpl;
import com.omniteam.backofisbackend.service.implementation.SecurityVerificationServiceImpl;
import com.omniteam.backofisbackend.shared.result.ErrorResult;
import com.omniteam.backofisbackend.shared.result.Result;
import com.omniteam.backofisbackend.shared.result.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private SecurityVerificationServiceImpl securityVerificationService;
    @Autowired
    private LogServiceImpl logService;

    @GetMapping("/getall")
    public ResponseEntity<?> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(userService.getAll(pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<Result> saveUser(@RequestBody UserAddRequest addRequest) {
        try {
            Result result = userService.add(addRequest);
            return ResponseEntity.ok(new SuccessResult(result.getId(), "Kullanıcı başarıyla eklendi."));
        }
        catch (Exception ex)
        {
            logService.loglama(EnumLogIslemTipi.UserAdd,securityVerificationService.inquireLoggedInUser());
            Method m = new Object() {}
                    .getClass()
                    .getEnclosingMethod();

            LogMethodCall logMethodCall =  m.getAnnotation(LogMethodCall.class);
            return ResponseEntity.ok(new ErrorResult(ex.getMessage()));
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable("id") Integer userId, @RequestBody UserUpdateRequest updateRequest) {
        return ResponseEntity.ok(userService.update(userId, updateRequest));
    }


}
