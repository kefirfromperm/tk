$(document).ready(function(){
    $('.js').show();
    $('.nojs').hide();

    messageEventListener();
    $('#message').change(messageEventListener);
    $('#message').keyup(messageEventListener);
});

function messageEventListener(){
    var length = $('#message').val().length;
    $('#counter').text(length);
    if(length>2000){
        $('#counter').css('color', 'red');
    }else if(length>140){
        $('#counter').css('color', 'green');
    }else{
        $('#counter').css('color', 'gray');
    }
}
