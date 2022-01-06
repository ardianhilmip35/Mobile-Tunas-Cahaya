package com.ardianhilmip.tunascahaya.ModelResponse;

public class User {
    int id_pelanggan;
    String nama,email,nomerhp,user_password,alamat_user;

    public User(int id_pelanggan, String nama, String email, String nomerhp, String user_password, String alamat_user) {
        this.id_pelanggan = id_pelanggan;
        this.nama = nama;
        this.email = email;
        this.nomerhp = nomerhp;
        this.user_password = user_password;
        this.alamat_user = alamat_user;
    }

    public int getId_pelanggan() {
        return id_pelanggan;
    }

    public void setId_pelanggan(int id_pelanggan) {
        this.id_pelanggan = id_pelanggan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomerhp() {
        return nomerhp;
    }

    public void setNomerhp(String nomerhp) {
        this.nomerhp = nomerhp;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getAlamat_user() {
        return alamat_user;
    }

    public void setAlamat_user(String alamat_user) {
        this.alamat_user = alamat_user;
    }
}
