package DataBase.DTO;
import java.time.LocalDateTime;
public class RequestDTO
{
    private int RID;
    private int CID;
    private int TID;
    private String ADDRESS;
    private LocalDateTime PLACEMENTTIME;
    private LocalDateTime PREFERREDTIMETOCARRYOUT;
    private RequestStatus STATUS;
    public RequestDTO(int RID, int CID, int TID, String ADDRESS, LocalDateTime PLACEMENTTIME, LocalDateTime PREFERREDTIMETOCARRYOUT, RequestStatus STATUS) {
        this.RID = RID;
        this.CID = CID;
        this.TID = TID;
        this.ADDRESS = ADDRESS;
        this.PLACEMENTTIME = PLACEMENTTIME;
        this.PREFERREDTIMETOCARRYOUT = PREFERREDTIMETOCARRYOUT;
        this.STATUS = STATUS;
    }

    public int getRID() {return RID;}
    public void setRID(int RID) {this.RID = RID;}

    public int getCID() {return CID;}
    public void setCID(int CID) {this.CID = CID;}

    public int getTID() {return TID;}
    public void setTID(int TID) {this.TID = TID;}

    public String getADDRESS() {return ADDRESS;}
    public void setADDRESS(String ADDRESS) {this.ADDRESS = ADDRESS;}

    public LocalDateTime getPLACEMENTTIME() {return PLACEMENTTIME;}
    public void setPLACEMENTTIME(LocalDateTime PLACEMENTTIME) {this.PLACEMENTTIME = PLACEMENTTIME;}

    public LocalDateTime getPREFERREDTIMETOCARRYOUT() {return PREFERREDTIMETOCARRYOUT;}
    public void setPREFERREDTIMETOCARRYOUT(LocalDateTime PREFERREDTIMETOCARRYOUT) {this.PREFERREDTIMETOCARRYOUT = PREFERREDTIMETOCARRYOUT;}

    public RequestStatus getSTATUS() {return STATUS;}
    public void setSTATUS(RequestStatus STATUS) {this.STATUS = STATUS;}
}
