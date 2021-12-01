<?php 

include 'config.php';

  $data = array();
  $resArr = array();
  $message = "Fail";
  $status = "0";  
  $error = "";

  extract($_POST);
  
  //data
    $orderlistArr = array();
     $subArr = array();
    
    //orderlist
     $orderlist =  mysqli_query($conn, "select * from order_item LEFT JOIN item ON order_item.item_id = item.item_id where order_item.order_id =".$order_id);

     if($orderlist){
         if(mysqli_num_rows($orderlist)>0){

           $order = mysqli_query($conn, "select * from orders where order_id= ".$order_id);
          $order_data= mysqli_fetch_array($order);
          while($rows = mysqli_fetch_assoc($orderlist)){

         
           
          

         // $total = $total + $price;
          $subArr[] = array(
                "item_id" => $rows['item_id'],
                "item_name" => $rows['item_name'],
             "price" => $rows['price'],
             "qty" => $rows['qty'],
              // "order"=> $orderslistArr,
   
             );
        
          $status = "1";
          $message = "success";
             }
            
         }
}
    
    $resArr = array(
      
    "status"=> $status,
    "date" => $order_data['create_date'],
    "order_id" => $order_data['order_id'],
    "data" => $subArr,
    "message" => $message,
    "error" => $error,
    //"total" => $total
  );

    
header('Content-Type: application/json');
echo json_encode($resArr);

?>
