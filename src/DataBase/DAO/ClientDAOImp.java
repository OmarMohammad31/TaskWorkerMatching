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
    @Override
    public ArrayList<ClientDTO> getAllClients() throws SQLException {
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
    public int insertClient(ClientDTO clientDTO) throws SQLException{
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


}
