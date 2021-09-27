package com.omniteam.backofisbackend.controller;

import com.omniteam.backofisbackend.requests.user.UserAddRequest;
import com.omniteam.backofisbackend.requests.user.UserUpdateRequest;
import com.omniteam.backofisbackend.service.UserService;
import com.omniteam.backofisbackend.shared.result.Result;
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

    @PostMapping("/add")
    public ResponseEntity<Result> saveUser(@RequestBody UserAddRequest addRequest) {
        return ResponseEntity.ok(userService.add(addRequest));
    }

    @PostMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable("id") Integer userId, @RequestBody UserUpdateRequest updateRequest) {
        return ResponseEntity.ok(userService.update(userId, updateRequest));
    }


}
