<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>ProfTeX - Dokument bearbeiten</title>
        <link href="css/document_create.css" rel="stylesheet" type="text/css"/>
        <link href="css/chosen.css" rel="stylesheet" type="text/css"/>
	<script src="js/pdf.js"></script>

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
                <select multiple data-placeholder="Auswählen...">
                    <option value="beispiel">beispiel</option>
                    <option value="beweis">beweis</option>
                    <option value="definition">definition</option>
                    <option value="lösung">lösung</option>
                    <option value="übung">übung</option>
                    <option value="vl1">vl1</option>
                    <option value="vl2">vl2</option>
                </select>
            </div>
            <div class="element chapter">
                <div class="checkbox"><input type="checkbox" name="choose" value="chapter-1" /></div>
                <div class="block">
                    <div><label for="title">Titel:</label><input type="text" name="title" class="block-title" value="Lorem ipsum" /><br /></div>
                    <button class="submit">Übernehmen</button><button class="delete">Löschen</button>
                </div>
            </div>

            <div class="element snippet">
                <div class="checkbox"><input type="checkbox" name="choose" value="snippet-1" /></div>
                <div class="block">
                    <div><label for="title">Titel:</label><input type="text" name="title" class="block-title" value="Lorem ipsum" /><br /></div>
                    <div><label for="desc">Beschreibung:</label><br />
                    <textarea name="desc" class="block-text">Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.</textarea></div>
                    <div><label for="tags">Tags:</label><input type="text" name="tags" class="block-tags" value="beweis, vl1" placeholder="definition, beispiel, lösung, übung, vl1"/></div>
                    <button class="submit">Übernehmen</button><button class="delete">Löschen</button>
                </div>
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
    <script type = "text/javascript">
        function newSnippet() {
            e = $('#right .element').last();
            
            
            var newSnippet=$('<div class="element snippet">' +
                '<div class="checkbox"><input type="checkbox" name="choose" value="chapter-n" /></div>' +
                '<div class="block">' +
                '    <div><label for="title">Titel:</label><input type="text" name="title" class="block-title" /><br /></div>' +
                '    <div><label for="desc">Beschreibung:</label><br />' +
                '    <textarea name="desc" class="block-text"></textarea></div>' +
                '    <div><label for="tags">Tags:</label><input type="text" name="tags" class="block-tags" placeholder="definition, beispiel, lösung, übung, vl1"/></div>' +
                '    <button class="submit">Übernehmen</button><button class="delete">Löschen</button>' +
                '</div>' +
            '</div>');
            
            e.after(newSnippet);
        }
        
        function newChapter() {
            e = $('#right .element').last();
            
            
            var newChapter=$('<div class="element chapter">' +
                '<div class="checkbox"><input type="checkbox" name="choose" value="chapter-1" /></div>' +
                '<div class="block">' +
                '    <div><label for="title">Titel:</label><input type="text" name="title" class="block-title" value="" /><br /></div>' +
                '    <button class="submit">Übernehmen</button><button class="delete">Löschen</button>' +
                '</div>' +
            '</div>');
            
            e.after(newChapter);
        }
        
        function accept() {
            console.log("Accept works!");
        }

        $('#left > img').click(function() {
            $( this ).toggleClass('big');
        });

        $('#tag-filter select').chosen({
            width: "100%",
            no_results_text: "Der Tag existiert bislang nicht!"
        });

        $('.block > button.submit').click( accept );

        $('.btn-block').click( newSnippet );
        
        $('.btn-chapter').click( newChapter );
    </script>
</html>
