<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>ProfTeX - Dokument bearbeiten</title>
        <link href="../css/document_create.css" rel="stylesheet" type="text/css"/>
        <link href="../css/chosen.css" rel="stylesheet" type="text/css"/>
	<script src="js/pdf.js"></script>
	<script>var room = <%=request.getParameter("room")%></script>

    </head>
    <nav>
        <div class="logo">ProfTeX</div>
        <a href ="user_config.jsp"><span class="threebars"><div></div></span></a>
    </nav>
    <div id="content">
        <div id="left">
            <img src="beispiel.jpg" />
            <img src="beispiel.jpg" />
            <img src="beispiel.jpg" />
            <img src="beispiel.jpg" />
            <div id="upload">
            	<input type="file" value="Bild hochladen"/>
            </div>
        </div>
        <div id="right">
            <div id="tag-filter">
                <select multiple data-placeholder="Filter-Tags auswählen...">
                    <option value="beispiel">beispiel</option>
                    <option value="beweis">beweis</option>
                    <option value="definition">definition</option>
                    <option value="lösung">lösung</option>
                    <option value="übung">übung</option>
                    <option value="vl1">vl1</option>
                    <option value="vl2">vl2</option>
                </select>
            </div>                
            <div class="new-element">
                <button class="btn-chapter">Neues Kapitel</button>
                <button class="btn-block">Neuer Block</button>
            </div>
            
            <button id="pdf" onclick="showpdf()">PDF anzeigen</button>
	    <div class="pdf-div" style="display: none;">
	      <div class="pdf-content">
		<div class="pdf-render"></div>
	      </div>
	      <button id="closepdf" onclick="closepdf()">Close PDF</button>
	    </div>            
        </div>

    </div>
    <script src="js/jquery.min.js" type="text/javascript"></script>
    <script src="js/chosen.jquery.min.js" type="text/javascript"></script>
    <script src="js/document_create.js" type="text/javascript"></script>
</html>
