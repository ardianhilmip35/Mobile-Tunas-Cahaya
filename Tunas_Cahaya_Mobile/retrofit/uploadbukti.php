<?php 


if ($_SERVER["REQUEST_METHOD"] == 'POST') {
  require "connection.php";
  if ($con) {

    $encoded_file = $_POST["EN_IMAGE"];
    $id_detail = $_POST["id_detail"];

    $filename = uniqid().".jpeg";

    $queryUPDATE = mysqli_query($con, "UPDATE tb_detailpemesanan SET bukti_pembayaran = '$filename', status = 'Success' WHERE id_detail= '$id_detail' ");

    if ($queryUPDATE) {
      file_put_contents("../img/bukti/".$filename, base64_decode($encoded_file));

      $response = array('pesan' => 'BERHASIL');
    } else {
      $response = array('pesan' => 'GAGAL');
    } 

  } else {
    $response = array('pesan' => 'NOT CONNECTED');
  }
  mysqli_close($con);
  echo json_encode($response);
}
?>