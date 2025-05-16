package DataBase.DTO;
public class WorkerDTO
{
    public int getWID()
    {
        return WID;
    }

    public void setWID(int WID)
    {
        this.WID = WID;
    }

    public String getNAME()
    {
        return NAME;
    }

    public void setNAME(String NAME)
    {
        this.NAME = NAME;
    }

    private int WID;
    private String NAME;
}
