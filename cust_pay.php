<?php
    require('connection.php');
    $user_id=$_POST['user_id'];
    $order_id=$_POST['order_id'];
    $query=mysqli_query($conn,"update payment_details set payment_cust=true where user_id=$user_id AND order_id=$order_id");
    if($query)
    {
        $success=1;
    }
    else
    {
        $success=0;
    }
    $newarray = array(
        "success" => $success
    );
    header("Content-Type: application/json");
    echo json_encode($newarray);
?>