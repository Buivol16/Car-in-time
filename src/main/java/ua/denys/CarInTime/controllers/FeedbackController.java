package ua.denys.CarInTime.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.denys.CarInTime.model.Feedback;
import ua.denys.CarInTime.repositories.FeedbackRepository;

import java.util.List;

@Controller
@RequestMapping(path = "/feedback")
public class FeedbackController {

    @Autowired
    FeedbackRepository feedbackRepository;

    @GetMapping
    public String getFeedback(@RequestParam(name = "userId") Long id, @CookieValue(name = "_bym") Long anotherId, Model model){
        if (id == null) return "redirect:/feedback?id="+anotherId;
        List<Feedback> feedbacks = feedbackRepository.getByConsumerId(id);
        model.addAttribute("feedbacks", feedbacks);
        model.addAttribute("title", feedbacks.get(0).getAuthor().getUsername()+"'s feedbacks");
        return "feedback";
    }

    @PostMapping
    public String leaveFeedback(@ModelAttribute(name = "feedback") Feedback feedback){
        return null;
    }
}
