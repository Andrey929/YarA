package com.example.yara.Controllers;

import com.example.yara.Service.UserService;
import com.example.yara.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user, Model model){
        if (!userService.createUser(user)){
            model.addAttribute("errorMessage", "Пользователь с почтой:" + user.getEmail() + " уже существует");
            return "regPage";
        }
        return "redirect:loginPage";
    }
}
