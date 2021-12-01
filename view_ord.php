<?php
    require('connection.php');
    $user_id=$_POST['user_id'];
    $query=mysqli_query($conn,"select * from order_details where user_id=$user_id");
    if(mysqli_num_rows($query)>0)
    {
        while($row=mysqli_fetch_array($query))
        {
            $product_id1=$row['product_id'];
            $product_quantity1=$row['product_quantity'];
            $query1=mysqli_query($conn,"select * from product_master where product_id=$product_id1");
            $row=mysqli_fetch_array($query1);
            $product_name1=$row['product_name'];
            $product_type1=$row['product_type'];
            $product_img1=$row['product_img'];
            $newarray=array('product_id'=>$product_id1,'product_name'=>$product_name1,'product_img'=>$product_img1,'product_type'=>$product_type1,'product_quantity'=>$product_quantity1);
            header('Content-Type: application/json');
            echo json_encode($newarray);
        }
    }

?>