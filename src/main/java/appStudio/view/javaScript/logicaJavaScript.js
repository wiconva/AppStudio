const API_URL = "http://localhost:5100/getClients"
const xhr = new XMLHttpRequest();

function onRequestHandler (){
    if (this.readyState == 4 && this.status == 200){
        console.log(this.response);
    }
}
xhr.addEventListener("load",onRequestHandler);
xhr.open("GET",API_URL);
xhr.send();