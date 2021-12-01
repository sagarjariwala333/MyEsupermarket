<?php
    require('connection.php');
    $user_id=$_POST['user_id'];
    $product_id=$_POST['product_id'];
    $product_quantity=$_POST['product_quantity'];
    $query=mysqli_query($conn,"update cart_master set product_quantity=$product_quantity where user_id=$user_id AND product_id=$product_id");
    if($query)
    {
        $success_update=1;
    }
    else
    {
        $success_update=0;
    }
    $newarray=array('success_update'=>$success_update);
    header('Content-Type: application/json');
    echo json_encode($newarray);
?>