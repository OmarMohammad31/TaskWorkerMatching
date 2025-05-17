package DataBase.DAO;
import DataBase.DTO.ClientDTO;
import DataBase.DTO.SpecialityDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SpecialityDAO extends GenericDAO<SpecialityDTO>
{
    public SpecialityDTO getMostRequestedSpeciality() throws SQLException;
    public SpecialityDTO getLeastRequestedSpeciality() throws SQLException;
}
