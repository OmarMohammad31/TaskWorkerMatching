package DataBase.DAO;
import DataBase.DTO.WorkerDTO;
import DataBase.DTO.WorkerWithAvgRatingDTO;
import DataBase.DTO.WorkerWithAvgRatingAndTotalWageDTO;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public interface WorkerDAO extends GenericDAO<WorkerDTO>
{
    public List<WorkerDTO> getHighestRatedWorkersPerSpecialty(LocalDateTime startTime, LocalDateTime endTime) throws SQLException;
    public ArrayList<WorkerWithAvgRatingAndTotalWageDTO> getTotalWageForEachWorkerWithRating(LocalDateTime startTime, LocalDateTime endTime) throws SQLException;
    public ArrayList<WorkerWithAvgRatingDTO> getWorkersWithMoreThan4AndHalfRating() throws SQLException;
    public WorkerDTO getMostRequestedWorker() throws SQLException;
    public WorkerDTO getleasttRequestedWorker() throws SQLException;
}