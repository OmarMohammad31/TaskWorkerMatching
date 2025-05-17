package DataBase.DAO;
import DataBase.DTO.TaskDTO;
import DataBase.DataBaseConnector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class TaskDAOImp implements TaskDAO {
    private static final TaskDAOImp instance = new TaskDAOImp();
    private static final String col_tid = "TID";
    private static final String col_name = "NAME";
    private static final String col_speciality = "SPECIALITY";
    private static final String col_avgneededtime = "AVGNEEDEDTIME";
    private static final String col_fee = "FEE";
    private static final String getAllTasksQuery = "SELECT * FROM TASK";
    private static final String getTaskQuery = "SELECT * FROM TASK WHERE TID = ?";
    private static final String insertTaskQuery = "INSERT INTO TASK(TID, NAME, SPECIALITY, AVGNEEDEDTIME, FEE) VALUES (?, ?, ?, ?, ?)";
    private static final String updateTaskQuery = "UPDATE TASK SET NAME = ?, SPECIALITY = ?, AVGNEEDEDTIME = ?, FEE = ? WHERE TID = ?";
    private static final String deleteTaskQuery = "DELETE FROM TASK WHERE TID = ?";
    private static final String getMostRequestedTaskQuery = "SELECT TOP 1 t.* FROM TASK t LEFT JOIN REQUEST r ON t.NAME = r.SPECIALITY GROUP BY t.TID, t.NAME, t.SPECIALITY, t.AVGNEEDEDTIME, t.FEE ORDER BY COUNT(r.RID) DESC;";
    private static final String getLeastRequestedTaskQuery = "SELECT TOP 1 t.* FROM TASK t LEFT JOIN REQUEST r ON t.NAME = r.SPECIALITY GROUP BY t.TID, t.NAME, t.SPECIALITY, t.AVGNEEDEDTIME, t.FEE ORDER BY COUNT(r.RID) ASC;";
    private static final String getSpecialityWithNoRequestsThisMonthQuery =  "SELECT DISTINCT t.SPECIALITY FROM TASK t WHERE NOT EXISTS (SELECT 1 FROM REQUEST r WHERE r.SPECIALITY = t.NAME AND YEAR(r.PLACEMENT_TIME) = YEAR(GETDATE()) AND MONTH(r.PLACEMENT_TIME) = MONTH(GETDATE()) )";
    
    public static TaskDAOImp getInstance() {return instance;}
    @Override
    public ArrayList<TaskDTO> getAll() throws SQLException {
        ArrayList<TaskDTO> allTasks = new ArrayList<>();
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(getAllTasksQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int tid = resultSet.getInt(col_tid);
            String name = resultSet.getString(col_name);
            String speciality = resultSet.getString(col_speciality);
            int avgneededtime = resultSet.getInt(col_avgneededtime);
            int fee = resultSet.getInt(col_fee);
            allTasks.add(new TaskDTO(tid, name, speciality, avgneededtime, fee));
        }
        DataBaseConnector.closeResultSet(resultSet);
        DataBaseConnector.closePreparedStatement(preparedStatement);
        return allTasks;
    }
    @Override
    public TaskDTO search(int TID) throws SQLException {
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(getTaskQuery);
        preparedStatement.setInt(1, TID);
        ResultSet resultSet = preparedStatement.executeQuery();
        TaskDTO taskDTO = null;
        if (resultSet.next()) {
            int tid = resultSet.getInt(col_tid);
            String name = resultSet.getString(col_name);
            String speciality = resultSet.getString(col_speciality);
            int avgneededtime = resultSet.getInt(col_avgneededtime);
            int fee = resultSet.getInt(col_fee);
            taskDTO = new TaskDTO(tid, name, speciality, avgneededtime, fee);
        }
        DataBaseConnector.closeResultSet(resultSet);
        DataBaseConnector.closePreparedStatement(preparedStatement);
        return taskDTO;
    }
    @Override
    public int insert(TaskDTO taskDTO) throws SQLException {
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(insertTaskQuery);
        preparedStatement.setInt(1, taskDTO.getTID());
        preparedStatement.setString(2, taskDTO.getName());
        preparedStatement.setString(3, taskDTO.getSPECIALITY());
        preparedStatement.setInt(4, taskDTO.getAVGNEEDEDTIME());
        preparedStatement.setInt(5, taskDTO.getFEE());
        int numOfInsertedRecords = preparedStatement.executeUpdate();
        DataBaseConnector.closePreparedStatement(preparedStatement);
        return numOfInsertedRecords;
    }
    @Override
    public int update(int TID, TaskDTO taskDTO) throws SQLException {
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(updateTaskQuery);
        preparedStatement.setString(1, taskDTO.getName());
        preparedStatement.setString(2, taskDTO.getSPECIALITY());
        preparedStatement.setInt(3, taskDTO.getAVGNEEDEDTIME());
        preparedStatement.setInt(4, taskDTO.getFEE());
        preparedStatement.setInt(5, TID);
        int numOfUpdatedRecords = preparedStatement.executeUpdate();
        DataBaseConnector.closePreparedStatement(preparedStatement);
        return numOfUpdatedRecords;
    }
    @Override
    public int delete(int TID) throws SQLException {
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(deleteTaskQuery);
        preparedStatement.setInt(1, TID);
        int numOfDeletedRecords = preparedStatement.executeUpdate();
        DataBaseConnector.closePreparedStatement(preparedStatement);
        return numOfDeletedRecords;
    }
    public TaskDTO getMostRequestedTask() throws SQLException {
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(getMostRequestedTaskQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        TaskDTO taskDTO = null;
        if (resultSet.next()) {
            int tid = resultSet.getInt(col_tid);
            String name = resultSet.getString(col_name);
            String speciality = resultSet.getString(col_speciality);
            int avgneededtime = resultSet.getInt(col_avgneededtime);
            int fee = resultSet.getInt(col_fee);
            taskDTO = new TaskDTO(tid, name, speciality, avgneededtime, fee);
        }
        DataBaseConnector.closeResultSet(resultSet);
        DataBaseConnector.closePreparedStatement(preparedStatement);
        return taskDTO;
    }
    public TaskDTO getLeastRequestedTask() throws SQLException {
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(getLeastRequestedTaskQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        TaskDTO taskDTO = null;
        if (resultSet.next()) {
            int tid = resultSet.getInt(col_tid);
            String name = resultSet.getString(col_name);
            String speciality = resultSet.getString(col_speciality);
            int avgneededtime = resultSet.getInt(col_avgneededtime);
            int fee = resultSet.getInt(col_fee);
            taskDTO = new TaskDTO(tid, name, speciality, avgneededtime, fee);
        }
        DataBaseConnector.closeResultSet(resultSet);
        DataBaseConnector.closePreparedStatement(preparedStatement);
        return taskDTO;
    }
    public ArrayList<String> getTaskWithNoRequestThisMonth() throws SQLException {
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(getSpecialityWithNoRequestsThisMonthQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<String> SpecialityWithNoTasksThisMonth = new ArrayList<>();
        while (resultSet.next()) {
            String speciality = resultSet.getString(col_speciality);
            SpecialityWithNoTasksThisMonth.add(speciality);
        }
        DataBaseConnector.closeResultSet(resultSet);
        DataBaseConnector.closePreparedStatement(preparedStatement);
        return SpecialityWithNoTasksThisMonth;
    }
}
