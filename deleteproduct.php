<?php
    require("connection.php");
    $product_id = $_POST['product_id'];
    $flag=$_POST['flag'];
    $qut=$_POST['qut'];

    if ($flag==1) 
    {
        # code...
        $result = mysqli_query($conn,"UPDATE `product_master` SET `product_quantity`= product_quantity+$qut WHERE product_id=$product_id");
        if ($result) 
        {
            $msg="Stock added";
            $success=1;
            # code...
        }
        else
        {
            $msg="";
            $success=0;
        }
    }
    elseif ($flag==0) 
    {
        # code...
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
    }
    else
    {
        $msg="";
        $success=0;
    }
    $newarray = array(
        "message" => $message,
        "success" => $success
    );
    header('Content-Type: application/json');
    echo json_encode($newarray);
?>