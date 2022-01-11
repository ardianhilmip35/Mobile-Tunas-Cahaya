package com.ardianhilmip.mobiletunascahaya.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ardianhilmip.mobiletunascahaya.Api.ApiInterface;
import com.ardianhilmip.mobiletunascahaya.Api.RetrofitClient;
import com.ardianhilmip.mobiletunascahaya.ModelResponse.RegisterResponse;
import com.ardianhilmip.mobiletunascahaya.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements  View.OnClickListener {

    EditText etNama, etEmail, etNomerhp, etPassword;
    Button btnRegis;
    TextView tvlogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tvlogin = findViewById(R.id.tvlogin);
        tvlogin.setOnClickListener(this);
        btnRegis = findViewById(R.id.btnRegis);
        btnRegis.setOnClickListener(this);

        etNama = findViewById(R.id.etNama);
        etEmail = findViewById(R.id.etEmail);
        etNomerhp = findViewById(R.id.etNohp);
        etPassword = findViewById(R.id.etPassword);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvlogin:
                switchOnLogin();
                break;
            case R.id.btnRegis:
                registerUser();
                break;
        }
    }

    private void switchOnLogin() {
        Intent i=new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(i);
    }


    private void registerUser() {
        String nama=etNama.getText().toString();
        String email=etEmail.getText().toString();
        String nomerhp=etNomerhp.getText().toString();
        String user_password=etPassword.getText().toString();

        if(nama.isEmpty()){
            etNama.requestFocus();
            etNama.setError("Please enter your name");
            return;
        }
        if(nama.length()>20){
            etNama.requestFocus();
            etNama.setError("Cannot exceed 20 characters");
            return;
        }
        if(email.isEmpty()){
            etEmail.requestFocus();
            etEmail.setError("Please enter your email");
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.requestFocus();
            etEmail.setError("Please enter correct email");
            return;
        }
        if(nomerhp.isEmpty()){
            etNomerhp.requestFocus();
            etNomerhp.setError("Please enter your phone number");
            return;
        }
        if(user_password.isEmpty()){
            etPassword.requestFocus();
            etPassword.setError("Please enter your password");
            return;
        }
        if(user_password.length()<8){
            etPassword.requestFocus();
            etPassword.setError("Enter 8 digit password");
            return;
        }

        ApiInterface apiInterface = RetrofitClient.RetrofitClient().create(ApiInterface.class);
        Call<RegisterResponse> call = apiInterface.register(nama,email,nomerhp,user_password);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                RegisterResponse registerResponse=response.body();
                if(response.isSuccessful()){

                    Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                    Toast.makeText(RegisterActivity.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(RegisterActivity.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

