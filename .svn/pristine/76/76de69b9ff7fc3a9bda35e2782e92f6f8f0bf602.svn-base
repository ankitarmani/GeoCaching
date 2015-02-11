<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> Upload images to Facebook</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<style>
html{
	font-family: "lucida grande",tahoma,verdana,arial,sans-serif;
}
.main{
	width:400px;
	margin:auto;
	border:2px solid #0066CC;
	color:#3B5998;
	padding:20px;
	font-size: 11px;
    -moz-border-radius: 4px 4px 4px 4px;
    border-radius: 4px 4px 4px 4px;
    -moz-box-shadow: 1px 1px 0 #d5d5d5;
	background: none repeat scroll 0 0 #F2F2F2;
}
.post_but {
    background: none repeat scroll 0 0 #EEEEEE;
    border-color: #999999 #999999 #888888;
    border-style: solid;
    border-width: 1px;
    color: #333333;
    cursor: pointer;
    display: inline-block;
    font-size: 11px;
    font-weight: bold;
    padding: 2px 6px;
    text-align: center;
    text-decoration: none;
}
a{
	color:#3B5998;
}
</style>
</head>

<body>
<?php
/******************Configuration options***********************/

require_once '../facebook-php-sdk/src/facebook.php';

$FB_APP_ID='165744406890419';
$FB_APP_SECRET='ff47667593259aec565bd63a00f8f49d';

$APP_RETURN_URL=((substr($_SERVER['SERVER_PROTOCOL'],0,4)=="HTTP")?"http://":"https://").$_SERVER['HTTP_HOST'].$_SERVER['SCRIPT_NAME'];

$code = $_REQUEST["code"];

if(empty($code)) 
{
    $dialog_url = "http://www.facebook.com/dialog/oauth?client_id=".$FB_APP_ID."&redirect_uri=".$APP_RETURN_URL."&scope=publish_stream";                  
    header("Location:$dialog_url");
}

$token_url = "https://graph.facebook.com/oauth/access_token?client_id=".$FB_APP_ID."&redirect_uri=".urlencode($APP_RETURN_URL)."&client_secret=".$FB_APP_SECRET."&code=".$code;
$access_token = file_get_contents($token_url);

$param1=explode("&",$access_token);
$param2=explode("=",$param1[0]);
$FB_ACCESS_TOKEN=$param2[1];

$facebook = new Facebook(array(
  'cookie' => true
));

/******************Configuration options***********************/

	$img = realpath('../date-bg4.gif');
	//$img = "../date-bg4.gif";
	// allow uploads
	$facebook->setFileUploadSupport("http://" . $_SERVER['SERVER_NAME']);
	// add a status message
	$photo = $facebook->api('/me/photos', 'POST', 
		array(
			'source' => '@' . $img,
			'message' => 'This photo was uploaded via Geocaching'
		)
	);

	echo '<p><a target="_blank" href="http://www.facebook.com/photo.php?fbid='.$photo['id'].'">Click here to watch this photo on Facebook.</a></p>';

?>
<div class="main">
	<p>Select a photo to upload on Facebook.</p>
	<form method="post" action="<?php echo $_SERVER['PHP_SELF']; ?>" enctype="multipart/form-data">
	<p>Select the image: <input type="file" name="pic" /></p>
	<p><input class="post_but" type="submit" value="Upload to my album" /></p>
	</form>
</div>
</body>
</html>