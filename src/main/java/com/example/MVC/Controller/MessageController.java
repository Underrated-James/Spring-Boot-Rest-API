package com.example.MVC.Controller;


import com.example.MVC.Entities.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class MessageController {

    @RequestMapping("/api/message")
    public Message getMessage(){
        return new Message("Hello from the API!");
    }


}
