function savePregled() {

    var formData = JSON.stringify(formToJson($("#pregledForm")));
    var doctorId=$("#doctorName").val();





    $.ajax({
        cache: false,
        type: "POST",
        url: '/pregled/zakazi',
        data: formData,
        datatype: 'json',
        contentType: 'application/json;charset=UTF-8',
        success: function (data) {
            console.log(data);
            alert("An appointment is scheduled.");
            createSchedule(doctorId);
        }
    });
    
}
function createSchedule(doctorId) {
    var startDateVar = moment();
    var calendar = null;
    createCal(startDateVar, doctorId);
    $('#previousCal').click(function () {
        createCal(startDateVar.subtract(1, 'w').format('DD-MM-YYYY'), doctorId);
    }

    );
    $('#nextCal').click(function () {
        createCal(startDateVar.add(1, 'w').format('DD-MM-YYYY'), doctorId);
    }

    );
}
function createCal(startDateString, doctorId) {
    $('.mycal').easycal('destroy');
    $('.mycal').easycal({
        startDate: startDateString, // OR 31/10/2104
        timeFormat: 'HH:mm',
        columnDateFormat: ' DD.MM.YYYY',
        minTime: '09:00:00',
        maxTime: '19:00:00',
        slotDuration: 30,
        timeGranularity: 30,

        dayClick: function (el, time) {
            console.log('Slot selected: ' + time);
            
            $('#termininfo').val(time);
            $('#pacientModal').modal({
                keyboard: false,
                backdrop: 'static'
            });
        },
        eventClick: function (eventId) {
            console.log('Event was clicked with id: ' + eventId);
        },

        events: getAjaxEvents(doctorId),

        overlapColor: '#FF0',
        overlapTextColor: '#000',
        overlapTitle: 'Multiple'
    });
}

function getAjaxEvents(doctorId) {
    var timetableEvents;
    //$.getJSON("http://localhost:8080/pregled/doctor/6", function(data){timetableEvents=data});

    $.ajax({
        url: "http://localhost:8080/pregled/doctor/" + doctorId,
        dataType: 'json',
        async: false,

        success: function (data) {
            timetableEvents = data
        }
    });
    return timetableEvents;
}
function formToJson(form) {
    var formArray = $(form).serializeArray();
    console.log(formArray);
    var jsonObj = {};
    jQuery.map(formArray, function (n, i) {
        jsonObj[n.name] = n.value;
    });

    return jsonObj;
}