package com.example.proyekakhir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //Deklarasi Variabel
    Button button_hitung;
    Button button_katalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //pengenalan komponen
        button_hitung = findViewById(R.id.hitung);
        button_katalog = findViewById(R.id.katalog);
    }

    public void HandleHitung(View view) {
        Intent hitung = new Intent(this, Hitung.class);
        startActivity(hitung);
    }

    public void HandleKatalog(View view) {
        Intent katalog = new Intent(this, Katalog.class);
        startActivity(katalog);
    }
}