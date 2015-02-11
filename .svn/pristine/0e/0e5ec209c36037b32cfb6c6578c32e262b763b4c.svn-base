function refreshPage(){
    setTimeout('window.location = "ListTours.jsp"',500);
}

function loadTour()
{
    var xmlhttp;

    var TourList=new Array();

    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5 
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }

    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            TourList=xmlhttp.responseText.split("|");

            var ElementId=0;
            for (CurrentRow=1; CurrentRow<=TourList.length/7; CurrentRow++, ElementId+=7)
            {
                document.getElementById("name"+CurrentRow).innerHTML=TourList[ElementId+1];
                document.getElementById("difficulty"+CurrentRow).innerHTML=TourList[ElementId+2];
                document.getElementById("description"+CurrentRow).innerHTML=TourList[ElementId+3];
                document.getElementById("rowcontrol"+CurrentRow).innerHTML=TourList[ElementId+4];
                document.getElementById("details"+CurrentRow).innerHTML=TourList[ElementId+5]+"<br/>";
                initializeMap(TourList[ElementId+6].toString(), CurrentRow, TourList[ElementId+1]);
            }
        }
    }
    //for client server use:
    //xmlhttp.open("GET", "http://192.168.0.101:8080/GeoCaching/ListToursServlet",true);
    
    console.log(window.location.href.match(/.*[/][^\d]*/)[0]);
    xmlhttp.open("GET", "http://localhost:8080/GeoCaching/ListToursServlet",true);
    xmlhttp.send();
 
}
function updatePage(){
   window.location.reload(); 
}

var map;                    
var infowindow;
var wrapper;
var Latlng=new Array();


function initializeMap(StartLatLng, idmap, Pointname)
{
    Latlng = StartLatLng.toString().split(";");
    var CoordLatLng = new google.maps.LatLng(parseFloat(Latlng[0]), parseFloat(Latlng[1]));
    var mapOptions = {zoom: 12, center: CoordLatLng, mapTypeId: google.maps.MapTypeId.TERRAIN};

    //Initialize geocoder for use of search bar with place Address option
    geocoder = new google.maps.Geocoder();
         
    map = new google.maps.Map(document.getElementById('map-canvas'+idmap), mapOptions);
    addStartPoint(CoordLatLng, "start"); 
}
function addStartPoint(location, name)
{
    var marker = new google.maps.Marker({
            position: location,
            map: map,
            title : name,
            draggable: false
        });
        marker.setIcon("images/start-race-2.png");
        
        
        map.panTo(marker.position);
}