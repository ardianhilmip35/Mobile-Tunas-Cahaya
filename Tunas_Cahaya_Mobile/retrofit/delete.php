<?php


require 'connection.php';
$id_pelanggan = $_POST['id_pelanggan'];

$deleteuser = "DELETE FROM tb_user WHERE id_pelanggan='$id_pelanggan'";
$result = mysqli_query($con, $deleteuser);

if ($result > 0) {

    $response['error'] = 200;
    $response['message'] = "Account has been deleted";
} else {

    $response['error'] = 400;
    $response['message'] = "Account has not been deleted";
}

echo json_encode($response);
?>