<?php
    require('connection.php');
	$subarr=array();
	$subarr['subarray'] = array();
    $user_id=$_POST['user_id'];
	$order_id=$_POST['order_id'];

    $sql="SELECT order_details.user_id, 
		order_details.product_id,
		order_details.product_quantity,
		product_master.product_name,
		product_master.product_type,
		product_master.product_img
		FROM product_master INNER JOIN order_details
		WHERE order_details.product_id=product_master.product_id 
		AND order_details.user_id=$user_id
		AND order_details.order_id=$order_id
		order by order_details.order_details_id desc";
    	
    	$query=mysqli_query($conn,$sql);

    if(mysqli_num_rows($query)>0)
    {
		  while($row=mysqli_fetch_array($query))
	      {
	            $arr = array();
	            $arr['user_id'] = $user_id;
	            $arr['product_id'] = $row['product_id'];
	            $arr['product_name'] = $row['product_name'];
	            $arr['product_type'] = $row['product_type'];
	            $arr['product_img'] = $row['product_img'];
	            $arr['product_quantity'] = $row['product_quantity'];
	            array_push($subarr['subarray'], $arr);
	      }
    }
    else
    {
    	$subarr=null;
    }
    //$newarray=array('Sub_Array'=>$subarr);
    header('Content-Type: application/json');
    echo json_encode($subarr);
?>