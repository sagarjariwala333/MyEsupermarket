<?php
	require('connection.php');
	 $newarray['subarr']=array();
	$user_id=$_POST['user_id'];
	$sql=
	"SELECT product_master.product_img,
	cart_master.product_id,
	product_master.product_name,
	product_master.product_price,
	cart_master.product_quantity
	FROM product_master INNER JOIN cart_master
	ON cart_master.product_id=product_master.product_id AND cart_master.user_id=$user_id";
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
            array_push($newarray['subarr'], $arr);
		}
		# code...
	}
	else
    {
    	$newarray=null;
    }
    //$newarray=array('Sub_Array'=>$subarr);
    header('Content-Type: application/json');
    echo json_encode($newarray);
?>