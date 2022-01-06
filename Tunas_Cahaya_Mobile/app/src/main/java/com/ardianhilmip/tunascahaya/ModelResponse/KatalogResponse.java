package com.ardianhilmip.tunascahaya.ModelResponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KatalogResponse {
    private int kode;
    private String pesan;
    private List<Katalog> data;

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

    public List<Katalog> getData() {
        return data;
    }

    public void setData(List<Katalog> data) {
        this.data = data;
    }
}
