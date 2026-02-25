package com.example.MVC.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("name", "John Doe");
        return "index";
    }
    @RequestMapping("/message")
    public String message(Model model){
        model.addAttribute("name", "John Doe");
        model.addAttribute("age", 30);
        return "index";
    }
}
