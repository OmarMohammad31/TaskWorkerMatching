package DataBase.DAO;
import java.sql.SQLException;
import java.util.ArrayList;
public interface GenericDAO<T>
{
    //customize the parameter names in your implementaion
    public ArrayList<T> getAll() throws SQLException;
    //int parameter as all tables has int primary keys
    public T search(int ID) throws SQLException;
    public int insert(T element) throws SQLException;
    //update the record with ID to be the same as element
    public int update(int ID, T element) throws SQLException;
    public int delete(int ID) throws SQLException;
}