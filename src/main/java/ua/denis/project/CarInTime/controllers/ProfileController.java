package ua.denis.project.CarInTime.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.denis.project.CarInTime.model.*;
import ua.denis.project.CarInTime.repositories.*;
import ua.denis.project.CarInTime.services.AccountService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/profile")
public class ProfileController {

    @Autowired
    CookieRepository cookieRepository;
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    OfferRepository offerRepository;
    @Autowired
    FeedbackRepository feedbackRepository;

    @GetMapping
    public String profilePage(@CookieValue(value = "_ui") String cookieValue, Model model, AccountService accountService, @CookieValue(value = "_bym") String cookieUserId, @RequestParam(name = "id", required = false) Long userId){
        if (userId == null) return "redirect:/profile?id="+cookieUserId;
        try{
            Cookie cookie1 = cookieRepository.getByCookieValue(cookieValue);
            ua.denis.project.CarInTime.model.Profile profile = profileRepository.getByUserId(userId);
            List<Offer> offers = offerRepository.getByAuthorId(userId);
            String count = accountService.countDays(LocalDate.parse(profile.getDateWhenConnected(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));

            Map<String, Object> attributes = new HashMap<>();

            attributes.put("username", profile.getUsername());
            attributes.put("imageLink", profile.getImgLink());
            attributes.put("fullName", profile.getFullName());
            attributes.put("allOffersCount", offers.size());
            attributes.put("ratingCount", profile.getRating());
            attributes.put("feedbacksCount", feedbackRepository.getByConsumerId(userId).size());
            attributes.put("liveCount", count);

            model.addAllAttributes(attributes);
            model.addAttribute("offersList", offerRepository.getByAuthorId(userId));
            model.addAttribute("title", profile.getUsername() + " profile");
            if (cookie1 != null) return "profile";
        }catch (Exception e){
        }
        model.addAttribute("error", "This user is unavailable");
        return "error";
    }
}
