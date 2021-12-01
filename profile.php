<?php
    require("connection.php");
    $user_id = $_POST['user_id'];
    $role=$_POST['role'];
    $result = mysqli_query($conn,"SELECT * FROM `user_master` WHERE `role` = '$role' and `user_id` = $user_id");
    if (mysqli_num_rows($result)>0) 
    {
        $row=mysqli_fetch_array($result); 
            $first_name=$row['first_name'];
            $last_name=$row['last_name'];
            $gender=$row['gender'];
            $email=$row['email'];
            $mobile_no=$row['mobile_no'];
            $id_photo=$row['id_photo'];
            $success=1;
    }
   else
   {
            $first_name="";
            $last_name="";
            $gender="";
            $email="";
            $mobile_no="";
            $id_photo="";
            $success=0;
    }
    $newarray = 
    array
    (
        'first_name'=>$first_name,
        'last_name'=>$last_name,
        'gender'=>$gender,
        'email'=>$email,
        'id_photo'=>$id_photo,
        'mobile_no'=>$mobile_no,
        'success'=>$success
    );
    header('Content-Type: application/json');
    echo json_encode($newarray);
?>