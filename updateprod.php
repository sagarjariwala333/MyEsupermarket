<?php
    require("connection.php");
    $product_id = $_POST['product_id'];
    $product_name = $_POST['product_name'];
    $product_img = $_POST['product_img'];
   $product_price = $_POST['product_price'];
    $product_type = $_POST['product_type'];
    $result = mysqli_query($conn,"UPDATE `product_master` SET `product_name`='$product_name',`product_img`='$product_img',`product_type`='$product_type',`product_price`='$product_price' WHERE `product_id` = $product_id");
    if($result)
    {
        $message = "Data updated successfully";
        $success = 1;
    }
    else
    {
        $message = "Data not updated successfully";
        $success = 0;
    }
    $newarray = array(
        "message" => $message,
        "success" =>$success
    );
    header('Content-Type: application/json');
    echo json_encode($newarray);
?>