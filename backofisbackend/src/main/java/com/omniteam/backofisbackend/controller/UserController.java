package com.omniteam.backofisbackend.controller;

import com.omniteam.backofisbackend.requests.user.UserAddRequest;
import com.omniteam.backofisbackend.service.RoleService;
import com.omniteam.backofisbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(userService.getAll(pageable));
    }

    @PostMapping
    public ResponseEntity saveUser(@RequestBody UserAddRequest addRequest)
    {
        return ResponseEntity.ok(userService.add(addRequest));
    }


}
