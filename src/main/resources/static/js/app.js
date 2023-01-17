function filter_by_titulo() {
  $.ajax({
    type: "get",
    url: "/libro/list/filter/titulo",
    data: {
      titulo: $('#titulo').val()
    },
    success: function (htmlRecibido) {
      $('#lista-libros').html(htmlRecibido);
    },
    error: function (e) {
      console.log(e);
    }
  });
}


function filter_by_autor() {
  $.ajax({
    type: "get",
    url: "/libro/list/filter/autor",
    data: {
      autor: $('#autor').val()
    },
    success: function (htmlRecibido) {
      $('#lista-libros').html(htmlRecibido);
    },
    error: function (e) {
      console.log(e);
    }
  });
}


