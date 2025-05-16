package DataBase.DAO;
import DataBase.DTO.ClientDTO;
import DataBase.DTO.TaskDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TaskDAO extends GenericDAO<TaskDTO>
{
    ArrayList<TaskDTO> getAllTasks() throws SQLException;
    int insertTask(TaskDTO taskDTO) throws SQLException;
}
