package com.example.proyekakhir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Hitung extends AppCompatActivity {
    //deklarasi variabel
    EditText editnamabarang, editjumlahbarang, edithargabarang, edituangbayar;
    Button btnproses;
    TextView txttotal;
    TextView txtkembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hitung);
        //pengenalan komponen
        editnamabarang = findViewById(R.id.nama_barang);
        editjumlahbarang = findViewById(R.id.jml_barang);
        edithargabarang = findViewById(R.id.harga_barang);
        edituangbayar = findViewById(R.id.uang_bayar);
        txttotal = findViewById(R.id.textView14);
        txtkembali = findViewById(R.id.textView15);
        btnproses = findViewById(R.id.button_proses);

        btnproses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String namabarang = editnamabarang.getText().toString().trim();
                String jumlahbarang = editjumlahbarang.getText().toString().trim();
                String hargabarang = edithargabarang.getText().toString().trim();
                String uangbayar = edituangbayar.getText().toString().trim();

                double jb = Double.parseDouble(jumlahbarang);
                double hrg = Double.parseDouble(hargabarang);
                double ubyr = Double.parseDouble(uangbayar);
                double total = (jb*hrg);
                double uangkembalian = (ubyr-total);

                txttotal.setText("Total Belanja : Rp." + total);
                txtkembali.setText("Uang Kembalian : Rp." + uangkembalian);
            }
        });

    }

    public void listkatalog(View view) {
        Intent listkatalog = new Intent(this, Katalog.class);
        startActivity(listkatalog);
    }
}