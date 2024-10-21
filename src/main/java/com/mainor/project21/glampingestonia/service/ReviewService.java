package com.mainor.project21.glampingestonia.service;

import com.mainor.project21.glampingestonia.model.Review;
import com.mainor.project21.glampingestonia.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public void addReview(String glampingId, int rating) throws ExecutionException, InterruptedException, IOException {
        Review review = new Review();
        review.setGlampingId(glampingId);
        review.setRating(rating);
        reviewRepository.saveReview(review);
    }

    public double getAverageRating(String glampingId) throws ExecutionException, InterruptedException, IOException {
        List<Review> reviews = reviewRepository.findByGlampingId(glampingId);
        if (reviews.isEmpty()) {
            return 0.0;
        }
        int totalRating = reviews.stream().mapToInt(Review::getRating).sum();
        return (double) totalRating / reviews.size();
    }
}
