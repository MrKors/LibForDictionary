$(document).ready(function () {
    $('#deleteTranslationRef').click(function (event) {
        event.preventDefault();
        var origin = $("#origin-value").val();
        var trans = $("#chooseTranslation").val()

        $.ajax({
            url: "/words/deleteTranslation",
            method: 'get',
            data: {trans, origin},
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



