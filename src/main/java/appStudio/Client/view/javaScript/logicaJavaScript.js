//Obtiene todos los clientes y los muestra en la tabla.
const API_URL_ROOT = "http://localhost:5100";

function getAllClients() {
    const API_URL = API_URL_ROOT+"/getClients";
    var contentTable = document.getElementById("allClientsTable");
    fetch(API_URL).then(response => response.text()).then( data =>{
    var clients = data.split(';');
    for (var i=0; i< clients.length-1; i++){
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
    var id =  1;
    var nombre = document.getElementById("Nombre");
    var apellido = document.getElementById("Apellido");
    var edad = document.getElementById("Edad");
    var responseDisplay = document.getElementById("response");
    responseDisplay.innerText = "";

    const API_URL = API_URL_ROOT+"/createClient?"+
                     "ID="+id+
                     "&Nombre="+nombre.value+
                     "&Apellido="+apellido.value+
                     "&edad="+edad.value;

    fetch(API_URL).then(response => response.text()).then(data=>{
       responseDisplay.append(data);
    });
}

//Elimina un cliente dado el ID.
function deleteClient(){
    var id = document.getElementById("ID");
    const API_URL = API_URL_ROOT+"/deleteClient?"+ "ID="+id.value;

    var responseDisplay = document.getElementById("response");
    responseDisplay.innerText = "";

    fetch(API_URL).then(response => response.text()).then(data=>{
       responseDisplay.append(data);
    });
}

//Actualiza un cliente dado el ID.
function updateClient(){
    var id = document.getElementById("ID");
    var nombre = document.getElementById("Nombre");
    var apellido = document.getElementById("Apellido");
    var edad = document.getElementById("Edad");
    var responseDisplay = document.getElementById("response");
    responseDisplay.innerText = "";

    const API_URL = API_URL_ROOT+"/updateClient?"+
                     "ID="+id.value+
                     "&Nombre="+nombre.value+
                     "&Apellido="+apellido.value+
                     "&edad="+edad.value;

    fetch(API_URL).then(response => response.text()).then(data=>{
       responseDisplay.append(data);
    });
}
//TODO: funcionalidad para el log.
function logear(){
    const user = document.getElementById("user");
    const password  = document.getElementById("password");
    if(user.value === "admin" && password.value == "admin"){
        console.long("Credenciales correctas!!!");
    }
}

//Oculata submenú cliente.
function submenuCliente (){
       var submenu = document.getElementById("subcliente");
       if(submenu.hidden == true){
           submenu.hidden = false;
           document.querySelector("#menucliente button").style.backgroundColor="#0000";
           document.querySelector("#menucliente button").style.color="red";
       }else{
           submenu.hidden = true;
           document.querySelector("#menucliente button").style.backgroundColor="aliceblue";
           document.querySelector("#menucliente button").style.color="black";
       }

       var submenuItems = document.querySelectorAll("#subcliente button");
       for (var i = 0; i< submenuItems.length; i++){
           submenuItems[i].addEventListener("mouseover",function (){this.style.backgroundColor = "#0000"});
           submenuItems[i].addEventListener("mouseleave",function (){this.style.backgroundColor="black"});
       }

}