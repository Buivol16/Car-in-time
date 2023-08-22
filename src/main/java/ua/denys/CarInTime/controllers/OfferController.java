package ua.denys.CarInTime.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.denys.CarInTime.repositories.OfferRepository;

@Controller
@RequestMapping(path = "/offer")
public class OfferController {

    @Autowired
    OfferRepository offerRepository;

    @GetMapping
    public String offerPage(@RequestParam(name = "id") Long id, Model model){
        var offer = offerRepository.findById(id).get();
        model.addAttribute("offer", offer);
        return "offer";
    }
}
