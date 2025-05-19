import DataBase.DAO.ClientDAOImp;
import DataBase.DTO.ClientDTO;
import java.sql.SQLException;
import java.time.LocalDateTime;
public class Main
{
    public static void main(String[] args) throws SQLException
    {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(ClientDAOImp.getInstance().insert(new ClientDTO(1,"ahmed","23","@gm","adsf","12", now,"123")));
    }
}