package com.spring.sqa_fullstack.controller.admin;

import com.spring.sqa_fullstack.model.Review;
import com.spring.sqa_fullstack.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @RequestMapping()
    public String allReviews(Model model) {
        List<Review> reviews = reviewService.getAllReviews();
        model.addAttribute("reviews", reviews);
        return "admin/review/reviews";
    }

    @RequestMapping("/{id}")
    public String getReviewById(@PathVariable(name = "id") Long id,
                                Model model) {
        Review review = reviewService.getReviewById(id);
        model.addAttribute("review", review);
        return "admin/review/detailReview";
    }

    @RequestMapping("/delete/{id}")
    public String deleteReview(@PathVariable(name = "id") Long id) {
        reviewService.deleteReviewById(id);
        return "redirect:/admin/reviews";
    }
}
