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

    $.ajax({
    	  url: "PDFServlet"
    	}).done(function(result) 
    	{
    	    $('.pdf-render').html(result);
    	    $('object.pdf_obj').css("width", $('div.pdf-div').height());
    	    $('object.pdf_obj').css("height", $('div.pdf-div').width());
    		//alert(result);
    	});
}

function closepdf()
{
    $(".pdf-div").hide();
}
