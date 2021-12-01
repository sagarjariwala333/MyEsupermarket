<?php
	require('connection.php');
	$subarr=array();
    $subarr['subarray'] = array();
	$sql="SELECT * FROM `product_master` order by product_id desc";
	$result=mysqli_query($conn,$sql);
	if (mysqli_num_rows($result) > -1) 
	{
		while($row=mysqli_fetch_array($result))
		{
			$arr = array();
			$arr['product_img']=$row['product_img'];
			$arr['product_id']=$row['product_id'];
			$arr['product_name']=$row['product_name'];
			$arr['product_price']=$row['product_price'];
			$arr['product_type']=$row['product_type'];
			$arr['product_quantity']=$row['product_quantity'];
			$arr['product_adddate']=$row['product_adddate'];   
			array_push($subarr['subarray'], $arr);
		}
		# code...
	}
	else
	{
		$subarr=null;
	}
	$newarray=array('Sub_Array'=>$subarr);
    header('Content-Type: application/json');
    echo json_encode($subarr);
?>
