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
    private static final String BestWorkerRatingQuery = "SELECT \n" +
            "    ws.SPECID,\n" +
            "    w.WID,\n" +
            "    w.NAME,\n" +
            "    w.PHONE,\n" +
            "    w.ADDRESS,\n" +
            "    w.EMAIL,\n" +
            "    AVG(er.WORKERRATING) AS AvgRating,\n" +
            "    COUNT(er.RID) AS NumRequests\n" +
            "FROM WORKER w, \n" +
            "     EXECUTEDREQUEST er, \n" +
            "     WORKERSPECIALITIES ws, \n" +
            "     REQUEST r\n" +
            "WHERE ws.WID = w.WID\n" +
            "AND w.WID = er.WID\n" +
            "AND r.RID = er.RID\n" +
            "AND r.PREFERREDTIMETOCARRYOUT BETWEEN ? AND ?\n" +
            "GROUP BY ws.SPECID, w.WID, w.NAME, w.PHONE, w.ADDRESS, w.EMAIL\n" +
            "ORDER BY ws.SPECID, AvgRating DESC";

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