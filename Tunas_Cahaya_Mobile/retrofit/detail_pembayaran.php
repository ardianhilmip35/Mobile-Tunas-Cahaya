<?php

require 'connection.php';

$id_detail = $_POST["id_detail"];

$Read_data = "SELECT * FROM tb_detailpemesanan WHERE id_detail = '$id_detail'";
$eksekusi = mysqli_query($con, $Read_data);
$cek = mysqli_affected_rows($con);

if($cek > 0){
    $response["kode"] = 1;
    $response["pesan"] = "Data Tersedia";
    $response["data"] = array();

    while($ambil = mysqli_fetch_object($eksekusi)){
        $F["id_detail"] = $ambil->id_detail;
        $F["nama_bangunan"] = $ambil->nama_bangunan;
        $F["total_harga"] = $ambil->total_harga;
        $F["hrg_cicilan"] = $ambil->hrg_cicilan;
        $F["cicilan"] = $ambil->cicilan;
        $F["tanggal_pembayaran"] = $ambil->tanggal_pembayaran;
        $F["status"] = $ambil->status;

        array_push($response["data"], $F);
    }
}else{
    $response["kode"] = 0;
    $response["pesan"] = "Data Tidak Tersedia";
}
echo json_encode($response);
mysqli_close($con);
?> 