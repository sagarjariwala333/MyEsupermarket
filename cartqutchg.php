<?php
	require("connection.php");
	$product_id=$_POST['product_id'];
	$user_id=$_POST['user_id'];
	$product_quantity=$_POST['product_quantity'];
	$product_price=$_POST['product_price'];
	$sql="SELECT * FROM `product_master` WHERE product_id=$product_id";
	$result=mysqli_query($conn,$sql);
	if (mysqli_num_rows($result)>0) 
	{
		$row=mysqli_fetch_array($result);
		$product_quantity1=$row['product_quantity'];
		# code...
	}
	else
	{
		$success=0;
		$msg="";
	}
	if ($product_quantity<$product_quantity1) 
	{
		$sql="UPDATE `cart_master` SET `product_quantity`=$product_quantity WHERE user_id=$user_id AND product_id=$product_id";
		$result=mysqli_query($conn,$sql);
		if ($result) 
		{
			$success=1;
			$msg="Quantity updated";
		}
		else
		{
			$success=0;
			$msg="";
		}
		# code...
	}
	else
	{

			$success=2;
			$msg="Less stock";
	}
	$newarray=array('Success'=>$success,'msg'=>$msg);
    header('Content-Type: application/json');
    echo json_encode($newarray);
?>