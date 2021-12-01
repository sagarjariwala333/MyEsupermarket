<?php
    require("connection.php");
    $user_id = $_POST['user_id'];
    $role=$_POST['role'];
    $result = mysqli_query($conn,"SELECT * FROM `user_master` WHERE `role` = $role and `user_id` = '$user_id'");
    if($result)
    {
        $message = "Data show successfully";
        $success = 1;
    }
    else
    {
        $message = "Data not show successfully";
        $success = 0;
    }
    $newarray = array(
        "message" => $message,
        "success" => $success
    );
    header('Content-Type: application/json');
    echo json_encode($newarray);
?>