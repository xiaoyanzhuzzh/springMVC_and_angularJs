$(function() {

    var userId = '';
    var $this = '';
    $('.deleteUser').on('click', function() {

        $this = $(this);
        userId = $this.data('id');
    });

    $('.confirmDelete').on('click', function() {
        $.ajax({
            url: '/web/users/' + userId,
            type: 'DELETE',
            success: function(data){

                $this.closest('tr').remove();
                $('#myModal').modal('hide');
            }
        })
    });
});
