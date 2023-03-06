package com.example.proyekakhir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateDeleteKatalog extends AppCompatActivity {
    EditText editText_namabarang, editText_hargabarang;
    DatabaseReference databaseBarang;
    String barangId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_katalog);
        //mengambil id dari intent sebelum/ katalog
        barangId = getIntent().getStringExtra("barangId");
        editText_namabarang = findViewById(R.id.editText_updateNamaBarang);
        editText_hargabarang = findViewById(R.id.editText_updateHargaBarang);
        //mengambil data dari firebase yg id nya barangId
        databaseBarang = FirebaseDatabase.getInstance().getReference("barang").child(barangId);
    }
    @Override
    protected void onStart() {
        super.onStart();
        databaseBarang.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //mengambil dara dari firebase dan dimasukkan ke obyek barang
                Barang barang = snapshot.getValue(Barang.class);
                //pengecekan apakah data dari firebase tidak kosong (!=null)
                if (barang !=null){
                    //menampilkan nama dan harga barang pada edittext
                    String namabarang = barang.getBarang_nama();
                    String hargabarang = barang.getBarang_harga();
                    editText_namabarang.setText(namabarang);
                    editText_hargabarang.setText(hargabarang);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void updatekatalog(View view) {
        //mengambil input dari edittext
        String namabarang = editText_namabarang.getText().toString();
        String hargabarang = editText_hargabarang.getText().toString();
        //pengecekan nilai kosong
        if (!TextUtils.isEmpty(namabarang) && !TextUtils.isEmpty(hargabarang)){
            //membuat obyek barnag
            Barang barang = new Barang(barangId, namabarang, hargabarang);
            //mengubah data di firebase
            databaseBarang.setValue(barang).addOnSuccessListener(this, new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    //menampilkan toast jika sukses
                    Toast.makeText(UpdateDeleteKatalog.this, "Barang diupdate", Toast.LENGTH_LONG).show();
                    //keluar dari activity update delete dan kembali ke activity katalog
                    finish();
                }
            });
        }else{
            Toast.makeText(UpdateDeleteKatalog.this, "Semua harus diisi", Toast.LENGTH_LONG).show();
        }
    }

    public void deletekatalog(View view) {
        //menghapus data dari firebase
        databaseBarang.removeValue().addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(UpdateDeleteKatalog.this, "Barang dihapus", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}