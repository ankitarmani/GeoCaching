function goToListTours(){
//    setTimeout('window.location = "ListTours.jsp"', 100);
        window.location.href = "ListTours.jsp";
}

function loadXMLDoc(v)
{
    var xmlhttp;

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
            document.getElementById("WaypointsCanvas").innerHTML=xmlhttp.responseText;
//            if (v.substring(0, 8) == "edittour" || v.substring(0, 7) == "addtour")
//            {
//                goToListTours();
//            }
            if (v.substring(0, 8) == "edittour")
            {
                window.location.href = "Questions.jsp?m=edit";
            }
            if (v.substring(0, 7) == "addtour")
            {
                window.location.href = "Questions.jsp?m=create";
            }
            
        }
    }
    
    //for client server use:
    //xmlhttp.open("GET", "http://131.220.239.232:8080/GeoCaching/CreateTourServlet?"+v,true);
  
    
    xmlhttp.open("GET", "http://localhost:8080/GeoCaching/CreateTourServlet?"+v,true);
    xmlhttp.send();
}

var map;
var markers = [];
var poly;
var v="";
var activeInfoWindow;                        
var infowindow;
var currentmarker;
var wrapper;
var alreadyMoved = false;

function error(msg)
{
    if (alreadyMoved == false)
    {
        var latlng = new google.maps.LatLng(0, 0);
        map.setCenter(latlng);
    }
}

function success(position)
{
    if (alreadyMoved == false)
    {
        var latlng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
        map.setCenter(latlng);
    }
}

function initialize()
{
    loadXMLDoc("newtour"); 
    var haightAshbury = new google.maps.LatLng(50.741235, 7.114334);
    var mapOptions = {zoom: 12, center: haightAshbury, mapTypeId: google.maps.MapTypeId.TERRAIN};
    var polyOptions = {strokeColor: '#000000', strokeOpacity: 1.0, strokeWeight: 3};

    //Initialize geocoder for use of search bar with place Address option
    geocoder = new google.maps.Geocoder();
    
    
    map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

    poly = new google.maps.Polyline(polyOptions);
    poly.setMap(map);
    
    //Initial Distance
    $("distance").html("0.00");

    google.maps.event.addListener(map, 'click', clickMapEventHandler);
    navigator.geolocation.getCurrentPosition(success, error);

    wrapper =  document.createElement("div");
    wrapper.innerHTML = "";
    wrapper.className = "map-popup";
    wrapper.style.height = "60px";
    wrapper.innerHTML = "<input type=\"text\" id=\"waynameinfo\" required placeholder=\"Enter Waypoint Name Here\" onchange=\"saveWaypointName()\" />";
                                            
    infowindow = new google.maps.InfoWindow({content: wrapper});
}

//Event handler for click save button in the infowindow event
function saveWaypointName()
{
    currentmarker.setTitle(document.getElementById("waynameinfo").value);
    var cname = currentmarker.getTitle();
    var index = getIndexByPosition(markers, currentmarker.position);
    
    loadXMLDoc("changewaypoint=" + "&index=" + index + "&name=" + cname);
}

//Event handle for click on the map event
function clickMapEventHandler(mouseEvent)
{
    addMarker(mouseEvent.latLng, "", false);
}

function harmonizeIcons(markers)
{
    for (var i=1; i+1<markers.length; i++)
    {
        markers[i].setIcon("images/number_"+i+".png");
        markers[i].setShadow("images/shadow.png");            
    }
    
    if (markers.length>1)
    {
        markers[markers.length-1].setIcon("images/cup.png");
        markers[markers.length-1].setShadow("images/shadow.png");
    
    }
    if (markers.length>0)
    {    
        markers[0].setIcon("images/start-race-2.png");
        markers[0].setShadow("images/shadow.png");
    }
    
    //Recalculate Distance
    calculateDistance();
}

