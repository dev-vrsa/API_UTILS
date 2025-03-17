package com.app.utility.me.router;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class pingrouter {


    @GetMapping("/ping")
    public String ping(){

        return "pong";

    }

}
