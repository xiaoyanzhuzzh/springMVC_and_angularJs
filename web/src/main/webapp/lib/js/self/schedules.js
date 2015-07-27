$(function() {

    var scheduleIdDelete = '';
    var $this = '';
    $('#publicScheduleUpdate').hide();
    $('#privateScheduleUpdate').hide();

    $('.deleteSchedule').on('click', function() {

        $this = $(this);
        scheduleIdDelete = $this.data('id');
    });

    $('.confirmDelete').on('click', function() {
        $.ajax({
            url: '/web/schedules/' + scheduleIdDelete,
            type: 'DELETE',
            success: function(){

                $this.closest('tr').remove();
                $('#myModal').modal('hide');
            }
        })
    });

    $('.updatePublicSchedule').on('click', function() {

        var $this = $(this);
        var id = $this.data('id');
        $.ajax({
            url: '/web/schedules/' + id,
            type: 'GET',
            contentType: 'application/json; charset=utf-8',
            success: function(data) {

                console.log(JSON.parse(data)[1]);

                var result = JSON.parse(data);
                if(result[0].customer == null) {
                console.log(JSON.parse(data)[0].course);

                    var $mySelect = $('#mySelect');
                    $.each(result[1], function (i, item) {
                        $mySelect.append($('<option>', {
                            value: item.id,
                            text : item.name
                        }));
                    });
                    $('#publicScheduleId').val(result[0].id);
                    
                    $('#publicScheduleUpdate').show();
                } else {
                    $('#privateScheduleUpdate').show();

                }

            }
        });

    });
});
