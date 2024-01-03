package com.codewithbhanuka.blog.controllers;

import com.codewithbhanuka.blog.entities.User;
import com.codewithbhanuka.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getUser(){
        System.out.println("Getting users");

        return userService.getUsers();
    }

    //Compares this principal to the specified object. Returns true if the
    // object passed in matches the principal represented by the
    // implementation of this interface.
    @GetMapping("/current-user")
    public String getLoggedInUser(Principal principal){
        return principal.getName();
    }
}
