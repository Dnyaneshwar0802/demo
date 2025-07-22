package com.tcs.config;


import com.google.api.client.util.Value;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Value("${firebase.config.enable:false}")
    private boolean firebaseEnabled;

    @Value("${firebase.config.path:}")
    private String firebaseConfigPath;

    @PostConstruct
    public void init() {
        if (firebaseEnabled) {
            try (InputStream serviceAccount = new ClassPathResource(firebaseConfigPath).getInputStream()) {
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();

                if (FirebaseApp.getApps().isEmpty()) {
                    FirebaseApp.initializeApp(options);
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to initialize Firebase", e);
            }
        }
    }
}

