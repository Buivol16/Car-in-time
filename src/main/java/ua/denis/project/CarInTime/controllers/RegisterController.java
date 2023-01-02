package ua.denis.project.CarInTime.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.denis.project.CarInTime.model.User;
import ua.denis.project.CarInTime.repositories.UserRepository;
import ua.denis.project.CarInTime.services.HashingService;
import ua.denis.project.CarInTime.services.RegisterService;

import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping(path = "/register")
public class RegisterController {

    @Autowired
    HashingService hashingService;
    @Autowired
    RegisterService registerService;
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String registerPage(Model model){
        model.addAttribute("user", new User());
        return "register";
    }
    @PostMapping
    public String registerUser(@ModelAttribute User user, Model model){
        if (registerService.checkUserToRegister(user)){
            try {
                user.setPassword(hashingService.getHashedString(user.getPassword()));
                userRepository.save(user);
            } catch (NoSuchAlgorithmException e) {
            } catch (Exception e){
                model.addAttribute("error", "This email is already registered");
                return "error";
            }
            return "redirect:/";
        }else{
            model.addAttribute("error", "Not all fields are filled");
            return "error";
        }
    }

}
