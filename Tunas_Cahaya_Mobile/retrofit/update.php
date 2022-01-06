<?php

require 'connection.php';

$id_pelanggan = $_POST['id_pelanggan'];
$nama = $_POST['nama'];
$email = $_POST['email'];
$nomerhp = $_POST['nomerhp'];
$alamat_user = $_POST['alamat_user'];

$update_query = "UPDATE tb_user SET nama='$nama', email='$email', nomerhp='$nomerhp', alamat_user='$alamat_user' WHERE id_pelanggan='$id_pelanggan'";
$result = mysqli_query($con, $update_query);


if ($result > 0) {

    $fetchuser = mysqli_query($con, "SELECT id_pelanggan, nama, email, nomerhp, alamat_user FROM tb_user WHERE email='$email'");

    if (mysqli_num_rows($fetchuser) > 0) {

        while ($row = $fetchuser->fetch_assoc()) {
            $response["user"] = $row;
        }
        $response['error'] = "200";
        $response['message'] = "user update success";
    } else {
        $response["user"] = (object)[];
        $response['error'] = "400";
        $response['message'] = "user update but detail not fetch";
    }
} else {
    $response["user"] = (object)[];
    $response['error'] = "400";
    $response['message'] = "user update failed";
}



echo json_encode($response);
?>