<?php
	require('connection.php');
	$subarr=array();
    $subarr['subarray'] = array();
	$user_id=$_POST['user_id'];
	$order_id=$_POST['order_id'];
	$sql=
	"SELECT product_master.product_img,
	product_master.product_id,
	product_master.product_name,
	product_master.product_price,
	old_orders_details.product_quantity
	FROM product_master INNER JOIN old_orders_details INNER JOIN old_orders
	WHERE old_orders_details.user_id=$user_id
	AND old_orders.order_id=$order_id 
    AND old_orders_details.product_id=product_master.product_id 
	AND old_orders.order_id=old_orders_details.order_id";

	$query=mysqli_query($conn,$sql);
	if (mysqli_num_rows($query)>0) 
	{
		while ($row=mysqli_fetch_array($query)) 
		{
			# code...
			$arr = array();
            $arr['user_id'] = $user_id;
            $arr['product_id'] = $row['product_id'];
            $arr['product_name'] = $row['product_name'];
            $arr['product_price'] = $row['product_price'];
            $arr['product_quantity'] = $row['product_quantity'];
            $arr['product_img'] = $row['product_img'];
            array_push($subarr['subarray'], $arr);
		}
		# code...
	}
	else
    {
    	$subarray=null;
    }
    header('Content-Type: application/json');
    echo json_encode($subarr);
?>