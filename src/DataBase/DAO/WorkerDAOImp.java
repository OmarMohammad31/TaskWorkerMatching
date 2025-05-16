package DataBase.DAO;
import DataBase.DTO.WorkerDTO;
import DataBase.DataBaseConnector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class WorkerDAOImp implements WorkerDAO
{
    private WorkerDAOImp instance = new WorkerDAOImp();
    public WorkerDAOImp getInstance() {return instance;}
    private static final String col_wid = "WID";
    private static final String col_name = "NAME";
    private static final String getAllWorkersQuery = "SELECT * FROM WORKER";
    private static final String searchForWorkerQuery = "SELECT * FROM WORKER WHERE WID = ?";
    private static final String insertWorkerQuery = "INSERT INTO WORKER(WID, NAME) VALUES(?, ?)";
    private static final String changeWorkerNameQuery = "UPDATE WORKER SET NAME = ? WHERE WID = ?";
    private static final String deleteWorkerQuery = "DELETE FROM WORKER WHERE WID = ?";
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
    public int update(int workerID, WorkerDTO workerDTO) throws SQLException {
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(changeWorkerNameQuery);
        preparedStatement.setString(1,workerDTO.getNAME());
        preparedStatement.setInt(2,workerID);
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
}