package DataBase.DAO;

import DataBase.DTO.SpecialityDTO;
import DataBase.DTO.TaskDTO;
import DataBase.DataBaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecialityDAOImp implements SpecialityDAO
{
    private static final SpecialityDAOImp instance = new SpecialityDAOImp();
    public static final SpecialityDAOImp getInstance(){return instance;}
    private static final String col_specialityID = "SPECID";
    private static final String col_name = "NAME";
    private static final String SearchSpecialityQuery = "SELECT * FROM SPECIALITY WHERE SPECID = ?";
    private static final String SelectAllSpecialityQuery = "SELECT * FROM SPECIALITY";
    private static final String InsertSpecialityQuery = "INSERT INTO SPECIALITY(SPECID, NAME) VALUES (?,?)";
    private static final String UpdateSpecialityQuery = "UPDATE SPECIALITY SET SPECID = ?, NAME = ? WHERE SPECID = ?";
    private static final String DeleteSpecialityQuery = "DELETE FROM SPECIALITY WHERE SPECID = ?";
    private static final String getMostRequestedSpecialityQuery = "SELECT TOP 1 s.SPECID, s.NAME, COUNT(*) AS SpecialityCount FROM SPECIALITY s, REQUEST r, TASK t WHERE s.SPECID = t.SPECID AND t.TID = r.TID GROUP BY s.SPECID, s.NAME ORDER BY SpecialityCount DESC";
    private static final String getLeastRequestedSpecialityQuery = "SELECT TOP 1 s.SPECID, s.NAME, COUNT(*) AS SpecialityCount FROM SPECIALITY s, REQUEST r, TASK t WHERE s.SPECID = t.SPECID AND t.TID = r.TID GROUP BY s.SPECID, s.NAME ORDER BY SpecialityCount ASC;";

    @Override
    public ArrayList<SpecialityDTO> getAll() throws SQLException{
        Connection connection = DataBaseConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SelectAllSpecialityQuery);
        ArrayList<SpecialityDTO> allSpecialities = new ArrayList<>();
        ResultSet resultset = preparedStatement.executeQuery();
        while(resultset.next()){
            int SID = resultset.getInt(col_specialityID);
            String name = resultset.getString(col_name);
            SpecialityDTO specialty = new SpecialityDTO(SID, name);
            allSpecialities .add(specialty);
        }
        //close connection
        DataBaseConnector.closeResultSet(resultset);
        DataBaseConnector.closePreparedStatement(preparedStatement);
        DataBaseConnector.closeConnection(connection);

        return allSpecialities ;
    }

    @Override
    //int parameter as all tables has int primary keys
    public SpecialityDTO search(int ID) throws SQLException{
        Connection connection = DataBaseConnector.getConnection();
        SpecialityDTO speciality = null;
        PreparedStatement preparedStatement = connection.prepareStatement(SearchSpecialityQuery);
        preparedStatement.setInt(1, ID);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            int SID = resultSet.getInt(col_specialityID);
            String name = resultSet.getString(col_name);
            speciality = new SpecialityDTO(SID, name);
        }
        DataBaseConnector.closeResultSet(resultSet);
        DataBaseConnector.closePreparedStatement(preparedStatement);
        DataBaseConnector.closeConnection(connection);
        return speciality;
    }

    @Override
    public int insert(SpecialityDTO speciality) throws SQLException{
        Connection connection = DataBaseConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(InsertSpecialityQuery);
        preparedStatement.setInt(1, speciality.getSPECIALTYID());
        preparedStatement.setString(2, speciality.getNAME());
        int numOfInsertedRecords = preparedStatement.executeUpdate();
        DataBaseConnector.closePreparedStatement(preparedStatement);
        DataBaseConnector.closeConnection(connection);
        return numOfInsertedRecords;
    }
    //update the record with ID to be the same as element
    @Override
    public int update(SpecialityDTO speciality) throws SQLException{
        Connection connection = DataBaseConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UpdateSpecialityQuery);
        preparedStatement.setInt(1, speciality.getSPECIALTYID());
        preparedStatement.setString(2, speciality.getNAME());
        preparedStatement.setInt(3, speciality.getSPECIALTYID());
        int result = preparedStatement.executeUpdate();
        DataBaseConnector.closePreparedStatement(preparedStatement);
        DataBaseConnector.closeConnection(connection);
        return result;
    }

    @Override
    public int delete(int ID) throws SQLException {
        Connection connection = DataBaseConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DeleteSpecialityQuery);
        preparedStatement.setInt(1, ID);
        int result = preparedStatement.executeUpdate();
        DataBaseConnector.closePreparedStatement(preparedStatement);
        DataBaseConnector.closeConnection(connection);
        return result;
    }
    @Override
    public SpecialityDTO getMostRequestedSpeciality() throws SQLException {
        Connection connection = DataBaseConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(getMostRequestedSpecialityQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        SpecialityDTO speciality = null;
        if (resultSet.next()) {
            int ID = resultSet.getInt(col_specialityID);
            String name = resultSet.getString(col_name);
            speciality = new SpecialityDTO(ID, name);
        }
        DataBaseConnector.closeResultSet(resultSet);
        DataBaseConnector.closePreparedStatement(preparedStatement);
        DataBaseConnector.closeConnection(connection);
        return speciality;
    }
    @Override
    public SpecialityDTO getLeastRequestedSpeciality() throws SQLException {
        Connection connection = DataBaseConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(getLeastRequestedSpecialityQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        SpecialityDTO speciality = null;
        if (resultSet.next()) {
            int ID = resultSet.getInt(col_specialityID);
            String name = resultSet.getString(col_name);
            speciality = new SpecialityDTO(ID, name);
        }
        DataBaseConnector.closeResultSet(resultSet);
        DataBaseConnector.closePreparedStatement(preparedStatement);
        DataBaseConnector.closeConnection(connection);
        return speciality;
    }

}
