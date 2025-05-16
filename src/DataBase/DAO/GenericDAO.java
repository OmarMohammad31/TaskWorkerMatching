package DataBase.DAO;
import java.util.ArrayList;
public interface GenericDAO<T>
{
    //customize the parameter names in your implementaion
    public ArrayList<T> getAll();
    //int parameter as all tables has int primary keys
    public T search(int ID);
    public int insert(T element);
    //update the record with ID to be the same as element
    public int update(int ID, T element);
    public int delete(T element);
}