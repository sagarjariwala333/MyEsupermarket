<?php
	require('connection.php');
	$user_id=$_POST['user_id'];
	$query=mysqli_query($conn,"DELETE FROM `user_master` WHERE role='S' AND user_id=$user_id");
	if ($query) 
	{
		$msg="Data deleted";
		$success=1;
	}
	else
	{
		$success=0;
		$msg="Data not deleted";
	}
	$newarray = array(
        "message" => $msg,
        "success" => $success
    );
    header('Content-Type: application/json');
    echo json_encode($newarray);
?>