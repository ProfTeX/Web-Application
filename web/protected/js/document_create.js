function newSnippet() {
    if ($('#right .chapter').length === 0) {
        newChapter();
    }

    var e = $('#right .element').last();
    var pos = e.data('position') + 1;

    var chapter_id = 0;

    if (e.hasClass('chapter')) {
        chapter_id = e.data('id');
    } else {
        chapter_id = e.data('chapter-id');
    }

    var newSnippet = $('<div id="new-snippet" data-id="" data-position="' + pos + '" data-chapter-id="' + chapter_id + '" class="element snippet">' +
            '<div class="checkbox"><input type="checkbox" name="choose" value="" /></div>' +
            '<div class="block">' +
            '    <div><label for="title">Titel:</label><input type="text" name="title" class="block-title" /><br /></div>' +
            '    <div><label for="desc">Beschreibung:</label><br />' +
            '    <textarea name="desc" class="block-text"></textarea></div>' +
            '    <div><label for="tags">Tags:</label><input type="text" name="tags" class="block-tags" placeholder="' + tags + '" /></div>' +
            '    <button class="submit">Übernehmen</button><button class="remove">Löschen</button>' +
            '</div>' +
            '</div>');

    e.after(newSnippet);
    $('#new-snippet').removeAttr('id');
}

