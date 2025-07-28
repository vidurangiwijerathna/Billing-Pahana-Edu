package com.book.controller;

import com.book.dto.TokenDTO;
import com.book.dto.UsersDTO;
import com.book.dto.requestDTO.RegistrationOrLoginAdminsDTO;
import com.book.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/v2")
@CrossOrigin("*")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/signup")
    public TokenDTO createUsers(@RequestBody RegistrationOrLoginAdminsDTO registrationOrLoginAdminsDTO){
        return userService.createUsers(registrationOrLoginAdminsDTO);
    }

    @PostMapping(path = "/signing")
    public TokenDTO loginUsers(@RequestBody RegistrationOrLoginAdminsDTO registrationOrLoginAdminsDTO){
        System.out.println(registrationOrLoginAdminsDTO);
        return userService.loginAdmin(registrationOrLoginAdminsDTO);
    }

    @GetMapping(path = "/all")
    public List<UsersDTO> getAllUsers(){
        return userService.getAllAdmins();
    }

    @PostMapping(path = "/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response){
        request.getSession().invalidate();

        return ResponseEntity.ok().build();
    }

}
