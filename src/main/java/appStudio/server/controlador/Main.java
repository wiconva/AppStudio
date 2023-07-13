package appStudio.server.controlador;


import appStudio.server.modelo.ClientDao;
import appStudio.server.modelo.ClienteDTO;
import appStudio.server.utilities.ConfigFileReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        startServer();
    }

    private static void startServer (){
        try {
            String configFileName =     "Server.ConnectionConfig.txt";
            String serverPortKey =      "ServerPort";

            ConfigFileReader cfr = new ConfigFileReader();
            String serverPort = cfr.getKeyValue(configFileName, serverPortKey);
            ServerSocket serverSocket = new ServerSocket(Integer.parseInt(serverPort));

            while (true) {
                Socket clientSocket = serverSocket.accept();
                Thread.sleep(100);
                InputStream is = clientSocket.getInputStream();
                byte data[] = new byte[is.available()];
                is.read(data);
                String msgRequest = new String(data, StandardCharsets.UTF_8);
                String msgResponse= "";

                if(data.length > 0) {
                    switch (getEndPoint(msgRequest)) {
                        case ("/createClient"):
                            msgResponse =createClient(getParameters(msgRequest));
                        break;
                        case  ("/getClient"):
                            msgResponse = getClient(getParameters(msgRequest));
                        break;
                        case  ("/getClients"):
                            msgResponse = getClients();
                            break;
                        case  ("/updateClient"):
                            msgResponse = updateClient(getParameters(msgRequest));
                            break;
                        case  ("/deleteClient"):
                            msgResponse = deleteClient(getParameters(msgRequest));
                            break;
                    }
                }
                sendResponse(200,msgResponse, clientSocket.getOutputStream());
                is.close();
                clientSocket.close();
            }

        } catch (IOException e) {
            System.out.println("Error initializing server.");
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private static String deleteClient(String [] values){
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setId(Integer.parseInt(values[0]));

            if(ClientDao.delete(clienteDTO) == 0){
                return "Cliente eliminado.";
            }else{
                return "Error al eliminar el cliente";
            }
    }

    private static String updateClient (String [] values ){
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(Integer.parseInt(values[0]));
        clienteDTO.setName(values[1]);
        clienteDTO.setSurname(values[2]);
        clienteDTO.setAge(Integer.parseInt(values[3]));
        if(ClientDao.update(clienteDTO) == 0){
            return "Se actualiza cliente.";
        }else{
            return "No se pudo actualizar el cliente";
        }
    }

    private static void sendResponse ( int responseCode, String msgResponse,OutputStream os) throws IOException {
        String outMsg = "HTTP/1.1 "+responseCode+" OK"+"\n"+
                        "Content-Type: text/html"+"\n"+
                        "Access-Control-Allow-Origin: *"+"\n"+
                        "Content-Length: "+msgResponse.length()+"\n\n"+
                        msgResponse;

        os.write(outMsg.getBytes(StandardCharsets.UTF_8));
        os.close();
    }

    private static String getClients(){
        String msgResponse="";
        List<ClienteDTO> cList = ClientDao.read();
        for(ClienteDTO c: cList){
            msgResponse = msgResponse + c.getId()+","+c.getName()+","+c.getSurname()+","+c.getAge()+";";
        }
        return msgResponse;
    }

    private static String getClient(String [] values){
        String msgResponse = "";
        List<ClienteDTO> cList ;
        cList = ClientDao.getClientById(Integer.parseInt(values[0]));
        ClienteDTO c = cList.get(0);
        msgResponse = c.getId()+","+c.getName()+","+c.getSurname()+","+c.getAge();
        return msgResponse;
    }

    private static String  createClient (String [] values){
        ClienteDTO c = new ClienteDTO();
        c.setId(Integer.parseInt(values[0]));
        c.setName(values[1]);
        c.setSurname(values[2]);
        c.setAge(Integer.parseInt(values[3]));

        String msgResponse = "";
        if(ClientDao.create(c) == 1) {
            msgResponse= "Error al crear el cliente.";
        }else {
            msgResponse = "Se creo el usuario con exito.";
        }
        return msgResponse;
    }

    private static String getEndPoint(String msg){
        String[] lines = msg.split("\n");
        String url = "";
        if(msg.contains("?")){
            url = lines[0].split(" ")[1].split("\\?")[0];
        }else{
            url = lines[0].split(" ")[1];
        }
        return url;
    }

    private static String [] getParameters (String msg){
        String[] lines = msg.split("\n");
        String parameters = lines[0].split(" ")[1].split("\\?")[1];
        String keyValues [] = parameters.split("&");
        String values [] = new String [keyValues.length];
        String msgResponse= "";
        int contador=0;
        for(String keyValue: keyValues){
            values [contador] = keyValue.split("=")[1];
            contador++;
        }
        return values;
    }

}
