<?php
    require("connection.php");
    $user_id =$_POST['user_id'];
    $query1 = mysqli_query($conn,"UPDATE `payment_details` SET `payment_staff`=1 WHERE `user_id`='$user_id'");
    if($query1)
    {
        $message1 = "successfull updated";
        $success1 = 1;
       // echo "DELETE FROM `order_master` WHERE `user_id`=$user_id";
        //die;
        $query2 = mysqli_query($conn,"DELETE FROM `order_master` WHERE `user_id`='$user_id'");
        if($query2)
        {
            $message2 = "successfully order_master delete";
            $success2 = 1;
            $query3 = mysqli_query($conn,"DELETE FROM `order_details` WHERE `user_id`='$user_id'");
            if($query2)
            {
                $message3 = "successfully order_details deleted";
                $success3 = 1;
                $query4 = mysqli_query($conn,"DELETE FROM `cart_master` WHERE `user_id`='$user_id'");
                if($query4)
                {
                    $message4 = "successfully cart_master deleted";
                    $success4 = 1;
                }
                else
                {
                    $message4="";
                    $success4 = 0;
                
                }
            }
            else
            {
                $message3="";
                $success = 0;
            }
        }
        else
        {
            $message2="";
            $success = 0;
        }
    }
    else
    {
        $message1="";
        $success = 0;
    }

    $newarray = array(
        "message1" => $message1,
        "message2" => $message2,
        "message3" => $message3,
        "message4" => $message4,
        "success1" => $success1,
        "success2" => $success2,
        "success3" => $success3,
        "success4" => $success4
    );

    header("Content-Type: application/json");
    echo json_encode($newarray);
?>