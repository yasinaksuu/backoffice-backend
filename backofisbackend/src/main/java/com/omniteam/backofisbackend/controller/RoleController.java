package com.omniteam.backofisbackend.controller;

import com.omniteam.backofisbackend.requests.RoleGetAllRequest;
import com.omniteam.backofisbackend.service.RoleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/roles")
public class RoleController {

    @Autowired
    RoleService roleService;


    /*   public RoleController(RoleService roleService) {
           this.roleService = roleService;
       }*/

    @ApiOperation("Rolleri getiren servis")
    @GetMapping("/getall")
    public ResponseEntity<?> getAllRoles(
            @RequestBody RoleGetAllRequest roleGetAllRequest
    ) {
        return ResponseEntity.ok(roleService.getAllRoles(roleGetAllRequest));
    }


}
