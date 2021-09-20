package com.omniteam.backofisbackend.controller;

import com.omniteam.backofisbackend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/roles")
public class RoleController {

    RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllRoles(Pageable pageable) {
        return ResponseEntity.ok(roleService.getAllRoles(pageable));
    }


}
