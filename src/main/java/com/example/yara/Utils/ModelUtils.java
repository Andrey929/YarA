package com.example.yara.Utils;


import com.example.yara.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class ModelUtils {
    public static User getActiveUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }
}
