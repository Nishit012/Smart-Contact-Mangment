package com.example.smartcontactmanager.Controllers;

import com.example.smartcontactmanager.Entities.User;
import com.example.smartcontactmanager.Helper.Message;
import com.example.smartcontactmanager.Repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/")
public class HomeController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;


    @RequestMapping("/")
    public String Home() {
        return "home";
    }

    @RequestMapping("/about")
    public String About() {
        return "about";
    }

    @RequestMapping("/signup")
    public String Signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @GetMapping("/login")
    public String customLogin() {
        return "login";
    }


    //registering user
    @PostMapping("/do_register")
    public String registerUSer(@ModelAttribute("user") User user, @RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model, HttpSession session) {

        try {
            if (!agreement) {
                throw new Exception("You must agree to the terms and conditions.");
            }
            user.setRole("ROLE_USER");
            user.setEnabled(true);

            user.setImageUrl("/img/default.png");
            user.setPassword(passwordEncoder.encode(user.getPassword()));  // encoding password before saving in database.
           this.userRepository.save(user);
            model.addAttribute("user", new User());
            session.setAttribute("message", new Message("Successfully registered!!", "alert-success"));
            return "signup";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("user", user);
            session.setAttribute("message", new Message("Something went wrong!!", "alert-danger"));
            return "signup";
        }
    }
}
