<?php
    require("connection.php");
    $password = $_POST['password'];
    $user_id = (int)$_POST['user_id'];
//    $mobile_no = $_POST['mobile_no'];
//    $role = $_POST['role'];

    $query = mysqli_query($conn,"select * from user_master where user_id=$user_id");
    if(mysqli_num_rows($query) == 1)
    {
        $row = mysqli_fetch_array($query);
        $password1 = $row['password'];
        if($password1==$password){
            $message="Login Successfully";
            $success=1;
        }
        else
        {
            $message="Invalid Password!";
            $success=0;
        }
    }
    else
    {
        $message="";
        $success=0;
    }
    
    $newarray=
    array
    (
        'success'=>$success,
        'message'=>$message,
    );
    header('Content-Type: application/json');
    echo json_encode($newarray);
?>