t<?php
	require('connection.php');
	$product_id=$_POST['product_id'];
	$qut=$_POST['qut'];
	$result = mysqli_query($conn,"UPDATE `product_master` SET `product_quantity`= product_quantity-$qut WHERE product_id=$product_id");
    if ($result) 
    {
        $msg="Stock removed";
        $success=1;
        # code...
    }
    else
    {
       $msg="";
       $success=0;
    }
     $newarray = array(
        "message" => $msg,
        "success" => $success
    );
    header('Content-Type: application/json');
    echo json_encode($newarray);
?>