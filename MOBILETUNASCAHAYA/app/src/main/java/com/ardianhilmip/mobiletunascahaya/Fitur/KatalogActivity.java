package com.ardianhilmip.mobiletunascahaya.Fitur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ardianhilmip.mobiletunascahaya.Activities.MainActivity;
import com.ardianhilmip.mobiletunascahaya.AdapterKatalog;
import com.ardianhilmip.mobiletunascahaya.Api.ApiInterface;
import com.ardianhilmip.mobiletunascahaya.Api.RetrofitClient;
import com.ardianhilmip.mobiletunascahaya.ModelResponse.Katalog;
import com.ardianhilmip.mobiletunascahaya.ModelResponse.KatalogResponse;
import com.ardianhilmip.mobiletunascahaya.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KatalogActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView katmall, katsekolah, kattemibadah;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager rvLayoutManager;
    private List<Katalog> katalogList = new ArrayList<>();

    ImageButton btnkembali;
    TextView txtkembali;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katalog);

        btnkembali = findViewById(R.id.btnkembali);
        btnkembali.setOnClickListener(this);
        txtkembali = findViewById(R.id.txtkembali);
        txtkembali.setOnClickListener(this);

        katmall = findViewById(R.id.katmall);
        katsekolah = findViewById(R.id.katsekolah);
        kattemibadah = findViewById(R.id.kattemibadah);

        rvLayoutManager = new LinearLayoutManager(KatalogActivity.this, LinearLayoutManager.HORIZONTAL, false);
        katmall.setLayoutManager(rvLayoutManager);
        readMall();

        rvLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        katsekolah.setLayoutManager(rvLayoutManager);
        readSekolah();

        rvLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        kattemibadah.setLayoutManager(rvLayoutManager);
        readTemibadah();
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
        Intent i=new Intent(KatalogActivity.this, MainActivity.class);
        startActivity(i);
    }

    private void readTemibadah() {
        ApiInterface ardData = RetrofitClient.RetrofitClient().create(ApiInterface.class);
        Call<KatalogResponse> tampilData = ardData.listtempatibadah();

        tampilData.enqueue(new Callback<KatalogResponse>() {
            @Override
            public void onResponse(Call<KatalogResponse> call, Response<KatalogResponse> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(KatalogActivity.this, "Kode : " + kode + " | Pesan : " + pesan, Toast.LENGTH_SHORT).show();

                katalogList = response.body().getData();

                adData = new AdapterKatalog(KatalogActivity.this, katalogList);
                kattemibadah.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<KatalogResponse> call, Throwable t) {
                Toast.makeText(KatalogActivity.this, "Gagal Menghubungi Server" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void readMall() {
        ApiInterface ardData = RetrofitClient.RetrofitClient().create(ApiInterface.class);
        Call<KatalogResponse> tampilData = ardData.listmall();

        tampilData.enqueue(new Callback<KatalogResponse>() {
            @Override
            public void onResponse(Call<KatalogResponse> call, Response<KatalogResponse> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(KatalogActivity.this, "Kode : " + kode + " | Pesan : " + pesan, Toast.LENGTH_SHORT).cancel();

                katalogList = response.body().getData();

                adData = new AdapterKatalog(KatalogActivity.this, katalogList);
                katmall.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<KatalogResponse> call, Throwable t) {
                Toast.makeText(KatalogActivity.this, "Gagal Menghubungi Server" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void readSekolah() {
        ApiInterface ardData = RetrofitClient.RetrofitClient().create(ApiInterface.class);
        Call<KatalogResponse> tampilData = ardData.listkatalog();

        tampilData.enqueue(new Callback<KatalogResponse>() {
            @Override
            public void onResponse(Call<KatalogResponse> call, Response<KatalogResponse> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(KatalogActivity.this, "Kode : " + kode + " | Pesan : " + pesan, Toast.LENGTH_SHORT).cancel();

                katalogList = response.body().getData();

                adData = new AdapterKatalog(KatalogActivity.this, katalogList);
                katsekolah.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<KatalogResponse> call, Throwable t) {
                Toast.makeText(KatalogActivity.this, "Gagal Menghubungi Server" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
