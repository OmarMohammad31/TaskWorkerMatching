package DataBase.DTO;
import java.time.LocalDateTime;
public class ClientDTO
{
    private int CID;
    private String NAME;
    private String PHONE;
    private String ADDRESS;
    private String EMAIL;
    private String CARDNUM;
    private LocalDateTime EXPDATE;
    private String CVV;

    @Override
    public String toString()
    {
        return "ClientDTO{" +
                "CID=" + CID + ", NAME='" + NAME + ' ' +
                ", PHONE='" + PHONE + ' ' +
                ", ADDRESS='" + ADDRESS + ' ' +
                ", EMAIL='" + EMAIL + ' ' +
                ", CARDNUM='" + CARDNUM + ' ' +
                ", EXPDATE=" + EXPDATE +
                ", CVV='" + CVV + ' ' +
                '}';
    }

    public ClientDTO(int CID, String NAME, String PHONE, String ADDRESS, String EMAIL, String CARDNUM, LocalDateTime EXPDATE, String CVV)
    {
        this.CID = CID;
        this.NAME = NAME;
        this.PHONE = PHONE;
        this.ADDRESS = ADDRESS;
        this.EMAIL = EMAIL;
        this.CARDNUM = CARDNUM;
        this.EXPDATE = EXPDATE;
        this.CVV = CVV;
    }

    public int getCID() {return CID;}
    public void setCID(int CID) {this.CID = CID;}

    public String getNAME() {return NAME;}
    public void setNAME(String NAME) {this.NAME = NAME;}

    public String getPHONE() {return PHONE;}
    public void setPHONE(String PHONE) {this.PHONE = PHONE;}

    public String getADDRESS() {return ADDRESS;}
    public void setADDRESS(String ADDRESS) {this.ADDRESS = ADDRESS;}

    public String getEMAIL() {return EMAIL;}
    public void setEMAIL(String EMAIL) {this.EMAIL = EMAIL;}

    public String getCARDNUM() {return CARDNUM;}
    public void setCARDNUM(String CARDNUM) {this.CARDNUM = CARDNUM;}

    public LocalDateTime getEXPDATE() {return EXPDATE;}
    public void setEXPDATE(LocalDateTime EXPDATE) {this.EXPDATE = EXPDATE;}

    public String getCVV() {return CVV;}
    public void setCVV(String CVV) {this.CVV = CVV;}
}
