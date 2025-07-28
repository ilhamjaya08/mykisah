package com.ilham.appcerita;

public class Cerita {
    private String nama, genre, image, deskripsi, penulis;
    private int views, tahun;

    public Cerita(String nama, String genre, String image, int views, String deskripsi, String penulis, int tahun) {
        this.nama = nama;
        this.genre = genre;
        this.image = image;
        this.views = views;
        this.deskripsi = deskripsi;
        this.penulis = penulis;
        this.tahun = tahun;
    }

    public String getNama() { return nama; }
    public String getGenre() { return genre; }
    public String getImage() { return image; }
    public int getViews() { return views; }
    public String getDeskripsi() { return deskripsi; }
    public String getPenulis() { return penulis; }
    public int getTahun() { return tahun; }
}
