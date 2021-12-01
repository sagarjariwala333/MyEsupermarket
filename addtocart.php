<?php
    require('connection.php');
    $user_id=$_POST['user_id'];
    $product_id=(int)$_POST['product_id'];
    $product_quantity=$_POST['product_quantity'];
    $price=$_POST['price'];
    $sql="select * from cart_master where user_id=$user_id AND product_id=$product_id";
    $result=mysqli_query($conn,$sql);
    echo mysqli_num_rows($result);
    if (mysqli_num_rows($result)>0) 
    {
        $success=2;
        # code...
    }
    else
    {
        $query=mysqli_query($conn,"insert into cart_master(user_id,product_id,product_quantity,price)values('$user_id',$product_id,$product_quantity,$price)");
        if($query)
        {
            $success=1;
        }
        else
        {
            $success=0;
        }
    }
    $newarray=array('Success'=>$success);
    header('Content-Type: application/json');
    echo json_encode($newarray);
?>