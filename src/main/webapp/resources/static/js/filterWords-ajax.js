$(document).ready(function () {
    $('#filterDictionary').on('change', function () {
        var id = $(this).val();
        console.log(id);

        $.ajax({
            url: "/words-list/filter",
            method: 'post',
            data: {id},
            success: function (data) {
                console.log(data)
                $('body').html(data);
            }
        })
    })
})