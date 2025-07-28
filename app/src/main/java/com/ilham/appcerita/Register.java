package com.ilham.appcerita;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Register extends AppCompatActivity {

    EditText edNama, edUsername, edEmail, edPassword;
    Button btnDaftar;
    TextView linkLogin;

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

        linkLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, Login.class));
            finish();
        });

        btnDaftar.setOnClickListener(v -> {
            String nama = edNama.getText().toString().trim();
            String username = edUsername.getText().toString().trim();
            String email = edEmail.getText().toString().trim();
            String password = edPassword.getText().toString().trim();

            if (nama.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Lengkapi semua field dulu ya 😵‍💫", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                File file = new File(getFilesDir(), "users.json");
                JSONArray userArray;

                if (file.exists()) {
                    // File udah ada, load data lama
                    InputStream is = openFileInput("users.json");
                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();
                    String json = new String(buffer, "UTF-8");
                    userArray = new JSONArray(json);
                } else {
                    // File belum ada, buat baru
                    userArray = new JSONArray();
                }

                // Buat object user baru
                JSONObject userObj = new JSONObject();
                userObj.put("nama", nama);
                userObj.put("username", username);
                userObj.put("email", email);
                userObj.put("password", password); // HARUS dienkripsi di real app
                userObj.put("registered_at", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));

                // Tambahin ke array & simpan lagi
                userArray.put(userObj);

                FileOutputStream fos = openFileOutput("users.json", Context.MODE_PRIVATE);
                fos.write(userArray.toString().getBytes());
                fos.close();

                Toast.makeText(this, "Akun berhasil dibuat 🎉", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, Login.class));
                finish();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Gagal menyimpan data 🥲", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static class Kategori {
        String nama;
        int icon;

        public Kategori(String nama, int icon) {
            this.nama = nama;
            this.icon = icon;
        }

        public String getNama() {
            return nama;
        }

        public int getIcon() {
            return icon;
        }
    }
}
