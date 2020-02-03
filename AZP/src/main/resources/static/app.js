var doctorJson;

function onReadyExecute() {

    $("#zakazi").click(savePregled);



//    $.getJSON("http://localhost:8080/doctor", createDoctorOptions);
    $.getJSON("http://localhost:8080/specijalizacija", createSpecOptions);
    $('#patientbirth').flatpickr({
        dateFormat: "d-m-Y"
    });

    $('#termininfo').flatpickr({

        enableTime: true,
        dateFormat: "d-m-Y H:i:S",
        time_24hr: true
    });



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

function savePregled() {

    var formData = JSON.stringify(formToJson($("#pregledForm")));






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
        }
    });

}

function createDoctorOptions(doctors) {


    var doctorSelect = $("#doctorname");
    $.each(doctors, function (index, doctor) {
        $(doctorSelect).append(new Option(doctor.name + " " + doctor.surname, doctor.id));
    }
    );
    $(doctorSelect).select2({dropdownAutoWidth: true});



}

function createSpecOptions(specialization) {
    var specializationSelect = $("#specname");
    $.each(specialization, function (index, specialization) {
        $(specializationSelect).append(new Option(specialization.description, specialization.id));
    }
    );
    $(specializationSelect).select2().on('change', selectSpecialization);
    $('#doctorname').select2({dropdownAutoWidth: true});



}

function resetDoctorOptions() {
    $("#doctorname option[value!=0]").remove();
    $('#doctorname').select2({dropdownAutoWidth: true});
}
function selectSpecialization() {
    var selectedSpecId = $("#specname").val();
    if (selectedSpecId != 0) {
        $.getJSON("http://localhost:8080/doctor/spec/" + selectedSpecId, createDoctorOptions);
    } else {
        resetDoctorOptions();
    }
}


$(document).ready(onReadyExecute);