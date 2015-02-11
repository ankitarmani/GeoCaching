var lat,
    lon,
    waypointIDs,
    waypointsCount,
    overalLat = {},
    overalLon = {};
    
waypointIDs   = [];
lat           = [];
lon           = [];

function showWarningMore4()
{
    $("#dialog-message").html("<p>There should be at most four questions for each coordinate!</p>");
    $("#dialog-message" ).dialog({
            modal: true,
            buttons: {
                    Ok: function() {
                            $( this ).dialog( "close" );
                    }
            }
    });
}

function loadWaypoints() {
    'use strict';
    var xmlhttp,
        currentRow,
        waypointsList;

    waypointsList = [];

    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    } else {// code for IE6, IE5 
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            waypointsList = xmlhttp.responseText.split("|");

            var ElementId = 0;
            overalLat = {};
            overalLon = {};
            waypointsCount = parseInt(waypointsList.length / 6);
            
            for (currentRow = 1; currentRow <= waypointsList.length / 6; currentRow += 1, ElementId += 6) {
                $("#waypoint" + currentRow).buttonset();

                lat[currentRow - 1] = waypointsList[ElementId];
                lon[currentRow - 1] = waypointsList[ElementId + 1];
                waypointIDs[currentRow - 1] = parseInt(waypointsList[ElementId + 2]);
                overalLat[waypointIDs[currentRow - 1]] = 0;
                overalLon[waypointIDs[currentRow - 1]] = 0;


                generateHtmlContentForWaypointDiv(waypointsList[ElementId], waypointsList[ElementId + 1], waypointsList[ElementId + 2], "waypoint" + currentRow);
                $("#dialog:ui-dialog").dialog("destroy");
                if (waypointsList[ElementId + 3] !== "") {
                    document.getElementById("waypointlabel" + currentRow).innerHTML = " waypoint " + waypointsList[ElementId + 3] + "";
                } else {
                    document.getElementById("waypointlabel" + currentRow).innerHTML = " waypoint";
                }

                var questions = waypointsList[ElementId + 5].split(";");
                
                for (var i = 0; i < questions.length / 2; i++) {
                    
                    var waypointID = parseInt(questions[i * 2]);
                    
                    if ((questions[i * 2].toString()).indexOf("lat") != -1) {
                        overalLat[waypointID] = overalLat[waypointID] + 1;
                    }
                    
                    if ((questions[i * 2].toString()).indexOf("lon") != -1) {
                        overalLon[waypointID] = overalLon[waypointID] + 1;
                    }
                    
                    $("#" + questions[i * 2]).attr("question", questions[i * 2 + 1]);
                    $("#" + questions[i * 2]).attr("checked", "checked");
                    $("label[for='" + questions[i * 2] + "']").attr("aria-pressed", true);
                    $("label[for='" + questions[i * 2] + "']").attr("class", "ui-button ui-widget ui-state-default ui-button-text-only ui-corner-left ui-state-active");
                }
            }
            bindEventHandler();
        }
    };

    //for client server use:
    //xmlhttp.open("GET", "http://192.168.0.101:8080/GeoCaching/QuestionServlet",true);

   // console.log(window.location.href.match(/.*[/][^\d]*/)[0]);
    xmlhttp.open("GET", "http://localhost:8080/GeoCaching/QuestionServlet?getQuestions", true);
    xmlhttp.send();
}

function saveQuestionToDb() {
    'use strict';
    var i, j,
        questionsString;

    questionsString = "";
    
    var flag = true;
    var flagEmpty = false;
    
    for (i = 0; i < waypointsCount; i += 1) {
        if (overalLat[waypointIDs[i]] == 0 || overalLon[waypointIDs[i]] == 0) {
            flag = false;
        }
    }
    
    var tmp = "";
    
    for (i = 0; i < waypointsCount; i += 1) {
        for (j = 0; j < lon[i].length; j += 1) {
            if ($("#" + waypointIDs[i].toString() + "lon" + j.toString()).attr("checked") === "checked") {
                tmp = $("#" + waypointIDs[i].toString() + "lon" + j.toString()).attr("question");

                if (tmp === "") {
                    flagEmpty = true;
                }
            }

        }

        for (j = 0; j < lat[i].length; j += 1) {
            if ($("#" + waypointIDs[i].toString() + "lat" + j.toString()).attr("checked") === "checked") {
                tmp = $("#" + waypointIDs[i].toString() + "lat" + j.toString()).attr("question");
            
                if (tmp === "") {
                    flagEmpty = true;
                }
            }
        }
        
    }

    if (flagEmpty){
        $("#dialog-message").html("<p>There are empty questions!</p>");
        $("#dialog-message" ).dialog({
                modal: true,
                buttons: {
                        Ok: function() {
                                $( this ).dialog( "close" );
                        }
                }
        }); 
    }
    else if (flag) {
        for (i = 0; i < waypointsCount; i += 1) {

            for (j = 0; j < lon[i].length; j += 1) {
                questionsString += waypointIDs[i].toString() + "|";
                questionsString += (100 + j).toString() + "|";
                if ($("#" + waypointIDs[i].toString() + "lon" + j.toString()).attr("checked") === "checked") {
                    questionsString += $("#" + waypointIDs[i].toString() + "lon" + j.toString()).attr("question");
                }
                questionsString += ";";
            }

            for (j = 0; j < lat[i].length; j += 1) {
                questionsString += waypointIDs[i].toString() + "|";
                questionsString += j.toString() + "|";
                if ($("#" + waypointIDs[i].toString() + "lat" + j.toString()).attr("checked") === "checked") {
                    questionsString += $("#" + waypointIDs[i].toString() + "lat" + j.toString()).attr("question");
                }
                questionsString += ";";
            }
        }
        $.ajax({
                url: 'http://localhost:8080/GeoCaching/QuestionServlet?saveQuestions=' + encodeURIComponent(questionsString),
                context: document.body
            }).done(function() { 
                    setTimeout('window.location = "ListTours.jsp"',500);
            });
    
        } else {
            $("#dialog-message").html("<p>There should be at least one question for each coordinate!</p>");
            $("#dialog-message" ).dialog({
                    modal: true,
                    buttons: {
                            Ok: function() {
                                    $( this ).dialog( "close" );
                            }
                    }
            });
        }
}