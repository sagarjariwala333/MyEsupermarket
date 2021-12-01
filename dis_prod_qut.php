<?php
	require('connection.php');
	$newarray['subarr']=array();
	$query=mysqli_query($conn,"SELECT `product_id`, `product_name`, `product_img`, `product_price`,`product_quantity` FROM `product_master`");
	if (mysqli_num_rows($query)>0) 
	{
		while ($row=mysqli_fetch_array($query)) 
		{
			# code...
			$arr = array();
            $arr['product_id'] = $row['product_id'];
            $arr['product_name'] = $row['product_name'];
            $arr['product_price'] = $row['product_price'];
            $arr['product_quantity']=$row['product_quantity'];
            $arr['product_img'] = $row['product_img'];
//            $arr['product_quantity'] = $row['product_quantity'];
  //          $arr['product_img'] = $row['product_img'];
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