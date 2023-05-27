package appStudio.server.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {
    //==========CRUD============
    //CREATE.
    public static int create (ClienteDTO clienteDTO){
        String sql = "INSERT INTO CLIENT (ID_CLIENT, NAME, SURNAME, AGE) VALUES (?,?,?,?)";
        Connection connection = new DBConnection().connect();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, clienteDTO.getId());
            ps.setString(2, clienteDTO.getName());
            ps.setString(3, clienteDTO.getSurname());
            ps.setInt(4, clienteDTO.getAge());
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    //READ.
    public static List<ClienteDTO> read (){

        String sql = "select * from CLIENT";
        List <ClienteDTO> clienteDTOList = new ArrayList<>();
        Connection connection = new DBConnection().connect();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                ClienteDTO cliente = new ClienteDTO();
                cliente.setId(rs.getInt(1));
                cliente.setName(rs.getString(2));
                cliente.setSurname(rs.getString(3));
                cliente.setAge(rs.getInt(4));
                clienteDTOList.add(cliente);
            }
            connection.close();
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return clienteDTOList;
    }

    //Delete.
    public static void delete (ClienteDTO clienteDTO){
        String sql = "DELETE FROM CLIENT WHERE ID_CLIENT = ?";
        Connection connection = new DBConnection().connect();

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, clienteDTO.getId());
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

 /*Métodos específicos*/
    public static List <ClienteDTO> getClientById (int id){
        List <ClienteDTO> clients = new ArrayList<>();
        ClienteDTO client = new ClienteDTO();
        String sqlQuery = "select * from CLIENT where ID_CLIENT = ? ";
        Connection con = new DBConnection().connect();
        try {
            PreparedStatement ps = con.prepareStatement(sqlQuery);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                client.setId(rs.getInt(1));
                client.setName(rs.getString(2));
                client.setSurname(rs.getString(3));
                client.setAge(rs.getInt(4));
                clients.add(client);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }
}
