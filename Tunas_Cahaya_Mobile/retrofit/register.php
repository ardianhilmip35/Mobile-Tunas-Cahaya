<?php

require 'connection.php';
$nama = $_POST['nama'];
$email = $_POST['email'];
$nomerhp = $_POST['nomerhp'];
$user_password = $_POST['user_password'];

$checkUser = "SELECT * from tb_user WHERE email='$email'";
$checkQuery = mysqli_query($con, $checkUser);

if (mysqli_num_rows($checkQuery) > 0) {

    $response['error'] = "002";
    $response['message'] = "User exist";
} else {
    $insertQuery = "INSERT INTO tb_user(nama,email,nomerhp,user_password) VALUES('$nama','$email','$nomerhp','$user_password')";
    $result = mysqli_query($con, $insertQuery);

    if ($result) {

        $response['error'] = "000";
        $response['message'] = "Register successful!";
    } else {
        $response['error'] = "001";
        $response['message'] = "Registeration failed!";
    }
}


echo json_encode($response);
?>