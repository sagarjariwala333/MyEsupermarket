<?php
	require('connection.php');
	//$path=$_POST['imgurl'];
	$image_name = $_POST['image_name'];
    $image = $_POST['image'];


    $path = "file:///C:/xampp/htdocs/Admin/Esupermarket/Images/$image_name.png";
    //$path = 'C:\xampp\htdocs\Admin\Esupermarket\Images/';
	$sql = "insert into temp_img(imgurl,imgname) values('$path','$image_name');";

	if(mysqli_query($conn,$sql))
	{
		echo "Done";
		//move_uploaded_file($_FILES["File"]["tmp_name"],$_SERVER["DOCUMENT_ROOT"].'/uploads/images/1.jpg')
		file_put_contents($path,base64_decode($image));
		echo json_encode
		(
			array
			(
				'success'=>1,
				'response'=>"Successfully Uploaded..."
			)
		);
	}
	else
	{
		echo json_encode
		(
			array
			(
				'success'=>0,
				'response'=>"Failed..."
			)
		);
	}
?>