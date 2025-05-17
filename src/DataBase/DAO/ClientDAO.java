package DataBase.DAO;
import DataBase.DTO.ClientDTO;
import java.sql.SQLException;
import java.util.ArrayList;
public interface ClientDAO extends GenericDAO<ClientDTO>
{
    ArrayList<ClientDTO> getAll() throws SQLException;
    int insert(ClientDTO clientDTO) throws SQLException;
    ClientDTO search(int ID) throws SQLException;
    int update(int ID, ClientDTO clientDTO) throws SQLException;
    int delete(int ID) throws SQLException;

}
