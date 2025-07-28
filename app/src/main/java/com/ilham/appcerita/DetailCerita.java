package com.ilham.appcerita;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailCerita extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cerita);

        TextView nama = findViewById(R.id.namaCerita);
        TextView genre = findViewById(R.id.genreCerita);
        TextView deskripsi = findViewById(R.id.deskripsiCerita);
        TextView penulis = findViewById(R.id.penulisCerita);
        TextView tahun = findViewById(R.id.tahunCerita);
        TextView views = findViewById(R.id.viewsCerita);
        ImageView image = findViewById(R.id.imageCerita);

        Intent intent = getIntent();
        nama.setText(intent.getStringExtra("nama"));
        genre.setText("Genre: " + intent.getStringExtra("genre"));
        deskripsi.setText(intent.getStringExtra("deskripsi"));
        penulis.setText("Penulis: " + intent.getStringExtra("penulis"));
        tahun.setText("Tahun: " + intent.getIntExtra("tahun", 0));
        views.setText(intent.getIntExtra("views", 0) + " views");

        int imgRes = getResources().getIdentifier(intent.getStringExtra("image"), "drawable", getPackageName());
        image.setImageResource(imgRes);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            finish(); // balik ke activity sebelumnya
        });

    }
}
