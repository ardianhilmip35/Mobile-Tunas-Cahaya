package com.ardianhilmip.mobiletunascahaya.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ardianhilmip.mobiletunascahaya.Api.ApiInterface;
import com.ardianhilmip.mobiletunascahaya.Api.RetrofitClient;
import com.ardianhilmip.mobiletunascahaya.ModelResponse.LoginResponse;
import com.ardianhilmip.mobiletunascahaya.R;
import com.ardianhilmip.mobiletunascahaya.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText emaillog, passwordlog;
    TextView tvcreateaccount;
    Button btnlogin;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvcreateaccount = findViewById(R.id.tvcreateaccount);
        tvcreateaccount.setOnClickListener(this);

        emaillog = findViewById(R.id.emaillog);
        passwordlog = findViewById(R.id.passwordlog);

        btnlogin = findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(this);

        sharedPrefManager =new SharedPrefManager(getApplicationContext());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvcreateaccount:
                switchOnRegister();
                break;
            case R.id.btnlogin:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String email=emaillog.getText().toString();
        String user_password=passwordlog.getText().toString();

        if(email.isEmpty()){
            emaillog.requestFocus();
            emaillog.setError("Please enter your email");
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emaillog.requestFocus();
            emaillog.setError("Please enter correct email");
            return;
        }
        if(user_password.isEmpty()){
            passwordlog.requestFocus();
            passwordlog.setError("Please enter your password");
            return;
        }
        if(user_password.length()<8){
            passwordlog.requestFocus();
            passwordlog.setError("Please enter your password");
            return;
        }
        ApiInterface apiInterface = RetrofitClient.RetrofitClient().create(ApiInterface.class);
        Call<LoginResponse> call= apiInterface.login(email, user_password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse loginResponse=response.body();

                if(response.isSuccessful()){

                    if(loginResponse.getError().equals("200")){

                        sharedPrefManager.saveUser(loginResponse.getUser());
                        Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();


                    }

                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void switchOnRegister() {
        Intent i=new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(i);
    }
}
