<?php

require 'connection.php';

$bukti_pembayaran = $_POST['bukti_pembayaran'];
$id_detail = $_POST['id_detail'];
$status = $_POST['status'];

$Update_data = "UPDATE tb_detailpemesanan SET bukti_pembayaran='$bukti_pembayaran', status='$status' WHERE id_detail='$id_detail'";
$eksekusi = mysqli_query($con, $Update_data);
$cek = mysqli_affected_rows($con);

if($cek > 0){
    $response["kode"] = 1;
    $response["pesan"] = "Pembayaran Sukses";
}else{
    $response["kode"] = 0;
    $response["pesan"] = "Maaf, pembayaran gagal. Coba Lagi Nanti.";
}

echo json_encode($response);
mysqli_close($con);
?> 