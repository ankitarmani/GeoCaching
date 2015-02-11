<?php

/*
 * Following code will list all the tours
 */

// array for JSON response
//$response = array();


// include db connect class
//require_once __DIR__ . '/db_connect.php';

// connecting to db
//$db = new DB_CONNECT();
 $iduser = $_POST['iduser'];
 $idtour = $_POST['idtour'];
 $iddate = $_POST['iddate'];
 //$targetDate = date('Y-m-d H:i:s',strtotime($iddate));
 $targetDate = date('Y-m-d',strtotime($iddate));
 
  mysql_connect("localhost","root","");
  mysql_select_db("geoquestdb");
  
  $result= mysql_query("SELECT lastCompletionDate FROM geouser_completed_tour where GeoUser_idUser='$iduser' and tour_idtour='$idtour'");
  
  $num_rows = mysql_num_rows($result);
  //echo $row["lastCompletionDate"] ;
  //echo date('Y-m-d H:i:s', strtotime($row["lastCompletionDate"]));
  
  
  //if (mysql_num_rows($result) > 0){
	//$row = mysql_fetch_array($result); 
	//$dbdate=date('Y-m-d H:i:s', strtotime($row["lastCompletionDate"]));
	  //if ($targetDate  > $dbdate) {
	    //  mysql_query("Update geouser_completed_tour set lastCompletionDate='$targetDate' where GeoUser_idUser='$iduser' and tour_idtour='$idtour'")
	      //$response["success"] = 1;

	    // echo no users JSON
	    //echo json_encode($response);
	  //} 
	  //else
	  //{
	    // $response["success"] = 1;

	    // echo no users JSON
	    //echo json_encode($response);
	  //}
	//}
  //else {
  
  if (mysql_num_rows($result) < 1){
	     mysql_query("INSERT INTO geouser_completed_tour (GeoUser_idUser, tour_idtour, lastCompletionDate)
			VALUES ('$_POST[iduser]','$_POST[idtour]','$targetDate')");
			$response["success"] = 1;

	    // echo no users JSON
	    echo json_encode($response);}
	    
 else{
	$row = mysql_fetch_array($result); 
	$dbdate=date('Y-m-d', strtotime($row["lastCompletionDate"]));
	if ($targetDate  > $dbdate) {
		mysql_query("Update geouser_completed_tour set lastCompletionDate='$targetDate' where GeoUser_idUser='$iduser' and tour_idtour='$idtour'");
		$response["success"] = 1;
		echo json_encode($response);
	}
	
 }
	// }

// get all tours from tours table
//$result = mysql_query("SELECT * FROM geouser where username='$email'") or die(mysql_error());



	    // no tours found
	   // $response["success"] = 1;

	    // echo no users JSON
	    //echo json_encode($response);

?>
