package com.ardianhilmip.tunascahaya.Fitur;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ardianhilmip.tunascahaya.Activites.HomeActivity;
import com.ardianhilmip.tunascahaya.Activites.LoginActivity;
import com.ardianhilmip.tunascahaya.ModelResponse.LoginResponse;
import com.ardianhilmip.tunascahaya.R;
import com.ardianhilmip.tunascahaya.RetrofitClient;
import com.ardianhilmip.tunascahaya.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAkunActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPrefManager sharedPrefManager;
    ImageButton btnkembali;
    TextView txtkembali;
    EditText etNama, etEmail, etNohp, etPassword, etAlamat;
    int userId;
    Button btnupdate, btnpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editakun);

        btnkembali = findViewById(R.id.btnkembali);
        btnkembali.setOnClickListener(this);
        txtkembali = findViewById(R.id.txtkembali);
        txtkembali.setOnClickListener(this);
        btnupdate = findViewById(R.id.btnedit);
        btnupdate.setOnClickListener(this);
        btnpassword = findViewById(R.id.btnpassword);
        btnpassword.setOnClickListener(this);

        sharedPrefManager=new SharedPrefManager(getApplicationContext());

        userId=sharedPrefManager.getUser().getId_pelanggan();

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
        switch (view.getId()) {
            case R.id.btnkembali:
                switchOnAkun();
                break;
            case R.id.txtkembali:
                switchOnAkun();
                break;
            case R.id.btnedit:
                update();
                break;
            case R.id.btnpassword:
                witchOnPass();
                break;
        }
    }

    private void witchOnPass() {
        Intent i=new Intent(EditAkunActivity.this, ResetPassActivity.class);
        startActivity(i);
    }

    private void update() {
        String nama=etNama.getText().toString().trim();
        String email=etEmail.getText().toString().trim();
        String nomerhp=etNohp.getText().toString().trim();
        String alamat=etAlamat.getText().toString().trim();



        if(nama.isEmpty()){
            etNama.requestFocus();
            return;
        }
        if(email.isEmpty()){
            etEmail.requestFocus();
            return;
        }
        if(nomerhp.isEmpty()){
            etNohp.requestFocus();
            return;
        }
        if(alamat.isEmpty()){
            etAlamat.requestFocus();
            return;
        }

        Call<LoginResponse> call= RetrofitClient
                .getInstance()
                .getApi()
                .updateUserAccount(userId,nama,email,nomerhp,alamat);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {


                LoginResponse updateReponse=response.body();
                if(response.isSuccessful()){

                    if(updateReponse.getError().equals("200")){

                        sharedPrefManager.saveUser(updateReponse.getUser());
                        Toast.makeText(getApplicationContext(), updateReponse.getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(EditAkunActivity.this,AkunActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), updateReponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                }
                else{
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void switchOnAkun() {
        Intent i=new Intent(EditAkunActivity.this, AkunActivity.class);
        startActivity(i);
    }
}
