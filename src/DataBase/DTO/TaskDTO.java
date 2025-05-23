package DataBase.DTO;
public class TaskDTO
{
    private int TID;
    private String Name;
    private int SID;
    private int AVGNEEDEDTIME;
    private int FEE;

    public TaskDTO(int TID, String name, int SID, int AVGNEEDEDTIME, int FEE) {
        this.TID = TID;
        Name = name;
        this.SID = SID;
        this.AVGNEEDEDTIME = AVGNEEDEDTIME;
        this.FEE = FEE;
    }
    @Override
    public String toString() {return "TaskDTO{" + "TID=" + TID + ", Name='" + Name + '\'' + ", SPECIALITY='" + SID + '\'' + ", AVGNEEDEDTIME=" + AVGNEEDEDTIME + ", FEE=" + FEE + '}'; }
    public int getTID() {return TID;}
    public void setTID(int TID) {this.TID = TID; }
    public String getName() {return Name;}
    public void setName(String name) {Name = name;}
    public int getSPECIALITYID() {return SID;}
    public void setSPECIALITY(int SPECIALITYID) {this.SID = SPECIALITYID;}
    public int getAVGNEEDEDTIME() {return AVGNEEDEDTIME;}
    public void setAVGNEEDEDTIME(int AVGNEEDEDTIME) {this.AVGNEEDEDTIME = AVGNEEDEDTIME;}
    public int getFEE() {return FEE;}
    public void setFEE(int FEE) {this.FEE = FEE;}
}