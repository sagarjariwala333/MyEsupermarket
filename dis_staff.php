<?php
	require('connection.php');
	$newarray['subarr']=array();
	$query=mysqli_query($conn,"SELECT `user_id`,`id_photo`, `first_name`,`mobile_no` FROM `user_master` WHERE role='S'");
	if (mysqli_num_rows($query)>0) 
	{
		while ($row=mysqli_fetch_array($query)) 
		{
			# code...
			$arr = array();
            $arr['user_id'] = $row['user_id'];
            $arr['id_photo'] = $row['id_photo'];
            $arr['first_name'] = $row['first_name'];
            $arr['mobile_no'] = $row['mobile_no'];
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