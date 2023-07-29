//Obtiene todos los clientes y los muestra en la tabla.
const API_URL_ROOT = "http://localhost:5100";

function getAllClients() {
    const API_URL = API_URL_ROOT+"/getClients";
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

//Consulta un cliente dado un ID.
function getClient(){
    var contentTable = document.getElementById("allClientsTable");
    var id = document.getElementById("idClient");
    const API_URL = API_URL_ROOT+"/getClient?ID="+id.value;

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

//Crea un cliente dado id, nombre, apellido, edad.
function createClient(){
    var id = document.getElementById("ID");
    var nombre = document.getElementById("Nombre");
    var apellido = document.getElementById("Apellido");
    var edad = document.getElementById("Edad");

    const API_URL = API_URL_ROOT+"/createClient?"+
                     "ID="+id.value+
                     "&Nombre="+nombre.value+
                     "&Apellido="+apellido.value+
                     "&edad="+edad.value;

    fetch(API_URL).then(response => response.text()).then(data=>{
       var responseDisplay = document.getElementById("response");
       responseDisplay.append(data);
    });
}

//Elimina un cliente dado el ID.
function deleteClient(){
    var id = document.getElementById("ID");
    const API_URL = API_URL_ROOT+"/deleteClient?"+
                     "ID="+id.value;

    fetch(API_URL).then(response => response.text()).then(data=>{
       var responseDisplay = document.getElementById("response");
       responseDisplay.append(data);
    });
}

//Actualiza un cliente dado el ID.
function updateClient(){
    var id = document.getElementById("ID");
    var nombre = document.getElementById("Nombre");
    var apellido = document.getElementById("Apellido");
    var edad = document.getElementById("Edad");

    const API_URL = API_URL_ROOT+"/updateClient?"+
                     "ID="+id.value+
                     "&Nombre="+nombre.value+
                     "&Apellido="+apellido.value+
                     "&edad="+edad.value;

    fetch(API_URL).then(response => response.text()).then(data=>{
       var responseDisplay = document.getElementById("response");
       responseDisplay.append(data);
    });
}

function logear(){
    const user = document.getElementById("user");
    const password  = document.getElementById("password");
    if(user.value === "admin" && password.value == "admin"){
        console.long("Credenciales correctas!!!");
    }
}

function submenuCliente (){
       var submenu = document.getElementById("subcliente");

       if(submenu.hidden == true){
           submenu.hidden = false;
       }else{
           submenu.hidden = true;
       }
}