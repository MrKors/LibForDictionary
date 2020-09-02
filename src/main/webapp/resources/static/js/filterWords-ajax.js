$(document).ready(function () {
    $('#filterDictionary').on('change', function () {
        var id = $(this).val();
        // console.log(id);

        $.ajax({
            url: "/words-list/filter",
            method: 'post',
            data: {id},
            success: function (data) {
                let parser = new DOMParser().parseFromString(data, "text/html")
                // console.log(parser)
                let table = parser.getElementById('div-table')
                // console.log(table)
                $('#div-table').html(table);
            }
        })
    })
})