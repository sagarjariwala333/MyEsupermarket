<?php
	require('connection.php');
	$product_id=$_POST['product_id'];
	$product_img=$_POST['product_img'];
	$product_price=$_POST['product_price'];
	$product_name=$_POST['product_name'];
	$sql="UPDATE `product_master` SET `product_name`='$product_name',`product_img`='$product_img',`product_price`='$product_price' WHERE product_id=$product_id";
	$result=mysqli_query($conn,$sql);
	if ($result) 
	{
		$success=1;
		$msg="Updated successfully";
		# code...
	}
	else
	{
		$success=0;
		$msg="Not updated successfully";
	}
	$newarray=array('Success'=>$success,'Message'=>$msg);
    header('Content-Type: application/json');
    echo json_encode($newarray);
?>