<?php

  $idtour= $_POST['idtour'];
 
  mysql_connect("localhost","root","");
  mysql_select_db("geoquestdb");
  
  $sql=mysql_query("select * from waypoint where tour_idtour='$idtour' order by orderPosition");
  $num_rows = mysql_num_rows($sql);
 
 // check for empty result
if (mysql_num_rows($sql) > 0) {
    // looping through all results
    // tours node
   $response["waypoints"] = array();
    
    while ($row = mysql_fetch_array($sql)) {
        // temp user array
        $list = array();
	$list["rows"]="$num_rows";
        $list["idwaypoint"] = $row["idwaypoint"];
        $list["name"] = $row["name"];
        $list["longitude"] = $row["longitude"];
	$list["latitude"] = $row["latitude"];
	$list["orderPosition"] = $row["orderPosition"];
	$list["tour_idtour"] = $row["tour_idtour"];

        // push single tour into final response array
        array_push($response["waypoints"], $list);
    }
    // success
    $response["success"] = 1;

    // echoing JSON response
    echo json_encode($response);
} else {
    // no tours found
    $response["success"] = 0;
    $response["message"] = "No lists found";

    // echo no users JSON
    echo json_encode($response);
}


?>
