package kiwidesserttill.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import kiwidesserttill.Model.Menu;


/**
 * Menu item Data Access Object
 * DB Connection, Select
 * @author HJS
 * @version 2016. 8.25
 */
public class DAOMenu {
    
    private Connection con=null;
    DBconn db = new DBconn();//DBConccetion Class    
    
    public DAOMenu(){
       con = db.connect(); //DB connection
    }
    /**
     * fetch the count from divideCode
     * @param divCode : divide code
     * @return  total count
     */
    public int selectCountProduct(String divCode){
        int count=0;
        Statement stmt =null;
        ResultSet rs =null;
        
        String sqlSelect = "select count(*) cnt from tblProducts where pdtDivCode = '"+divCode+"';";
        System.out.println("sqlSelect : " + sqlSelect);            
        try{
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sqlSelect); 
            
            rs.beforeFirst();            
            if(rs.next()){
                count = Integer.valueOf(rs.getString("cnt"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
          //  db.close(stmt, con);
        }
        return count;
    }
    
    /**
     * fetch the count from divideCode
     * @param productName : Product name
     * @return  Menu
     */
    public Menu selectAProduct(String productName){
        Menu aMenu = new Menu();
        Statement stmt =null;
        ResultSet rs =null;
        
        String sqlSelect = "select pdtName, price1, price2, price3 from tblProducts where pdtName = '"+productName+"';";
        System.out.println("sqlSelect : " + sqlSelect);            
        try{
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sqlSelect);      
            
            rs.beforeFirst();            
            if(rs.next()){
                aMenu.setPdtName(rs.getString("pdtName"));
                aMenu.setPrice1(Double.valueOf(rs.getString("price1")));
                aMenu.setPrice2(Double.valueOf(rs.getString("price2")));
                aMenu.setPrice3(Double.valueOf(rs.getString("price3")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
          //  db.close(stmt, con);
        }
        return aMenu;
    }
    /**
     * fetching list of product by divCode
     * @param divCode : divide category code
     * @return  : array list set
     */
    public ArrayList<String> selectProductList(String divCode){
        ArrayList<String> data = new ArrayList<>();
        Statement stmt =null;
        ResultSet rs =null;
        
        String sqlSelect = "select pdtName from tblProducts where pdtDivCode = '"+divCode+"';";
        System.out.println("sqlSelect : " + sqlSelect);        
        try {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sqlSelect);
            
            while(rs.next()){
                String nm = rs.getString("pdtName");
                data.add(nm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
          //  db.close(stmt, con);
        }
        
        return data;
    }
    
}
