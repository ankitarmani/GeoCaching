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
 $idtour= $_POST['idtour'];
 mysql_connect("localhost","root","");
 mysql_select_db("geoquestdb");

// get all tours from tours table
$result = mysql_query("SELECT * FROM tour where idtour='5'") or die(mysql_error());
$fullpath="C:\\\\Users\\\\Beatriz\\\\Desktop\\\\testing\\\\b-it_2012_s_t2\\\\GeoCaching\\\\web\\\\uploads\\\\";
$length = strlen($fullpath);

// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // tours node
    $response["tour"] = array();
    
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $tour = array();
        $tour["idtour"] = $row["idtour"];
        $tour["name"] = $row["name"];
        $tour["description"] = $row["description"];
	$tour["difficulty"] = $row["difficulty"];
	$tour["badge"] = substr($row["badgeURL"],$length);
	$tour["distance"] = $row["distance"];

        // push single tour into final response array
        array_push($response["tour"], $tour);
    }
    // success
    $response["success"] = 1;

    // echoing JSON response
    echo json_encode($response);
} else {
    // no tours found
    $response["success"] = 0;
    $response["message"] = "No tours found";

    // echo no users JSON
    echo json_encode($response);
}
?>
