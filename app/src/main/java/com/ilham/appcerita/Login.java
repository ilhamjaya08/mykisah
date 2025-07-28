package com.ilham.appcerita;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private EditText username, password;
    private Button btnLogin;
    private TextView linkRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inisialisasi view
        username = findViewById(R.id.editekuser);
        password = findViewById(R.id.editekpassword);
        btnLogin = findViewById(R.id.tombollogin);
        linkRegister = findViewById(R.id.linkRegister);

        // Aksi tombol login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyNama = username.getText().toString().trim();
                String keyPass = password.getText().toString().trim();

                if (keyNama.equals("admin") && keyPass.equals("admin")) {
                    // Login berhasil
                    Toast.makeText(Login.this, "Login Berhasil! 📖✨", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, MainActivity.class));
                    finish();
                } else {
                    // Login gagal
                    new AlertDialog.Builder(Login.this)
                            .setMessage("Username atau password salah, coeg 😵‍💫")
                            .setNegativeButton("Ulangi", null)
                            .create()
                            .show();

                    // Reset input
                    username.setText("");
                    password.setText("");
                }
            }
        });

        // Aksi klik "Daftar di sini"
        linkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }
}
