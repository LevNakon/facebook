let $box = $("box");
let $clicker = $("#clicker");
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
        error:function(error){
            console.log(error);
        },
        success: function (res) {
            $(res).each(function (i,odj) {
                console.log(obj.name);
            })
        }
    })
});