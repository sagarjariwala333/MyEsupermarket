<?php
    require('connection.php');
    $message = "";
    $error = "";
    $resArr = array();


    $query=mysqli_query($conn,"select * from order_master");
    if(mysqli_num_rows($query)>0)
    {
        while($row=mysqli_fetch_array($query))
        {
            $user_id1=$row['user_id'];
            $query2=mysqli_query($conn,"select * from user_master where user_id=$user_id1");
            if(mysqli_num_rows($query)>0)
            {
                $row=mysqli_fetch_array($query2);
                $first_name1=$row['first_name'];
                $id_photo1=$row['id_photo'];
                $mobile_no1=$row['mobile_no'];
                $newarray=array('user_id'=>$user_id1,'first_name'=>$first_name1,'id_photo'=>$id_photo1,'mobile_no'=>$mobile_no1);
                header('Content-Type: application/json');
                echo json_encode($newarray);
            }
            else
            {
                $first_name1="";
                $id_photo1="";
                $mobile_no1="";
                $user_id1="";
                $newarray=array('user_id'=>$user_id1,'first_name'=>$first_name1,'id_photo'=>$id_photo1,'mobile_no'=>$mobile_no1);
                header('Content-Type: application/json');
                echo json_encode($newarray);
            }
        }
    }
    else
    {
        $first_name1="";
        $id_photo1="";
        $mobile_no1="";
        $user_id1="";
        $newarray=array('user_id'=>$user_id1,'first_name'=>$first_name1,'id_photo'=>$id_photo1,'mobile_no'=>$mobile_no1);
       
    }

    

    header('Content-Type: application/json');
    echo json_encode($newarray);
?>