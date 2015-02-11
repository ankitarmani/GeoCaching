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
 $email = $_POST['email'];
 $password = $_POST['password'];
 mysql_connect("localhost","root","");
 mysql_select_db("geoquestdb");

// get all tours from tours table
$result = mysql_query("SELECT * FROM geouser where username='$email'") or die(mysql_error());

// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // tours node
    $response["user"] = array();
    
    while ($row = mysql_fetch_array($result)) {
    
	$test = $row['password'];
	if ($test==$password){
        // temp user array
        $tour = array();
        $tour["iduser"] = $row["idUser"];
        $tour["username"] = $row["username"];
        $tour["password"] = $row["password"];
        // push single tour into final response array
        array_push($response["user"], $tour);
	 // success
	$response["success"] = 1;

	// echoing JSON response
	echo json_encode($response);
	
	}
	else if ($test!=$password){
	    // no tours found
	    $responses["success1"] = 0;
	    $responses["message"] = "No user found";

	    // echo no users JSON
	    echo json_encode($responses);
	}
    }
   
} else {
    // no tours found
    $response["success"] = 0;
    $response["message"] = "No user found";

    // echo no users JSON
    echo json_encode($response);
}
?>
