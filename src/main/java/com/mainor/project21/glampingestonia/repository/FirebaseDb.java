package com.mainor.project21.glampingestonia.repository;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseDb {
    private static Firestore db;

    public static synchronized Firestore getFirestoreDb() throws IOException {
        if (db != null)
            return db;

        FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
                .setProjectId("glamping-estonia")
                .setCredentials(GoogleCredentials.fromStream(new FileInputStream("src/main/resources/glamping-estonia-ba00f415d06a.json")))
                .build();

        db = firestoreOptions.getService();
        return db;
    }
}
