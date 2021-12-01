<?php
    require('connection.php');
    $subarr=array();
    $subarr['subarray'] = array();
    $query=mysqli_query($conn,"select order_id,order_master.user_id,user_master.first_name,user_master.mobile_no,user_master.id_photo from order_master INNER JOIN user_master ON user_master.user_id=order_master.user_id");
    if(mysqli_num_rows($query)>0)
    {
        while($row=mysqli_fetch_array($query))
        {
            $arr = array();
            $arr['order_id'] = $row['order_id'];
            $arr['user_id'] = $row['user_id'];
            $arr['first_name'] = $row['first_name'];
            $arr['mobile_no'] = $row['mobile_no'];
            $arr['id_photo'] = $row['id_photo'];
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