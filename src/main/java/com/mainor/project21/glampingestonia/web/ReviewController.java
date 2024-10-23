package com.mainor.project21.glampingestonia.web;

import com.mainor.project21.glampingestonia.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/{glampingId}")
    public void addReview(@PathVariable String glampingId, @RequestParam int rating) throws ExecutionException, InterruptedException, IOException {
        reviewService.addReview(glampingId, rating);
    }

    @GetMapping("/average/{glampingId}")
    public double getAverageRating(@PathVariable String glampingId) throws ExecutionException, InterruptedException, IOException {
        return reviewService.getAverageRating(glampingId);
    }
}
