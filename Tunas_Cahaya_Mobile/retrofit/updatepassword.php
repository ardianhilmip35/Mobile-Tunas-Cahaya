<?php

require 'connection.php';
$email = $_POST['email'];
$current = $_POST['current'];
$new = $_POST['new'];


$checkuser = "SELECT * FROM tb_user WHERE email='$email' and user_password='$current'";

$result = mysqli_query($con, $checkuser);


if (mysqli_num_rows($result) > 0) {

	$updatePass = mysqli_query($con, "UPDATE tb_user SET user_password='$new' WHERE email='$email'");

	if ($updatePass > 0) {

		$response['error'] = "200";
		$response['message'] = "password update success";
	} else {
		$response['error'] = "400";
		$response['message'] = "password not updated";
	}
} else {
	$response['error'] = "400";
	$response['message'] = "User does not exist";
}

echo json_encode($response);
