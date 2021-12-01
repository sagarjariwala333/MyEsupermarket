<?php
	require('connection.php');
	$product_id=$_POST['product_id'];
	$sql="SELECT * FROM product_master WHERE product_id=$product_id";
	$result=mysqli_query($conn,$sql);
	if (mysqli_num_rows($result)==1) 
	{
		$success=1;
		$message="Data selected";
		$row=mysqli_fetch_array($result);
		$product_name=$row['product_name'];
		$product_img=$row['product_img'];
		$product_quantity=$row['product_quantity'];
		$product_type=$row['product_type'];	
		$product_price=$row['product_price'];
	}
	else
	{
		$success=0;
		$message="Data not selected";
		$product_name="";
		$product_img="";
		$product_quantity="";
		$product_type="";
		$product_price="";
	}
	$newarray=
	array(
		"success"=>$success,
		"message"=>$message,
		"product_name"=>$product_name,
		"product_img"=>$product_img,
		"product_price"=>$product_price,
		"product_type"=>$product_type,
		"product_quantity"=>$product_quantity
	);
	header('Content-Type: application/json');
    echo json_encode($newarray);
?>