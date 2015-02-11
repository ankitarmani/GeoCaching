<?php

  $idtour= $_POST['idtour'];
 
  mysql_connect("localhost","root","");
  mysql_select_db("geoquestdb");
  
  $sql=mysql_query("SELECT idwaypoint, orderPosition, digitPosition, question
FROM waypoint a
INNER JOIN question b ON a.idwaypoint = b.waypoint_idwaypoint
WHERE a.tour_idtour ='$idtour' order by orderPosition");
  $num_rows = mysql_num_rows($sql);
 
 // check for empty result
if (mysql_num_rows($sql) > 0) {
    // looping through all results
    // tours node
   $response["question"] = array();
    
    while ($row = mysql_fetch_array($sql)) {
        // temp user array
        $list = array();
        $list["idwaypoint"] = $row["idwaypoint"];
        $list["orderposition"] = $row["orderPosition"];
        $list["digitposition"] = $row["digitPosition"];
	$list["question"] = $row["question"];
	

        // push single tour into final response array
        array_push($response["question"], $list);
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
