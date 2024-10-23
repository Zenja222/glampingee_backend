package com.mainor.project21.glampingestonia.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
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
        Firestore dbFirestore = FirebaseDb.getFirestoreDb();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Glamping> glampings = new ArrayList<>();

        for (DocumentSnapshot document : documents) {
            Glamping glamping = document.toObject(Glamping.class);
            if (glamping != null) {
                glamping.setId(document.getId());
                glampings.add(glamping);
            }
        }
        return glampings;
    }

    public Glamping findById(String id) throws ExecutionException, InterruptedException, IOException {
        Firestore dbFirestore = FirebaseDb.getFirestoreDb();
        ApiFuture<DocumentSnapshot> future = dbFirestore.collection(COLLECTION_NAME).document(id).get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            Glamping glamping = document.toObject(Glamping.class);
            if (glamping != null) {
                glamping.setId(document.getId());
            }
            return glamping;
        } else {
            return null;
        }
    }

    public List<Glamping> filterByField(String sortField, String sortDirection) throws ExecutionException, InterruptedException, IOException {
        Firestore dbFirestore = FirebaseDb.getFirestoreDb();
        Query query = dbFirestore.collection(COLLECTION_NAME);

        if ("asc".equalsIgnoreCase(sortDirection)) {
            query = query.orderBy(sortField);
        } else if ("desc".equalsIgnoreCase(sortDirection)) {
            query = query.orderBy(sortField, Query.Direction.DESCENDING);
        }

        ApiFuture<QuerySnapshot> future = query.get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Glamping> glampings = new ArrayList<>();


        for (DocumentSnapshot document : documents) {
            Glamping glamping = document.toObject(Glamping.class);
            if (glamping != null) {
                glamping.setId(document.getId());
                glampings.add(glamping);
            }
        }
        return glampings;
    }

    public Glamping save(Glamping glamping) throws ExecutionException, InterruptedException, IOException {
        Firestore dbFirestore = FirebaseDb.getFirestoreDb();
        DocumentReference documentReference;

        if (glamping.getId() == null || glamping.getId().isEmpty()) {
            documentReference = dbFirestore.collection(COLLECTION_NAME).document();
            glamping.setId(documentReference.getId());
        } else {
            documentReference = dbFirestore.collection(COLLECTION_NAME).document(glamping.getId());
        }
        ApiFuture<WriteResult> future = documentReference.set(glamping);
        future.get();
        return glamping;
    }

    public void delete(String id) throws IOException, ExecutionException, InterruptedException {
        Firestore dbFirestore = FirebaseDb.getFirestoreDb();
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<WriteResult> future = documentReference.delete();
        future.get();
    }

}
