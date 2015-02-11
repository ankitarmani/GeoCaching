<%-- 
    Document   : Questions
    Created on : Jun 5, 2012, 9:51:47 PM
    Author     : alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="controllers.QuestionServlet"%>
<%@page import="java.io.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Questions Page</title>
        <link rel="stylesheet" href="CSS/reset.css" type="text/css" media="all">
        <link rel="stylesheet" href="CSS/Style.css" type="text/css" media="all">
        <link type="text/css" href="CSS/smoothness/jquery-ui-1.8.20.custom.css" rel="stylesheet" />
        <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.20.custom.min.js"></script>
        <script src="js/questionsUtilityScripts.js"></script>
        <script src="Questions.js"></script>
        <script type="text/javascript">
            $(document).ready(function()
            {
                $("#mode").buttonset();
                
                $("#textEdit").click(function() {
                    saveTour();
                });

            });
        </script>
    </head>
    <body>
        <header>
            <div class="container">
                <h1><a href="index.jsp">GeoCaching</a></h1>
                <nav>
                    <ul>
                        <li><a href="index.jsp">Home</a></li>
                        <li><a href="ListTours.jsp">Browse Tours</a></li>                   
                          <%
                            String Mode = request.getParameter("m" );

                            if (Mode.contains("edit")){
                                out.print("<li><a href='#'>Edit Tour</a></li>");
                            }else if (Mode.contains("create")){
                                out.print("<li><a href='#'>Create Tour</a></li>");
                            }
                          %> 
                        <li><a href="#" class="current">Questions</a></li>
                        <li><a href="#">About</a></li>
                        <li><a href="mailto:scrum-geocache@googlegroups.com?Subject=Hello">Contact</a></li>
                    </ul>
                </nav>
            </div>
        </header>                           
        <div class="main-box" id="main-box">
                <div id="mode" style="text-align:center; font: 62.5%; width:auto;" >
                        <label for="radiolevelset" style="float : center;">Mode:</label>
                        <div>
                        <input type="radio" style="vertical-align: center" id="activeEdit" name="radio" /><label for="activeEdit">Activate/Deactivate</label>
                        <input type="radio" style="vertical-align: center" id="textEdit" name="radio" checked="checked" /><label for="textEdit">Edit questions text</label>
                
                        </div>
                </div>
                <div class="container">                  
                    <div style="text-align: center; background:#efefef;width:auto;border:3px solid #336699;border-radius:10px;-webkit-border-radius:10px;-moz-border-radius:10px;-o-border-radius:10px;">                   
                            <%
                                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/GetToursNumber?object=waypoint");
                                dispatcher.include(request,response);      
                                QuestionServlet ex = new QuestionServlet();
                                out.print(ex.buildDIV((String)session.getAttribute("WaypointsNumber")));
                            %> 
                           
                            <script>loadWaypoints();</script>
                             
                            <input id="saveButton" class="submit-button" type=button value="Save the tour questions" onclick="saveQuestionToDb()"/>
                             <br/>
                           
                    </div>
                           
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
