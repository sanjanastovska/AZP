$(document).ready(popolniTabela);

function popolniTabela() {
    var tabela = $("#doctorTable");
//    $(tabela).html("<thead><tr><th>ID</th><th>DOCTOR</th><th>SPECIALIZATION</th><th>SHEDULES</th></tr></thead>");

//    $.ajax(
//            {
//                url: "http://localhost:8080/pregled",
//                dataType: "json",
//                success: function (data, textStatus, jqXHR) {
//                    $(tabela).html("<tr><th>ID</th><th>DOCTOR</th><th>SPECIALIZATION</th><th>SHEDULES</th></tr>");
//
//                    $.each(data, function (index, pregled) {
//                        $(tabela).append("<tr><td>" + pregled.doctor.id +
//                                "</td><td>" + pregled.doctor.name + " " + pregled.doctor.surname +
//                                "</td><td>" + pregled.doctor.spec.description +
//                                "</td><td>" + pregled.date + "  " + pregled.pacient.name + " " + pregled.pacient.surname + "</td></tr>")
//                    });
//                }
//            });

    $(tabela).DataTable({
        "processing": true,
        "lengthMenu": [[5, 10, 25, 50, -1], ["5", "10", "25 ", "50", "All"]],
        autoWidth: true,
        "ajax": {
            "url": "http://localhost:8080/doctor",
            "dataSrc": ""
        },
        "columns": [
            {"data": "id"},
            {"data": "name"},
            {"data": "spec.description"},
            {"data": "id"}
        ],
        "columnDefs": [
            {

                "render": function (data, type, row) {
                    return data + ' ' + row.surname;
                },
                "targets": 1
            },
            {

                "render": function (data, type, row) {
                    return '<button type="button" class="btn btn-primary" data-doctorid="'+ data +'" data-toggle="modal" data-target="#myModal" data-backdrop="static" data-keyboard="false" onclick="createSchedule(' + data + ')">Vidi Raspored</button>';
//                    return '<a href="http://localhost:8080/pregledView?id=' + data + '">Vidi Pregled</a>';
                },
                "targets": 3
            }
        ]
    });
    $('#myModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var doctor = button.data('doctorid') // Extract info from data-* attributes
        $("#doctorName").val(doctor);
    })
}