package DataBase.DTO;
public class ExecutedRequestDTO
{
    private int WID;
    private int RID;
    private int ACTUALTIMETAKEN;
    private int WORKERRATING;
    private int CLIENTRATING;
    private String CLIENTFEEDBACK;
    private String WORKERFEEDBACK;

    public ExecutedRequestDTO(int WID, int RID, int ACTUALTIMETAKEN, int WORKERRATING, int CLIENTRATING, String CLIENTFEEDBACK, String WORKERFEEDBACK) {
        this.WID = WID;
        this.RID = RID;
        this.ACTUALTIMETAKEN = ACTUALTIMETAKEN;
        this.WORKERRATING = WORKERRATING;
        this.CLIENTRATING = CLIENTRATING;
        this.CLIENTFEEDBACK = CLIENTFEEDBACK;
        this.WORKERFEEDBACK = WORKERFEEDBACK;
    }

    public int getWID() {return WID;}
    public void setWID(int WID) {this.WID = WID;}

    public int getRID() {return RID;}
    public void setRID(int RID) {this.RID = RID;}

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
