package com.exatern.demo.service.impl;

import com.exatern.demo.service.UserService;
import com.exatern.demo.service.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DefaultUserService implements UserService {


    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if(userDTO != null && userDTO.getName() == null){
            throw new RuntimeException("Name cannot be null or empty");
        }
        UserDTO createdUser = new UserDTO();
        createdUser.setId(new Random().nextLong());
        createdUser.setName(userDTO.getName());
        return createdUser;
    }
}
