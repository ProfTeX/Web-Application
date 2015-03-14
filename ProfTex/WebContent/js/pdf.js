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


    katex.render(renderstr, $('.pdf-render')[0]);
}

function closepdf()
{
    $(".pdf-div").hide();
}
