// $(document).ready(function () {
//     $('#deleteBtn').on("click", function (event) {
//         event.preventDefault();
//         var word = JSON.stringify({
//             "originValue": $('#origin').val(),
//             "translation": $('#translate').val(),
//             "dictionary": null
//         });
//
//         $.ajax({
//             url: "words/delete",
//             method: "post",
//             dataType: "json",
//             data: word,
//             success: [function (data) {
//                 alert(data)
//             }]
//         })
//     });
// });
//
//
//
