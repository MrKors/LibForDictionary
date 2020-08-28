$(function (){
    $('#chooseTranslation').change(function (){
        let trans = $('#chooseTranslation :selected').text();
        document.getElementById('editTranslation').value = trans;
    })
});