package ua.denys.CarInTime.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.denys.CarInTime.model.Profile;
import ua.denys.CarInTime.model.User;
import ua.denys.CarInTime.services.HashingService;
import ua.denys.CarInTime.services.RegisterService;
import ua.denys.CarInTime.repositories.ProfileRepository;
import ua.denys.CarInTime.repositories.UserRepository;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping(path = "/register")
public class RegisterController {

    @Autowired
    HashingService hashingService;
    @Autowired
    RegisterService registerService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProfileRepository profileRepository;

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
                profileRepository.save(new Profile(user, null, null, null, 0, LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
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
