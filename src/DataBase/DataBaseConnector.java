package DataBase;
import java.sql.*;
public class DataBaseConnector
{
    private static final DataBaseConnector instance = new DataBaseConnector();
    public static final DataBaseConnector getInstance(){return instance;}
    private static String url = "jdbc:sqlserver://localhost:1433;databaseName=TaskWorkerMatching;AUTO_SERVER=TRUE;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
    private DataBaseConnector(){}
    public static Connection getConnection() throws SQLException {return DriverManager.getConnection(url);}
    public static void closeConnection(Connection connection) throws SQLException{connection.close();}
    public static void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {preparedStatement.close();}
    public static void closeResultSet(ResultSet resultSet)throws SQLException {resultSet.close();}
}