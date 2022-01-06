package com.ardianhilmip.tunascahaya.Fitur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ardianhilmip.tunascahaya.ModelResponse.UpdatePassResponse;
import com.ardianhilmip.tunascahaya.R;
import com.ardianhilmip.tunascahaya.RetrofitClient;
import com.ardianhilmip.tunascahaya.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPassActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etPasswordLama, etPasswordBaru, etKonfirmasiPassword;
    Button btnupdate;
    SharedPrefManager sharedPrefManager;
    ImageButton btnkembali;
    TextView txtkembali;
    String userEmailId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        btnkembali = findViewById(R.id.btnkembali);
        btnkembali.setOnClickListener(this);
        txtkembali = findViewById(R.id.txtkembali);
        txtkembali.setOnClickListener(this);
        btnupdate = findViewById(R.id.btnupdate);
        btnupdate.setOnClickListener(this);
        sharedPrefManager=new SharedPrefManager(getApplicationContext());
        userEmailId=sharedPrefManager.getUser().getEmail();

        etPasswordLama = findViewById(R.id.etPasswordLama);
        etPasswordBaru = findViewById(R.id.etPasswordBaru);
        etKonfirmasiPassword = findViewById(R.id.etKonfirmasiPassword);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnupdate:
                update();
                break;
            case R.id.btnkembali:
                switchOnEdit();
                break;
            case R.id.txtkembali:
                switchOnEdit();
                break;
        }
    }

    private void switchOnEdit() {
        Intent i=new Intent(ResetPassActivity.this, EditAkunActivity.class);
        startActivity(i);
    }

    private void update() {
        String userCurrentPassword=etPasswordLama.getText().toString().trim();
        String userNewPassword=etPasswordBaru.getText().toString().trim();
        String userConfNewPassword=etKonfirmasiPassword.getText().toString().trim();


        if(userCurrentPassword.isEmpty()){
            etPasswordLama.setError("Enter current password");
            etPasswordLama.requestFocus();
            return;
        }

        if(userCurrentPassword.length()<8){
            etPasswordLama.setError("Enter 8 digit password");
            etPasswordLama.requestFocus();
            return;
        }


        if(userNewPassword.isEmpty()){
            etPasswordBaru.setError("Enter current password");
            etPasswordBaru.requestFocus();
            return;
        }

        if(userNewPassword.length()<8){
            etPasswordBaru.setError("Enter 8 digit password");
            etPasswordBaru.requestFocus();
            return;
        }
        if(userConfNewPassword.isEmpty()){
            etKonfirmasiPassword.setError("Enter current password");
            etKonfirmasiPassword.requestFocus();
            return;
        }
        if(!userConfNewPassword.equals(userConfNewPassword)){
            etKonfirmasiPassword.setError("Password Would Not be matched");
            etKonfirmasiPassword.requestFocus();
            return;
        }


        Call<UpdatePassResponse> call= RetrofitClient
                .getInstance()
                .getApi()
                .updateUserPass(userEmailId,userCurrentPassword,userNewPassword);

        call.enqueue(new Callback<UpdatePassResponse>() {
            @Override
            public void onResponse(Call<UpdatePassResponse> call, Response<UpdatePassResponse> response) {

                UpdatePassResponse passwordResponse=response.body();


                if(response.isSuccessful()){


                    if(passwordResponse.getError().equals("200")){
                        Intent intent=new Intent(ResetPassActivity.this,AkunActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), passwordResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdatePassResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
