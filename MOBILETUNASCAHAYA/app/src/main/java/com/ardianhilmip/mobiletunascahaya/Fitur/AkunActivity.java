package com.ardianhilmip.mobiletunascahaya.Fitur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ardianhilmip.mobiletunascahaya.Activities.LoginActivity;
import com.ardianhilmip.mobiletunascahaya.Activities.MainActivity;
import com.ardianhilmip.mobiletunascahaya.R;
import com.ardianhilmip.mobiletunascahaya.SharedPrefManager;

public class AkunActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPrefManager sharedPrefManager;
    ImageButton btnkembali;
    TextView txtkembali, etNama, etEmail, etNohp, etAlamat;
    Button btnkeluar;
    RelativeLayout editAkun;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun);

        btnkembali = findViewById(R.id.btnkembali);
        btnkembali.setOnClickListener(this);
        txtkembali = findViewById(R.id.txtkembali);
        txtkembali.setOnClickListener(this);
        btnkeluar = findViewById(R.id.btnkeluar);
        btnkeluar.setOnClickListener(this);
        editAkun = findViewById(R.id.editakun);
        editAkun.setOnClickListener(this);


        sharedPrefManager=new SharedPrefManager(getApplicationContext());

        etNama = findViewById(R.id.etNama);
        String userName=sharedPrefManager.getUser().getNama();
        etNama.setText(userName);

        etEmail = findViewById(R.id.etEmail);
        String userEmail=sharedPrefManager.getUser().getEmail();
        etEmail.setText(userEmail);

        etNohp = findViewById(R.id.etNohp);
        String nomerhp=sharedPrefManager.getUser().getNomerhp();
        etNohp.setText(nomerhp);

        etAlamat = findViewById(R.id.etAlamat);
        String userAlamat=sharedPrefManager.getUser().getAlamat_user();
        etAlamat.setText(userAlamat);
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
            case R.id.btnkeluar:
                logoutUser();
                break;
            case R.id.editakun:
                switchOnEditAkun();
                break;
        }
    }

    private void switchOnEditAkun() {
        Intent i=new Intent(AkunActivity.this, EditAkunActivity.class);
        startActivity(i);
    }

    private void switchOnHome() {
        Intent i=new Intent(AkunActivity.this, MainActivity.class);
        startActivity(i);
    }

    private void logoutUser() {

        sharedPrefManager.logout();
        Intent intent=new Intent(AkunActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

        Toast.makeText(this, "You have been logged out", Toast.LENGTH_SHORT).show();


    }
}
