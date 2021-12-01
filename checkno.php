<?php
    require("connection.php");
    $mobile_no = $_POST['mobile_no'];
    $result = mysqli_query($conn,"SELECT `mobile_no` FROM `user_master` WHERE `mobile_no`=$mobile_no");
    $row = mysqli_fetch_assoc($result);
    if($row > 0)
    {
        $message = "Mobile number already exits";
        $success = 0;
    }
    else
    {
        $message = "Mobile number not exits";
        $success = 1;
    }
    $newarray = array(
        "meassage" => $message,
        "success" => $success
    );
    header("Content-Type: application/json");
    echo json_encode($newarray);
?>