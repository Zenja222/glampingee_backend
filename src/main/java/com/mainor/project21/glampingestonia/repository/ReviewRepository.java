package com.mainor.project21.glampingestonia.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.mainor.project21.glampingestonia.model.Review;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class ReviewRepository {

    private static final String COLLECTION_NAME = "reviews";

    public void saveReview(Review review) throws ExecutionException, InterruptedException, IOException {
        Firestore dbFirestore = FirebaseDb.getFirestoreDb();
        ApiFuture<DocumentReference> future = dbFirestore.collection(COLLECTION_NAME).add(review);
        future.get();
    }

    public List<Review> findByGlampingId(String glampingId) throws ExecutionException, InterruptedException, IOException {
        Firestore dbFirestore = FirebaseDb.getFirestoreDb();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COLLECTION_NAME)
                .whereEqualTo("glampingId", glampingId)
                .get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Review> reviews = new ArrayList<>();

        for (DocumentSnapshot document : documents) {
            Review review = document.toObject(Review.class);
            reviews.add(review);
        }
        return reviews;
    }
}
