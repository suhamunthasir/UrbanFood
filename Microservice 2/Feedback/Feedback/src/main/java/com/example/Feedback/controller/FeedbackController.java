package com.example.Feedback.controller;

import com.example.Feedback.model.Feedback;
import com.example.Feedback.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List; // Import the List class

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    // POST method to submit feedback
    @PostMapping("/submit")
    public Feedback submitFeedback(@RequestBody Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    // GET method to retrieve all feedback
    @GetMapping("/all")
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll(); // Fetch all feedback from the database
    }
}
