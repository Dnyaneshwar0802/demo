package com.tcs.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.tcs.model.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class FirebaseService {

    private final Firestore db;

    public FirebaseService() {
        this.db = FirestoreClient.getFirestore();
    }

    public ApiFuture<WriteResult> saveUser(User user) {
        return db.collection("users").document(user.getId()).set(user);
    }

    // Add similar methods for Product, Order
}
