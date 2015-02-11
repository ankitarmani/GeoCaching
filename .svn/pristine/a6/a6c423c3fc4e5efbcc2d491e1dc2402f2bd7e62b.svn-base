
var TourInfo=new Array();
var TourPoints=new Array();

function loadTour()
{
    var xmlhttpE;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttpE=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5 
        xmlhttpE=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttpE.onreadystatechange=function()
    {
        if (xmlhttpE.readyState==4 && xmlhttpE.status==200)
        {
            TourInfo=xmlhttpE.responseText.split("|");
           
            $("#tourname").val(TourInfo[1]);  
           
            if (TourInfo[2]=="low")
            {
                document.getElementById("low").checked = true;  
                $("#low").click();
                $("#radiolevelset").buttonset("refresh");
            }
            else if (TourInfo[2]=="medium")
            {
                document.getElementById("medium").checked = true;
                $("#medium").click();
                $("#radiolevelset").buttonset("refresh");
            }
            else if (TourInfo[2]=="high")
            {
                document.getElementById("high").checked = true; 
                $("#high").click();
                $("#radiolevelset").buttonset("refresh");
            }
            $("#tourdescription").val(TourInfo[3]);  
     
            TourPoints=TourInfo[4].split(";");
            TourSouvenirs=TourInfo[7].split(";");
            TourSouvenirsDownloads =  TourInfo[8].split(";");
            
            var i=0;
            while (i<TourPoints.length-1)
            {
                addMarker(new google.maps.LatLng(parseFloat(TourPoints[i+2]),parseFloat(TourPoints[i+3])), TourPoints[i+1], true);
                i+=4;
            }
            
            $("#souvenirNumber").val(parseInt(TourInfo[6]));
            $("#souvenirNumber").change();
            $("distance").html(TourInfo[5]);
            
            for (i=0; i<parseInt(TourInfo[6]); i++)
            {
                $("#img"+(i+1).toString()).val(TourSouvenirs[i+1]);
                $("#download"+(i+1).toString()).val(parseInt(TourSouvenirsDownloads[i+1]));
            }
            
            $("#imgb").val(TourInfo[9]);
            
            
            loadXMLDoc("refresh");
        }
    }
    //for client server use:
    //xmlhttpE.open("GET", "http://192.168.0.101:8080/GeoCaching/LoadTour",true);
 
    xmlhttpE.open("GET", "http://localhost:8080/GeoCaching/LoadTour",true);
    xmlhttpE.send();
}