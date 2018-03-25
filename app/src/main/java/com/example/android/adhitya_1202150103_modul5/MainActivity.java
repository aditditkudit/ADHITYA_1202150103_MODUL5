package com.example.android.adhitya_1202150103_modul5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//Deklarasi variabel yang akan digunakan
    Database db;
    RecyclerView rcv;
    Adapter adapter;
    ArrayList<Model> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Inisiasi Recycler View
        rcv = findViewById(R.id.listData);
        //Inisiasi Arralist baru
        listData = new ArrayList<>();
        //Membuat database baru
        db = new Database(this);
        //Memanggil method readData dari class Database
        db.readData(listData);

        //Inisiasi Shared Preference
        SharedPreferences sharedP = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int warna = sharedP.getInt("Colourground", R.color.white);

        //Membuat Adapter Baru
        adapter = new Adapter(this, listData, warna);
        //Menghindari perubahaan ukuran yang tidak perlu ketika menambahkan / hapus item pada recycler view
        rcv.setHasFixedSize(true);
        //Menampilkan layoutnya linier
        rcv.setLayoutManager(new LinearLayoutManager(this));
        //Inisiasi adapter untuk recycler view
        rcv.setAdapter(adapter);
        swipeHapus();
    }
    public void swipeHapus(){
        //Membuat touch helper baru untuk recyclerView
        ItemTouchHelper.SimpleCallback touchCall = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int posisi = viewHolder.getAdapterPosition();
                Model current = adapter.getModel(posisi);
                //Apabila item di swip ke arah kiri
                if (direction==ItemTouchHelper.LEFT){
                    //remove item yang dipilih dengan mengenali todonya sebagai primary key
                    if (db.hapusData(current.getToDo())){
                        //Menghapus data
                        adapter.deleteData(posisi);
                        //Membuat snack bar dan pemberitahuan bahwa item sudah terhapus dengan durasi
                        Snackbar.make(findViewById(R.id.coorD), "Data Telah dihapus", 1000).show();
                    }
                }
            }
        };
        //Menentukan itemTouchHelper untuk recyclerview
        ItemTouchHelper touchHelp = new ItemTouchHelper(touchCall);
        touchHelp.attachToRecyclerView(rcv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Mendapatkan id dari item
        int id = item.getItemId();
        //Apabila item yang dipilih adalah setting
        if (id==R.id.action_settings){
            //Membuat intent baru dari list to do ke pengaturan
            Intent intent = new Intent(MainActivity.this, setting.class);
            //Memulai intent
            startActivity(intent);
            finish();
        }
        return true;
    }
    //Method buat menuju ToDo ADD
    public void addData(View view) {
        //Intent dari list to do ke add to do
        Intent intent = new Intent(MainActivity.this, addTodo.class);
        //Memulai intent
        startActivity(intent);
    }
}
