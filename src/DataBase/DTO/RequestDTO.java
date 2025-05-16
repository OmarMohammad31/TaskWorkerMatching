package DataBase.DTO;
import java.time.LocalDateTime;
public class RequestDTO
{
    private int RID;
    private int WID;
    private int CID;
    private int TID;
    private String ADDRESS;
    private LocalDateTime PLACEMENTTIME;
    private LocalDateTime PREFERREDTIMETOCARRYOUT;
    private RequestStatus STATUS;
    private int ACTUALTIMETAKEN;
    private int WORKERRATING;
    private int CLIENTRATING;
    private String CLIENTFEEDBACK;
    private String WORKERFEEDBACK;

    public int getRID() {return RID;}
    public void setRID(int RID) {this.RID = RID;}

    public int getWID() {return WID;}
    public void setWID(int WID) {this.WID = WID;}

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

    public int getACTUALTIMETAKEN() {return ACTUALTIMETAKEN;}
    public void setACTUALTIMETAKEN(int ACTUALTIMETAKEN) {this.ACTUALTIMETAKEN = ACTUALTIMETAKEN;}

    public int getWORKERRATING() {return WORKERRATING;}
    public void setWORKERRATING(int WORKERRATING) {this.WORKERRATING = WORKERRATING;}

    public int getCLIENTRATING() {return CLIENTRATING;}
    public void setCLIENTRATING(int CLIENTRATING) {this.CLIENTRATING = CLIENTRATING;}

    public String getCLIENTFEEDBACK() {return CLIENTFEEDBACK;}
    public void setCLIENTFEEDBACK(String CLIENTFEEDBACK) {this.CLIENTFEEDBACK = CLIENTFEEDBACK;}

    public String getWORKERFEEDBACK() {return WORKERFEEDBACK;}
    public void setWORKERFEEDBACK(String WORKERFEEDBACK) {this.WORKERFEEDBACK = WORKERFEEDBACK;}
}
