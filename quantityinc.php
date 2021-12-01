<?php
    require('connection.php');
    
    $product_id=$_POST['product_id'];
    $product_quantity=$_POST['product_quantity'];

    $query=mysqli_query($conn,"select product_quantity from product_master where product_id=$product_id");
    if(mysqli_num_rows($query)==1)
    {
        $row=mysqli_fetch_array($query);
        $product_quantity1=$row['product_quantity'];
        if($product_quantity<$product_quantity1)
        {
            $success=1;
            $message="Product Incremented";
        }
        else
        {
            $success=0;
            $message="Product Shortage";
        }
    }
    else
    {
        $success=0;
        $message="";
    }
    $newarray=array('Success'=>$success,'message'=>$message);
    header('Content-Type: application/json');
    echo json_encode($newarray);
?>