package com.ilham.appcerita;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;


public class CeritaAdapter extends RecyclerView.Adapter<CeritaAdapter.ViewHolder> {

    private Context context;
    private List<Cerita> list;

    public CeritaAdapter(Context context, List<Cerita> list) {
        this.context = context;
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cover;
        TextView nama, genre, views;

        public ViewHolder(View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.coverCerita);
            nama = itemView.findViewById(R.id.namaCerita);
            genre = itemView.findViewById(R.id.genreCerita);
            views = itemView.findViewById(R.id.viewsCerita);
        }
    }

    @Override
    public CeritaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_cerita, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CeritaAdapter.ViewHolder holder, int position) {
        Cerita data = list.get(position);
        holder.nama.setText(data.getNama());
        holder.genre.setText(data.getGenre());
        holder.views.setText(String.format(Locale.getDefault(), "%,d views", data.getViews()));
        int imgRes = context.getResources().getIdentifier(data.getImage(), "drawable", context.getPackageName());

        if (imgRes != 0) {
            holder.cover.setImageResource(imgRes);
        } else {
            holder.cover.setImageResource(R.drawable.sample_cover); // fallback
        }
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailCerita.class);
            intent.putExtra("nama", data.getNama());
            intent.putExtra("genre", data.getGenre());
            intent.putExtra("image", data.getImage());
            intent.putExtra("views", data.getViews());
            intent.putExtra("deskripsi", data.getDeskripsi());
            intent.putExtra("penulis", data.getPenulis());
            intent.putExtra("tahun", data.getTahun());
            intent.putExtra("pdf", data.getPdf());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
