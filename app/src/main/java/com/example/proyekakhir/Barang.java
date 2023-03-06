package com.example.proyekakhir;

public class Barang {
    private String barang_id;
    private String barang_nama;
    private String barang_harga;
    public Barang(){
    }
    public Barang(String barId, String barNama, String barHarga){
        this.barang_id = barId;
        this.barang_nama = barNama;
        this.barang_harga = barHarga;
    }
    public String getBarang_id(){
        return barang_id;
    }
    public String getBarang_nama(){
        return barang_nama;
    }
    public String getBarang_harga(){
        return barang_harga;
    }
}
