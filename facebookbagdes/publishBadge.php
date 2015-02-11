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
  font-size:20px;
  margin-top: 5px; margin-bottom: 0px;
  text-align: center;
  font-weight: normal;
  color: #222;
  }

  table{
    border-spacing: 0px;
    border-collapse: collapse;
    width: 730px;
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

	//Redirect back to index.php
	header('Refresh: 5; URL=index.php');

		//Directory where Wampserver will read Badges
		$serverFolder = "../Geo SVN Project/trunk/GeoCaching/web/uploads/";
	
		//Variables sent from index.php
		$user = $_GET['user'];
		$picture = $_GET['picture'];
		$tour = $_GET['tour'];
		$distance = $_GET['distance'];

		//Table structure
		echo "</br>";
		echo "</br>";
		echo "</br>";
		echo '<div align="center">';
		echo '<table>';
		echo '<tr>';
		echo '<th width="70" height="78">';
		echo '<div align="center">';
		echo "<img class=\"photo\" width=\"100\" height=\"100\" src=\"{$serverFolder}{$picture}\">";
		echo '</div></th>';
		echo '<td width="600"><div align="center" class="heading">Badge for player '.$user.' is being uploaded to Facebook';
		echo '</div></p><div align="center">Uploading may take some seconds...</div>';
		echo '</td>';
		echo '</tr>';
		echo '</table>';
		echo "</div>";		
	
		require_once '../facebook-php-sdk/src/facebook.php';
	
		//Facebook App authentication
		$facebook = new Facebook(array(
		'appId'  => '165744406890419',
		'secret' => 'ff47667593259aec565bd63a00f8f49d',
		'fileUpload' => true // enable file upload support
		));

		// get a login url with the correct perms
		$facebook->getLoginUrl(array(
		  'scope' => 'photo_upload,first_name,email,publish_stream,read_stream'
		));

		$facebook -> setFileUploadSupport(true);

		$album_details = array('message' => 'Badge upload trial', 'name' => 'Geocaching Badges');
        $create_album = $facebook -> api('/me/albums', 'post', $album_details);
        $album_uid = $create_album['id'];
		
        $photo_details = array('message' => 'Player '.$user.' has just won a badge after successfully completing the "'
		.$tour.'" tour with a distance of '.$distance.' meters');
        
		//Complete picture path
		$file = $serverFolder.$picture;
        $photo_details['image'] = '@' . realpath($file);

        $upload_photo = $facebook -> api('/' . $album_uid . '/photos', 'post', $photo_details);
		
?>