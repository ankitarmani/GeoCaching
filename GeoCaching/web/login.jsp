<%-- 
    Document   : login
    Created on : May 23, 2012, 10:40:35 AM
    Author     : Beatriz
--%>


<%@page import="controllers.Login"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>GeoCaching</title>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <link type="text/css" href="CSS/smoothness/jquery-ui-1.8.20.custom.css" rel="stylesheet" />

        <link rel="stylesheet" href="CSS/reset.css" type="text/css" media="all" />
        <link rel="stylesheet" href="CSS/Style.css" type="text/css" media="all" />
        <style type="text/css">
            #map-canvas {width: 300px; height: 300px}
            #WaypointsCanvas { list-style-type: none; margin: 0; padding: 0; width: 100%; }
            #WaypointsCanvas li { margin: 0 3px 3px 3px; padding: 0.4em; padding-left: 1.5em; font-size: 1.2em; height: 18px; }
            #WaypointsCanvas li span { position: absolute; margin-left: -1.3em; }
        </style>            
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
                        <li><a href="createTour.jsp" class="current">Create Tour</a></li>
                        <li><a href="#">About</a></li>
                        <li><a href="mailto:scrum-geocache@googlegroups.com?Subject=Hello">Contact</a></li>
                    </ul>
                </nav>
            </div>
        </header>                              
        <div class="main-box" id="main-box">  
            <div class="container">     
                <form method="post" action="Login">
                    <br><br>
                    <table align="center"><tr><td><h2>Login Authentication</h2></td></tr></table>
                    <div class="tableLogin">
                    <table>
                        <tr><td colspan=2></td></tr>
                        <tr><td colspan=2>&nbsp;</td></tr>
                        <tr>
                            <td>&nbsp;&nbsp;<b>Login Name:</b></td>
                            <td><input type="text" placeholder="Enter Username" required name="username"><br><br/></td>
                        </tr>
                        <tr>
                            <td>&nbsp;&nbsp;<b>Password:</b></td>
                            <td><input type="password" placeholder="Enter Password" required name="password"></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><br/><input id="submitLogin" type="submit" value="Submit"></td>
                        </tr>
                        <tr><td colspan=2>&nbsp;</td></tr>
                    </table>
                    </div>    
                    <br><br>
                </form>
            </div>
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