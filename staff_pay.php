<?php
    require('connection.php');
    $user_id=$_POST['user_id'];
    $order_id=$_POST['order_id'];
    $query=mysqli_query($conn,"select * from payment_details where user_id=$user_id AND order_id=$order_id");
    //echo mysqli_num_rows($query);die; 
    if(mysqli_num_rows($query)==1)
    {
       //$fetch_pay=1;
       $row=mysqli_fetch_array($query);
       $payment_cust1=$row['payment_cust'];
       //$payment_staff1=$row['payment_staff'];
       if($payment_cust1==1)
       {
            $query=mysqli_query($conn,"UPDATE `payment_details` SET `payment_staff`=1 WHERE user_id=$user_id");
            if($query)
            {
               // echo "$user_id+$order_id";die;
                //echo "INSERT INTO `history_master`(`user_id`,`order_id`) VALUES ($user_id,$order_id)"; die;
                $query1=mysqli_query($conn,"INSERT INTO `history_master`(`user_id`,`order_id`) VALUES ($user_id,$order_id)");
                //echo mysqli_error();
                if($query1)
                {
                    $success=1;
                    /*$his_insert=1;
                    //delete from order_details
                    //$query=mysqli_query($conn,"DELETE FROM `order_details` WHERE user_id=$user_id");
                    $query=mysqli_query($conn,"DELETE FROM `order_master` WHERE user_id=$user_id");
                    if($query)
                    {
                        //$del_orddetails=1;
                        $del_ordmaster=1;
                        //Delete from order_master
                        //$query=mysqli_query($conn,"DELETE FROM `order_master` WHERE user_id=$user_id");
                        $query=mysqli_query($conn,"DELETE FROM `order_details` WHERE user_id=$user_id");
                        if($query)
                        {
                            //$del_ordmaster=1;
                            $del_orddetails=1;
                            //delete from cart
                            $query=mysqli_query($conn,"DELETE FROM `cart_master` WHERE user_id=$user_id");

                            if($query)
                            {
                                $del_cart=1;
                            }
                            else
                            {
                                $del_cart=0;
                            }
                        }
                        else
                        {
                            $del_orddetails=0;
                            $del_cart=0;
                        }*/

                    /*}
                    else
                    {
                        $del_orddetails=0;
                        $del_cart=0;
                        $del_ordmaster=0;
                    }*/
                }
                else
                {
                    $success=0;
                }
            }
            else
            {
                $success=0;
            }                      
        }
        else
        {
            $success=2;
        }
    }
    else
    {
       $success=0;
    }
    $newarray=array("success"=>$success);
    header("Content-Type: application/json");
    echo json_encode($newarray);
?>