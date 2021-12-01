<?php
	require("connection.php");
	$user_id=$_POST['user_id'];
	$order_id=$_POST['order_id'];
	$sql="UPDATE `order_master` SET `flag`=1 WHERE user_id=$user_id AND order_id=$order_id";
	$result=mysqli_query($conn,$sql);
	if ($result) 
	{
		$success=1;
		$msg="Flag updated";
	}
	else
	{
		$success=0;
		$msg="Flag not updated";
	}
	$newarray=array("success"=>$success,"meassage"=>$msg);
	header('Content-Type: application/json');
    echo json_encode($newarray);
?>