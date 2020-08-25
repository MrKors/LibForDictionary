$(document).ready(function () {
    alert("function ready")
    $('#deleteTranslationRef').click(function (event) {
        alert("function ON")
        event.preventDefault();
        var origin = $("#origin-value").val();
        var trans = $("#chooseTranslation").val()
        alert(origin + " " +  trans)

        $.ajax({
            url: "/words/deleteTranslation",
            method: 'get',
            data: {trans, origin},
            success: [function (data) {
                write(data)
            }]
        })
    })
})



