// Generated by CoffeeScript 1.9.1
(function() {
  var $closenewroom, $createroom, $newroom, $popup, $roombox, $roomname;

  $newroom = $('#new_room');

  $popup = $('#popup');

  $roomname = $('#newroomname');

  $createroom = $('#createroom');

  $closenewroom = $('#popup .close');

  $roombox = $('#roombox');

  $newroom.click($.fn.show.bind($popup));

  $closenewroom.click($.fn.hide.bind($popup));

  $createroom.click(function() {
    return $.ajax({
      method: 'PUT',
      url: 'room',
      data: {
        name: $roomname.val()
      }
    }).done(function(data) {
      var it, url;
      data = JSON.parse(data).pop();
      url = 'protected/document_create.jsp?room=' + data.id;
      it = $('<a>').attr('href', url).addClass('button').html(data.name);
      return $roombox.append(it);
    });
  });

}).call(this);
