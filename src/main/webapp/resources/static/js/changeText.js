$(function (){
    $('#chooseTranslation').change(function (){
        var trans = $('#chooseTranslation :selected').val();
        document.getElementById('editTranslation').value = trans;
    })
});