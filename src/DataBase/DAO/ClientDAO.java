package DataBase.DAO;
import DataBase.DTO.ClientDTO;
import java.sql.SQLException;
import java.util.ArrayList;
public interface ClientDAO extends GenericDAO<ClientDTO>
{
    ArrayList<ClientDTO>  getAllClients() throws SQLException;
    int insertClient(ClientDTO clientDTO) throws SQLException;
}
