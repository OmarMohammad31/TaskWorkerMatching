package DataBase.DAO;
import DataBase.DTO.WorkerDTO;
import DataBase.DTO.WorkerWithAvgRating;
import DataBase.DTO.WorkerWithAvgRatingAndTotalWageDTO;
import DataBase.DataBaseConnector;

import javax.xml.crypto.Data;
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
    private static final String BestWorkerRatingQuery = "WITH AvgRatings AS (SELECT w.WID, w.NAME, w.PHONE, w.ADDRESS, w.EMAIL, ws.SPECID, AVG(er.WORKERRATING) AS AvgRating\n" +
            "    FROM WORKER w\n" +
            "    JOIN EXECUTEDREQUEST er ON w.WID = er.WID\n" +
            "    JOIN WORKERSPECIALITIES ws ON ws.WID = w.WID\n" +
            "    JOIN REQUEST r ON er.RID = r.RID\n" +
            "    WHERE r.PREFERREDTIMETOCARRYOUT BETWEEN ? AND ?\n" +
            "    GROUP BY ws.SPECID, w.WID, w.NAME, w.PHONE, w.ADDRESS, w.EMAIL\n" +
            "),\n" +
            "MaxRatings AS (\n" +
            "    SELECT \n" +
            "        SPECID, \n" +
            "        MAX(AvgRating) AS MaxAvgRating\n" +
            "    FROM AvgRatings\n" +
            "    GROUP BY SPECID\n" +
            ")\n" +
            "SELECT ar.*\n" +
            "FROM AvgRatings ar\n" +
            "JOIN MaxRatings mr \n" +
            "    ON ar.SPECID = mr.SPECID \n" +
            "    AND ar.AvgRating = mr.MaxAvgRating\n" +
            "ORDER BY ar.SPECID;";
    private static final String getTotalWageForEachWorkerWithRatingQuery = "SELECT \n" +
            "    W.WID,\n" +
            "    W.NAME,\n" +
            "    W.PHONE,\n" +
            "    W.ADDRESS,\n" +
            "    W.EMAIL,\n" +
            "    COALESCE(SUM(T.FEE), 0) AS TotalDueWage,\n" +
            "    COALESCE(AVG(ER.WORKERRATING), 0) AS AverageRating\n" +
            "FROM WORKER W\n" +
            "LEFT JOIN EXECUTEDREQUEST ER ON W.WID = ER.RID\n" +
            "LEFT JOIN REQUEST R ON ER.RID = R.RID \n" +
            "    AND R.PREFERREDTIMETOCARRYOUT BETWEEN ? AND ?\n" +
            "LEFT JOIN TASK T ON R.TID = T.TID\n" +
            "GROUP BY W.WID, W.NAME, W.PHONE, W.ADDRESS, W.EMAIL\n" +
            "ORDER BY TotalDueWage DESC;";
    private static final String getWorkersWithMoreThan4AndHalfRatingQuery = "SELECT \n" +
            "    W.WID AS WorkerID,\n" +
            "    W.NAME AS WorkerName,\n" +
            "    MIN(ER.WORKERRATING) AS MinimumRating\n" +
            "FROM WORKER W\n" +
            "INNER JOIN EXECUTEDREQUEST ER ON W.WID = ER.WID  -- Changed to INNER JOIN\n" +
            "GROUP BY W.WID, W.NAME\n" +
            "HAVING MIN(ER.WORKERRATING) >= 4.5  -- No COALESCE needed\n" +
            "ORDER BY W.WID;";
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
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int workerId = resultSet.getInt(col_wid);
            String name = resultSet.getString(col_name);
            String phone = resultSet.getString(col_phone);
            String address = resultSet.getString(col_address);
            String email = resultSet.getString(col_email);
            workers.add(new WorkerDTO(workerId, name, phone, address, email));
        }
        DataBaseConnector.closeResultSet(resultSet);
        DataBaseConnector.closePreparedStatement(preparedStatement);
        DataBaseConnector.closeConnection(connection);
        return workers;
    }
    @Override
    public ArrayList<WorkerWithAvgRatingAndTotalWageDTO> getTotalWageForEachWorkerWithRating(LocalDateTime startTime, LocalDateTime endTime) throws SQLException{
        ArrayList<WorkerWithAvgRatingAndTotalWageDTO> workers = new ArrayList<>();
        Connection connection = DataBaseConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(getTotalWageForEachWorkerWithRatingQuery);
        preparedStatement.setObject(1,startTime);
        preparedStatement.setObject(2,endTime);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int wid = resultSet.getInt(col_wid);
            String name = resultSet.getString(col_name);
            String phone = resultSet.getString(col_phone);
            String Address = resultSet.getString(col_address);
            String Email = resultSet.getString(col_email);
            int totalWage = resultSet.getInt("TotalDueWage");
            double avgRating = resultSet.getDouble("TotalDueWage");
            workers.add(new WorkerWithAvgRatingAndTotalWageDTO(totalWage, avgRating, new WorkerDTO(wid, name, phone,Address, Email)));
        }
        DataBaseConnector.closeResultSet(resultSet);
        DataBaseConnector.closePreparedStatement(preparedStatement);
        DataBaseConnector.closeConnection(connection);
        return workers;
    }
    @Override
    public ArrayList<WorkerWithAvgRating> getWorkersWithMoreThan4AndHalfRating() throws SQLException{
        ArrayList<WorkerWithAvgRating> workers = new ArrayList<>();
        Connection connection = DataBaseConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(getWorkersWithMoreThan4AndHalfRatingQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int wid = resultSet.getInt(col_wid);
            String name = resultSet.getString(col_name);
            String phone = resultSet.getString(col_phone);
            String address = resultSet.getString(col_address);
            String Email = resultSet.getString(col_email);
            double rating = resultSet.getDouble("MinimumRating");
            workers.add(new WorkerWithAvgRating(rating, new WorkerDTO(wid, name, phone, address, Email)));
        }
        DataBaseConnector.closeResultSet(resultSet);
        DataBaseConnector.closePreparedStatement(preparedStatement);
        DataBaseConnector.closeConnection(connection);
        return workers;
    }
}