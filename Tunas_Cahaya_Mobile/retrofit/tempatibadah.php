<?php

require 'connection.php';

$Read_data = "SELECT * FROM tb_katalog WHERE Kategori = 'Tempat Ibadah'";
$eksekusi = mysqli_query($con, $Read_data);
$cek = mysqli_affected_rows($con);

if($cek > 0){
    $response["kode"] = 1;
    $response["pesan"] = "Data Tersedia";
    $response["data"] = array();

    while($ambil = mysqli_fetch_object($eksekusi)){
        $F["id_katalog"] = $ambil->id_katalog;
        $F["gambar"] = $ambil->gambar;
        $F["nama_bangunan"] = $ambil->nama_bangunan;
        $F["alamat"] = $ambil->alamat;
        $F["deskripsi"] = $ambil->deskripsi;

        array_push($response["data"], $F);
    }
}else{
    $response["kode"] = 0;
    $response["pesan"] = "Data Tidak Tersedia";
}

echo json_encode($response);
mysqli_close($con);
?>