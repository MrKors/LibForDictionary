// alert(123)
$(function (){
    // alert("start func");
    $('#chooseTranslation').change(function (){
        var trans = $('#chooseTranslation :selected').val();
        // alert(trans);
        document.getElementById('editTranslation').value = trans;
    })
    // alert("end func");
});