package DataBase.DAO;
import DataBase.DTO.WorkerDTO;
import DataBase.DataBaseConnector;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WorkerDAOImp implements WorkerDAO
{
    private WorkerDAOImp instance = new WorkerDAOImp();
    public WorkerDAOImp getInstance() {return instance;}
    private static final String col_wid = "WID";
    private static final String col_name = "NAME";
    private static final String col_phone = "PHONE";
    private static final String col_address = "ADDRESS";
    private static final String col_email = "EMAIL";
    private static final String getAllWorkersQuery = "SELECT * FROM WORKER";
    private static final String searchForWorkerQuery = "SELECT * FROM WORKER WHERE WID = ?";
    private static final String insertWorkerQuery = "INSERT INTO WORKER(WID, NAME) VALUES(?, ?)";
    private static final String changeWorkerNameQuery = "UPDATE WORKER SET NAME = ? WHERE WID = ?";
    private static final String deleteWorkerQuery = "DELETE FROM WORKER WHERE WID = ?";
    private static final String BestWorkerRatingQuery = "SELECT w1.WID, w1.NAME, w1.PHONE, w1.ADDRESS, w1.EMAIL, ws1.SPECID, AVG(er1.WORKERRATING) AS AvgRating FROM WORKER w1, EXECUTEDREQUEST er1, WORKERSPECIALITIES ws1, REQUEST r1 WHERE ws1.WID = w1.WID AND w1.WID = er1.WID AND er1.RID = r1.RID AND r1.PREFERREDTIMETOCARRYOUT BETWEEN ? AND ? GROUP BY ws1.SPECID, w1.WID, w1.NAME, w1.PHONE, w1.ADDRESS, w1.EMAIL HAVING AVG(er1.WORKERRATING) = (SELECT MAX(AVG(er2.WORKERRATING)) FROM WORKER w2, EXECUTEDREQUEST er2, WORKERSPECIALITIES ws2, REQUEST r2 WHERE ws2.WID = w2.WID AND w2.WID = er2.WID AND er2.RID = r2.RID AND r2.PREFERREDTIMETOCARRYOUT BETWEEN ? AND ? AND ws2.SPECID = ws1.SPECID GROUP BY ws2.SPECID, w2.WID) ORDER BY ws1.SPECID;";

    @Override
    public ArrayList<WorkerDTO> getAll() throws SQLException {
        ArrayList<WorkerDTO> allWorkers = new ArrayList<>();
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(getAllWorkersQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int wid = resultSet.getInt(col_wid);
            String workerName = resultSet.getString(col_name);
            WorkerDTO workerDTO = new WorkerDTO(wid, workerName);
            allWorkers.add(workerDTO);
        }
        DataBaseConnector.closeResultSet(resultSet);
        DataBaseConnector.closePreparedStatement(preparedStatement);
        return allWorkers;
    }
    @Override
    public WorkerDTO search(int workerID) throws SQLException {
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(searchForWorkerQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int wid = resultSet.getInt(col_wid);
            String workerName = resultSet.getString(col_name);
            DataBaseConnector.closeResultSet(resultSet);
            DataBaseConnector.closePreparedStatement(preparedStatement);
            return new WorkerDTO(workerID, workerName);
        }
        DataBaseConnector.closeResultSet(resultSet);
        DataBaseConnector.closePreparedStatement(preparedStatement);
        return null;
    }
    @Override
    public int insert(WorkerDTO workerDTO) throws SQLException {
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(insertWorkerQuery);
        preparedStatement.setInt(1,workerDTO.getWID());
        preparedStatement.setString(2,workerDTO.getNAME());
        int numOfAffectedRows = preparedStatement.executeUpdate();
        DataBaseConnector.closePreparedStatement(preparedStatement);
        return numOfAffectedRows;
    }
    @Override
    public int update(WorkerDTO workerDTO) throws SQLException {
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(changeWorkerNameQuery);
        preparedStatement.setString(1,workerDTO.getNAME());
        preparedStatement.setInt(2,workerDTO.getWID());
        int numOfAffectedRows = preparedStatement.executeUpdate();
        DataBaseConnector.closePreparedStatement(preparedStatement);
        return numOfAffectedRows;
    }
    @Override
    public int delete(int workerID) throws SQLException {
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(deleteWorkerQuery);
        preparedStatement.setInt(1,workerID);
        int numOfAffectedRows = preparedStatement.executeUpdate();
        DataBaseConnector.closePreparedStatement(preparedStatement);
        return numOfAffectedRows;
    }
    @Override
    public List<WorkerDTO> getHighestRatedWorkersPerSpecialty(LocalDateTime startTime, LocalDateTime endTime) throws SQLException {
        List<WorkerDTO> workers = new ArrayList<>();
        Connection connection = DataBaseConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(BestWorkerRatingQuery);
        preparedStatement.setObject(1, startTime);
        preparedStatement.setObject(2, endTime);
        preparedStatement.setObject(3, startTime);
        preparedStatement.setObject(4, endTime);
        ResultSet resultSet = preparedStatement.executeQuery();
        WorkerDTO worker = null;
        while (resultSet.next()) {
            int workerId = resultSet.getInt(col_wid);
            String name = resultSet.getString(col_name);
            String phone = resultSet.getString(col_phone);
            String address = resultSet.getString(col_address);
            String email = resultSet.getString(col_email);

            worker = new WorkerDTO(workerId, name, phone, address, email);
            workers.add(worker);
        }
        DataBaseConnector.closeResultSet(resultSet);
        DataBaseConnector.closePreparedStatement(preparedStatement);
        DataBaseConnector.closeConnection(connection);
        return workers;
    }
}