package com.ardianhilmip.mobiletunascahaya.Fitur;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ardianhilmip.mobiletunascahaya.Activities.MainActivity;
import com.ardianhilmip.mobiletunascahaya.R;

public class KonsulActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton btnkembali;
    TextView txtkembali;
    LinearLayout btnwa;
    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konsul);

        btnkembali = findViewById(R.id.btnkembali);
        btnkembali.setOnClickListener(this);
        txtkembali = findViewById(R.id.txtkembali);
        txtkembali.setOnClickListener(this);
        btnwa = findViewById(R.id.btnwa);
        btnwa.setOnClickListener(this);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btnkembali:
                switchOnHome();
                break;
            case R.id.txtkembali:
                switchOnHome();
                break;
            case R.id.btnwa:
                sendtowa();
                break;
            case R.id.btn:
                sendtowa();
                break;
        }
    }

    private void sendtowa() {
        Intent kirimWA = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=6282132395874&text=Halo%20min,%20bolehkan%20saya%20konsultasi%20mengenai%20proyek%20saya?"));
        startActivity(kirimWA);
    }

    private void switchOnHome() {
        Intent i=new Intent(KonsulActivity.this, MainActivity.class);
        startActivity(i);
    }
}