function newChapter() {
    var e = $('#tag-filter');
    var pos = 0;

    if ($('#right .element').last().data('position') !== undefined) {
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

    $('.chosen-container .search-choice span').each(function () {
        //element is removed after function call but added before
        if (params.deselected !== this.innerHTML) {
            tags.push(this.innerHTML);
        }
    });

    if (tags.length !== 0) {
        var regex = new RegExp(tags.join('|'));
        $('#right .snippet').each(function () {
            if ($(this).find('.block-tags').val().match(regex, 'gi')) {
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
    var block = $(this).parent().parent();
    if (block.data('id') === ""){
        block.remove();
        return;
    }
    if (block.hasClass('chapter')) {
        $.ajax({
            url: "../chapter?id="+block.data('id'),
            mimeType: "application/json",
            method: "DELETE",
//            data: {
//                id: block.data('id')
//            },
            statusCode: {
                404: function () {
                    console.log('404');
                },
                400: function () {
                    console.log('400');
                },
                200: function (data) {
                    console.log('200');
                    block.remove();
                }
            }
        });
    }
    if (block.hasClass('snippet')) {
        $.ajax({
            url: "../snippet?id="+block.data('id'),
            mimeType: "application/json",
            method: "DELETE",
//            data: {
//                id: block.data('id')
//            },
            statusCode: {
                404: function () {
                    console.log('404');
                },
                400: function () {
                    console.log('400');
                },
                200: function (data) {
                    console.log('200');
                    block.remove();
                }
            }
        });
    }
}

function getBlocks() {
    var output = '';
    for (var i = 0; i < tagObjs.length; i++) {
        output += '<option value="' + tagObjs[i].name + '">' + tagObjs[i].name + '</option>\n';
    }
    
    $('#tag-filter select').html(output).trigger('chosen:updated');;
    
    $.ajax({
        url: '../room',
        method: 'GET',
        mimeType: "application/json",
        data: {
            id: room
        },
        statusCode: {
            400: function () {
                console.log('StatusCode: 400');
            },
            200: function (data) {
                var output = '';
                //var data = JSON.parse(result.responseText);
                console.log(data);
                for (var i = 0; i < data[0].chapters.length; i++) {
                    output += '<div data-id="' + data[0].chapters[i].id + '" data-position="' + data[0].chapters[i].position + '" class="element chapter">' +
                            '<div class="checkbox"><input type="checkbox" name="choose" /></div>' +
                            '<div class="block">' +
                            '    <div><label for="title">Titel:</label><input type="text" name="title" class="block-title" value="' + data[0].chapters[i].name + '" /><br /></div>' +
                            '    <button class="submit">Übernehmen</button><button class="remove">Löschen</button>' +
                            '</div>' +
//                            '<div class="move_up"><span></span></div>' +
//                            '<div class="move_down"><span></span></div>' +
                            '</div>';
                    for (var z = 0; z < data[0].chapters[i].snippets.length; z++) {
                        var selTags = data[0].chapters[i].snippets[z].tags.map(function(e){return e.name;}).join(', ');
                        output += '<div data-id="' + data[0].chapters[i].snippets[z].id + '" data-chapter-id="' + data[0].chapters[i].id + '" data-position="' + data[0].chapters[i].snippets[z].position + '" class="element snippet">' +
                                '<div class="checkbox"><input type="checkbox" name="choose" /></div>' +
                                '<div class="block">' +
                                '    <div><label for="title">Titel:</label><input type="text" name="title" class="block-title" value="' + data[0].chapters[i].snippets[z].title + '" /><br /></div>' +
                                '    <div><label for="desc">Beschreibung:</label><br />' +
                                '    <textarea name="desc" class="block-text">' + data[0].chapters[i].snippets[z].content + '</textarea></div>' +
                                '    <div><label for="tags">Tags:</label><input type="text" name="tags" class="block-tags" placeholder="' + tags + '" value="' + selTags + '"/></div>' +
                                '    <button class="submit">Übernehmen</button><button class="remove">Löschen</button>' +
                                '</div>' +
//                                '<div class="move_up"><span></span></div>' +
//                                '<div class="move_down"><span></span></div>' +
                                '</div>';
                    }
                }
                $('#tag-filter').after(output);
            }
        }
    });
}

function accept() {
    var block = $(this).parent().parent();
    
    // if chapter
    if (block.hasClass('chapter')) {
        if (block.data('id') === "") {
            $.ajax({
                url: "../chapter",
                method: "PUT",
                mimeType: "application/json",
                data: {
                    name: block.find('.block-title').val(),
                    position: block.data('position'),
                    room: room
                },
                statusCode: {
                    404: function () {
                        console.log('404');
                    },
                    400: function () {
                        console.log('400');
                    },
                    200: function (data) {
                        block.data('id', data.id);
                    }
                }
            });
        }
        else {
            $.ajax({
                url: "../chapter",
                method: "POST",
                mimeType: "application/json",
                data: {
                    id: block.data('id'),
                    name: block.find('.block-title').val(),
                    position: block.data('position')
                },
                statusCode: {
                    404: function () {
                        console.log('404');
                    },
                    400: function () {
                        console.log('400');
                    },
                    200: function (data) {
                        console.log('200');
                    }
                }
            });
        }
    }
    // if snippet
    if (block.hasClass('snippet')) {
        if (block.data('id') === "") {
            $.ajax({
                url: "../snippet",
                method: "PUT",
                mimeType: "application/json",
                data: {
                    title: block.find('.block-title').val(),
                    content: block.find('.block-text').val(),
                    tags: block.find('.block-tags').val().replace(' ', ''),
                    position: block.data('position'),
                    chapter: block.data('chapter-id')
                },
                statusCode: {
                    404: function () {
                        console.log('404');
                    },
                    400: function () {
                        console.log('400');
                    },
                    200: function (data) {
                        console.log(data);
                        block.data('id', data.id);
                    }
                }
            });
        }
        else {
            $.ajax({
                url: "../snippet",
                method: "POST",
                mimeType: "application/json",
                data: {
                    id: block.data('id'),
                    title: block.find('.block-title').val(),
                    content: block.find('.block-text').val(),
                    tags: block.find('.block-tags').val().replace(' ', ''),
                    position: block.data('position'),
                    chapter: block.data('chapter-id')
                },
                statusCode: {
                    404: function () {
                        console.log('404');
                    },
                    400: function () {
                        console.log('400');
                    },
                    200: function (data) {
                        console.log('200');
                    }
                }
            });
        }
    }
}

function addImages( images ) {
	images.forEach( function( e ) {
		var elem = $('<img />');
		elem.attr('src', 'images/' + e);
		$('#left').prepend( elem );
	});
}

function loadImages() {
	$.get('../image?room=' + room.id ).done( function( data ) {
		console.log(data);
		data = JSON.parse( data );
		addImages( data );
	});
}

document.addEventListener("DOMContentLoaded", getBlocks);
document.addEventListener("DOMContentLoaded", loadImages);


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