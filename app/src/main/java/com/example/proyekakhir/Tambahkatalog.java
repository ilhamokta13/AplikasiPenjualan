package com.example.proyekakhir;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Tambahkatalog extends AppCompatActivity {
//Deklarasi Variabel
    private EditText editText_namabarang, editText_hargabarang;
    private DatabaseReference databaseBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahkatalog);
        editText_namabarang = findViewById(R.id.editTextNama);
        editText_hargabarang = findViewById(R.id.editTextHarga);
        databaseBarang = FirebaseDatabase.getInstance().getReference("barang");


    }

    public void addBarang(View view) {
        //mengambil data dari edittext
        String namabarang = editText_namabarang.getText().toString();
        String hargabarang = editText_hargabarang.getText().toString();
        if(!TextUtils.isEmpty(namabarang) && !TextUtils.isEmpty(hargabarang)){
        //membuat id unik secara otomatis
            String id = databaseBarang.push().getKey();
            Barang barang = new Barang(id, namabarang, hargabarang);
            databaseBarang.child(id).setValue(barang)
                    .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            //jika proses penambahan data sukses, edittext dikosongkan
                            editText_namabarang.setText("");
                            editText_hargabarang.setText("");
                            Toast.makeText(Tambahkatalog.this, "Barang berhasil ditambahkan", Toast.LENGTH_LONG).show();
                        }
                    });
        }else{
            Toast.makeText(this,"Semua Box Haru Diisi", Toast.LENGTH_LONG).show();
        }
        finish();
    }
}