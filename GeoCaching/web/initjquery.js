
 function showmodal(evt)
{
    content = "<div><img " + "src=\"" + evt.target.result + "\"" + "/></div>";
    $( "#dialog-modal" ).html(content);
    $( "#dialog-modal" ).dialog({
        modal: true
    });
}

function initJQuery()
{
    $("#radiolevelset").buttonset();
    $("#WaypointsCanvas").sortable(
    {
        stop: sortEventHadler
    });                      
    $("#WaypointsCanvas").disableSelection();
    initialize();
    
    $("#imgb").click(function()
    {
        var fileinput = document.getElementById("bage");
        var content;

        if (fileinput.files.length)
        {
            var fr = new FileReader;
            fr.onloadend = showmodal;
            fr.readAsDataURL(fileinput.files[0]);

        }
        else
        {
            var imgSrc = $(this).val();
            var ind;

            if (imgSrc.lastIndexOf("\\", imgSrc.length-1) > -1)
            {
                ind = imgSrc.lastIndexOf("\\", imgSrc.length-1); 
            }
            else
            {
                ind = imgSrc.lastIndexOf("/", imgSrc.length-1);
            }

            content = "<div><img " + "src=\"uploads/" + imgSrc.substr(ind, imgSrc.length - ind) + "\"" + "/></div>";

            $( "#dialog-modal" ).html(content);
            $( "#dialog-modal" ).dialog({
                modal: true
            });

        }
    });
    
    var i;
    
    
    for (i=0; i<5; i++)
    {
        $("#img"+(i+1).toString()).click(function()
        {
            
            var name = $(this).attr("name");
            var souvenirname = "souvenir"+name[name.toString().length-1];
            var fileinput = document.getElementById(souvenirname);
            var content;
            
            if (fileinput.files.length)
            {
                var fr = new FileReader;
                fr.onloadend = showmodal;
                fr.readAsDataURL(fileinput.files[0]);

            }
            else
            {
                var imgSrc = $(this).val();
                var ind;

                if (imgSrc.lastIndexOf("\\", imgSrc.length-1) > -1)
                {
                    ind = imgSrc.lastIndexOf("\\", imgSrc.length-1); 
                }
                else
                {
                    ind = imgSrc.lastIndexOf("/", imgSrc.length-1);
                }

                content = "<div><img " + "src=\"uploads/" + imgSrc.substr(ind, imgSrc.length - ind) + "\"" + "/></div>"
        
                $( "#dialog-modal" ).html(content);
                $( "#dialog-modal" ).dialog({
                    modal: true
                });

            }
        });
    }

    for (i=parseInt($("#souvenirNumber").val())+1; i<=5; i++)
    {
        $("#souvenir"+i.toString()).val(null);    
        $("#souvenir"+i.toString()).hide();
        $("#download"+i.toString()).val(1);    
        $("#download"+i.toString()).hide();
        $("#img"+i.toString()).hide();
        $("#img"+i.toString()).val("");
    }
    
    $("#souvenirNumber").change(function()
    {
        
        for (i=1; i<=parseInt($("#souvenirNumber").val()); i++)
        {
            $("#souvenir"+i.toString()).show();
            $("#download"+i.toString()).show();
            $("#img"+i.toString()).show();
        }
        
        for (i=parseInt($("#souvenirNumber").val())+1; i<=5; i++)
        {
            $("#souvenir"+i.toString()).val(null);    
            $("#souvenir"+i.toString()).hide();
            $("#download"+i.toString()).val(1);    
            $("#download"+i.toString()).hide();
            $("#img"+i.toString()).hide();
            $("#img"+i.toString()).val("");
        }
    });
    
    $( "#dialog:ui-dialog" ).dialog( "destroy" );

}

function sortEventHadler(event, ui)
{
    
    var v = "changeorder=";
    
    var i;
    
    for (i=0; i<markers.length; i++)
    {
        console.log($("#waypoint" + i.toString()).index());
        v = v + $("#waypoint" + i.toString()).index().toString() + ",";
    }

    var oldmarkers = $.extend(true, [], markers);

    for (i=0; i<markers.length; i++)    
        markers[$("#waypoint" + i.toString()).index()] = oldmarkers[i];
    
    var path = poly.getPath();
    for (i=0; i<markers.length; i++)
        path.setAt(i, markers[i].position);
    
    harmonizeIcons(markers);
    loadXMLDoc(v);
}