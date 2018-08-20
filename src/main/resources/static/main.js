let $box = $("box");
let $clicker = $("#clicker");
let token = $('#_csrf').attr('content');
let header = $('#_csrf_header').attr('content');
$clicker.click(function () {
    let $input = $("#inp");
    let parametr = $input.val();
    $input.val("");
    let param = JSON.stringify({parametr});
    $.ajax({
        url: 'http://localhost:8080/findUsers',
        type: 'PUT',
        data: param,
        contentType:'application/json',
        beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token);
        },
        success: function(data, textStatus, jqXHR) {
            console.log(data);
            $(data).each(function (i, obj) {
                let $div = $("<div/>", {
                    text: obj.name
                });
                $box.append($div);
            })
        },
        error: function(request, status, error) {
            alert(status);
            alert(error);
        }
    })
});