<?php
    require("connection.php");
    $first_name = $_POST['first_name'];
    $last_name = $_POST['last_name'];
    $email = $_POST['email'];
    $id_photo = $_POST['id_photo'];
    $password = $_POST['password'];
    $gender = $_POST['gender'];
    $role = 'S';
    $mobile_no = $_POST['mobile_no'];
    $result = mysqli_query($conn,"INSERT INTO `user_master`(`id_photo`, `first_name`, `last_name`, `gender`, `email`, `mobile_no`, `password`, `role`) VALUES ('$id_photo','$first_name','$last_name','$gender','$email','$mobile_no','$password','$role')");
    if($result)
    {
        $message = "Data inserted successfully";
        $success = 1;
    
    }
    else
    {
        $message = "Data not inserted successfully";
        $success = 0;
    }
    $newarray = array(
        "meassage" => $message,
        "success" => $success
    );
    header("Content-Type: application/json");
    echo json_encode($newarray);
?>
