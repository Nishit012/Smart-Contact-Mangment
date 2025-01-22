package com.example.smartcontactmanager.Controllers;


import com.example.smartcontactmanager.Entities.User;
import com.example.smartcontactmanager.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/index")
    public String dashboard(Model model, Principal principal) {
        String userName= principal.getName();
        User user=userRepository.getUserByUserName(userName);
        model.addAttribute("user", user);
        model.addAttribute("imageUrl", user.getImageUrl());

        return "normal/user_dashboard";
    }
}
