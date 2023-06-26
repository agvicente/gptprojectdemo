package com.agvicente.gptprojectdemo.resources;

import com.agvicente.gptprojectdemo.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResources {

//    @GetMapping
//    public ResponseEntity<User> findAll(){
//        return ResponseEntity.ok().body(null);
//    }
}
