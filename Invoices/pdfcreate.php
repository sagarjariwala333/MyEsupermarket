<?php 
require("connection.php");
require("mpdf/vendor/autoload.php");
$user_id = $_POST['user_id'];
$order_id = $_POST['order_id'];
$html = "";
$html .= '<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 2px solid #211c1c;
    text-align: left;
    padding: 8px;
}

.box {
  float: left;
}

#page-border {
  width: 100%;
  height: 99%;
  border:4px double black;
}

.userinfo {
  margin:4px;
}

.ema {
  border:1px solid gray;
  border-radius: 4px;
  float: right;
  width:250px;
}

th {
  background-color: #aed6d8;
}

tr {
  background-color: #ddeeee;
}
</style>';
// echo "SELECT * FROM `user_master` WHERE `user_id`='$user_id'";
// die;
$query1 = mysqli_query($conn,"SELECT * FROM `user_master` WHERE `user_id`='$user_id'");
$row1 = mysqli_fetch_assoc($query1);
$query = mysqli_query($conn,"SELECT `payment_date` FROM `payment_details` WHERE `user_id`='$user_id' and order_id='$order_id'");
//echo "SELECT `payment_date` FROM `payment_details` WHERE `user_id`='$user_id' and order_id='order_id'";
//die;
$row = mysqli_fetch_assoc($query);

$html .= '<div id="page-border" >
<div style="margin:10px;">
<div style="font-size:1.20em; position: absolute; top:0; right:0; text-align:right;"> Date : '.$row['payment_date'].'</div>
<img src="Invoices/pdf2.jpg" style="align: top" width="130px" height="130px"/>';

$html .= '<div class="box">
  <div class="userinfo"> User Id &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;&nbsp; '.$row1['user_id'].'</div>
  <div class="userinfo"> Name &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;&nbsp; '.$row1['first_name'].' '.$row1['last_name'].'</div>
  <div class="userinfo"> Gender &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;&nbsp;'.$row1['gender'].'</div>
  <div class="userinfo"> E-mail &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;&nbsp;'.$row1['email'].'</div>
  <div class="userinfo"> Mobile no &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;&nbsp;'.$row1['mobile_no'].'</div>
  </div> <br>';
  
$row = mysqli_query($conn,"SELECT old_orders_details.order_id,
old_orders_details.user_id,
old_orders_details.product_id,
old_orders_details.product_quantity,
product_master.product_name,
product_master.product_price
FROM old_orders_details INNER JOIN product_master
ON product_master.product_id=old_orders_details.product_id
WHERE old_orders_details.user_id=$user_id
AND old_orders_details.order_id=$order_id");
// <table width="100%" style="border: 2px dotted black;" cellpadding="6" cellspacing="3">
$html .= '<table>
<div class="ordertb">
<tr> <th> Name </th> <th> Unit </th> <th> Unit price </th> <th> Total </th> </tr>';
// echo mysqli_num_rows($query2);
// die;
$total = 0;
if(mysqli_num_rows($row)>0)
{
  while($row2 = mysqli_fetch_assoc($row))
  {
    $html .= '<tr> <td>'.$row2['product_name'].'</td> <td style="text-align:center">'.$row2['product_quantity'].'</td> <td style="text-align:right">'.$row2['product_price'].'</td> <td style="text-align:right">'.($row2['product_quantity'] * $row2['product_price']).'</td> </tr> ';
    $total = $total + $row2['product_quantity'] * $row2['product_price'];
  }

}
$html .= '</div>
</table>
<div class="totalprice" style="font-size: 1.20em; position: absolute; right:0; text-align: right; border: 3px solid black; margin-top: 10px; padding: 10px; background-color: #87c2c5"> Total Price : '.$total.' </div>

</div>
</div>
';

$mpdf = new \Mpdf\Mpdf();
$mpdf = new \Mpdf\Mpdf([
    'format' => 'A5-P',
    'margin_left' => 10,
    'margin_right' =>10,
    'margin_top' => 2,
    'margin_bottom' => 0,
    'margin_header' => 0,
    'margin_footer' => 0,
]);

$mpdf->WriteHTML($html);
$timestamp = time();
$cpath = "Invoices/Al-`$timestamp`.pdf";
$mpdf->Output("$cpath",'F');

$resArr = array("success" => 1, "data" => $cpath);

header("content-type:application/json;charset=utf-8");
echo str_replace("\/", "/", json_encode($resArr));

?>


