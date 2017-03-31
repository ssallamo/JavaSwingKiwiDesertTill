package kiwidesserttill.Model;

/**
 * Database Menu fetching model
 * @author HJS
 * @version 2016. 8.25
 */
public class Menu {    
    private String pdtCode;
    private String pdtDivCode;
    private String pdtName;
    private double price1;
    private double price2;
    private double price3;

    public String getPdtCode() {
        return pdtCode;
    }
    public String getPdtDivCode() {
        return pdtDivCode;
    }
    public String getPdtName() {
        return pdtName;
    }
    public double getPrice1() {
        return price1;
    }
    public double getPrice2() {
        return price2;
    }
    public double getPrice3() {
        return price3;
    }
    public void setPdtCode(String pdtCode) {
        this.pdtCode = pdtCode;
    }
    public void setPdtDivCode(String pdtDivCode) {
        this.pdtDivCode = pdtDivCode;
    }
    public void setPdtName(String pdtName) {
        this.pdtName = pdtName;
    }
    public void setPrice1(double price1) {
        this.price1 = price1;
    }
    public void setPrice2(double price2) {
        this.price2 = price2;
    }
    public void setPrice3(double price3) {
        this.price3 = price3;
    }
}
