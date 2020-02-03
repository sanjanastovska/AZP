$(document).ready(popolniTabela);

function popolniTabela() {
    var tabela = $("#preglediTable");
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

    $('#preglediTable').DataTable({
        "processing": true,
        "lengthMenu": [[5, 10, 25, 50, -1], ["5", "10", "25 ", 50, "All"]],
        "ajax": {
            "url": "http://localhost:8080/pregled",
            "dataSrc": ""
        },
        "columns": [
            {"data": "id"},
            {"data": "doctor.name"},
            {"data": "doctor.spec.description"},
            {"data": "date"},
            {"data": "id"}
        ],
        "createdRow": function (row, data, dataIndex) {
            if (data.pacient.name == "Sanja") {
                $(row).addClass('alert-warning');
            }
        },
        "columnDefs": [
            {

                "render": function (data, type, row) {
                    return data + ' ' + row.doctor.surname;
                },
                "targets": 1
            },
            {

                "render": function (data, type, row) {
                    return '<a href="http://localhost:8080/pregledView?id=' + data + '">Vidi Pregled</a>';
                },
                "targets": 4
            },
            {

                "render": function (data, type, row) {
                    return data + ' ' + row.pacient.name + ' ' + row.pacient.surname;
                },
                "targets": 3
            }
        ]
    });
}