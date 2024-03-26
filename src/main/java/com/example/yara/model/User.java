package com.example.yara.model;

import com.example.yara.enums.Roles;
import jakarta.persistence.*;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;


@Entity
@Table(name = "users")
@Data
@RequiredArgsConstructor
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String name;
    private String lastName;
    private String email;
    @Column(name = "password",length = 1000)
    private String password;
    @ElementCollection(targetClass = Roles.class,fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Roles> roles = new HashSet<>();
    private LocalDateTime dateOfCreate;
    private Boolean active;


    @PrePersist
    private void init(){
        dateOfCreate = LocalDateTime.now();
    }
    public boolean isAdmin(){
        return roles.contains(Roles.ROLE_ADMIN);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Roles role : roles) {
            System.out.println(role.getAuthority());
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
    public String getNameUserRole(){
        if (roles.contains(Roles.ROLE_ADMIN)){
            return "ADMIN";
        }else if(roles.contains(Roles.ROLE_TEACHER)){
            return "TEACHER";
        }else return "USER";
    }

}
