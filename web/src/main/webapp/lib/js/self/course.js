$(document).ready(function() {


    var $updateCourseForm = $('#updateCourseForm');

    $updateCourseForm.submit(function() {

        console.log('++++++++++');
        var id = $('#id').val();
        var name = $('#name').val();
        var coach = $('#coachSelect').find("option:selected").data('id');
        console.log(id);
        console.log(name);
        console.log(coach);

        var data = {
            id: id,
            name: name,
            coachId: coach
        };

        $.ajax({
            contentType : 'application/json; charset=utf-8',
            url: '/web/courses/update',
            type: 'POST',
            dataType : 'json',
            data: JSON.stringify(data),
            success: function(data) {
                alert('hahah');
            }
        })
    });
});