package com.example.proyekakhir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Katalog extends AppCompatActivity {
    //deklarasi variabel
    Button button_tambah;
    ListView listviewbarang;
    List<Barang> barangArray;
    private ArrayList<Barang> listBarang;
    private DatabaseReference databaseBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katalog);
        listviewbarang = findViewById(R.id.listview_barang);
        listBarang = new ArrayList<>();
        databaseBarang = FirebaseDatabase.getInstance().getReference("barang");
        listviewbarang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //mengambil barang dari posisi listview yang diambil
                Barang barang = listBarang.get(position);
                //mengambil id
                String barangId = barang.getBarang_id();
                //mengirimkan id melalui intent ke UpdateDeleteKatalog
                Intent updelete = new Intent(Katalog.this, UpdateDeleteKatalog.class);
                updelete.putExtra("barangId", barangId);
                startActivity(updelete);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        databaseBarang.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               listBarang.clear();
               //perulangan untuk menampilkan semua data di firebase
                for (DataSnapshot postSnapshot : snapshot.getChildren()){
                    //setiap data dimasukkan ke dalam obyek class barang
                    Barang barang = postSnapshot.getValue(Barang.class);
                    //dan simpan dalam list
                    listBarang.add(barang);
                }
                //menampilkan list yg berisi data dari firebase ke listview
                listview_katalog katalogList_adapter = new listview_katalog(Katalog.this, listBarang);
                listviewbarang.setAdapter(katalogList_adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void HandleTambah(View view) {
        Intent tambah = new Intent(this, Tambahkatalog.class);
        startActivity(tambah);
    }

}