package com.ardianhilmip.tunascahaya.Fitur;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ardianhilmip.tunascahaya.Activites.HomeActivity;
import com.ardianhilmip.tunascahaya.ApiInterface;
import com.ardianhilmip.tunascahaya.ModelResponse.PembayaranResponse;
import com.ardianhilmip.tunascahaya.R;
import com.ardianhilmip.tunascahaya.RetrofitClient;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPembayaranActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton btnkembali;
    TextView tvId, tvNama, tvHartot, txtkembali, tvHarcil, tvJumcil, tvStatus, tvTanggal;
    private String xNama, xTanggal;
    private int xId, xHartot, xHarcil, xJumcil;
    private int IMG_REQUEST = 21;
    final int REQUEST_GALLERY = 9544;
    private ImageView buktitf;
    Button btnupdateimg, btnbayar;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_pembayaran);

        btnkembali = findViewById(R.id.btnkembali);
        btnkembali.setOnClickListener(this);
        txtkembali = findViewById(R.id.txtkembali);
        txtkembali.setOnClickListener(this);

        tvId = findViewById(R.id.tviddetail);
        tvNama = findViewById(R.id.detailnama);
        tvHartot = findViewById(R.id.detailtotal);
        tvHarcil = findViewById(R.id.detailcicilan);
        tvJumcil = findViewById(R.id.detailjumlah);
        tvStatus = findViewById(R.id.detailstatus);
        tvTanggal =findViewById(R.id.detailtanggal);
        buktitf = findViewById(R.id.buktipembayaran);

        btnupdateimg = findViewById(R.id.btnupdateimg);
        btnupdateimg.setOnClickListener(this);
        btnbayar = findViewById(R.id.btnbayar);
        btnbayar.setOnClickListener(this);


        Intent terima = getIntent();
        xNama = terima.getStringExtra("xNama");
        xTanggal = terima.getStringExtra("xTanggal");
        xId = terima.getIntExtra("xId", -1);
        xHartot = terima.getIntExtra("xHartot", -1);
        xHarcil = terima.getIntExtra("xHarcil", -1);
        xJumcil = terima.getIntExtra("xJumcil", -1);

        tvNama.setText(xNama);
        tvHartot.setText(String.valueOf(xHartot));
        tvHarcil.setText(String.valueOf(xHarcil));
        tvJumcil.setText("Cicilan Ke-"+String.valueOf(xJumcil));
        tvTanggal.setText(xTanggal);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnkembali:
                switchOnListPembayaran();
                break;
            case R.id.txtkembali:
                switchOnListPembayaran();
                break;
            case R.id.btnupdateimg:
                pilihimage();
                break;
            case R.id.btnbayar:
                btnbayar();
                break;
        }
    }

    private void btnbayar() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream);
        byte[] imageInByte =byteArrayOutputStream.toByteArray();
        String encodedImage = Base64.encodeToString(imageInByte, Base64.DEFAULT);

        ApiInterface  apiInterface = RetrofitClient.getInstance().getApi();
        Call<PembayaranResponse> callUpload = apiInterface.uploadBayar(encodedImage, xId);

        callUpload.enqueue(new Callback<PembayaranResponse>() {
            @Override
            public void onResponse(Call<PembayaranResponse> call, Response<PembayaranResponse> response) {
                String pesan = response.body().getPesan();
                if (pesan.equals("BERHASIL")) {
                    Toast.makeText(DetailPembayaranActivity.this, "Berhasil upload", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DetailPembayaranActivity.this, SuksesActivity.class));
                    finish();
                } else if (pesan.equals("GAGAL")) {
                    Toast.makeText(DetailPembayaranActivity.this, "Gagal upload", Toast.LENGTH_SHORT).show();
                } else if (pesan.equals("NOT CONNECTED")) {
                    Toast.makeText(DetailPembayaranActivity.this, "Gagal menghubungi server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PembayaranResponse> call, Throwable t) {
                Toast.makeText(DetailPembayaranActivity.this, "Error :\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void pilihimage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data !=null){

            Uri path = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                buktitf.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void switchOnListPembayaran() {
        Intent i=new Intent(DetailPembayaranActivity.this, PembayaranActivity.class);
        startActivity(i);
    }
}
