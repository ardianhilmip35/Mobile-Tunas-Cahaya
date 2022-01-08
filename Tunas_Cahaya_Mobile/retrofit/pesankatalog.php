<?php

require 'connection.php';

$id_katalog = $_POST['id_katalog'];
$id_pelanggan = $_POST['id_pelanggan'];
$tgl_pemesanan = $_POST['tgl_pemesanan'];

$Insert_data = "INSERT INTO tb_pemesanan(id_katalog,id_pelanggan,tgl_pemesanan) VALUES('$id_katalog','$id_pelanggan','$tgl_pemesanan')";
$eksekusi = mysqli_query($con, $Insert_data);
$cek = mysqli_affected_rows($con);

if($cek > 0){
    $response["kode"] = 1;
    $response["pesan"] = "";
    
}else{
    $response["kode"] = 0;
    $response["pesan"] = "Data Tidak Tersedia";
}

echo json_encode($response);
mysqli_close($con);
?> 