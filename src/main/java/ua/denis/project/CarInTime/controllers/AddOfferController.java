package ua.denis.project.CarInTime.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.denis.project.CarInTime.model.Offer;
import ua.denis.project.CarInTime.model.Profile;
import ua.denis.project.CarInTime.repositories.OfferRepository;
import ua.denis.project.CarInTime.repositories.ProfileRepository;

@Controller
@RequestMapping(path = "/addoffer")
public class AddOfferController {

    @Autowired
    OfferRepository offerRepository;
    @Autowired
    ProfileRepository profileRepository;

    @GetMapping
    public String loadAddingOfferPage(Model model, @CookieValue(name = "_bym") String id){
        model.addAttribute("offer", new Offer());
        return "addoffer";
    }
    @PostMapping
    public String addOffer(@ModelAttribute(name = "offer") Offer offer, @CookieValue(name = "_bym") String id){
        Profile profile = profileRepository.getByUserId(Long.parseLong(id));
        offer.setAuthor(profile);
        offerRepository.save(offer);
        return "redirect:/profile";
    }


}
