    package com.ilham.appcerita;

    import android.content.Intent;
    import android.os.Bundle;
    import android.util.Log;
    import android.widget.Button;
    import android.widget.ImageView;

    import androidx.activity.EdgeToEdge;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.core.graphics.Insets;
    import androidx.core.view.ViewCompat;
    import androidx.core.view.WindowInsetsCompat;
    import androidx.recyclerview.widget.GridLayoutManager;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import org.json.JSONArray;
    import org.json.JSONObject;

    import java.io.InputStream;
    import java.nio.charset.StandardCharsets;
    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.Comparator;
    import java.util.List;

    public class Dashboard extends AppCompatActivity {

        private RecyclerView recyclerKategori, recyclerCerita;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_dashboard);
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });

            recyclerKategori = findViewById(R.id.recyclerKategori);
            recyclerCerita = findViewById(R.id.recyclerCeritaPopuler);

            setupKategori();
            setupCeritaPopuler();

            ImageView btnLogout = findViewById(R.id.iconUser);
            btnLogout.setOnClickListener(v -> {
                SessionManager session = new SessionManager(Dashboard.this);
                session.logout();

                Intent intent = new Intent(Dashboard.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            });

        }

        private void setupKategori() {
            List<Kategori> listKategori = new ArrayList<>();
            listKategori.add(new Kategori("Romance", R.drawable.love));
            listKategori.add(new Kategori("Fiksi", R.drawable.war));
            listKategori.add(new Kategori("Horor", R.drawable.fiction));
            listKategori.add(new Kategori("Lainnya", R.drawable.love));

            KategoriAdapter adapter = new KategoriAdapter(this, listKategori);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            recyclerKategori.setLayoutManager(layoutManager);
            recyclerKategori.setAdapter(adapter);
        }

        private void setupCeritaPopuler() {
            List<Cerita> listCerita = loadCeritaFromJson();

            // Sort dari views terbanyak
            Collections.sort(listCerita, (a, b) -> Integer.compare(b.getViews(), a.getViews()));

            CeritaAdapter adapter = new CeritaAdapter(this, listCerita);
            recyclerCerita.setLayoutManager(new GridLayoutManager(this, 2));
            recyclerCerita.setAdapter(adapter);
        }

        private List<Cerita> loadCeritaFromJson() {
            List<Cerita> ceritaList = new ArrayList<>();

            try {
                InputStream is = getAssets().open("cerita.json"); // pastikan cerita.json ada di folder assets
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();

                String json = new String(buffer, StandardCharsets.UTF_8);
                JSONArray jsonArray = new JSONArray(json);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);

                    String nama = obj.getString("nama");
                    String genre = obj.getString("genre");
                    String image = obj.getString("image");
                    int views = obj.getInt("views");
                    String deskripsi = obj.getString("deskripsi");
                    String penulis = obj.getString("penulis");
                    int tahun = obj.getInt("tahun");

                    ceritaList.add(new Cerita(nama, genre, image, views, deskripsi, penulis, tahun));
                }

            } catch (Exception e) {
                Log.e("Dashboard", "Error loading cerita.json: " + e.getMessage());
            }

            return ceritaList;
        }
    }
