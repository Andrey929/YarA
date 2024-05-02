package com.example.yara.Handlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            String authorityName = authority.getAuthority();
            if (authorityName.equals("ROLE_ADMIN")) {
                response.sendRedirect("/adminPanel");
                return;
            } else if (authorityName.equals("ROLE_TEACHER")) {
                response.sendRedirect("/teacherPanel");
                return;
            }
        }
        response.sendRedirect("/homePage");
    }

}
