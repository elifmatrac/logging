package com.exatern.demo.controller;

import com.exatern.demo.controller.model.UserModel;
import com.exatern.demo.service.UserService;
import com.exatern.demo.service.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserModel> create(@Valid @RequestBody UserModel model) {

        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(model, dto);
        UserDTO createdUser =userService.createUser(dto);

        UserModel createdUserModel = new UserModel();
        BeanUtils.copyProperties(createdUser, createdUserModel);

        return new ResponseEntity<UserModel>(createdUserModel, HttpStatus.CREATED);

    }
}
