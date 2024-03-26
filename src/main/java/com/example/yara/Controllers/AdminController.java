package com.example.yara.Controllers;


import com.example.yara.Service.UserService;
import com.example.yara.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    @Autowired
    UserService userService;

    @GetMapping("/adminPanel")
    public String getAdminPanel(Model model){
        model.addAttribute("users",userService.getAllUsers());
        return "adminPanel";
    }
    @GetMapping("/adminPanel/setActive/{id}")
    public String setActiveUser(@PathVariable Long id){
        userService.bunUser(id);
        return "redirect:/adminPanel";
    }
    @GetMapping("/adminPanel/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "redirect:/adminPanel";
    }
}
