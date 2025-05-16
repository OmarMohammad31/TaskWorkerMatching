package DataBase.DAO;

import DataBase.DTO.TaskDTO;
import DataBase.DataBaseConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaskDAOImp implements TaskDAO {
    private static final TaskDAOImp instance = new TaskDAOImp();
    public static TaskDAOImp getInstance() { return instance; }

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
    public TaskDTO search(int id) throws SQLException {
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(getTaskQuery);
        preparedStatement.setInt(1, id);
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

        if (taskDTO == null) {
            throw new SQLException("Task not found");
        }
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
    public int update(int ID, TaskDTO taskDTO) throws SQLException {
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(updateTaskQuery);

        preparedStatement.setString(1, taskDTO.getName());
        preparedStatement.setString(2, taskDTO.getSPECIALITY());
        preparedStatement.setInt(3, taskDTO.getAVGNEEDEDTIME());
        preparedStatement.setInt(4, taskDTO.getFEE());
        preparedStatement.setInt(5, ID);

        int numOfUpdatedRecords = preparedStatement.executeUpdate();
        DataBaseConnector.closePreparedStatement(preparedStatement);

        if (numOfUpdatedRecords == 0) {
            throw new SQLException("Task does not exist");
        }
        return numOfUpdatedRecords;
    }
    @Override
    public int delete(int ID) throws SQLException {
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(deleteTaskQuery);
        preparedStatement.setInt(1, ID);
        int numOfDeletedRecords = preparedStatement.executeUpdate();
        DataBaseConnector.closePreparedStatement(preparedStatement);

        if (numOfDeletedRecords == 0) {
            throw new SQLException("Task does not exist");
        }
        return numOfDeletedRecords;
    }
}
