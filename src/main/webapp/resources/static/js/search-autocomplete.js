$(document).ready(function () {
    $('#search').autocomplete({
        source: "/words/search"
        //     function (request, response) {
        //     $.ajax({
        //         url: "/words/search",
        //         dataType: 'json',
        //         data: {term: request.term},
        //         success: function (data) {
        //             console.log(data);
        //
        //         }
        //     })
        // }
    })
})