package com.prax19.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

   @GetMapping
    public String test2() {
        return "index.html";
    }
    
    @GetMapping("/g")
    public ResponseEntity<?> test() {
        return new ResponseEntity<>("test", HttpStatus.OK);
    }
    
}
