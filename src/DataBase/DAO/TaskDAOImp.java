package DataBase.DAO;

import DataBase.DTO.ClientDTO;
import DataBase.DTO.TaskDTO;
import DataBase.DataBaseConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskDAOImp implements TaskDAO
{
    private static final String col_tid = "TID";
    private static final String col_name = "NAME";
    private static final String col_speciality = "SPECIALITY";
    private static final String col_avgNeededTime = "AVGNEEDEDTIME";
    private static final String col_fee = "FEE";
    private static final String getAllTasksQuery = "SELECT * FROM TASK";
    private static final String insertTaskQuery= "INSERT INTO TASK(TID, Name, SPECIALITY, AVGNEEDEDTIME, FEE) VALUES(?, ?, ?, ?, ?)";
    public ArrayList<TaskDTO> getAllTasks() throws SQLException {
        ArrayList<TaskDTO> allTasks = new ArrayList<>();
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(getAllTasksQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int TID = resultSet.getInt(col_tid);
            String NAME = resultSet.getString(col_name);
            String SPECIALITY = resultSet.getString(col_speciality);
            int AVGNEEDEDTIME = resultSet.getInt(col_avgNeededTime);
            int FEE = resultSet.getInt(col_fee);
            allTasks.add(new TaskDTO(TID,NAME,SPECIALITY,AVGNEEDEDTIME,FEE));
        }
        DataBaseConnector.closeResultSet(resultSet);
        DataBaseConnector.closePreparedStatement(preparedStatement);
        return allTasks;
    }
    public int insertTask(TaskDTO taskDTO) throws SQLException{
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(insertTaskQuery);
        preparedStatement.setInt(1,taskDTO.getTID());
        preparedStatement.setString(2, taskDTO.getName());
        preparedStatement.setString(3,taskDTO.getSPECIALITY());
        preparedStatement.setInt(4, taskDTO.getAVGNEEDEDTIME());
        preparedStatement.setInt(5,taskDTO.getFEE());
        int numOfInsertedRecords = preparedStatement.executeUpdate();
        DataBaseConnector.closePreparedStatement(preparedStatement);
        return numOfInsertedRecords;
    }
}
