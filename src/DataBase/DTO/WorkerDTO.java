package DataBase.DTO;
public class WorkerDTO
{
    private int WID;
    private String NAME;
    private String PHONE;
    private String ADDRESS;
    private String EMAIL;

    public String getEMAIL() {return EMAIL;}
    public void setEMAIL(String EMAIL) {this.EMAIL = EMAIL;}
    public String getADDRESS() {return ADDRESS;}
    public void setADDRESS(String ADDRESS) {this.ADDRESS = ADDRESS;}
    public String getPHONE() {return PHONE;}
    public void setPHONE(String PHONE) {this.PHONE = PHONE;}
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

    public WorkerDTO(int WID, String NAME, String PHONE, String ADDRESS, String EMAIL) {
        this.WID = WID;
        this.NAME = NAME;
        this.PHONE = PHONE;
        this.ADDRESS = ADDRESS;
        this.EMAIL = EMAIL;
    }

}
