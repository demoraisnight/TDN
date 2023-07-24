package com.vinicius.cinema.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = "SecurityController")
@Controller
public class SecurityController {



    @ApiOperation("Faz login no sistema")
    @PostMapping(value = "login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password){
        return "redirect:/login";
    }
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content"),
    })
    @ApiOperation("Faz logout no sistema")
    @GetMapping(value = "logout")
    public ResponseEntity<Void> logout(){
        return ResponseEntity.noContent().build();
    }


}
