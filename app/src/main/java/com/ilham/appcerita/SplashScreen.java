package com.ilham.appcerita;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SessionManager session = new SessionManager(this);

        new Handler().postDelayed(() -> {
            Intent intent;
            if (session.isLoggedIn()) {
                // User udah login, langsung ke Dashboard
                intent = new Intent(SplashScreen.this, Dashboard.class);
            } else {
                // Belum login, ke Login
                intent = new Intent(SplashScreen.this, Login.class);
            }
            startActivity(intent);
            finish();
        }, 2000);
    }

}
