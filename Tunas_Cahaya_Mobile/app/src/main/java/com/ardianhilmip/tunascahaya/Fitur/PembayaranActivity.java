package com.ardianhilmip.tunascahaya.Fitur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ardianhilmip.tunascahaya.Activites.HomeActivity;
import com.ardianhilmip.tunascahaya.AdapterPembayaran;
import com.ardianhilmip.tunascahaya.ApiInterface;
import com.ardianhilmip.tunascahaya.ModelResponse.Pembayaran;
import com.ardianhilmip.tunascahaya.ModelResponse.PembayaranResponse;
import com.ardianhilmip.tunascahaya.R;
import com.ardianhilmip.tunascahaya.RetrofitClient;
import com.ardianhilmip.tunascahaya.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PembayaranActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton btnkembali;
    TextView txtkembali, tvIdpelanggan;

    private RecyclerView listpembayaran;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager rvLayoutManager;
    private List<Pembayaran> listPembayaran = new ArrayList<>();
    SharedPrefManager sharedPrefManager;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        Intent i=new Intent(PembayaranActivity.this, HomeActivity.class);
        startActivity(i);
    }

    public void pembayaran(){
        sharedPrefManager=new SharedPrefManager(getApplicationContext());
        ApiInterface ardData = RetrofitClient.getInstance().getApi();
        Call<PembayaranResponse> tampilData1 = ardData.listpembayaran(sharedPrefManager.getUser().getId_pelanggan());

        tampilData1.enqueue(new Callback<PembayaranResponse>() {
            @Override
            public void onResponse(Call<PembayaranResponse> call, Response<PembayaranResponse> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(PembayaranActivity.this, "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();

                listPembayaran = response.body().getData();
                adData = new AdapterPembayaran(PembayaranActivity.this,  listPembayaran);
                listpembayaran.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<PembayaranResponse> call, Throwable t) {
                Toast.makeText(PembayaranActivity.this, "Gagal Menghubungi Server" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
