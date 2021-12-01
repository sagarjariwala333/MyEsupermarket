<?php
    require('connection.php');
    $user_id=$_POST['user_id'];
    $amt=0;
    $query=mysqli_query($conn,"insert into order_master(user_id)values($user_id)");
    if($query)
    {
        $query1=mysqli_query($conn,"select * from order_master order by order_id desc limit 1");
        if(mysqli_num_rows($query1) == 1)
        {
            $row=mysqli_fetch_array($query1);
            $order_id1=$row['order_id'];
            $query2=mysqli_query($conn,"select * from cart_master where user_id=$user_id");
            if(mysqli_num_rows($query2)>0)
            {
                while($row1=mysqli_fetch_array($query2))
                {
                    $product_id1=$row1['product_id'];
                    $product_quantity1=$row1['product_quantity'];
                    $price1=$row1['price'];
                    $amt=$amt+$price1;
                    $query3=mysqli_query($conn,"INSERT INTO `order_details`(`user_id`, `order_id`, `product_id`,`product_quantity`,`price`) VALUES ($user_id,$order_id1,$product_id1,$product_quantity1,$price1)");
                }
                $sql1="INSERT INTO `payment_details`(`user_id`, `order_id`, `payment_type`, `payment_method`, `amount`) VALUES ($user_id,$order_id1,'Cash','Offline',$amt)";
                $query4=mysqli_query($conn,$sql1);
                if($query4)
                {
                    $success=1;
                    $meassage="Paid";
                    $newarray=array(
                      "order_id"=>$order_id1,
                      "success"=>$success,
                      "amt"=>$amt,
                      "meassage"=>$meassage);
                }
                else
                {
                    $newarray="";
                }
            }
            else
            {
                $newarray="";
            }
        }
        else
        {
            $newarray="";
        }
    }
    else
    {
        $newarray="";
    }
    header('Content-Type: application/json');
    echo json_encode($newarray);
?>