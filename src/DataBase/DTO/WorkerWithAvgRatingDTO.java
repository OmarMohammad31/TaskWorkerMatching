package DataBase.DTO;
public class WorkerWithAvgRating
{
    private double avgRating;
    private WorkerDTO workerDTO;

    public WorkerWithAvgRating(double avgRating, WorkerDTO workerDTO) {
        this.avgRating = avgRating;
        this.workerDTO = workerDTO;
    }
    public double getAvgRating() {return avgRating;}
    public void setAvgRating(double avgRating) {this.avgRating = avgRating;}
    public WorkerDTO getWorkerDTO() {return workerDTO;}
    public void setWorkerDTO(WorkerDTO workerDTO) {this.workerDTO = workerDTO;}
}
