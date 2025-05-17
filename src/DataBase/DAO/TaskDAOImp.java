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
    private static final String insertTaskQuery = "INSERT INTO TASK(TID, NAME, SID, AVGNEEDEDTIME, FEE) VALUES (?, ?, ?, ?, ?)";
    private static final String updateTaskQuery = "UPDATE TASK SET NAME = ?, SID = ?, AVGNEEDEDTIME = ?, FEE = ? WHERE TID = ?";
    private static final String deleteTaskQuery = "DELETE FROM TASK WHERE TID = ?";
    private static final String getMostRequestedTaskQuery = " Select TOP 1 t.TID, t.name, count(*) as request_count From Task t, Request R Where t.TID = r.TID group by t.TID, t.NAME Order by request_count desc";
    private static final String getLeastRequestedTaskQuery = "Select TOP 1 t.TID, t.name, count(*) as request_count From Task t, Request R Where t.TID = r.TID group by t.TID, t.NAME Order by request_count asc;";
    private static final String getSpecialityWithNoRequestsThisMonthQuery =  "SELECT s.SPECIALITYID, s.NAME FROM SPECIALITY s WHERE NOT EXISTS (SELECT 1 FROM TASK t JOIN REQUEST r ON t.TID = r.TID WHERE t.SID = s.SPECIALITYID AND r.PLACEMENTTIME >= DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()), 0) AND r.PLACEMENTTIME < DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()) + 1, 0));";
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
    public int update(TaskDTO taskDTO) throws SQLException {
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(updateTaskQuery);
        preparedStatement.setString(1, taskDTO.getName());
        preparedStatement.setString(2, taskDTO.getSPECIALITY());
        preparedStatement.setInt(3, taskDTO.getAVGNEEDEDTIME());
        preparedStatement.setInt(4, taskDTO.getFEE());
        preparedStatement.setInt(5, taskDTO.getTID());
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
    public ArrayList<TaskDTO> getTaskWithNoRequestThisMonth() throws SQLException {
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(getSpecialityWithNoRequestsThisMonthQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<TaskDTO> SpecialityWithNoTasksThisMonth = new ArrayList<>();
        while (resultSet.next()) {
            int TID = resultSet.getInt(col_tid);
            String Name = resultSet.getString(col_name);
            String SPECIALITY = resultSet.getString(col_speciality);
            int AVGNEEDEDTIME = resultSet.getInt(col_avgneededtime);
            int FEE = resultSet.getInt(col_fee);
            SpecialityWithNoTasksThisMonth.add(new TaskDTO(TID, Name, SPECIALITY, AVGNEEDEDTIME, FEE));
        }
        DataBaseConnector.closeResultSet(resultSet);
        DataBaseConnector.closePreparedStatement(preparedStatement);
        return SpecialityWithNoTasksThisMonth;
    }
}