package com.ardianhilmip.tunascahaya.Fitur;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.ardianhilmip.tunascahaya.ApiInterface;
import com.ardianhilmip.tunascahaya.ModelResponse.PesanResponse;
import com.ardianhilmip.tunascahaya.R;
import com.ardianhilmip.tunascahaya.RetrofitClient;
import com.ardianhilmip.tunascahaya.SharedPrefManager;
import com.bumptech.glide.Glide;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailKatalogActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvId, tvNama, tvDeskripsi, txtkembali, tvIdpelanggan, tvTanggal;
    ImageView tvGambar;
    private String xId, xNama, xGambar, xDeskripsi;

    public static String BASE_IMG="https://ws-tif.com/tunas-cahaya-build/retrofit/img/";
    ImageButton btnkembali;
    Button btnpesan;

    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_katalog);

        sharedPrefManager=new SharedPrefManager(getApplicationContext());
        tvIdpelanggan = findViewById(R.id.tvpelanggan);
        String idpelanggan = String.valueOf(sharedPrefManager.getUser().getId_pelanggan());
        tvIdpelanggan.setText(idpelanggan);


        btnkembali = findViewById(R.id.btnkembali);
        btnkembali.setOnClickListener(this);
        txtkembali = findViewById(R.id.txtkembali);
        txtkembali.setOnClickListener(this);


        btnpesan = findViewById(R.id.btnpesan);
        btnpesan.setOnClickListener(this);


        tvId = findViewById(R.id.tvkatalog);



        tvTanggal = findViewById(R.id.tvTanggal);
        Calendar calendar = Calendar.getInstance();
        int hari = calendar.get(Calendar.DAY_OF_MONTH);
        int bulan = calendar.get(Calendar.MONTH);
        int tahun = calendar.get(Calendar.YEAR);
        String tanggal = tahun + "-" + bulan + "-" + hari;


        tvNama = findViewById(R.id.namaimage);
        tvGambar = findViewById(R.id.image);
        tvDeskripsi = findViewById(R.id.tvdeskripsi);
        tvDeskripsi.setMovementMethod(new ScrollingMovementMethod());


        Intent terima = getIntent();
        xId = terima.getStringExtra("xId");
        xNama = terima.getStringExtra("xNama");
        xGambar = terima.getStringExtra("xGambar");
        xDeskripsi = terima.getStringExtra("xDeskripsi");


        tvNama.setText(xNama);
        tvDeskripsi.setText(xDeskripsi);
        Glide.with(DetailKatalogActivity.this).load(BASE_IMG+xGambar).into(tvGambar);
        tvId.setText(xId);

        tvTanggal.setText(tanggal);


       }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnkembali:
                switchOnKatalog();
                break;
            case R.id.txtkembali:
                switchOnKatalog();
                break;
            case R.id.btnpesan:
                pesansekarang();
                break;
        }
    }


    private void switchOnKatalog() {
        Intent i=new Intent(DetailKatalogActivity.this, KatalogActivity.class);
        startActivity(i);
    }


    private void pesansekarang() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah anda sudah melakukan konsultasi kepada pihak kami?");
        builder.setNegativeButton("Belum", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent a=new Intent(DetailKatalogActivity.this, KonsulActivity.class);
                startActivity(a);
            }
        });
        builder.setPositiveButton("Sudah", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               pemesananproduk();
            }
        });
        builder.show();
    }


    private void pemesananproduk() {
        String Idpelanggan = tvIdpelanggan.getText().toString();
        String Katalog = tvId.getText().toString();
        String Tanggal = tvTanggal.getText().toString();

        ApiInterface ardData = RetrofitClient.getInstance().getApi();
        Call<PesanResponse> pesan = ardData.PesanSekarang(Idpelanggan, Katalog, Tanggal);

        pesan.enqueue(new Callback<PesanResponse>() {
            @Override
            public void onResponse(Call<PesanResponse> call, Response<PesanResponse> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();
                Intent i=new Intent(DetailKatalogActivity.this, PembayaranActivity.class);
                startActivity(i);
                Toast.makeText( DetailKatalogActivity.this, "Kode : " + kode + " | Pesan : " + pesan, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PesanResponse> call, Throwable t) {
                Toast.makeText(DetailKatalogActivity.this, "Gagal Menghubungi Server" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
