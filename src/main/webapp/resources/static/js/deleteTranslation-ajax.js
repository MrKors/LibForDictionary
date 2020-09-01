$(document).ready(function () {
    $('#deleteTranslationRef').click(function (event) {
        event.preventDefault();
        var id = $('#chooseTranslation').val()
        console.log(id)
        var origin = $("#originValue").val();
        console.log(origin)
        // var trans = $("#chooseTranslation").val()

        $.ajax({
            url: "/words/deleteTranslation",
            method: 'get',
            data: {id, origin},
            // contentType: 'text/html',
            success: [function (data) {
                console.log(data)
                $('body').html(data);
            }],
            error: [function () {
                alert("Choose translation to delete it");
            }]
        });
    });
});



