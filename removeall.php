<?php
    require('connection.php');
    $user_id=$_POST['user_id'];
    $query=mysqli_query($conn,"delete from cart_master where user_id=$user_id");
    if($query)
    {
        $success=1;
        $msg="All products deleted";
    }
    else
    {
        $success=0;
        $msg="";
    }
    $newarray=array('Success'=>$success,'msg'=>$msg);
    header('Content-Type: application/json');
    echo json_encode($newarray);
?>