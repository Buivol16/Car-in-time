package ua.denys.CarInTime.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.denys.CarInTime.model.Profile;
import ua.denys.CarInTime.repositories.ProfileRepository;

@Controller
@RequestMapping(path = "/changeinformation")
public class ChangeInformationController {

    @Autowired
    ProfileRepository profileRepository;

    @GetMapping
    public String changeInformation(Model model){
        model.addAttribute("profile", new Profile());
        return "changeInformation";
    }
    @PostMapping
    public String changeInformation(@ModelAttribute(value = "profile") Profile profile, @CookieValue(value = "_bym") String id){
        Profile profile1 = profileRepository.getByUserId(Long.parseLong(id));
        if (!profile.getFullName().isBlank()) profile1.setFullName(profile.getFullName());
        if (!profile.getImgLink().isBlank()) profile1.setImgLink(profile.getImgLink());
        if (!profile.getUsername().isBlank()) profile1.setUsername(profile.getUsername());
        profileRepository.save(profile1);
        return "redirect:/profile";
    }

}
