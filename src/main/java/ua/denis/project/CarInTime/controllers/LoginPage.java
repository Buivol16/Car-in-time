package ua.denis.project.CarInTime.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.denis.project.CarInTime.model.User;
import ua.denis.project.CarInTime.repository.UserRepository;

@Controller
public class LoginPage {

    @Autowired
    UserRepository userRepository;


    @GetMapping("/")
    public String showPage(){
        return "login";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, BindingResult bindingResult){
        User user = new User();
        model.addAttribute("user", user);
        if (userRepository.findByEmail(user.getEmail()).isEmpty()) return "error";
        else if (user.getEmail() != null && user.getPassword() != null) userRepository.save(user);
        return "success";
    }

}
