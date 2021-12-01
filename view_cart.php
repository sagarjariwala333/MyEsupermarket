<?php
		require('connection.php');
		$subarr=array();
		$subarr['subarray'] = array();
		$user_id=$_POST['user_id'];
		$sql=
		"SELECT product_master.product_id,
		product_master.product_name,
		product_master.product_img,
		product_master.product_type,
		product_master.product_price,
		cart_master.price,
		cart_master.product_quantity
		FROM product_master INNER JOIN cart_master
		ON product_master.product_id=cart_master.product_id
		AND cart_master.user_id=$user_id
		order by cart_master.cart_id desc";
		$result=mysqli_query($conn,$sql);
		if (mysqli_num_rows($result)>0) 
		{
			# code...
			while($row=mysqli_fetch_array($result))
			{
				$arr = array();
				$arr['product_id']=$row['product_id'];
				$arr['product_name']=$row['product_name'];
				$arr['product_img']=$row['product_img'];
				$arr['product_type']=$row['product_type'];
				$arr['product_price']=$row['price'];
				$arr['one_price']=$row['product_price'];
				$arr['product_quantity']=$row['product_quantity'];
				array_push($subarr['subarray'], $arr);
			}
		}
		else
		{
			$subarr="";
		}
		$newarray=array('Sub_Array'=>$subarr);
    	header('Content-Type: application/json');
    	echo json_encode($subarr);
?>