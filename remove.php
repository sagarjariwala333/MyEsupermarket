<?php
    require('connection.php');
    $product_id=$_POST['product_id'];
    $user_id=$_POST['user_id'];
    $query=mysqli_query($conn,"delete from cart_master where product_id=$product_id and user_id=$user_id");
    if($query)
    {
        $success=1;
        $msg="Product deleted";
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