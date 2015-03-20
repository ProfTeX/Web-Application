<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>ProfTeX - Dokument bearbeiten</title>
        <link rel="stylesheet" type="text/css" href="css/document_create.css">
	<script src="js/pdf.js"></script>

    </head>
    <nav>
        <div class="logo">ProfTeX</div>
        <div class="threebars"><div></div></div>
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
            <div class="chapter-heading">1. Das ist eine Überschrift</div>
            <div class="chapter-subheading">1.1 Das ist eine Unterüberschrift</div>

            <div class="element">
                <div class="checkbox"><input type="checkbox" name="choose" value="block-1" /></div>
                <div class="block">
                    <div><label for="title">Titel:</label><input type="text" name="title" class="block-title" value="Lorem ipsum" /><br /></div>
                    <div><label for="desc">Beschreibung:</label><br />
                    <textarea name="desc" class="block-text">Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.</textarea></div>
                    <div><label for="tags">Tags:</label><input type="text" name="tags" class="block-tags" value="beweis, vl1" placeholder="definition, beispiel, lösung, übung, vl1"/></div>
                    <button id="submit">Übernehmen</button>
                </div>
            </div>
            
            <div class="element">
                <div class="checkbox"><input type="checkbox" name="choose" value="block-1" /></div>
                <div class="block">
                    <div><label for="title">Titel:</label><input type="text" name="title" class="block-title" /><br /></div>
                    <div><label for="desc">Beschreibung:</label><br />
                    <textarea name="desc" class="block-text"></textarea></div>
                    <div><label for="tags">Tags:</label><input type="text" name="tags" class="block-tags" placeholder="definition, beispiel, lösung, übung, vl1"/></div>
                    <button id="submit">Übernehmen</button>
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
    <script type="text/javascript" src="js/jquery.min.js" ></script>
    <script type = "text/javascript">
        var doc = $('#doc'),
            editor = $('#new-block');

        function editBlock() {
            e = $( this ); 
            
            var newBlock=$('<div class="element">' +
                '<div class="checkbox"><input type="checkbox" name="choose" value="block-1" /></div>' +
                '<div class="block">' +
                '    <div><label for="title">Titel:</label><input type="text" name="title" class="block-title" /><br /></div>' 
                '    <div><label for="desc">Beschreibung:</label><br />' +
                '    <textarea name="desc" class="block-text"></textarea></div>' +
                '    <div><label for="tags">Tags:</label><input type="text" name="tags" class="block-tags" placeholder="definition, beispiel, lösung, übung, vl1"/></div>' +
                '    <button id="submit">Übernehmen</button>' +
                '</div>' +
            '</div>');
            
            e.prepend(newBlock);
            /*editor.show();
            
            if(e.attr('data-title')) {
                $('.block-title').val( e.data('title') );
                $('.block-text').val( e.html() );
                $('.block-tags').val( e.data('tags') );
                
                e.remove();
            } else {
                $('.block-title').val('');
                $('.block-text').val('');
                $('.block-tags').val('');
            }*/
        }
        
        function accept() {
            var newBlock = $('<p>');

            newBlock.data('title', $('.block-title').val());
            newBlock.data('tags', $('.block-tags').val());
            newBlock.html( $('.block-text').val() );

            newBlock.click( editBlock );

            doc.append(newBlock);

            editor.hide();
        }

        $('#left > img').click(function() {
            $( this ).toggleClass('big');
        });

        //$('#new-block > button').click( accept );

        $('.btn-block').click(function() {
            editBlock.call( null );
        });
    </script>
</html>
