<?php
	require('connection.php');
	$subarr=array();
    $subarr['subarray'] = array();
	$user_id=$_POST['user_id'];
	$sql=
	"SELECT old_orders.user_id,
	old_orders.order_id,
	old_orders.date_oldorders,
	payment_details.amount
	FROM old_orders INNER JOIN payment_details
	ON old_orders.user_id=payment_details.user_id
	AND old_orders.order_id=payment_details.order_id
	AND old_orders.user_id=$user_id
	order by old_orders.old_orderid desc";
	$query=mysqli_query($conn,$sql);
	if (mysqli_num_rows($query)>0) 
	{
		while ($row=mysqli_fetch_array($query)) 
		{
			$arr = array();
			$arr['old_orderid'] = $row['amount'];
            $arr['order_id'] = $row['order_id'];
            $arr['date_oldorders'] = $row['date_oldorders'];
            //date('d/m/Y',$row['date_oldorders']);
            array_push($subarr['subarray'], $arr);
		}
	}
	else
    {
    	$subarray=null;
    }
    header('Content-Type: application/json');
    echo json_encode($subarr);
?>