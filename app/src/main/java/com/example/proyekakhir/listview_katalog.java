package com.example.proyekakhir;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class listview_katalog extends ArrayAdapter {
    private Activity context;
    List<Barang> list_barang;

    public listview_katalog(@NonNull Activity context, List<Barang> barangArray) {
        super(context, R.layout.layout_listviewkatalog, barangArray);
        this.context = context;
        this.list_barang = barangArray;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_listviewkatalog, null, true);
        TextView textViewNamaBarang = listViewItem.findViewById(R.id.textViewNamaBarang);
        TextView textViewHargaBarang = listViewItem.findViewById(R.id.textViewHargaBarang);
        Barang barang = list_barang.get(position);
        textViewNamaBarang.setText(barang.getBarang_nama());
        textViewHargaBarang.setText(barang.getBarang_harga());
        return listViewItem;
    }
}
