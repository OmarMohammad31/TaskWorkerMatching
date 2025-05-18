package DataBase.DAO;
import DataBase.DTO.RequestDTO;
import DataBase.DTO.RequestStatus;
import DataBase.DataBaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
public class RequestDAOImp implements RequestDAO
{
    private static final RequestDAOImp instance = new RequestDAOImp();
    public static final RequestDAOImp getInstance(){return instance;}
    private static final String col_RID = "RID";
    private static final String col_CID = "CID";
    private static final String col_TID = "TID";
    private static final String col_Address = "ADDRESS";
    private static final String col_PlacementTime = "PLACEMENTTIME";
    private static final String col_PreferredTimeToCarryOut = "PREFERREDTIMETOCARRYOUT";
    private static final String col_Status = "STATUS";
    private static final String SearchRequestQuery = "SELECT * FROM REQUEST WHERE RID = ?";
    private static final String SelectAllRequestQuery = "SELECT * FROM REQUEST";
    private static final String InsertRequestQuery = "INSERT INTO REQUEST(RID, CID, TID, ADDRESS, PLACEMENTTIME, PREFERREDTIMETOCARRYOUT, STATUS) VALUES (?,?,?.?.?.?.?)";
    private static final String UpdateRequestQuery = "UPDATE REQUEST SET  RID = ?, CID = ?, TID = ?, ADDRESS = ?, PLACEMENTTIME = ?, PREFERREDTIMETOCARRYOUT = ?, STATUS = ? WHERE RID = ?";
    private static final String DeleteRequestQuery = "DELETE FROM REQUEST WHERE RID = ?";

    @Override
    public ArrayList<RequestDTO> getAll() throws SQLException {
        Connection connection = DataBaseConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SelectAllRequestQuery);
        ArrayList<RequestDTO> allRequests = new ArrayList<>();
        ResultSet resultset = preparedStatement.executeQuery();
        while(resultset.next()){
            int RID = resultset.getInt(col_RID);
            int CID = resultset.getInt(col_CID);
            int TID = resultset.getInt(col_TID);
            String Address = resultset.getString(col_Address);
            LocalDateTime PlacementTime = resultset.getObject(col_PlacementTime, LocalDateTime.class);
            LocalDateTime PreferredTimeToCarryOut = resultset.getObject(col_PreferredTimeToCarryOut, LocalDateTime.class);
            RequestStatus Status = RequestStatus.valueOf(resultset.getString(col_Status));
            allRequests.add(new RequestDTO(RID, CID, TID, Address, PlacementTime, PreferredTimeToCarryOut, Status));
        }
        //close connection
        DataBaseConnector.closeResultSet(resultset);
        DataBaseConnector.closePreparedStatement(preparedStatement);
        DataBaseConnector.closeConnection(connection);
        return allRequests ;
    }

    @Override
    public RequestDTO search(int RID) throws SQLException {
        Connection connection = DataBaseConnector.getConnection();
        RequestDTO request = null;
        PreparedStatement preparedStatement = connection.prepareStatement(SearchRequestQuery);
        preparedStatement.setInt(1, RID);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            int CID = resultSet.getInt(col_CID);
            int TID = resultSet.getInt(col_TID);
            String Address = resultSet.getString(col_Address);
            LocalDateTime PlacementTime = resultSet.getObject(col_PlacementTime, LocalDateTime.class);
            LocalDateTime PreferredTimeToCarryOut = resultSet.getObject(col_PreferredTimeToCarryOut, LocalDateTime.class);
            RequestStatus Status = RequestStatus.valueOf(resultSet.getString(col_Status));
            request = new RequestDTO(RID, CID, TID, Address, PlacementTime, PreferredTimeToCarryOut, Status);
        }
        DataBaseConnector.closeResultSet(resultSet);
        DataBaseConnector.closePreparedStatement(preparedStatement);
        DataBaseConnector.closeConnection(connection);
        return request;
    }

    @Override
    public int insert(RequestDTO request) throws SQLException {
        Connection connection = DataBaseConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(InsertRequestQuery);

        preparedStatement.setInt(1, request.getRID());
        preparedStatement.setInt(2, request.getCID());
        preparedStatement.setInt(3, request.getTID());
        preparedStatement.setString(4, request.getADDRESS());
        preparedStatement.setObject(5, request.getPLACEMENTTIME());
        preparedStatement.setObject(6, request.getPREFERREDTIMETOCARRYOUT());
        preparedStatement.setString(7, request.getSTATUS().name());

        int numOfInsertedRecords = preparedStatement.executeUpdate();
        DataBaseConnector.closePreparedStatement(preparedStatement);
        DataBaseConnector.closeConnection(connection);
        return numOfInsertedRecords;
    }

    @Override
    public int update(RequestDTO request) throws SQLException {
        Connection connection = DataBaseConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UpdateRequestQuery);

        preparedStatement.setInt(1, request.getCID());
        preparedStatement.setInt(2, request.getTID());
        preparedStatement.setString(3, request.getADDRESS());
        preparedStatement.setObject(4, request.getPLACEMENTTIME());
        preparedStatement.setObject(5, request.getPREFERREDTIMETOCARRYOUT());
        preparedStatement.setString(6, request.getSTATUS().name());
        preparedStatement.setInt(7, request.getRID());
        preparedStatement.setInt(8, request.getCID());
        int numOfUpdatedRows = preparedStatement.executeUpdate();
        DataBaseConnector.closePreparedStatement(preparedStatement);
        DataBaseConnector.closeConnection(connection);
        return numOfUpdatedRows;
    }

    @Override
    public int delete(int RID) throws SQLException {
        Connection connection = DataBaseConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DeleteRequestQuery);
        preparedStatement.setInt(1, RID);
        int numOfDeletedRows = preparedStatement.executeUpdate();
        DataBaseConnector.closePreparedStatement(preparedStatement);
        DataBaseConnector.closeConnection(connection);
        return numOfDeletedRows;
    }
}
