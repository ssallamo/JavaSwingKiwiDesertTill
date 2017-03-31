package kiwidesserttill.Model;

/**
 * Log On/Off Data Model
 * @author HJS
 * @version 2016. 08. 15.
 */
public class LogOnOff {
    
    private String empId;
    private String empName;
    private String empPassword;
    private String lastLogOnTm;
    private String lastLogOffTm;
    
    public LogOnOff(){
    }

    public LogOnOff(String id, String name, String pw, String xLogOntm, String xLogOffTm){
        this.empId = id;
        this.empName = name;
        this.empPassword = pw;
        this.lastLogOnTm = xLogOntm;
        this.lastLogOffTm = xLogOffTm;
    }
    
    public String getEmpName() {
        return empName;
    }

    public String getEmpId() {
        return empId;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public String getLastLogOnTm() {
        return lastLogOnTm;
    }

    public String getLastLogOffTm() {
        return lastLogOffTm;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }

    public void setLastLogOnTm(String lastLogOnTm) {
        this.lastLogOnTm = lastLogOnTm;
    }

    public void setLastLogOffTm(String lastLogOffTm) {
        this.lastLogOffTm = lastLogOffTm;
    }
    
}
