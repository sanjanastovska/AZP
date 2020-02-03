var proceduri = [];

$(document).ready(loadPregledById);
function zacuvajPregled() {
    var postObject = {};
    postObject.id = new URLSearchParams(window.location.search.slice(1)).get('id');
    postObject.komentar = $("#comm").val();
    postObject.simptomi = $("#simpt").val();
    postObject.proceduri = $("#izvrsenipreg").select2().val();
    
     $.ajax({
        cache: false,
        type: "POST",
        url: '/pregled/zacuvaj',
        data: JSON.stringify(postObject),
        datatype: 'json',
        contentType: 'application/json;charset=UTF-8',
        success: function (data) {
            console.log(data);
            alert("Podatocite se zacuvani");
        }
    });
    console.log(postObject);
}
function createProcedruresOptions(procedures) {
    var procedureSelect = $("#izvrsenipreg");
    $(procedureSelect).html("");

    $.each(procedures, function (index, arrayElement) {
        $(procedureSelect).append(new Option(arrayElement.procedure.description, arrayElement.procedure.description));
    }
    );
    $(procedureSelect).select2().val(proceduri).change();

}
function loadPregledById() {
    var pregledId = new URLSearchParams(window.location.search.slice(1)).get('id');
    var urlPregled = "/pregled/" + pregledId;

    var detaliZaPregledot;
    $.getJSON(urlPregled, null, function (pregled) {
        var doctorExamDetails = pregled.doctor.name + " " + pregled.doctor.surname + " " + pregled.doctor.spec.description + " @" + pregled.date;
        $("#doctorExamDetails").html(doctorExamDetails);

        var pacientImeIPrezime = pregled.pacient.name + " " + pregled.pacient.surname;
        $("#pacientImePrezime").html(pacientImeIPrezime);

        var dateOfBirth = pregled.pacient.dateOfBirth;
        $("#dateOfBirth").html(dateOfBirth);

        var adresa = pregled.pacient.address;
        $("#address").html(adresa);

        var socialnumber = pregled.pacient.socialSecNum;
        $("#SocialNo").html(socialnumber);


        var simptomi = pregled.simptomi;
        $("#simpt").val(simptomi);

//        var preg = pregled.id.komentar;
//       $("#izvrsenipreg").html(preg);
//       
        var komentari = pregled.komentar;
        $("#comm").val(komentari);
        proceduri = pregled.proceduriStringArray ? pregled.proceduriStringArray : [];

        $.getJSON("http://localhost:8080/medProcWithSpec/specijalizacija/" + pregled.doctor.spec.id, createProcedruresOptions);

    });



}
