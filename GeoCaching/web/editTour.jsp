<%-- 
    Document   : editTour
    Created on : May 8, 2012, 7:09:21 PM
    Author     : alex

  <div class="radiobtn">
        <label for="radiobtn">Difficulty:</label>
        <input type="radio" name="g1" id="low" value="Low"> Low<br>
        <input type="radio" name="g1" id="medium" value="Medium" checked> Medium<br>
        <input type="radio" name="g1" id="high" value="High">High
    </div>

--%>




<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>GeoCaching</title>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <link type="text/css" href="CSS/smoothness/jquery-ui-1.8.20.custom.css" rel="stylesheet" />
        <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.20.custom.min.js"></script>
        
        <link rel="stylesheet" href="CSS/reset.css" type="text/css" media="all" />
        <link rel="stylesheet" href="CSS/Style.css" type="text/css" media="all" />
        <style type="text/css">
            #map-canvas {width: 400px; height:400px}
            #WaypointsCanvas { list-style-type: none; margin: 0; padding: 0; width: 100%; }
            #WaypointsCanvas li { margin: 0 3px 3px 3px; padding: 0.4em; padding-left: 1.5em; font-size: 1.2em; height: 18px; }
            #WaypointsCanvas li span { position: absolute; margin-left: -1.3em; }
	</style>
        <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?libraries=geometry&key=AIzaSyD3lQBr4cZ2do3Nr3QB4iFpQkdjCrS703E&sensor=true"></script> 
        <script type="text/javascript" src="GoogleMaps.js"></script>
        <script type="text/javascript" src="initjquery.js"></script>
        <script type="text/javascript" src="editTour.js"></script> 
        <script type="text/javascript">
            $(document).ready(function()
            {
                initJQuery();
                $("#saveButton").click(function() {
                        editTour();
                });
                loadTour(); 
            });
        </script>  
        
        <%
            String tour = request.getParameter( "tour" );
            if (tour!="") session.setAttribute( "IDtour", tour );
        %>
    </head>

    <body>
        <!-- header -->
        <header>
            <div class="container">
                <h1><a href="index.jsp">GeoCaching</a></h1>
                <nav>
                    <ul>
                        <li><a href="index.jsp">Home</a></li>
                        <li><a href="ListTours.jsp">Browse Tours</a></li>
                        <li><a href="createTour.jsp">Create Tour</a></li>
                        <li><a href="#" class="current">Edit Tour</a></li>
                        <li><a href="#">About</a></li>
                        <li><a href="mailto:scrum-geocache@googlegroups.com?Subject=Hello">Contact</a></li>
                    </ul>
                </nav>
            </div>
        </header>                              
        <div class="main-box" id="main-box">
        <%@ include file="tourMainForm.jsp" %>
        </div>	
        <!-- footer -->
        <footer>
            <div class="container">
                <div class="wrapper">
                    <div class="fleft">Copyright - SCRUM 2012</div>
                </div>
            </div>
        </footer>
    </body>
</html>