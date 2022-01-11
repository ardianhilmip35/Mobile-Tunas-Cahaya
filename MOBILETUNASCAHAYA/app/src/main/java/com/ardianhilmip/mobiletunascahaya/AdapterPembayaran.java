package com.ardianhilmip.mobiletunascahaya;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ardianhilmip.mobiletunascahaya.Api.ApiInterface;
import com.ardianhilmip.mobiletunascahaya.Api.RetrofitClient;
import com.ardianhilmip.mobiletunascahaya.Fitur.DetailPembayaranActivity;
import com.ardianhilmip.mobiletunascahaya.ModelResponse.Pembayaran;
import com.ardianhilmip.mobiletunascahaya.ModelResponse.PembayaranResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterPembayaran extends RecyclerView.Adapter<AdapterPembayaran.HolderData> {
    private Context ctx;
    private List<Pembayaran> listPembayaran;
    private List<Pembayaran> detailPembayaran;
    private int id_detail;

    public AdapterPembayaran(Context ctx, List<Pembayaran> listPembayaran){
        this.ctx = ctx;
        this.listPembayaran = listPembayaran;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pembayaran,parent,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        Pembayaran pm = listPembayaran.get(position);
        holder.tvIddetail.setText(String.valueOf(pm.getId_detail()));
        holder.tvNamabangunan.setText(pm.getNama_bangunan());
        holder.tvTotharga.setText(String.valueOf(pm.getTotal_harga()));
        holder.tvHarcicilan.setText(String.valueOf(pm.getHrg_cicilan()));
        holder.tvTanggal.setText(pm.getTanggal_pembayaran());
        holder.tvStatus.setText(pm.getStatus());
        holder.tvJumcicilan.setText(String.valueOf(pm.getCicilan()));
    }

    @Override
    public int getItemCount() {
        return listPembayaran.size();
    }


    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvIddetail, tvNamabangunan, tvTotharga, tvHarcicilan, tvTanggal, tvStatus, tvJumcicilan;
        ImageView tvGambar;
        CardView cardView;


        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvIddetail = itemView.findViewById(R.id.tvdetail);
            tvNamabangunan = itemView.findViewById(R.id.NamaBangunan);
            tvTotharga = itemView.findViewById(R.id.tvHargaTotal);
            tvHarcicilan = itemView.findViewById(R.id.tvCicilan);
            tvTanggal = itemView.findViewById(R.id.tvTanggalPembayaran);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvJumcicilan = itemView.findViewById(R.id.tvjumcicilan);
            cardView = itemView.findViewById(R.id.pembayaran_card);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    id_detail = Integer.parseInt(tvIddetail.getText().toString());
                    GetDetail();
                }
            });
        }

        private void GetDetail() {
            ApiInterface ardData = RetrofitClient.RetrofitClient().create(ApiInterface.class);
            Call<PembayaranResponse> ambil = ardData.getDetailPembayaran(id_detail);

            ambil.enqueue(new Callback<PembayaranResponse>() {
                @Override
                public void onResponse(Call<PembayaranResponse> call, Response<PembayaranResponse> response) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();
                    detailPembayaran = response.body().getData();

                    int VarIdDetail = detailPembayaran.get(0).getId_detail();
                    int VarHarTot = detailPembayaran.get(0).getTotal_harga();
                    int VarHarCicil = detailPembayaran.get(0).getHrg_cicilan();
                    int VarJumCicil = detailPembayaran.get(0).getCicilan();
                    String VarNamaBangunan = detailPembayaran.get(0).getNama_bangunan();
                    String VarTanggal = detailPembayaran.get(0).getTanggal_pembayaran();

                    Intent godetail = new Intent(ctx, DetailPembayaranActivity.class);
                    godetail.putExtra("xId", VarIdDetail);
                    godetail.putExtra("xNama", VarNamaBangunan);
                    godetail.putExtra("xHartot", VarHarTot);
                    godetail.putExtra("xHarcil", VarHarCicil);
                    godetail.putExtra("xJumcil", VarJumCicil);
                    godetail.putExtra("xTanggal", VarTanggal);
                    ctx.startActivity(godetail);

                    Toast.makeText(ctx, "Kode : "+kode+" |Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<PembayaranResponse> call, Throwable t) {
                    Toast.makeText(ctx,"Gagal" +t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
