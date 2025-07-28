package com.ilham.appcerita;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Register extends AppCompatActivity {

    private EditText edNama, edUsername, edEmail, edPassword;
    private Button btnDaftar;
    private TextView linkLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edNama = findViewById(R.id.edNama);
        edUsername = findViewById(R.id.edUsername);
        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        btnDaftar = findViewById(R.id.btnDaftar);
        linkLogin = findViewById(R.id.linkLogin);

        btnDaftar.setOnClickListener(v -> {
            // Buat validasi / simpan data disini slur
            Toast.makeText(this, "Akun berhasil dibuat 🎉", Toast.LENGTH_SHORT).show();
            // Contoh auto balik ke login
            startActivity(new Intent(this, Login.class));
            finish();
        });

        linkLogin.setOnClickListener(v -> {
            // Balik ke Login
            startActivity(new Intent(this, Login.class));
            finish();
        });
    }
}
