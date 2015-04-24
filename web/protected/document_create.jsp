<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.TagAccess" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>ProfTeX - Dokument bearbeiten</title>
        <link href="../css/document_create.css" rel="stylesheet" type="text/css"/>
        <link href="../css/chosen.css" rel="stylesheet" type="text/css"/>
        <script src="js/pdf.js"></script>
        <script>
            var room = <%=request.getParameter("room")%>;
            var tagObjs = <%=(new TagAccess()).getAllTags().toString()%>;
            var tags = tagObjs.map(function(e){return e.name;}).join(', ');
            console.log(tags);
        </script>

    </head>
    <body>
        <nav>
            <div class="logo"><a href = "../Rooms" style="text-decoration: none;color:#FFF;">ProfTeX</a></div>
            <a href ="logout.jsp" class="logout"><span></span></a>
            <a href ="user_config.jsp"  class="settings"><span></span></span></a>
        </nav>
        <div id="content">
            <div id="left">
                <div id="upload">
                    <input type="file" value="Bild hochladen"/>
                </div>
            </div>
            <div id="right">
                <div id="tag-filter">
                    <select multiple data-placeholder="Filter-Tags auswählen...">
                        
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
    </body>
</html>
