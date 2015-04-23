function newSnippet() {
    var e = $('#tag-filter');
    var pos = 0;
    
    if($('#right .element').last().data('position') !== undefined) {
        e = $('#right .element').last();
        pos = e.data('position') + 1;
    };

    var newSnippet = $('<div id="new-snippet" data-id="" data-position="' + pos + '" class="element snippet">' +
            '<div class="checkbox"><input type="checkbox" name="choose" value="" /></div>' +
            '<div class="block">' +
            '    <div><label for="title">Titel:</label><input type="text" name="title" class="block-title" /><br /></div>' +
            '    <div><label for="desc">Beschreibung:</label><br />' +
            '    <textarea name="desc" class="block-text"></textarea></div>' +
            '    <div><label for="tags">Tags:</label><input type="text" name="tags" class="block-tags" placeholder="definition, beispiel, lösung, übung, vl1"/></div>' +
            '    <button class="submit">Übernehmen</button><button class="remove">Löschen</button>' +
            '</div>' +
            '</div>');

    e.after(newSnippet);
    $('#new-snippet').removeAttr('id');
}

function newChapter() {
    var e = $('#tag-filter');
    var pos = 0;
    
    if($('#right .element').last().data('position') !== undefined) {
        e = $('#right .element').last();
        pos = e.data('position') + 1;
    };

    var newChapter = $('<div id="new-chapter" data-id="" data-position="' + pos + '" class="element chapter">' +
            '<div class="checkbox"><input type="checkbox" name="choose" value="" /></div>' +
            '<div class="block">' +
            '    <div><label for="title">Titel:</label><input type="text" name="title" class="block-title" value="" /><br /></div>' +
            '    <button class="submit">Übernehmen</button><button class="remove">Löschen</button>' +
            '</div>' +
            '</div>');
    
    e.after(newChapter);
    $('#new-chapter').removeAttr('id');
}

function selectChapter() {
    e = $(this).parent();
    var newStatus = e.find('.checkbox input').prop('checked');

    while (e.next().hasClass('snippet')) {
        e = e.next();
        e.find('.checkbox input').prop('checked', newStatus);
    }
}

function tagged(e, params) {
    var tags = [];
    
    $('.chosen-container .search-choice span').each(function(){
        //element is removed after function call but added before
        if(params.deselected !== this.innerHTML) {
            tags.push(this.innerHTML);
        }
    });
    
    if (tags.length !== 0) {
        var regex = new RegExp(tags.join('|'));    
        $('#right .snippet').each(function() {
            if($(this).find('.block-tags').val().match(regex, 'gi')) {
                $(this).find('.checkbox input').prop('checked', true);
            } else {
                $(this).find('.checkbox input').prop('checked', false);
            }
        });
    } else {
        $('#right .snippet').find('.checkbox input').prop('checked', false);
    }
}

function remove() {
    e = $(this);
    console.log(e);
    e.parent().parent().remove();
}

function getBlocks() {
    $.ajax({
        url: '../room?id=' + room,
        method: 'GET',
        mimeType: "application/json",
        statusCode: {
            400: function() {
                console.log('StatusCode: 400');
            },
            200: function(data) {
                var output = '';
                for(var i = 0; i < data[0].chapters.length; i++) {
                    output += '<div data-id="' + data[0].chapters[i].id + '" data-position="' + data[0].chapters[i].position + '" class="element chapter">' +
                                    '<div class="checkbox"><input type="checkbox" name="choose" /></div>' +
                                    '<div class="block">' +
                                    '    <div><label for="title">Titel:</label><input type="text" name="title" class="block-title" value="' + data[0].chapters[i].name + '" /><br /></div>' +
                                    '    <button class="submit">Übernehmen</button><button class="remove">Löschen</button>' +
                                    '</div>' +
                                    '</div>';
                    for(var z = 0; z < data[0].chapters[i].snippets.length; z++) {
                        output += '<div data-id="' + data[0].chapters[i].snippets[z].id + '" data-chapter-id="' + data[0].chapters[i].id + '" data-position="' + data[0].chapters[i].snippets[z].position + '" class="element snippet">' +
                                    '<div class="checkbox"><input type="checkbox" name="choose" /></div>' +
                                    '<div class="block">' +
                                    '    <div><label for="title">Titel:</label><input type="text" name="title" class="block-title" value="' + data[0].chapters[i].snippets[z].title + '" /><br /></div>' +
                                    '    <div><label for="desc">Beschreibung:</label><br />' +
                                    '    <textarea name="desc" class="block-text">' + data[0].chapters[i].snippets[z].content + '</textarea></div>' +
                                    '    <div><label for="tags">Tags:</label><input type="text" name="tags" class="block-tags" placeholder="definition, beispiel, lösung, übung, vl1" value="' + data[0].chapters[i].snippets[z].tags.join(',') + '"/></div>' +
                                    '    <button class="submit">Übernehmen</button><button class="remove">Löschen</button>' +
                                    '</div>' +
                                    '</div>';
                    }
                    $('#tag-filter').after(output);
                }
                
            }
        }
    });
}

function accept() {
	var block = $(this).parent().parent();
	if(block.hasClass('chapter')){
		if ($(this).parent().parent().data('id')===""){
			$.ajax({
	            url: "chapter/",
	            method: "POST",
	            data: {
	                name: block.find('.block-title').val(),
	                room: room,
	                snippets: snippets
	            },
	            statusCode: {
	                401: function() {
	                    reject({status: 401 });
	                },
	                200: function(data) {
	                    resolve(data);
	                }
	            }
	        });
		}
		else{
			$.ajax({
	            url: "chapter/",
	            method: "PUT",
	            data: {
	                name: email,
	                room: password,
	                snippets: snippets
	            },
	            statusCode: {
	                401: function() {
	                    reject({status: 401 });
	                },
	                200: function(data) {
	                    resolve(data);
	                }
	            }
	        });
		}
		if($(this).parent().parent().hasClass('snippet')===""){
			
		}
		else{
			
		}
		
		
		
	}
	
    console.log("Accept works!");
}

document.addEventListener("DOMContentLoaded", getBlocks);

$('#left > img').click(function () {
    $(this).toggleClass('big');
});

$('#tag-filter select').chosen({
    width: "100%",
    no_results_text: "Der Tag existiert bislang nicht!"
});

$('#tag-filter select').on('change', tagged);

$('#right').on('click', '.block button.submit', accept);

$('#right').on('click', '.block button.remove', remove);

$('#right').on('click', '.chapter .checkbox', selectChapter);

$('.btn-block').on('click', newSnippet);

$('.btn-chapter').on('click', newChapter);