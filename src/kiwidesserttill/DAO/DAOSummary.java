package kiwidesserttill.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import kiwidesserttill.Model.Summary;
import kiwidesserttill.Model.Order;

/**
 * Summary Data Access Object
 * DB Connection, Select
 * @author HJS
 * @version 2016. 8.10
 */
public class DAOSummary {
    
    private Connection con=null;
    DBconn db = new DBconn();//DBConccetion Class    
    
    public DAOSummary(){
       con = db.connect(); //DB connection
    }
    
    /**
     * fetching date list what have taken ordering
     * @param combo 
     */
    public void selectDateCombo(JComboBox combo){        
        Statement stmt =null;
        ResultSet rs =null;
        
        String sqlSelect = "select distinct(dt)as dt FROM tblDailySales order by 1 desc;";
        System.out.println("sqlSelect : " + sqlSelect);  
        try {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sqlSelect);    
            combo.addItem("-"); //default none choosen date 
            while(rs.next()){
                int i=0;
                String comboStr = new String("comsrt"+i++);
                comboStr = rs.getString(i++);
                combo.addItem(comboStr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSummary.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
          //  db.close(stmt, con);
        }       
    }
    
    /**
     * fetching daily list of sold orders
     * @param dt : seaching date
     * @return : dataset
     */
    public ArrayList<Summary> selectDailySales(String dt){
        ArrayList<Summary> data = new ArrayList<>();
        Statement stmt =null;
        ResultSet rs =null;
        
        String query = "select salesSeq, dt, totalPrice, tax, paidPrice"
                 + ", isnull((select a.name from tblEmp a where a.id = b.id), b.id) as name, tm " 
                 + " FROM tblDailySales b where dt ='" + dt +"' order by 1 desc;"; 
        System.out.println("sqlSelect : " + query);
        try {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(query);
            
            int i=0;
            while(rs.next()){
                int seq = rs.getInt("salesSeq");
                String dbDate = rs.getString("dt");
                double totalPrice  = rs.getDouble("totalPrice");
                double tax = rs.getDouble("tax");
                double paidPrice = rs.getDouble("paidPrice");
                String nm = rs.getString("name");
                String tm = rs.getString("tm");
                
                Summary row = new Summary();
                row.setSalesSeq(seq);
                row.setDt(dbDate);
                row.setTotalPrice(totalPrice);
                row.setTax(tax);
                row.setPaidPrice(paidPrice);
                row.setTillerNm(nm);
                row.setTm(tm);
                data.add(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSummary.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
          //  db.close(stmt, con);
        }  
        return data;        
    }
    
    /**
     * fetching data list about one transaction
     * @param salesSeq : order number
     * @return  :dataset
     */
    public ArrayList<Order> selectSoldItems(int salesSeq){
        ArrayList<Order> data = new ArrayList<>();
        Statement stmt =null;
        ResultSet rs =null;
        
        String query = "select seq, seqDep, no, qty, pdtName, price " 
                     + "from tblSoldItems where salesSeq='"+ salesSeq +"'" 
                     + "order by (replace(CONVERT(char,seq),' ', '')+replace(CONVERT(char,seqDep), ' ', ''));"; 
        System.out.println("query : " + query);
        try {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(query);            
            int i=0;
            while(rs.next()){
                int    seq        = rs.getInt("seq");
                int    seqDep     = rs.getInt("seqDep");
                String no         = rs.getString("no");
                int    qty        = rs.getInt("qty");
                String nm         = rs.getString("pdtName");
                double price      = rs.getDouble("price");                
                Order row = new Order();
                row.setSeq(seq);
                row.setDependSeq(seqDep);
                row.setNo(no);
                row.setQty(qty);
                row.setDesertNm(nm);
                row.setPrice(price);
                data.add(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSummary.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
          //  db.close(stmt, con);
        }
        return data;        
    }
}
