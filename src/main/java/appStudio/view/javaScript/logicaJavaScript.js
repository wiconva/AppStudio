//Obtiene todos los clientes y los muestra en la tabla.
function getAllClients() {
    const API_URL = "http://localhost:5100/getClients"
    var contentTable = document.getElementById("allClientsTable");
    fetch(API_URL).then(response => response.text()).then( data =>{
    var clients = data.split(';');
    for (var i=0; i< clients.length; i++){
        var rowTable = document.createElement("tr");
        var dataRowTable = clients[i].split(",");
        for(var j=0; j< dataRowTable.length ; j++){
            var dataTable = document.createElement("td");
            dataTable.innerText=dataRowTable[j];
            rowTable.append(dataTable);
        }
        allClientsTable.append(rowTable);
    }
    });

}
//
function getClient(){
    var contentTable = document.getElementById("allClientsTable");
    var id = document.getElementById("idClient");
    const API_URL = "http://localhost:5100/getClient?ID="+id.value;

    fetch(API_URL).then(response=>response.text()).then(data=>{
        var client = data.split(",");
        var row= document.createElement("tr");
        for(var i=0; i< client.length; i++){
            var cell = document.createElement("td");
            cell.innerText = client[i];
            row.append(cell);
        }
    contentTable.append(row);
    });
}