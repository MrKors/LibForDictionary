$(document).ready(function () {
    $('#filterDictionary').on('change', function () {
        var id = $(this).val();
        console.log(id);

        $('#search').autocomplete({

            source: '/words/search/' + id,
            //     function (request, response) {
            //     $.getJSON('/words/search', {
            //         term: request.term,
            //         id: $('#id').val()
            //     }, response);
            // },
            //     function (request, response) {
            //     $.ajax({
            //         url: "/words/search",
            //         dataType: 'json',
            //         data: {
            //             term: request.term,
            //             id: $('#id').val()
            //         },
            //         success: function (data) {
            //             console.log(data);
            //             response (data);
            //         }
            //     })
            // }
        })
    })
})