package com.ardianhilmip.tunascahaya.Fitur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ardianhilmip.tunascahaya.Activites.HomeActivity;
import com.ardianhilmip.tunascahaya.AdapterKatalog;
import com.ardianhilmip.tunascahaya.ApiInterface;
import com.ardianhilmip.tunascahaya.ModelResponse.Katalog;
import com.ardianhilmip.tunascahaya.ModelResponse.KatalogResponse;
import com.ardianhilmip.tunascahaya.R;
import com.ardianhilmip.tunascahaya.RetrofitClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KatalogActivity extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView katmall, katsekolah, kattemibadah;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager rvLayoutManager;
    private List<Katalog> katalogList = new ArrayList<>();


    ImageButton btnkembali;
    TextView txtkembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katalog);
        btnkembali = findViewById(R.id.btnkembali);
        btnkembali.setOnClickListener(this);
        txtkembali = findViewById(R.id.txtkembali);
        txtkembali.setOnClickListener(this);

        katmall = findViewById(R.id.katmall);
        katsekolah = findViewById(R.id.katsekolah);
        kattemibadah = findViewById(R.id.kattemibadah);
        
        rvLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        katmall.setLayoutManager(rvLayoutManager);
        readMall();

        rvLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        katsekolah.setLayoutManager(rvLayoutManager);
        readSekolah();

        rvLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        kattemibadah.setLayoutManager(rvLayoutManager);
        readTemibadah();

    }

    private void readTemibadah() {
        ApiInterface ardData = RetrofitClient.getInstance().getApi();
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
        ApiInterface ardData = RetrofitClient.getInstance().getApi();
        Call<KatalogResponse> tampilData = ardData.listmall();

        tampilData.enqueue(new Callback<KatalogResponse>() {
            @Override
            public void onResponse(Call<KatalogResponse> call, Response<KatalogResponse> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(KatalogActivity.this, "Kode : " + kode + " | Pesan : " + pesan, Toast.LENGTH_SHORT).show();

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
        ApiInterface ardData = RetrofitClient.getInstance().getApi();
        Call<KatalogResponse> tampilData = ardData.listkatalog();

        tampilData.enqueue(new Callback<KatalogResponse>() {
            @Override
            public void onResponse(Call<KatalogResponse> call, Response<KatalogResponse> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(KatalogActivity.this, "Kode : " + kode + " | Pesan : " + pesan, Toast.LENGTH_SHORT).show();

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
        Intent i=new Intent(KatalogActivity.this, HomeActivity.class);
        startActivity(i);
    }
}
