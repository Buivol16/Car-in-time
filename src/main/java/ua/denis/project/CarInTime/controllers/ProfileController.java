package ua.denis.project.CarInTime.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import ua.denis.project.CarInTime.model.Cookie;
import ua.denis.project.CarInTime.repositories.CookieRepository;

@Controller
public class ProfileController {

    @Autowired
    CookieRepository cookieRepository;

    @GetMapping("/profile")
    public String profilePage(@CookieValue(value = "_ui") String cookie){
        try{
            Cookie cookie1 = cookieRepository.getByCookieValue(cookie);
            if (cookie1 != null) return "profile";
        }catch (Exception e){
        }
        return "error";
    }
}
