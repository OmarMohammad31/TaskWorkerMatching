package DataBase.DTO;
public class WorkerWithAvgRatingAndTotalWageDTO
{
    private WorkerDTO workerDTO;
    private double tvgRating;
    private int totalWage;
    public WorkerWithAvgRatingAndTotalWageDTO(int totalWage, double tvgRating, WorkerDTO workerDTO) {
        this.totalWage = totalWage;
        this.tvgRating = tvgRating;
        this.workerDTO = workerDTO;
    }
    public int getTotalWage() {return totalWage;}
    public void setTotalWage(int totalWage) {this.totalWage = totalWage;}

    public double getTvgRating() {return tvgRating;}
    public void setTvgRating(double tvgRating) {this.tvgRating = tvgRating;}

    public WorkerDTO getWorkerDTO() {return workerDTO;}
    public void setWorkerDTO(WorkerDTO workerDTO) {this.workerDTO = workerDTO;}
}