<?php
    require('connection.php');
    $product_id=$_POST['product_id'];
    $query=mysqli_query($conn,"select * from product_master where product_id=$product_id");
    if(mysqli_num_rows($query)==1){
        $success=1;
        $row = mysqli_fetch_array($query);
        $product_name1=$row['product_name'];
        $product_price1=$row['product_price'];
        $product_img1=$row['product_img'];
        $message="Product details fetched.";
    }
    else
    {
        $success=0;
        $message="Error fetching data";
        $product_name1="";
        $product_price1="";
        $product_img1="";
    }
    $newarray=
    array
    (
        'Success'=>$success,
        'message'=>$message,
        'product_name'=>$product_name1,
        'product_price'=>$product_price1,
        'product_img'=>$product_img1
    );
    header('Content-Type: application/json');
    echo json_encode($newarray);
?>