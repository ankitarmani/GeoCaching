<%-- 
    Document   : ListTours
    Created on : May 2, 2012, 10:41:39 PM
    Author     : alex
--%>

<%@page import="controllers.ListToursServlet"%>
<%@page   import="java.io.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>GeoCaching</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="CSS/reset.css" type="text/css" media="all">
        <link rel="stylesheet" href="CSS/Style.css" type="text/css" media="all">
       
        <style type="text/css">
        table.servicesT{          
            color: #404040;
            background-color: #fafafa;
            border: 1px #6699CC solid;
            border-collapse: collapse;
            border-spacing: 0px;
            margin-top: 0px;
            width:100%;
        }
        table.servicesT td.servHd{	
            border-bottom: 2px solid #6699CC;
            background-color: #336699;
            text-align: center;
            font-weight: bold;
            color: white;
        }
        table.servicesT td.servSHd{	
            border-bottom: 2px solid #6699CC;
            background-color: #336699;
            text-align: left;
            font-weight: bold;
            color: white;
        }

        table.servicesT td{	
            border-bottom: 1px dotted #6699CC;
            color: #404040;
            background-color: white;
            text-align: left;
            padding-left: 3px;
        } 
        .servBodL{
            border-left: 1px dotted #CEDCEA; 

        } 
        .noBorders{
            border:none;padding:0 px;border-spacing:0 px;
            
        }
          .E:link,.E:visited
        {
            display:inline-block;
            color:orange;
            background-color:white;
            text-align:center;
            text-decoration:none;
            text-align:center;
            padding:2px;
            font-weight:bold;
        }
        .E:hover,.E:active
        {
          background-color:green;
          color:white;
        }
        .D:link,.D:visited
        {
            display:inline-block;
            color:orange;
            background-color:white;
            text-align:center;
            text-decoration:none;
            text-align:center;
            padding:2px;
            font-weight:bold;
        }
        .D:hover,.D:active
        {
         background-color:red;
         color:white;
        }
        .btnCntl
        {
            display:inline-block;
            color:black;
            background-color:transparent;
            text-align:center;
            padding:2px;
            font-weight:bold;
            border:1px solid #ebebeb;
            cursor: pointer;
        }   
        
        .btnCntl:hover
        {
            display:inline-block;
            color:blue;
            background-color:transparent;
            text-align:center;
            padding:2px;
            font-weight:bold;
            border:1px solid #ebebeb;
            cursor: pointer;
        }   
        
        #report { border-collapse:collapse;}
        #report h4 { margin:0px; padding:0px;}
        #report ul { margin:10px 0 10px 40px; padding:0px;}
        #report th { background:#7CB8E2 url(header_bkg.png) repeat-x scroll center left; color:#fff; padding:7px 15px; text-align:left;}
        #report td { background:#C7DDEE none repeat-x scroll center left; color:#000; padding:7px 15px; }
        #report tr.odd td { background:#fff url(row_bkg.png) repeat-x scroll center left; cursor:pointer; }
     
    </style>
    
        <link type="text/css" href="CSS/smoothness/jquery-ui-1.8.20.custom.css" rel="stylesheet" />
        <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.20.custom.min.js"></script>
        <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?libraries=geometry&key=AIzaSyD3lQBr4cZ2do3Nr3QB4iFpQkdjCrS703E&sensor=true"></script> 
        
        <script type="text/javascript" src = "ListTours.js">  </script>
        
        
        <script type="text/javascript">  
            $(document).ready(function(){
                $("#report tr:odd").addClass("odd");
                $("#report tr:not(.odd)").show();
                $("#report tr:first-child").show();
                 $("#expandAll").hide();

                $("#report tr.odd").click(function(){
                    $(this).next("tr").toggle();
                    $(this).find(".arrow").toggleClass("up");
                });
                
                $( "#collapseAll" ).click(function() {
                        $("#report tr:not(.odd)").hide();
                        $("#report tr:first-child").show();
                        $("#collapseAll").hide();
                        $("#expandAll").show();
			return false;
		});
                
                $( "#expandAll" ).click(function() {
                        $("#report tr:not(.odd)").show();
                        $("#collapseAll").show();
                        $("#expandAll").hide();
			return false;
		});
                
            });
        </script> 
        
        
   
    </head>

    <body onload=" loadTour();">


        <!-- header -->
        <header>
            <div class="container">
                <h1><a href="index.jsp">GeoCaching</a></h1>
                <nav>
                    <ul>
                        <li><a href="index.jsp">Home</a></li>
                        <li><a href="ListTours.jsp" class="current">Browse Tours</a></li>
                        <li><a href="createTour.jsp">Create Tour</a></li>
                        <li><a href="#">About</a></li>
                        <li><a href="mailto:scrum-geocache@googlegroups.com?Subject=Hello">Contact</a></li>
                    </ul>
                </nav>
            </div>
        </header>

        <div class="main-box">
            <div class="container">
                <div class="inside">                     
                    <div class="form-content">  
                        <div style="text-align: right;"> 
                            <button id="collapseAll" class="btnCntl">HIDE DETAILS</button>
                            <button id="expandAll" class="btnCntl">SHOW DETAILS</button>                           
                        </div>
                            <% 
                                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/GetToursNumber?object=tour");
                                dispatcher.include(request,response);
                                String TourCounts=(String)session.getAttribute("ToursNumber");
                                ListToursServlet ex = new ListToursServlet();
                                out.print(ex.buildTable(TourCounts));
                            %> 
                     </div>
                    </div>
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