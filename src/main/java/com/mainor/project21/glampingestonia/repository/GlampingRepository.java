package com.mainor.project21.glampingestonia.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.mainor.project21.glampingestonia.model.Glamping;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class GlampingRepository {

    private static final String COLLECTION_NAME = "glampings";

    public List<Glamping> findAll() throws ExecutionException, InterruptedException, IOException {
        Firestore dbFirestore = FirebaseDb.getFirestoreDb(); // Use your custom FirebaseDb class
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Glamping> glampings = new ArrayList<>();

        if (documents != null) {
            for (DocumentSnapshot document : documents) {
                Glamping glamping = document.toObject(Glamping.class);
                if (glamping != null) {
                    glamping.setId(document.getId());
                    glampings.add(glamping);
                }
            }
        }
        return glampings;
    }
}
