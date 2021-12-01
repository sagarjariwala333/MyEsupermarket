<?php
    require("connection.php");
    $product_name = $_POST['product_name'];
    $product_img = $_POST['product_img'];
    $product_quantity = $_POST['product_quantity'];
    $product_type = $_POST['product_type'];
    $product_price = $_POST['product_price'];
   /* $filename = $_POST['product_img']['name'];
    $filetmpname = $_FILES['product_img']['tmp_name'];
    //folder where images will be uploaded
    $folder = 'C:\xampp\htdocs\Admin\Esupermarket\Images/';
    //function for saving the uploaded images in a specific folder
    move_uploaded_file($filetmpname, $folder.$filename);*/
    //inserting image details (ie image name) in the database
//    $sql = “INSERT INTO `uploadedimage` (`imagename`) VALUES (‘$filename’)”;
    $result = mysqli_query($conn,"INSERT INTO `product_master`(`product_name`, `product_img`, `product_quantity`, `product_type`, `product_price`) VALUES ('$product_name','$product_img','$product_quantity','$product_type','$product_price')"); 
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
   
?>