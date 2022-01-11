package com.ardianhilmip.mobiletunascahaya.Fitur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ardianhilmip.mobiletunascahaya.Activities.MainActivity;
import com.ardianhilmip.mobiletunascahaya.R;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton btnkembali;
    TextView txtkembali;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        btnkembali = findViewById(R.id.btnkembali);
        btnkembali.setOnClickListener(this);
        txtkembali = findViewById(R.id.txtkembali);
        txtkembali.setOnClickListener(this);
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

        }
    }

    private void switchOnHome() {
        Intent i=new Intent(AboutActivity.this, MainActivity.class);
        startActivity(i);
    }
}
