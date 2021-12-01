<?php
    require("connection.php");
    $mobile_no = (int)$_POST['mobile_no'];
    $query = mysqli_query($conn,"select * from user_master where mobile_no=$mobile_no");
    if(mysqli_num_rows($query) == 1)
    {
        $success=1;
        $row = mysqli_fetch_array($query);
        $number1 = $row['mobile_no'];
        $role = $row['role'];
        $user_id = $row['user_id'];
        $message = "Mobile number found";
    }
    else
    {
        $message="Mobile Number Not Found";
        $success=0;
        $role="";
        $user_id="";
    }

    $newarray=array('success'=>$success,'message'=>$message,'role'=>$role,'user_id'=>$user_id);
    header('Content-Type: application/json');
    echo json_encode($newarray);
?>  