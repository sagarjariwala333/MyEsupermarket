<?php
    require("connection.php");
    $user_id = $_POST['user_id'];
   // $role = 'S';
    // echo "DELETE FROM `user_master` WHERE  `user_id` = '$user_id' AND `role` = '$role'";
    // die;
    $result = mysqli_query($conn,"DELETE FROM `user_master` WHERE  `user_id` = $user_id");
    $row = mysqli_fetch_assoc($result);
    // echo "<pre>";
    // print_r($row);
    // die;
    if($row == 0)
    {
        $message = "Staff deleted";
        $success = 1;
    }
    else
    {
        $message = "Staff not deleted";
        $success = 0;
    }
    $newarray = array(
        "message" => $message,
        "success" => $success
    );
    header('Content-Type: application/json');
    echo json_encode($newarray);
?>