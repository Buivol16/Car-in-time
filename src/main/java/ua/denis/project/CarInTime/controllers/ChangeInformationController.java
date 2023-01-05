package ua.denis.project.CarInTime.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.denis.project.CarInTime.model.Profile;
import ua.denis.project.CarInTime.repositories.ProfileRepository;

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
    public String changeInformation(@ModelAttribute(value = "profile") ua.denis.project.CarInTime.model.Profile profile, @CookieValue(value = "_bym") String id){
        ua.denis.project.CarInTime.model.Profile profile1 = profileRepository.getByUserId(Long.parseLong(id));
        if (!profile.getFullName().isBlank()) profile1.setFullName(profile.getFullName());
        if (!profile.getImgLink().isBlank()) profile1.setImgLink(profile.getImgLink());
        if (!profile.getUsername().isBlank()) profile1.setUsername(profile.getUsername());
        profileRepository.save(profile1);
        return "redirect:/profile";
    }

}
