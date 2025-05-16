package DataBase.DTO;
public class TaskDTO
{
    public int getTID()
    {
        return TID;
    }

    public void setTID(int TID)
    {
        this.TID = TID;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public String getSPECIALITY()
    {
        return SPECIALITY;
    }

    public void setSPECIALITY(String SPECIALITY)
    {
        this.SPECIALITY = SPECIALITY;
    }

    public int getAVGNEEDEDTIME()
    {
        return AVGNEEDEDTIME;
    }

    public void setAVGNEEDEDTIME(int AVGNEEDEDTIME)
    {
        this.AVGNEEDEDTIME = AVGNEEDEDTIME;
    }

    public int getFEE()
    {
        return FEE;
    }

    public void setFEE(int FEE)
    {
        this.FEE = FEE;
    }

    private int TID;
    private String Name;
    private String SPECIALITY;
    private int AVGNEEDEDTIME;
    private int FEE;
}
