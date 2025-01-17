package com.example.smartcontactmanager.Controllers;

import com.example.smartcontactmanager.Entities.User;
import com.example.smartcontactmanager.Helper.Message;
import com.example.smartcontactmanager.Repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

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

    @RequestMapping("/login")
    public String Login() {
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
            user.setImageUrl("default.png");
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
