<?php
    require("connection.php");
    $user_id = $_POST['user_id'];
    $first_name = $_POST['first_name'];
    $last_name = $_POST['last_name'];
    $email = $_POST['email'];
    $gender = $_POST['gender'];
    $role = $_POST['role'];
      $id_photo = $_POST['id_photo'];
    $mobile_no = $_POST['mobile_no'];
    $result = mysqli_query($conn,"UPDATE `user_master` SET `id_photo`='$id_photo',`first_name`='$first_name',`last_name`='$last_name',`gender`='$gender',`email`='$email',`mobile_no`='$mobile_no' WHERE `user_id`='$user_id' AND `role`='$role'");
    if($result)
    {
        $message = "Data updated successfully";
        $success = 1;
    
    }
    else
    {
        $message = "Data not updated successfully";
        $success = 0;
    }
    $newarray = array(
        "meassage" => $message,
        "success" => $success
    );
    header("Content-Type: application/json");
    echo json_encode($newarray);
?>
