function generateHtmlContentFromLatLong(lat, lon, waypointID) {
    "use strict";
    var htmlContent = "",
        i,
        c,
        buttonID,
        afterLimit = 0,
        afterDot = false;
        
    for (i = 0; i < lat.length; i += 1) {
        c = lat.charAt(i);
        
        buttonID = waypointID.toString() + "lat" + i.toString();
        if (c === "." || afterLimit > 4) {
            htmlContent += "<input class='coordinateDigit'" +
                " digit='" + c.toString() +
                "' question=''" +
                "' disabled type='checkbox' id='" +
                buttonID +
                "'/><label for='" +
                buttonID +
                "'>" +
                c +
                "</label>";
            afterDot = true;
        } else {
            htmlContent += "<input class='coordinateDigit'" +
                " digit='" + c.toString() +
                "' question=''" +
                " type='checkbox' id='" +
                buttonID +
                "'/><label for='" +
                buttonID +
                "'>" +
                c +
                "</label>";
            if (afterDot == true) {
                afterLimit = afterLimit + 1;
            }

        }
    }

    htmlContent += "<br>";
    afterLimit = 0;
    afterDot = false;
    for (i = 0; i < lon.length; i += 1) {
        c = lon.charAt(i);
        buttonID = waypointID.toString() + "lon" + i.toString();
        if (c === "." || afterLimit > 4) {
            htmlContent += "<input class='coordinateDigit'" +
                " digit='" + c.toString() +
                "' question =''" +
                " disabled type='checkbox' id='" +
                buttonID +
                "'/><label for='" +
                buttonID +
                "'>" +
                c +
                "</label>";
            afterDot = true;
        } else {
            htmlContent += "<input class='coordinateDigit'" +
                " digit='" + c.toString() +
                "' question=''" +
                " type='checkbox' id='" +
                buttonID +
                "'/><label for='" +
                buttonID +
                "'>" +
                c +
                "</label>";
            if (afterDot == true) {
                afterLimit = afterLimit + 1;
            }
        }
    }

    return htmlContent;
}

function generateHtmlContentForWaypointDiv(lat, lang, waypointID, divID) {
    "use strict";

    $("#" + divID).html(generateHtmlContentFromLatLong(lat, lang, waypointID));
    $("#" + divID).buttonset();
}

function bindEventHandler() {
        $('.coordinateDigit').on('click', function () {
        if ($("#activeEdit").attr("checked") === "checked") {
            if ($(this).attr("checked") === "checked") {
                                
                var waypointID = parseInt($(this).attr("id"));
                                
                if (($(this).attr("id")).indexOf("lat") != -1) {
                    if (overalLat[waypointID] === 4) {
                        showWarningMore4();
                        $(this).attr("checked", false);
                        $("label[for='" + $(this).attr("id") + "']").attr("aria-pressed", false);
                        $("label[for='" + $(this).attr("id") + "']").attr("class", "ui-button ui-widget ui-state-default ui-button-text-only");
                    } else {
                        overalLat[waypointID] = overalLat[waypointID] + 1;
                    }
                }

                if (($(this).attr("id")).indexOf("lon") != -1) {
                    if (overalLon[waypointID] === 4) {
                        showWarningMore4();
                        $(this).attr("checked", false);
                        $("label[for='" + $(this).attr("id") + "']").attr("aria-pressed", false);
                        $("label[for='" + $(this).attr("id") + "']").attr("class", "ui-button ui-widget ui-state-default ui-button-text-only");
                    
                    } else {
                        overalLon[waypointID] = overalLon[waypointID] + 1;
                    }
                }                
            } else {
                var waypointID = parseInt($(this).attr("id"));
                if (($(this).attr("id")).indexOf("lat") != -1) {
                    overalLat[waypointID] = overalLat[waypointID] - 1;
                }

                if (($(this).attr("id")).indexOf("lon") != -1) {
                    overalLon[waypointID] = overalLon[waypointID] - 1;
                }
            }
            
        
        } else {
                $("#dialog-confirm").html("<p>Print your question for digit " + $(this).attr("digit") + ":</p><input type='text' id='question' value='" + $(this).attr("question") + "'/>");
                
                var digitObject = $(this).attr("id");

                $("#dialog-confirm").dialog({
                                resizable: false,
                                height: 250,
                                modal: true,
                                buttons: {
                                        "Save": function() {
                                                $("#" + digitObject).attr("question", $("#question").val());
                                                $(this).dialog( "close" );
                                        },
                                        Cancel: function() {
                                                $(this).dialog( "close" );
                                        }
                                }
                        });
                if ($(this).attr("checked") != "checked") {
                    $(this).attr("checked", "checked");
                    $("label[for='" + $(this).attr("id") + "']").attr("aria-pressed", true);
                    $("label[for='" + $(this).attr("id") + "']").attr("class", "ui-button ui-widget ui-state-default ui-button-text-only ui-corner-left ui-state-active");
                } else {
                    $(this).attr("checked", false);
                    $("label[for='" + $(this).attr("id") + "']").attr("aria-pressed", false);
                    $("label[for='" + $(this).attr("id") + "']").attr("class", "ui-button ui-widget ui-state-default ui-button-text-only");
                }
                
        }
    });
}