<div class="container">
    <div>
        <div class="form-content">
            
            <div id="dialog-modal" title="Picture">
            </div>            
            
           <form action="upload.jsp" enctype="multipart/form-data" id="file_upload_form" method="post" target="upload_target">



                <fieldset>

                    <table style= "width:100%" cellspacing= "0"> 
                <tr> 

                    <td style= "width:550px" >
                            <table style= "width:100%" cellspacing= "0"> 
                            <tr> 
                                <td style= "width:80%" >  
                                    <div class="name">
                                        <label for="name">&nbsp;&nbsp;Name:</label>
                                        &nbsp;&nbsp;<input style= "width:90%" type="text" id="tourname" required placeholder="Enter Tour Name" /><br/>
                                    </div>
                                    <div class="name">
                                       <br/><label for="description">&nbsp;&nbsp;Description:</label><br/>
                                        &nbsp;&nbsp;<textarea style= "width:90%" type="description" rows="3" required  id="tourdescription" placeholder="Enter Description" ></textarea>
                                    </div>
                                    <div>
                                        <div><br/>&nbsp;&nbsp;<button name="imgb" id="imgb">Badge</button><input name="bage" type="file" id="bage" accept="image/*"/></div>
                                        
                                        <label for="souvenirsImage"><br/>&nbsp;&nbsp;Souvenirs:</label> &nbsp;&nbsp;<input type="number" value="0" min="0" max="5" name="souvenirNumber" id="souvenirNumber"/>
                                        <div>
                                            <button name="img1" id="img1">Souvenir 1</button><input name="souvenirs1" type="file" id="souvenir1" accept="image/*"/><input type="number" value="1" min="0" name="download1" style="width: 30px" id="download1"/><br/>
                                            <button name="img2" id="img2">Souvenir 2</button><input name="souvenirs2" type="file" id="souvenir2" accept="image/*"/><input type="number" value="1" min="0" name="download2" style="width: 30px" id="download2"/><br/>
                                            <button name="img3" id="img3">Souvenir 3</button><input name="souvenirs3" type="file" id="souvenir3" accept="image/*"/><input type="number" value="1" min="0" name="download3" style="width: 30px" id="download3"/><br/>
                                            <button name="img4" id="img4">Souvenir 4</button><input name="souvenirs4" type="file" id="souvenir4" accept="image/*"/><input type="number" value="1" min="0" name="download4" style="width: 30px" id="download4"/><br/>
                                            <button name="img5" id="img5">Souvenir 5</button><input name="souvenirs5" type="file" id="souvenir5" accept="image/*"/><input type="number" value="1" min="0" name="download5" style="width: 30px" id="download5"/><br/>                         
                                        </div>                                       
                                        <iframe id="upload_target" name="upload_target" src="" style="width:0;height:0;border:0px solid #fff;"></iframe>
                                    </div>
                                </td>
                                <td style= "width:20%" >  

                                    <div id="radiolevelset" style="font: 62.5%; width:auto;" >
                                            <label for="radiolevelset">Difficulty:</label>
                                            <input type="radio" style="vertical-align: center" id="low" name="radio" /><label for="low">Low</label>
                                            <input type="radio" style="vertical-align: center" id="medium" name="radio" checked="checked" /><label for="medium">Medium</label>
                                            <input type="radio" style="vertical-align: center" id="high" name="radio" /><label for="high">High</label>
                                    </div>
                                </td>
                            </tr> 
                            </table>
                        <label for="touroverview">&nbsp;&nbsp;<strong>Tour Overview</strong></label><br/>
                            <div>
                            <ul id="WaypointsCanvas">
                            </ul>
                        </div>
                            <div class="submit-button" style="float:left">  
                                &nbsp;&nbsp;<input id="saveButton" type=button value="Save the tour changes"/>
                            </div>

                            <br/>
                            <br/>
                                <br/>
                        


                    </td> 

                    <td style= "width:auto" > 
                        <div style= "width:100%" >
                            <label for="waypointlocation"><strong>Waypoint Location:</strong></label>

                            <p><br>
                                <label for="name">Place:</label>
                                <input type="text" id="address" placeholder="Enter Waypoint Name" />
                                <input type="button" name="Search" id="searchbtn" value="Search" onclick="codeAddress()">
                            </p><br>
                             Total Distance: <distance style="color:blue"></distance> meters
                            </div>
                        <div id="map-canvas" style= "width:100%"></div>
	
                        </td> 


                </tr> 
                </table>





                </fieldset>
            </form>
        </div>
    </div>
</div>