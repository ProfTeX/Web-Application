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

function accept() {
    console.log("Accept works!");
}

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