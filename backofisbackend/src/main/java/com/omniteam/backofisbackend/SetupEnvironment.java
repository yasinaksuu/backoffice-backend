package com.omniteam.backofisbackend;

import com.omniteam.backofisbackend.entity.Role;
import com.omniteam.backofisbackend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SetupEnvironment {
    RoleRepository roleRepository;
    public SetupEnvironment(@Autowired RoleRepository roleRep) {
        this.roleRepository=roleRep;
        System.out.println("Setup check is going to starting..");
        if(this.roleRepository.countAllBy()<=0){
            setupStandartRoles();
        }

    }
    public void setupStandartRoles()
    {
        List<Role> roleList = new ArrayList();
        roleList.add(new Role("Admin"));
        roleList.add(new Role("Seller"));
        roleList.add(new Role("Chief"));
        roleList.add(new Role("Manager"));
        roleList.add(new Role("Standard"));
        roleRepository.saveAll(roleList);
    }
}
