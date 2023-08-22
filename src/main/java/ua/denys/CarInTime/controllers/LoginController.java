package ua.denys.CarInTime.controllers;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.denys.CarInTime.services.AccountService;
import ua.denys.CarInTime.services.HashingService;
import ua.denys.CarInTime.model.User;
import ua.denys.CarInTime.repositories.CookieRepository;

import java.security.NoSuchAlgorithmException;


@Controller
@RequestMapping(path = "/")
public class LoginController {

}
