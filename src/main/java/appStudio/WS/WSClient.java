package appStudio.WS;


import appStudio.server.modelo.ClientDao;
import appStudio.server.modelo.ClienteDTO;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class WSClient {

    public static void getAllClient(HttpExchange exchange) {
        List<ClienteDTO> clients =  ClientDao.read();
        String msgHTTP = "";

        for (ClienteDTO c : clients){
            msgHTTP =msgHTTP+"ID="+c.getId()+
                    ",Nombre="+c.getName()+
                    ",Apellido="+c.getSurname()+
                    ",Edad="+c.getAge()+";\n";
        }

        sendHTTPResponse(msgHTTP,exchange);
    }

    public static void getClient (HttpExchange httpExchange) throws IOException {
        String [] params = httpExchange.getRequestURI().getQuery().split("=");
        int id = Integer.parseInt(params[1]);
        List <ClienteDTO> clients =ClientDao.getClientById(id);

        String msgHTTP = "";
        for (ClienteDTO c : clients){
            msgHTTP =msgHTTP+"ID="+c.getId()+
                    ",Nombre="+c.getName()+
                    ",Apellido="+c.getSurname()+
                    "Edad="+c.getAge()+";\n";
        }

        sendHTTPResponse(msgHTTP,httpExchange);
    }

    public static void createClient (HttpExchange ex){
        String[] query = ex.getRequestURI().getQuery().split("&");
        String[][]params = new String[query.length][2];
        int cont = 0;
        for(String s : query){
            params[cont] = query[cont].split("=");
            cont++;
        }

        ClienteDTO newClient = new ClienteDTO();
        newClient.setId(Integer.parseInt(params[0][1]));
        newClient.setName(params[1][1]);
        newClient.setSurname(params[2][1]);
        newClient.setAge(Integer.parseInt(params[3][1]));
        if(ClientDao.create(newClient) == 0){
            sendHTTPResponse("Cliente ingresado con Exito :)",ex);
        }else {
            sendHTTPResponse("No se pudo crear el Cliente :(",ex);
        }

    }

    public static void sendHTTPResponse (String responseBody,HttpExchange ex ){
        byte data [] = responseBody.getBytes(StandardCharsets.UTF_8);
        try {
            ex.sendResponseHeaders(200, data.length);
            OutputStream os = ex.getResponseBody();
            os.write(data);
            os.close();
            ex.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
