package com.ardianhilmip.mobiletunascahaya.ModelResponse;

import java.util.List;

public class PembayaranResponse {
    private int kode;
    private String pesan;

    private List<Pembayaran> data;

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<Pembayaran> getData() {
        return data;
    }

    public void setData(List<Pembayaran> data) {
        this.data = data;
    }
}
