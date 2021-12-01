<?php
    require('connection.php');
    $user_id=$_POST['user_id'];
    $product_id=$_POST['product_id'];
    $product_quantity=$_POST['product_quantity'];
    $query=mysqli_query($conn,"select * from product_master where product_id=$product_id");
    if(mysqli_num_rows($query)==1)
    {
        $row=mysqli_fetch_array($query);
        $success_productid=1;
        $product_quantity1=$row['product_quantity'];
        if($product_quantity1>$product_quantity)
        {
            $possible_inc=1;
            $query=mysqli_query($conn,"update cart_master set product_quantity=$product_quantity where user_id=$user_id AND product_id=$product_id");
            if($query)
            {
                $success_update=1;
            }
            else
            {
                $success_update=0;
            }
        }
        else
        {
            $possible_inc=0;
            $success_update="";
        }
    }
    else
    {
        $success_productid=0;
        $possible_inc="";
        $success_update="";
    }
    $newarray=array('success_productid'=>$success_productid,'possible_inc'=>$possible_inc,'success_update'=>$success_update);
    header('Content-Type: application/json');
    echo json_encode($newarray);
?>