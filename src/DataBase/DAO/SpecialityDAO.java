package DataBase.DAO;
import DataBase.DTO.SpecialityDTO;
import java.sql.SQLException;
public interface SpecialityDAO extends GenericDAO<SpecialityDTO>
{
    public SpecialityDTO getMostRequestedSpeciality() throws SQLException;
    public SpecialityDTO getLeastRequestedSpeciality() throws SQLException;
}
