package com.ilham.appcerita;

import android.app.AlertDialog;
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
import java.io.InputStream;

public class Login extends AppCompatActivity {

    private EditText username, password;
    private Button btnLogin;
    private TextView linkRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.editekuser);
        password = findViewById(R.id.editekpassword);
        btnLogin = findViewById(R.id.tombollogin);
        linkRegister = findViewById(R.id.linkRegister);

        btnLogin.setOnClickListener(view -> {
            String inputUsername = username.getText().toString().trim();
            String inputPassword = password.getText().toString().trim();

            File file = new File(getFilesDir(), "users.json");

            if (!file.exists()) {
                // File users.json belum ada → redirect ke register
                Toast.makeText(this, "Data user belum ada, daftar dulu ya 🫠", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Login.this, Register.class));
                return;
            }

            try {
                // Baca isi users.json
                InputStream is = openFileInput("users.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                String jsonStr = new String(buffer, "UTF-8");

                JSONArray userArray = new JSONArray(jsonStr);
                boolean found = false;

                for (int i = 0; i < userArray.length(); i++) {
                    JSONObject user = userArray.getJSONObject(i);

                    String storedUsername = user.getString("username");
                    String storedPassword = user.getString("password");

                    if (inputUsername.equals(storedUsername) && inputPassword.equals(storedPassword)) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    // SET LOGIN TRUE
                    SessionManager session = new SessionManager(this);
                    session.setLogin(true);

                    Toast.makeText(this, "Login Berhasil! 🚀", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, Dashboard.class));
                    finish();
                } else {
                    new AlertDialog.Builder(this)
                            .setMessage("Username atau password salah 😵‍💫")
                            .setNegativeButton("Ulangi", null)
                            .create()
                            .show();

                    username.setText("");
                    password.setText("");
                }

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Gagal membaca data user 😬", Toast.LENGTH_SHORT).show();
            }
        });

        // Klik teks "Daftar di sini"
        linkRegister.setOnClickListener(view -> {
            startActivity(new Intent(Login.this, Register.class));
        });
    }
}
