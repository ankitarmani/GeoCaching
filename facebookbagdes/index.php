<style type="text/css">

  .photo {
    float: left;
    margin: 0.5em;
    border: 1px solid #ccc;
    padding: 1em;
    font-size: 10px;
  }
  
  .heading {
  font-family: Georgia, "Times New Roman", Times, serif;
  font-size:24px;
  margin-top: 5px; margin-bottom: 0px;
  text-align: center;
  font-weight: normal;
  color: #222;
  }
  
  table{
    border-spacing: 0px;
    border-collapse: collapse;
    width: 750px;
	border-radius: 10px;
	border-style:solid;
	border: 2px inset blue;
  }
  
  th {
    border-radius: 10px;
    text-align: center;
    font-weight: bold;
    padding: 2px;
    border: 2px solid #FFFFFF;
    background: #4a70aa;
    color: #FFFFFF;
  }

  td {
    border-radius: 10px;
    text-align: left;
    padding: 2px;
    border: 2px solid #FFFFFF;
    background: #e3f0f7;
  }
  
</style>

<?php
	// server parameters
	$server = "localhost";
	$login = "root";
	$pass = "root";
	$db = "geoquestdb";

	//Sent Userid
	$userSession = 1;

	//Directory where Bagdes are located 
	$localFolder = "C:/wamp/www/Geo SVN Project/trunk/GeoCaching/web/uploads/";
	$length = strlen($localFolder);

	//Directory where Wampserver will read Badges
	$serverFolder = "../Geo SVN Project/trunk/GeoCaching/web/uploads/";

	//Connection to DB
	mysql_connect($server, $login, $pass) or die(mysql_error());
	mysql_select_db($db) or die(mysql_error());
	//echo "Connected to Database";

	// Retrieve all the data from the "tour" table
	$result = mysql_query("SELECT tour.*,geouser_completed_tour.*,geouser.* FROM tour,geouser_completed_tour,geouser WHERE geouser_completed_tour.GeoUser_idUser =".$userSession." 
	AND geouser_completed_tour.tour_idtour = tour.idtour") or die(mysql_error());  
	
	if (!empty($result))
	{
		//Fetch badges owned by user
		while($row = mysql_fetch_array($result))
		{
			$tour[] = $row['name'];
			$description[] = $row['description'];
			$difficulty[] = $row['difficulty'];
			$distance[] = $row['distance'];
			$lastCompletionDate[] = $row['lastCompletionDate'];
			$images[] = substr($row['badgeURL'],$length);
			$user = $row['username'];
		//	echo "Badge: ".$row['badgeURL']."</br>";
		//	echo "User: ".$row['username']."</br>";
		//	echo "Badge: ".substr($row['badgeURL'],$length)."</br>";
		}
		
		//Display User
		echo "<div class=\"heading\">";
		echo 'Bagdes collected by: '.$user.'</br>';
		echo "</br>";
		echo "</div>\n";

		//Display Tour Information and Badges
		for ($i=0; $i< count($images); ++$i) 
			{
		//		echo $images[$i]."</br>";
				echo '<div align="center">';
				echo "<table>";
				echo "<tr>";
				echo "";
				echo '<th width="150">Tour:</th>
					  <td width="550">'.$tour[$i].'</td>';
				echo '<td rowspan="5" width="59">';
				echo "<img class=\"photo\" width=\"80\" height=\"80\" src=\"{$serverFolder}{$images[$i]}\"> </td>";
				echo '<td rowspan="5" width="59">';
				echo '<a href="publishBadge.php?user='.$user.'&picture='.$images[$i].'&tour='.$tour[$i].'&distance='.$distance[$i].'">
				<img class="photo" src=images/facebook-share-button.png width="80" height="80"> </td>';
				echo "</tr>";
				echo "<tr>";	  
				echo "<th>Description:</th>
					  <td>".$description[$i]."</td>";
				echo "</tr>";
				echo "<tr>";	  
				echo "<th>Difficulty:</th>
					  <td>".$difficulty[$i]."</td>";
				echo "</tr>";
				echo "<tr>";	  
				echo "<th>Distance:</th>
					  <td>".$distance[$i]." meters</td>";
				echo "</tr>";
				echo "<th>Completion Date:</th>
					  <td>".$lastCompletionDate[$i]."</td>";
				echo "</tr>";
				echo "</table>";
				echo "</div>";
				echo "</br>";

			}
	} else {
		echo '<div align="center">The user has no badges</div>';
	}
?>