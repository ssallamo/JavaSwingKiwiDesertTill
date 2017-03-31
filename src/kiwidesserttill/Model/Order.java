package kiwidesserttill.Model;

/**
 * Menu Data Model
 * @author HJS
 * @version 2016. 08. 15.
 */
public class Order {
    
    private int Seq; //row Sequence Number
    private int DependSeq; //when adding add on Order, not increasing Order No, and past Seq No will be assigned
    private String No; //by using display 
    private int Qty; //Qty 
    private String DesertNm;
    private double Price;
    
    public Order(){}
    
    public Order(int Seq, int DependSeq, int Qty, String desertNm, double Price ){
        this.Seq = Seq;
        this.DependSeq = DependSeq;
        this.No = String.valueOf(Seq);
        this.Qty = Qty;
        this.DesertNm = DesertNm;
        this.Price = Double.valueOf(String.format("%.02f", Price));                
    }

    public int getSeq() {
        return Seq;
    }

    public int getDependSeq() {
        return DependSeq;
    }

    public String getNo() {
        return No;
    }

    public int getQty() {
        return Qty;
    }

    public String getDesertNm() {
        return DesertNm;
    }

    public double getPrice() {
        return Price;
    }

    public void setSeq(int Seq) {
        this.Seq = Seq;
    }

    public void setDependSeq(int DependSeq) {
        this.DependSeq = DependSeq;
    }

    public void setNo(String No) {
        this.No = No;
    }

    public void setQty(int Qty) {
        this.Qty = Qty;
    }

    public void setDesertNm(String DesertNm) {
        this.DesertNm = DesertNm;
    }

    public void setPrice(double Price) {
        this.Price = Double.valueOf(String.format("%.02f", Price));
    }    
}