// Add a marker to the map and push to the array.
function addMarker(location, name, old)
{
    
    var marker = new google.maps.Marker({
            position: location,
            map: map,
            title : name,
            draggable: true
        });
  
    path = poly.getPath();
    path.push(location);
    
    google.maps.event.addListener(marker, 'click', clickMarkerEventHandler);   
    google.maps.event.addListener(marker, 'rightclick', removeMarkerEventHandler);

    markers.push(marker);

    v = location.toString();
    
    if (old == false)
    {
        loadXMLDoc("addwaypoint="+v+"&name="+name);
    }
    else
    {
        alreadyMoved = true;
    }
    map.panTo(marker.position);

    //Dragend listener to update waypoint coordinates
    google.maps.event.addListener(marker, 'dragend', function() {
        newMarkerPosition = marker.position;
        for (var i = 0; i < markers.length; i++){
            if (markers[i].position == newMarkerPosition){
                loadXMLDoc("changewaypoint="+"&coordinates="+newMarkerPosition.toString()+"&index="+i);
                path.setAt(i, markers[i].position);
            }
        }
        calculateDistance();
    });
    
    google.maps.event.addListener(marker, 'drag', function() {
        newMarkerPosition = marker.position;
        for (var i = 0; i < markers.length; i++)
            if (markers[i].position == newMarkerPosition)
                path.setAt(i, markers[i].position);
    });

    harmonizeIcons(markers);
}

function getIndexByPosition(markers, position)
{
    for (var i = 0; i < markers.length; i++)
        if (markers[i].position == position)
            return i;
    return -1;
}

function clickMarkerEventHandler(mouseEvent)
{
    index = getIndexByPosition(markers, mouseEvent.latLng);

    currentmarker = markers[index];

    wrapper.innerHTML = "Waypont name:" 
                    + "<input type=\"text\" id=\"waynameinfo\" value=\"" + currentmarker.getTitle() + "\" onkeyup=\"saveWaypointName()\" />";

    infowindow.setContent(wrapper);
    infowindow.open(map, currentmarker);
    
}

function calculateDistance()
{
    if (typeof google.maps.geometry != "undefined")
    {
        $("distance").html( google.maps.geometry.spherical.computeLength(poly.getPath()).toFixed(2));
    }
}

function removeMarkerEventHandler(mouseEvent)
{
    index = getIndexByPosition(markers, mouseEvent.latLng);

    markers[index].setMap(null);
    markers.splice(index, 1);
    poly.getPath().removeAt(index);
    
    //Recalculate Distance        
    harmonizeIcons(markers);

    loadXMLDoc("deletewaypoint="+"&index="+index);
}

//Function for treatment of address and conversion to coordinates
function codeAddress()
{
    var address = document.getElementById("address").value;
    geocoder.geocode( {'address': address}, function(create, status) {
        if (status == google.maps.GeocoderStatus.OK)
        {
            map.panTo(create[0].geometry.location);
            addMarker(create[0].geometry.location, "", false);
        }
        else
        {
            alert("Geocode was not successful for the following reason: " + status);
        }
    });
}

// Sets the map on all markers in the array.
function setAllMap(map)
{
    for (var i = 0; i < markers.length; i++)
    {
        markers[i].setMap(map);
    }
    poly.setMap(map);
}

// Removes the overlays from the map, but keeps them in the array.
function clearOverlays()
{
    setAllMap(null);
}

// Deletes all markers in the array by removing references to them.

//function deleteOverlays()
//{
//    clearOverlays();
//    markers = [];
//    poly = new google.maps.Polyline(polyOptions);
//    poly.setMap(map);
//}

function getLevelFromTheForm()
{
    var level = "low"

    if (document.getElementById("medium").checked)
    {
        level = "medium";
    }
    else if (document.getElementById("high").checked)
    {
        level = "high";
    }
    
    return level;
}

function editTour()
{
    pushInfoToDB("edittour");
}

function saveTour()
{
    pushInfoToDB("addtour");
}

function addSouvenirImage(event)
{
    alert(this.value);
}

function pushInfoToDB(parameter)
{
    $("#file_upload_form").submit();
    
    $("#upload_target").load(function() 
    {
        var level=getLevelFromTheForm();

        var name = document.getElementById("tourname").value;
        var description = document.getElementById("tourdescription").value;
        var distance = google.maps.geometry.spherical.computeLength(poly.getPath()).toFixed(2);

        if (name == "" && description == "")
            alert('Please add name and decription!');
        else
        {    
            loadXMLDoc(parameter+"&level="+level+"&name="+name+"&description="+description+"&distance="+distance);
        }
    });
}

