package DataBase.DTO;
public class WorkerSpecialitiesDTO
{
    private int WID;
    private int SPECID;

    public WorkerSpecialitiesDTO(int WID, int SPECID) {
        this.WID = WID;
        this.SPECID = SPECID;
    }
    public int getWID() {return WID;}
    public void setWID(int WID)
    {
        this.WID = WID;
    }
    public int getSPECID()
    {
        return SPECID;
    }
    public void setSPECID(int SPECID)
    {
        this.SPECID = SPECID;
    }
}