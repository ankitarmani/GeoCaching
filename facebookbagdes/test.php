<?php
$images = "../Geo SVN Project/trunk/GeoCaching/web/uploads/"; # Location of small versions 
$big    = "badges/"; # Location of big versions (assumed to be a subdir of above) 
$cols   = 2; # Number of columns to display 

if ($handle = opendir($images)) { 
   while (false !== ($file = readdir($handle))) { 
       if ($file != "." && $file != ".." && $file != rtrim($big,"/")) { 
           $files[] = $file; 
       } 
   } 
   closedir($handle); 
} 

$colCtr = 0; 

echo '<table width="100%" cellspacing="3"><tr>'; 

foreach($files as $file) 
{ 
  if($colCtr %$cols == 0) 
    echo '</tr><tr><td colspan="2"><hr /></td></tr><tr>'; 
  echo '<td align="center"><a href="' . $images . $big . $file . '"><img src="' . $images . $file . '" /></a></td>'; 
  $colCtr++; 
} 

echo '</table>' . "\r\n"; 

$user = $_GET['user'];
		$picture = $_GET['picture'];
		$tour = $_GET['tour'];
		$distance = $_GET['distance'];

echo 'Player '.$user.' has just won a badge after successfully completing 
		the "'.$tour.'" tour with a distance of '.$distance.' meters';

?>