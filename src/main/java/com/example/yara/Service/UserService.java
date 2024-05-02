package com.example.yara.Service;

import com.example.yara.enums.Roles;
import com.example.yara.model.User;
import com.example.yara.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService{
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private final PasswordEncoder passwordEncoder;
@Transactional
    public boolean createUser(@NonNull User user){
    try {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) return false;
        user.setActive(true);
        user.getRoles().add(Roles.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        log.info("User creating with email: " + user.getEmail());
    }catch (Exception e){
        System.out.println(e.getMessage());
    }
        return true;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public void bunUser(Long id){
        User user = userRepository.getById(id);
        if (user.getActive()){
            user.setActive(false);
            log.info("User with Email: "+user.getEmail()+" was banned");
        }else {
            user.setActive(true);
            log.info("User with Email: "+user.getEmail()+" was activated");
        }
        userRepository.save(user);

    }
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public void switchRole(Long id,String role){
        User user = userRepository.getById(id);
        user.getRoles().clear();
        switch (role){
            case "Admin"-> user.getRoles().add(Roles.ROLE_ADMIN);
            case "Teacher"->{
                teacherService.createTeacher(user);
                user.getRoles().add(Roles.ROLE_TEACHER);
            }
            case "User"->user.getRoles().add(Roles.ROLE_USER);
        }
        userRepository.save(user);
        log.info("Set "+role+" role user with email: "+user.getEmail());

    }

}
