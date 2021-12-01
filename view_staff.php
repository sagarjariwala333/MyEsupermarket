<?php
	require('connection.php');
	$subarr=array();
    $subarr['subarray'] = array();
	$sql="SELECT * FROM `user_master` WHERE role='S' order by user_id desc";
	$result=mysqli_query($conn,$sql);
	if (mysqli_num_rows($result) > -1) 
	{
		while($row=mysqli_fetch_array($result))
		{
			$arr = array();
			$arr['id_photo']=$row['id_photo'];
			$arr['user_id']=$row['user_id'];
			$arr['mobile_no']=$row['mobile_no'];
			$arr['first_name']=$row['first_name']; 
			array_push($subarr['subarray'], $arr);  
		}
		# code...
	}
	else
	{
		$subarr="";
	}
	$newarray=array('Sub_Array'=>$subarr);
    header('Content-Type: application/json');
    echo json_encode($subarr);
?>