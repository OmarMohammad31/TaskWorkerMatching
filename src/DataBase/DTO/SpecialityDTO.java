package DataBase.DTO;
public class SpecialityDTO
{
    public int getSPECIALTYID()
    {
        return SPECIALTYID;
    }

    public void setSPECIALTYID(int SPECIALTYID)
    {
        this.SPECIALTYID = SPECIALTYID;
    }

    public String getNAME()
    {
        return NAME;
    }

    public void setNAME(String NAME)
    {
        this.NAME = NAME;
    }

    private int SPECIALTYID;
    private String NAME;
}
