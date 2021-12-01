<?php
	require('connection.php');
	$product_id=$_POST['product_id'];
	$query=mysqli_query($conn,"delete from product_master where product_id=$product_id");
	if ($query) 
	{
		$msg="Data deleted";
		$success=1;
	}
	else
	{
		$msg="Data not deleted";
		$success=0;
	}
	$newarray = array(
        "meassage" => $msg,
        "success" => $success
    );
    header("Content-Type: application/json");
    echo json_encode($newarray);
?>