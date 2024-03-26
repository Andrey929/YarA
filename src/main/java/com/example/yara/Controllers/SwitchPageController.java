package com.example.yara.Controllers;

import com.example.yara.Service.UserService;
import com.example.yara.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwitchPageController {
    @Autowired
    UserService userService;
    @GetMapping("/RegisterPage")
    public String getRegPage(Model model){
        model.addAttribute("user", new User());
        return "regPage";
    }

    @GetMapping("/LoginPage")
    public String getLoginPage(){
        return "loginPage";
    }

    @GetMapping("/")
    public String getIndexPage(){
        return "index";
    }

    @GetMapping("/homePage")
    public String getHomePage(){
        return "homePage";}

}
