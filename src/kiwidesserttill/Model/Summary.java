package kiwidesserttill.Model;

/**
 * Summary Screen Data Model
 * @author HJS
 * @version 2016. 08. 15.
 */
public class Summary {
    private int salesSeq;
    private String dt;
    private double totalPrice;
    private double tax;
    private double paidPrice;
    private String tillerNm;
    private String tm;

    public int getSalesSeq() {
        return salesSeq;
    }
    public String getDt() {
        return dt;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public double getTax() {
        return tax;
    }
    public double getPaidPrice() {
        return paidPrice;
    }
    public String getTillerNm() {
        return tillerNm;
    }
    public String getTm() {
        return tm;
    }
    public void setSalesSeq(int salesSeq) {
        this.salesSeq = salesSeq;
    }
    public void setDt(String dt) {
        this.dt = dt;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public void setTax(double tax) {
        this.tax = tax;
    }
    public void setPaidPrice(double paidPrice) {
        this.paidPrice = paidPrice;
    }
    public void setTillerNm(String tillerNm) {
        this.tillerNm = tillerNm;
    }    
    public void setTm(String tm) {
        this.tm = tm;
    }
}
