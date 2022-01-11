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
import com.ardianhilmip.mobiletunascahaya.AdapterPembayaran;
import com.ardianhilmip.mobiletunascahaya.Api.ApiInterface;
import com.ardianhilmip.mobiletunascahaya.Api.RetrofitClient;
import com.ardianhilmip.mobiletunascahaya.ModelResponse.Pembayaran;
import com.ardianhilmip.mobiletunascahaya.ModelResponse.PembayaranResponse;
import com.ardianhilmip.mobiletunascahaya.R;
import com.ardianhilmip.mobiletunascahaya.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PembayaranActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton btnkembali;
    TextView txtkembali, tvIdpelanggan;

    private RecyclerView listpembayaran;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager rvLayoutManager;
    private List<Pembayaran> listPembayaran = new ArrayList<>();
    SharedPrefManager sharedPrefManager;
    int userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);

        btnkembali = findViewById(R.id.btnkembali);
        btnkembali.setOnClickListener(this);
        txtkembali = findViewById(R.id.txtkembali);
        txtkembali.setOnClickListener(this);

        listpembayaran = findViewById(R.id.listpembayaran);

        rvLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        listpembayaran.setLayoutManager(rvLayoutManager);
        pembayaran();
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
        Intent i=new Intent(PembayaranActivity.this, MainActivity.class);
        startActivity(i);
    }

    public void pembayaran(){
        sharedPrefManager=new SharedPrefManager(getApplicationContext());
        ApiInterface ardData = RetrofitClient.RetrofitClient().create(ApiInterface.class);
        Call<PembayaranResponse> tampilData1 = ardData.listpembayaran(sharedPrefManager.getUser().getId_pelanggan());

        tampilData1.enqueue(new Callback<PembayaranResponse>() {
            @Override
            public void onResponse(Call<PembayaranResponse> call, Response<PembayaranResponse> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                if (pesan.equals("Data Tersedia")){
                    listPembayaran = response.body().getData();

                    adData = new AdapterPembayaran(PembayaranActivity.this,  listPembayaran);
                    listpembayaran.setAdapter(adData);
                    adData.notifyDataSetChanged();
                    listpembayaran.setVisibility(View.VISIBLE);

                }else if(pesan.equals("Data Tidak Tersedia")){
                    listpembayaran.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<PembayaranResponse> call, Throwable t) {
                Toast.makeText(PembayaranActivity.this, "Gagal Menghubungi Server" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
