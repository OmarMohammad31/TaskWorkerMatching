package DataBase.DTO;
import java.time.LocalDateTime;
public class AvailableTimeSlotsDTO
{
    private int WID;
    private int SLOTID;
    private LocalDateTime STARTOFSLOT;
    private LocalDateTime ENDOFSLOT;

    public AvailableTimeSlotsDTO(int WID, LocalDateTime STARTOFSLOT, int SLOTID, LocalDateTime ENDOFSLOT)
    {
        this.WID = WID;
        this.STARTOFSLOT = STARTOFSLOT;
        this.SLOTID = SLOTID;
        this.ENDOFSLOT = ENDOFSLOT;
    }
    public int getWID() {return WID;}
    public void setWID(int WID) {this.WID = WID;}

    public int getSLOTID() {return SLOTID;}
    public void setSLOTID(int SLOTID) {this.SLOTID = SLOTID;}


    public LocalDateTime getSTARTOFSLOT() {return STARTOFSLOT;}
    public void setSTARTOFSLOT(LocalDateTime STARTOFSLOT) {this.STARTOFSLOT = STARTOFSLOT;}

    public LocalDateTime getENDOFSLOT() {return ENDOFSLOT;}
    public void setENDOFSLOT(LocalDateTime ENDOFSLOT) {this.ENDOFSLOT = ENDOFSLOT;}

}