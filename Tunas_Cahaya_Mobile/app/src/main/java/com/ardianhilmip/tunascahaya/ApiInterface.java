package com.ardianhilmip.tunascahaya;

import com.ardianhilmip.tunascahaya.ModelResponse.KatalogResponse;
import com.ardianhilmip.tunascahaya.ModelResponse.LoginResponse;
import com.ardianhilmip.tunascahaya.ModelResponse.PesanResponse;
import com.ardianhilmip.tunascahaya.ModelResponse.RegisterResponse;
import com.ardianhilmip.tunascahaya.ModelResponse.UpdatePassResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("register.php")
    Call<RegisterResponse> register(
            @Field("nama") String nama,
            @Field("email") String email,
            @Field("nomerhp") String nomerhp,
            @Field("user_password") String user_password

    );

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("user_password") String user_password

    );

    @FormUrlEncoded
    @POST("update.php")
    Call<LoginResponse> updateUserAccount(
            @Field("id_pelanggan") int id_pelanggan,
            @Field("nama") String nama,
            @Field("email") String email,
            @Field("nomerhp") String nomerhp,
            @Field("alamat_user") String alamat_user
    );

    @FormUrlEncoded
    @POST("updatepassword.php")
    Call<UpdatePassResponse> updateUserPass(
            @Field("email") String email,
            @Field("current") String currentPassword,
            @Field("new") String newPassword
    );

    @GET("sekolah.php")
    Call<KatalogResponse> listkatalog();

    @GET("tempatibadah.php")
    Call<KatalogResponse> listtempatibadah();

    @GET("mall.php")
    Call<KatalogResponse> listmall();

    @FormUrlEncoded
    @POST("detail_ktalog.php")
    Call<KatalogResponse> getDetailKatalog(
            @Field("id_katalog") String id_katalog
    );

    @FormUrlEncoded
    @POST("pesankatalog.php")
    Call<PesanResponse> PesanSekarang(
            @Field("id_pelanggan") String id_pelanggan,
            @Field("id_katalog") String id_katalog,
            @Field("tgl_pemesanan") String tgl_pemesanan
    );
}
