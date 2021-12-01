<?php
    require('connection.php');
    $first_name = $_POST['first_name'];
    $last_name = $_POST['last_name'];
    $gender = $_POST['gender'];
    $email = $_POST['email'];
    $mobile_no = (int)$_POST['mobile_no'];
    $password = $_POST['password'];
    $id_photo=$_POST['id_photo'];
    //$role = 'C';
    $query = mysqli_query($conn,"insert into user_master(id_photo,first_name,last_name,gender,email,mobile_no,password,role)values('$id_photo','$first_name','$last_name','$gender','$email',$mobile_no,'$password','C')");
    if($query)
    {
        $success=1;
    }
    else
    {
        $success=0;
    }
    $newarray=array('success'=>$success);
    header('Content-Type: application/json');
    echo json_encode($newarray);
?>