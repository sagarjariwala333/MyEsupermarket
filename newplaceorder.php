<?php
	require("connection.php");
	$user_id=$_POST['user_id'];
	$order_id=$_POST['order_id'];
	$payment_type=$_POST['payment_type'];
	$payment_method=$_POST['payment_method'];
	$amount=0;
	$sql="INSERT INTO `payment_details`(`user_id`, `order_id`, `payment_type`, `payment_method`, `amount`, `payment_cust`, `payment_staff`) VALUES ($user_id,$order_id,'Cash','Offline',$amount,0,0)";
	$result=mysqli_query($conn,$sql);
	if ($result) 
	{
		$success=1;
		$meassage="Paymnet inserted";
# code...
		$sql="INSERT INTO `order_master`(`user_id`) VALUES ($user_id)";
		$result1=mysqli_query($conn,$sql);
		if ($result1)
		{
			$
			# code...
		}
	}
	else
	{
		$success=0;
		$meassage="Paymnet not inserted";
	}
	$newarray=
	array
	(
		"success"=>$success,
		"meassage"=>$meassage
	);
	header('Content-Type: application/json');
    echo json_encode($newarray);
?>

