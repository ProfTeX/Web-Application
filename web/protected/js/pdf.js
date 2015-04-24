function showpdf()
{
    var renderstr = 'c = \\pm\\sqrt{a^2 + b^2}';

    $(".pdf-div").css({
        position: "absolute",
        display: "block",
        width: "100%",
        height: "100%",
        top: 0,
        left: 0,
        zIndex: 1000000,
        background: "#FFF"
    }); //.appendTo($("#doc").css("position", "relative"));

    var snippet = [];
    $('.snippet .checkbox input').each(function () {
        if ($(this).prop('checked')) {
            snippet.push($(this).parent().parent().data('id'));
        }
    });

    $.ajax({
        url: "../pdf",
        method: "GET",
        mimeType: "application/json",
        data: {
            ids: snippet.join(',')
        },
        statusCode: {
            404: function () {
                console.log('404');
            },
            400: function () {
                console.log('400');
            },
            200: function (data) {
                $('.pdf-render').html(data);
                $('object.pdf_obj').css("width", $('div.pdf-div').width());
                $('object.pdf_obj').css("height", $('div.pdf-div').height() - $('button#closepdf').height());
            }
        }
    });
}

function closepdf()
{
    $(".pdf-div").hide();
}
