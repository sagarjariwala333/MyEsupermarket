<?php
	 require("connection.php");
	 $id_photo = $_FILES['id_photo'];
	 $filename = time()."-". $_FILES['id_photo']['name'];
     $filetmpname = $_FILES['id_photo']['tmp_name'];
     $folder = 'C:\xampp\htdocs\Admin\Esupermarket\Images/';
	// $role = $_POST['role'];
	  move_uploaded_file($filetmpname, $folder.$filename);

	   $newarray = array(
        "image_name" => $filename 
    );
    header('Content-Type: application/json');
    echo json_encode($newarray);
	  //$query1 = mysqli_query($conn,"INSERT INTO `user_master` (`id_photo`) VALUES ('$filename')");
?>