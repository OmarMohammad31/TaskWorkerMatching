package DataBase.DAO;
import DataBase.DTO.ClientDTO;
import DataBase.DataBaseConnector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
public class ClientDAOImp implements ClientDAO
{
    private static final ClientDAOImp instance = new ClientDAOImp();
    public static final ClientDAOImp getInstance(){return instance;}
    private static final String col_cid = "CID";
    private static final String  col_name = "NAME";
    private static final String col_phone = "PHONE";
    private static final String col_address = "ADDRESS";
    private static final String col_email = "EMAIL";
    private static final String col_cardNum = "CARDNUM";
    private static final String col_expDate = "EXPDATE";
    private static final String col_cvv = "CVV";
    private static final String getAllClientsQuery = "SELECT * FROM CLIENT";
    private final String insertClientQuery = "INSERT INTO CLIENT(CID, NAME, PHONE, ADDRESS, EMAIL, CARDNUM, EXPDATE, CVV) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private final String getClientQuery = "SELECT * FROM CLIENT WHERE CID = ?";
    private final String updateClientQuery = "UPDATE ClIENT SET NAME = ?, PHONE = ?, ADDRESS = ?, EMAIL = ?, CARDNUM = ?, EXPDATE = ?, CVV = ? WHERE CID = ?";
    private final String deleteClientQuery = "DELETE FROM CLIENT WHERE CID = ?";
    @Override
    public ArrayList<ClientDTO> getAll() throws SQLException {
        ArrayList<ClientDTO> allClients = new ArrayList<>();
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(getAllClientsQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int CID = resultSet.getInt(col_cid);
            String name = resultSet.getString(col_name);
            String phone = resultSet.getString(col_phone);
            String address = resultSet.getString(col_address);
            String email = resultSet.getString(col_email);
            String cardNum = resultSet.getString(col_cardNum);
            LocalDateTime ExpDate = resultSet.getObject(col_expDate, LocalDateTime.class);
            String CVV = resultSet.getString(col_cvv);
            allClients.add(new ClientDTO(CID, name, phone, address, email, cardNum, ExpDate, CVV));
        }
        DataBaseConnector.closeResultSet(resultSet);
        DataBaseConnector.closePreparedStatement(preparedStatement);
        return allClients;
    }
    @Override
    public int insert(ClientDTO clientDTO) throws SQLException{
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(insertClientQuery);
        preparedStatement.setInt(1,clientDTO.getCID());
        preparedStatement.setString(2, clientDTO.getNAME());
        preparedStatement.setString(3,clientDTO.getPHONE());
        preparedStatement.setString(4,clientDTO.getADDRESS());
        preparedStatement.setString(5,clientDTO.getEMAIL());
        preparedStatement.setString(6,clientDTO.getCARDNUM());
        preparedStatement.setObject(7,clientDTO.getEXPDATE());
        preparedStatement.setString(8,clientDTO.getCVV());
        int numOfInsertedRecords = preparedStatement.executeUpdate();
        DataBaseConnector.closePreparedStatement(preparedStatement);
        return numOfInsertedRecords;
    }
    @Override
    public ClientDTO search(int cid) throws SQLException{
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(getClientQuery);
        preparedStatement.setInt(1,cid);
        ResultSet resultSet = preparedStatement.executeQuery();
        ClientDTO clientDTO = null;
        if(resultSet.next()){
            String name = resultSet.getString(col_name);
            String phone = resultSet.getString(col_phone);
            String address = resultSet.getString(col_address);
            String email = resultSet.getString(col_email);
            String cardNum = resultSet.getString(col_cardNum);
            LocalDateTime ExpDate = resultSet.getObject(col_expDate, LocalDateTime.class);
            String CVV = resultSet.getString(col_cvv);
            clientDTO = new ClientDTO(cid, name, phone, address, email, cardNum, ExpDate, CVV);
        }
        DataBaseConnector.closeResultSet(resultSet);
        DataBaseConnector.closePreparedStatement(preparedStatement);
        return clientDTO;
    }
    @Override
    public int update(ClientDTO clientDTO) throws SQLException{
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(updateClientQuery);
        preparedStatement.setString(1,clientDTO.getNAME());
        preparedStatement.setString(2,clientDTO.getPHONE());
        preparedStatement.setString(3,clientDTO.getADDRESS());
        preparedStatement.setString(4,clientDTO.getEMAIL());
        preparedStatement.setString(5,clientDTO.getCARDNUM());
        preparedStatement.setObject(6,clientDTO.getEXPDATE());
        preparedStatement.setString(7,clientDTO.getCVV());
        preparedStatement.setInt(8,clientDTO.getCID());
        int numOfUpdatedRecords = preparedStatement.executeUpdate();
        DataBaseConnector.closePreparedStatement(preparedStatement);
        return numOfUpdatedRecords;
    }
    @Override
    public int delete(int CID) throws SQLException{
        PreparedStatement preparedStatement = DataBaseConnector.getConnection().prepareStatement(deleteClientQuery);
        preparedStatement.setInt(1,CID);
        int numOfDeletedRecords = preparedStatement.executeUpdate();
        DataBaseConnector.closePreparedStatement(preparedStatement);
        return numOfDeletedRecords;
    }
}