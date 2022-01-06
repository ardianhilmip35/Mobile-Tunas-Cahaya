package com.ardianhilmip.tunascahaya.Activites;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ardianhilmip.tunascahaya.Fitur.AboutActivity;
import com.ardianhilmip.tunascahaya.Fitur.AkunActivity;
import com.ardianhilmip.tunascahaya.Fitur.KatalogActivity;
import com.ardianhilmip.tunascahaya.Fitur.KonsulActivity;
import com.ardianhilmip.tunascahaya.R;
import com.ardianhilmip.tunascahaya.SharedPrefManager;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvnama,notifkonsul;
    SharedPrefManager sharedPrefManager;
    ImageButton btnabout, btnkonsul, btnsettings, btnkatalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnkonsul = findViewById(R.id.btnkonsul);
        btnkonsul.setOnClickListener(this);
        btnabout = findViewById(R.id.btnabout);
        btnabout.setOnClickListener(this);
        btnkatalog = findViewById(R.id.btnkatalog);
        btnkatalog.setOnClickListener(this);
        btnsettings = findViewById(R.id.btnsettings);
        btnsettings.setOnClickListener(this);
        tvnama = findViewById(R.id.tvnama);
        notifkonsul =findViewById(R.id.notifkonsul);
        notifkonsul.setOnClickListener(this);
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


        }
    }
    private void sendtowa() {
        Intent kirimWA = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=6285236814716&text=Halo%20min,%20bolehkan%20saya%20konsultasi%20mengenai%20proyek%20saya?"));
        startActivity(kirimWA);
    }

    private void switchOnKatalog() {
        Intent i=new Intent(HomeActivity.this, KatalogActivity.class);
        startActivity(i);
    }

    private void switchOnAkun() {
        Intent i=new Intent(HomeActivity.this, AkunActivity.class);
        startActivity(i);
    }

    private void switchOnAbout() {
        Intent i=new Intent(HomeActivity.this, AboutActivity.class);
        startActivity(i);
    }

    private void switchOnKonsul() {
        Intent i=new Intent(HomeActivity.this, KonsulActivity.class);
        startActivity(i);
    }
}
