import DataBase.DAO.ClientDAOImp;
import DataBase.DTO.ClientDTO;
import DataBase.DataBaseConnector;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args) throws SQLException
    {
        LocalDateTime myTime = LocalDateTime.of(2025, 5, 16, 14, 30);
        ClientDTO clientDTO = new ClientDTO(1, "omar", "01010", "asdf", "@test.com", "123", myTime, "12");
        ArrayList<ClientDTO> myClients = ClientDAOImp.getInstance().getAll();
        for (ClientDTO client:myClients){
            System.out.println(client.toString());
        }
    }
}