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
        if(this.roleRepository.countAllBy()<=0)
            setupStandartRoles();
    }




    public void setupStandartRoles()
    {
        List<Role> roleList = new ArrayList();
        roleList.add(new Role("standart"));
        roleList.add(new Role("leader"));
        roleList.add(new Role("chief"));
        roleList.add(new Role("admin"));
        roleList.add(new Role("owner"));
        roleRepository.saveAll(roleList);
    }


}
