package com.ardianhilmip.mobiletunascahaya.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ardianhilmip.mobiletunascahaya.Fitur.AboutActivity;
import com.ardianhilmip.mobiletunascahaya.Fitur.AkunActivity;
import com.ardianhilmip.mobiletunascahaya.Fitur.KatalogActivity;
import com.ardianhilmip.mobiletunascahaya.Fitur.KonsulActivity;
import com.ardianhilmip.mobiletunascahaya.Fitur.PembayaranActivity;
import com.ardianhilmip.mobiletunascahaya.R;
import com.ardianhilmip.mobiletunascahaya.SharedPrefManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvnama,notifkonsul;
    SharedPrefManager sharedPrefManager;
    ImageButton btnabout, btnkonsul, btnsettings, btnkatalog, btnpembayaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnkonsul = findViewById(R.id.btnkonsul);
        btnkonsul.setOnClickListener(this);
        btnabout = findViewById(R.id.btnabout);
        btnabout.setOnClickListener(this);
        btnkatalog = findViewById(R.id.btnkatalog);
        btnkatalog.setOnClickListener(this);
        btnsettings = findViewById(R.id.btnsettings);
        btnsettings.setOnClickListener(this);
        tvnama = findViewById(R.id.tvnama);
        notifkonsul = findViewById(R.id.notifkonsul);
        notifkonsul.setOnClickListener(this);
        btnpembayaran = findViewById(R.id.btnpemesanan);
        btnpembayaran.setOnClickListener(this);

        sharedPrefManager=new SharedPrefManager(getApplicationContext());

        String userName="Hey, "+ sharedPrefManager.getUser().getNama();
        tvnama.setText(userName);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btnkonsul:
                switchOnKonsul();
                break;
            case R.id.btnabout:
                switchOnAbout();
                break;
            case R.id.btnsettings:
                switchOnAkun();
                break;
            case R.id.btnkatalog:
                switchOnKatalog();
                break;
            case R.id.notifkonsul:
                sendtowa();
                break;
            case R.id.btnpemesanan:
                switchOnPembayaran();
                break;
        }
    }

    private void switchOnPembayaran() {

        Intent i=new Intent(MainActivity.this, PembayaranActivity.class);
        startActivity(i);
    }

    private void sendtowa() {
        Intent kirimWA = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=6285236814716&text=Halo%20min,%20bolehkan%20saya%20konsultasi%20mengenai%20proyek%20saya?"));
        startActivity(kirimWA);
    }

    private void switchOnKatalog() {
        Intent i=new Intent(MainActivity.this, KatalogActivity.class);
        startActivity(i);
    }

    private void switchOnAkun() {
        Intent i=new Intent(MainActivity.this, AkunActivity.class);
        startActivity(i);
    }

    private void switchOnAbout() {
        Intent i=new Intent(MainActivity.this, AboutActivity.class);
        startActivity(i);
    }

    private void switchOnKonsul() {
        Intent i=new Intent(MainActivity.this, KonsulActivity.class);
        startActivity(i);
    }
}