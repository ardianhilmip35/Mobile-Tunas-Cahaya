package com.ardianhilmip.mobiletunascahaya.Fitur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ardianhilmip.mobiletunascahaya.Activities.MainActivity;
import com.ardianhilmip.mobiletunascahaya.R;

public class SuksesActivity extends AppCompatActivity implements View.OnClickListener {

    Button backHome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suksespembayaran);

        backHome = findViewById(R.id.btnbackhome);
        backHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnbackhome:
                btnbackhome();
                break;
        }
    }

    private void btnbackhome() {
        Intent i=new Intent(SuksesActivity.this, MainActivity.class);
        startActivity(i);
    }
}
