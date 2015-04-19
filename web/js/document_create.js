function newSnippet() {
    e = $('#right .element').last();


    var newSnippet = $('<div class="element snippet">' +
            '<div class="checkbox"><input type="checkbox" name="choose" value="chapter-n" /></div>' +
            '<div class="block">' +
            '    <div><label for="title">Titel:</label><input type="text" name="title" class="block-title" /><br /></div>' +
            '    <div><label for="desc">Beschreibung:</label><br />' +
            '    <textarea name="desc" class="block-text"></textarea></div>' +
            '    <div><label for="tags">Tags:</label><input type="text" name="tags" class="block-tags" placeholder="definition, beispiel, lösung, übung, vl1"/></div>' +
            '    <button class="submit">Übernehmen</button><button class="remove">Löschen</button>' +
            '</div>' +
            '</div>');

    e.after(newSnippet);
}

function newChapter() {
    e = $('#right .element').last();


    var newChapter = $('<div class="element chapter">' +
            '<div class="checkbox"><input type="checkbox" name="choose" value="chapter-n" /></div>' +
            '<div class="block">' +
            '    <div><label for="title">Titel:</label><input type="text" name="title" class="block-title" value="" /><br /></div>' +
            '    <button class="submit">Übernehmen</button><button class="remove">Löschen</button>' +
            '</div>' +
            '</div>');

    e.after(newChapter);
}

function markChapter() {
    e = $(this).parent();
    var newStatus = e.find('.checkbox input').prop('checked');

    while (e.next().hasClass('snippet')) {
        e = e.next();
        e.find('.checkbox input').prop('checked', newStatus);
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

$('#right').on('click', '.block button.submit', accept);

$('#right').on('click', '.block button.remove', remove);

$('#right').on('click', '.chapter .checkbox', markChapter);

$('.btn-block').on('click', newSnippet);

$('.btn-chapter').on('click', newChapter);