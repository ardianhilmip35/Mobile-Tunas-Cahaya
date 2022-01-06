package com.ardianhilmip.tunascahaya;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ardianhilmip.tunascahaya.Fitur.DetailKatalogActivity;
import com.ardianhilmip.tunascahaya.Fitur.KatalogActivity;
import com.ardianhilmip.tunascahaya.ModelResponse.Katalog;
import com.ardianhilmip.tunascahaya.ModelResponse.KatalogResponse;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterKatalog  extends RecyclerView.Adapter<AdapterKatalog.HolderData>{
   private Context ctx;
   private List<Katalog> katalogList;
   private List<Katalog> detailkatalog;
   private String id_katalog;
   public static String BASE_IMG="https://ws-tif.com/tunas-cahaya-build/retrofit/img/";

    public AdapterKatalog(Context ctx, List<Katalog> katalogList) {
        this.ctx = ctx;
        this.katalogList = katalogList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        Katalog katalog = katalogList.get(position);

        holder.tvId.setText(katalog.getId_katalog());
        holder.tvNama.setText(katalog.getNama_bangunan());
        holder.tvAlamat.setText(katalog.getAlamat());
        holder.tvDeskripsi.setText(katalog.getDeskripsi());
        Glide.with(ctx).asBitmap().load(BASE_IMG + katalog.getGambar()).into(holder.tvGambar);

    }

    @Override
    public int getItemCount() {
        return katalogList.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvId, tvNama, tvAlamat, tvDeskripsi;
        ImageView tvGambar;
        CardView produk_card;


        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tvkatalog);
            tvNama = itemView.findViewById(R.id.namaimage);
            tvAlamat = itemView.findViewById(R.id.txtAlamat);
            tvGambar = itemView.findViewById(R.id.tvgambar);
            tvDeskripsi = itemView.findViewById(R.id.tvdeskripsi);
            produk_card = itemView.findViewById(R.id.produk_card);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    id_katalog = tvId.getText().toString();
                    GetDetail();
                }
            });
        }

        private void GetDetail(){
            ApiInterface ardData = RetrofitClient.getInstance().getApi();
            Call<KatalogResponse> ambilData = ardData.getDetailKatalog(id_katalog);

            ambilData.enqueue(new Callback<KatalogResponse>() {
                @Override
                public void onResponse(Call<KatalogResponse> call, Response<KatalogResponse> response) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();
                    detailkatalog = response.body().getData();

                    String VarIdKatalog = detailkatalog.get(0).getId_katalog();
                    String VarNamaBangunan = detailkatalog.get(0).getNama_bangunan();
                    String VarGambar = detailkatalog.get(0).getGambar();
                    String VarDeskripsi = detailkatalog.get(0).getDeskripsi();

                    Intent godetail = new Intent(ctx, DetailKatalogActivity.class);
                    godetail.putExtra("xId", VarIdKatalog);
                    godetail.putExtra("xNama", VarNamaBangunan);
                    godetail.putExtra("xGambar", VarGambar);
                    godetail.putExtra("xDeskripsi", VarDeskripsi);
                    ctx.startActivity(godetail);

                    Toast.makeText(ctx, "Kode : " + kode + " | Pesan : " + pesan, Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<KatalogResponse> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal Menghubungi Server" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}


