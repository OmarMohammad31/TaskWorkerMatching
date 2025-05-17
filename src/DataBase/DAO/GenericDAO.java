package DataBase.DAO;
import java.sql.SQLException;
import java.util.ArrayList;
public interface GenericDAO<T>
{
    public ArrayList<T> getAll() throws SQLException;
    public T search(int ID) throws SQLException;
    public int insert(T element) throws SQLException;
    public int update(T element) throws SQLException;
    public int delete(int ID) throws SQLException;
}