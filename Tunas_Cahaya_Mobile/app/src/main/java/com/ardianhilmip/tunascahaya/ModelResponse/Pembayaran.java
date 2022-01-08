package com.ardianhilmip.tunascahaya.ModelResponse;

public class Pembayaran {
    int id_detail,  total_harga, hrg_cicilan, cicilan;
    String nama_bangunan, status, tanggal_pembayaran, bukti_pembayaran;

    public int getId_detail() {
        return id_detail;
    }

    public void setId_detail(int id_detail) {
        this.id_detail = id_detail;
    }

    public int getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(int total_harga) {
        this.total_harga = total_harga;
    }

    public int getHrg_cicilan() {
        return hrg_cicilan;
    }

    public void setHrg_cicilan(int hrg_cicilan) {
        this.hrg_cicilan = hrg_cicilan;
    }

    public String getNama_bangunan() {
        return nama_bangunan;
    }

    public void setNama_bangunan(String nama_bangunan) {
        this.nama_bangunan = nama_bangunan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTanggal_pembayaran() {
        return tanggal_pembayaran;
    }

    public void setTanggal_pembayaran(String tanggal_pembayaran) {
        this.tanggal_pembayaran = tanggal_pembayaran;
    }

    public int getCicilan() {
        return cicilan;
    }

    public void setCicilan(int cicilan) {
        this.cicilan = cicilan;
    }

    public String getBukti_pembayaran() {
        return bukti_pembayaran;
    }

    public void setBukti_pembayaran(String bukti_pembayaran) {
        this.bukti_pembayaran = bukti_pembayaran;
    }
}
