$(function() {

    $('#register').on('click', function() {

        var nickName = $('#nickName').val();
        var name = $('#name').val();
        var role = $("input[name='role']:checked").val();
        var gender = $("input[name='gender']:checked").val();
        var age = $('#age').val();
        var password = $('#password').val();
        var email = $('#email').val();

        var data = {
            nickName: nickName,
            name: name,
            role: role,
            gender: gender,
            password: password,
            email: email,
            age: age
        };

        $.ajax({
            contentType : 'application/json; charset=utf-8',
            url: '/web/register',
            type: 'POST',
            dataType : 'json',
            data: JSON.stringify(data),
            success: function(resp) {

                console.log(resp);
                if(resp == 'Register Success') {
                    window.location.href = '/web/employees/';
                } else {
                    $('#userNameTip').val('用户名不可用');
                }
            }
        })
    });
});