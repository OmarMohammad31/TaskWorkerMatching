package DataBase.DTO;
public class WorkerDTO
{
    private int WID;
    private String NAME;
    public int getWID() {return WID;}
    public void setWID(int WID) {this.WID = WID;}
    public String getNAME() {return NAME;}
    public void setNAME(String NAME) {this.NAME = NAME;}
    public WorkerDTO(int WID, String NAME)
    {
        this.WID = WID;
        this.NAME = NAME;
    }
    @Override
    public String toString() {return "WorkerDTO{" + "WID=" + WID + ", NAME='" + NAME + '\'' + '}';}
}
