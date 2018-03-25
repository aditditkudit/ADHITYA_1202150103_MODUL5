package com.example.android.adhitya_1202150103_modul5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addTodo extends AppCompatActivity {
    //Deklarasi Variable buat Penambahan Todo
    private EditText todo, deskripsi, prioritas;
    private Button add;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
        todo = (EditText) findViewById(R.id.todo);
        deskripsi = (EditText) findViewById(R.id.deskripsi);
        prioritas = (EditText) findViewById(R.id.prioritas);
        db = new Database(this);
    }

    public void tambahToDo(View view) {
        if (db.nambahData(new Model(todo.getText().toString(), deskripsi.getText().toString(),
            prioritas.getText().toString()))){
            //Menampilkan toast jika nambah ToDonya berhasil
            Toast.makeText(addTodo.this, "Success adding ToDo", Toast.LENGTH_SHORT).show();
            //Pindah Activity addToDO ke MainActivity
            startActivity(new Intent(addTodo.this, MainActivity.class));
            //Menutup aktivitas agar tidak kembali ke activity addTodo
            addTodo.this.finish();
        } else {
            //Apabila editText kosong maka akan muncul Toast
            Toast.makeText(addTodo.this, "Tidak bisa menambah List", Toast.LENGTH_SHORT).show();
            todo.setText(null);
            deskripsi.setText(null);
            prioritas.setText(null);
        }
    }

    @Override
    public void onBackPressed() {
        // Menuju Activity MainActivity
        Intent back = new Intent(addTodo.this, MainActivity.class);
        //Menuju Intent
        startActivity(back);
        //Menutup aktivitas setelah intent dijalankan
        this.finish();

    }
}
