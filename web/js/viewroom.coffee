$newroom = $ '#new_room'
$popup = $ '#popup'
$roomname = $ '#newroomname'
$createroom = $ '#createroom'
$closenewroom = $ '#popup .close'
$roombox = $ '#roombox'

$newroom.click $.fn.show.bind($popup)
$closenewroom.click $.fn.hide.bind($popup)

$createroom.click () ->
  $.ajax
    method: 'PUT'
    url: 'room'
    data:
      name: $roomname.val()
  .done ( data ) ->
    data = data.pop()
    url = 'protected/document_create.jsp?room=' + data.id
    it = $('a').attr('href', url ).addClass('button').html( data.name )
    $roombox.append it