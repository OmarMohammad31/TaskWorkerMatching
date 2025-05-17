package DataBase.DAO;
import DataBase.DTO.ClientDTO;
import DataBase.DTO.TaskDTO;
import java.sql.SQLException;
import java.util.ArrayList;
public interface TaskDAO extends GenericDAO<TaskDTO>
{
    ArrayList<TaskDTO> getAll() throws SQLException;
    int insert(TaskDTO taskDTO) throws SQLException;
    TaskDTO search(int ID) throws SQLException;
    int update(TaskDTO taskDTO) throws SQLException;
    int delete(int ID) throws SQLException;
}