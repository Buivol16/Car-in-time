package ua.denis.project.CarInTime.controllers;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.denis.project.CarInTime.model.User;
import ua.denis.project.CarInTime.repositories.CookieRepository;
import ua.denis.project.CarInTime.services.AccountService;
import ua.denis.project.CarInTime.services.HashingService;

import java.security.NoSuchAlgorithmException;


@Controller
@RequestMapping(path = "/")
public class LoginController {

    @Autowired
    AccountService accountService;
    @Autowired
    CookieRepository cookieRepository;

    @GetMapping
    public String loginPage(Model model, @CookieValue(name = "_ui", required = false) String cookie){
        if (cookieRepository.getByCookieValue(cookie) != null) return "redirect:/profile";
        var bool = false;
        model.addAttribute("user", new User());
        model.addAttribute("remember", bool);
        return "login";
    }
    @PostMapping
    public String loginUser(@ModelAttribute User user, @RequestParam(name = "rememberMe", defaultValue = "false") boolean remember, Model model, HttpServletResponse response, HashingService hashingService){
        try {
            user = accountService.checkUser(user);
            if (user != null){
                var cookie = new jakarta.servlet.http.Cookie("_ui", hashingService.getHashedString(user.getEmail()+user.getPassword()));
                var cookieId = new Cookie("_bym", String.valueOf(user.getId()));
                if(remember){
                    cookie.setMaxAge(2592000);
                    cookieId.setMaxAge(2592000);
                    response.addCookie(cookie);
                    response.addCookie(cookieId);
                    cookieRepository.save(new ua.denis.project.CarInTime.model.Cookie(cookie.getValue(), user));
                }else{
                    cookie.setMaxAge(-1);
                    cookieId.setMaxAge(-1);
                    response.addCookie(cookie);
                    response.addCookie(cookieId);
                    cookieRepository.save(new ua.denis.project.CarInTime.model.Cookie(cookie.getValue(), user));
                }
                return "redirect:/profile";
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            return "redirect:/profile";
        }
        model.addAttribute("error", "This user is unavailable");
        return "error";

    }
}
