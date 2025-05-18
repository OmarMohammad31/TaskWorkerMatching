package DataBase.DTO;
public class SpecialityDTO
{
    private int SPECIALTYID;
    private String NAME;

    public SpecialityDTO(int SPECIALTYID, String NAME) {
        this.SPECIALTYID = SPECIALTYID;
        this.NAME = NAME;
    }

    @Override
    public String toString() {
        return "SpecialityDTO{" +
                "SPECIALTYID=" + SPECIALTYID +
                ", NAME='" + NAME + '\'' +
                '}';
    }

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
}
