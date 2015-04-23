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
   		url: "../pdf",
   		method: "GET",
   		data: {
        	ids: XXX
   		},
        statusCode: {
            404: function() {
            	Console.log('404');
            },
        	400: function() {
            	Console.log('400');
            },
            200: function(data) {
            	Console.log('200');
                resolve(data);
            }
        }
   	});
//   		}).done(function(result) 
//   				{
//   					$('.pdf-render').html(result);
//   					$('object.pdf_obj').css("width", $('div.pdf-div').width());
//   					$('object.pdf_obj').css("height", $('div.pdf-div').height() - $('button#closepdf').height());
//   				});
}

function closepdf()
{
    $(".pdf-div").hide();
}
