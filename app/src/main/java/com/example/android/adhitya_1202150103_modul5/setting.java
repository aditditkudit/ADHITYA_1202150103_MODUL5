package com.example.android.adhitya_1202150103_modul5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class setting extends AppCompatActivity {
    //Deklarasi variabel yang akan digunakan
    TextView shpclr;
    int idColor;
    AlertDialog.Builder alert;
    SharedPreferences.Editor SPE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //Membuat alert dialog baru bernama alert
        alert = new AlertDialog.Builder(this);
        //Inisiasi SharedPreference
        SharedPreferences shareP = getApplicationContext().getSharedPreferences("Preferences", 0);
        SPE = shareP.edit();
        //Mengakses TextView pada layout
        idColor = shareP.getInt("ColourGround", R.color.white);
        //Set Shape color dengan color id yang dipilih
        shpclr = findViewById(R.id.shapecolor);
    }

    @Override
    public void onBackPressed() {
        //Inisiasi dan deklarasi Intent
        Intent intent = new Intent(setting.this, MainActivity.class);
        //Mulai intent
        startActivity(intent);
        //Menutup aktivity setelah Intent dijalankan
        finish();
    }
    //Method yang dijalankan ketika pilihan pada menu dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            this.onBackPressed();
        }
        return true;
    }
    //Mendapatkan string warna yang akan digunakan untuk mengubah shape color
    public String getShapeColor(int c){
        if (c == R.color.red){
            return "Red";
        }else if (c == R.color.green){
            return "Green";
        }else if (c == R.color.blue){
            return "Blue";
        }else {
            return "Default";
        }
    }
    //Mendapatkan id dari warna yang akan digunakan
    public int getIdColor(int c){
        if (c == R.color.red){
            return R.color.red;
        }else if (c == R.color.green){
            return R.color.green;
        }else if (c == R.color.blue){
            return R.color.blue;
        }else {
            return R.color.white;
        }
    }
// Method jika layar di klik
    public void pilihWarna(View view) {
        //Set Title dari alert menjadi shape COlor
        alert.setTitle("Shape Color");
        //Membuat view baru
        View view1 = getLayoutInflater().inflate(R.layout.color, null);
        //Menampilkan view yang telah dibuat
        alert.setView(view1);

        //Akses radio group pada layout
        final RadioGroup radioGro = view1.findViewById(R.id.radiocolor);
        radioGro.check(getColor(idColor));
        //Ketika menekan OK pada alert dialog
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Mendapatkan ID radio button yang dipilih
                int b = radioGro.getCheckedRadioButtonId();
                switch (b){
                    case R.id.merah:
                        idColor = R.color.red;
                        break;
                    case R.id.ijo:
                        idColor = R.color.green;
                        break;
                    case R.id.biru:
                        idColor = R.color.blue;
                        break;
                    case R.id.putih:
                        idColor = R.color.white;
                        break;
                }
                //Set Shape color menjadi color id yang dipilih
                shpclr.setText(getShapeColor(idColor));
                //menaruh Shared Preference
                SPE.putInt("ColourGround", idColor);
                //Commit Shared preference
                SPE.commit();
            }
        });
        //Ketika menekan cancel pada alert dialog
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        //Membuat dan menampilkan alert dialog
        alert.create().show();
    }
}
