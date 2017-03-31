package kiwidesserttill.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import kiwidesserttill.OrderEastListModel;

/**
 * Order Data Access Object
 * DB Connection, Select, insert, delete, update
 * @author HJS
 * @version 2016. 8.10
 */
public class DAOOrder {
    
    private Connection con=null;
    DBconn db = new DBconn();//DBConccetion Class    
    
    public DAOOrder(){
       con = db.connect(); //DB connection
    }
    
    /**
     * Saves One Order Transaction
     * @param data Talbe data
     * @param totalPrice
     * @param tillerId
     * @return true 1 fail 0
     */
    public int insertOrder(OrderEastListModel data, Double totalPrice, String tillerId){
        int result = 0;
        int rowCount = data.getRowCount();
        System.out.println("rowCount : " + data.getRowCount());
        PreparedStatement ps = null;
        ResultSet rs =null;
        
        //1. insert 1 rowData into tblDailySales (include Tax : calculating 10%)
        double tax = totalPrice*0.1;
        double pureBenefit = totalPrice*0.9;
        String sqlInsert = "insert into tblDailySales (dt, totalPrice, tax, paidPrice, id, tm) "
                    + "values ( Convert(varchar(10),Getdate(),112) ,"
                    + totalPrice + " , " 
                    + tax + " , " 
                    + pureBenefit + " , '"
                    + tillerId +"' , "
                    + "Convert(varchar(30),Getdate(),120))SELECT @@IDENTITY";            
        System.out.println("sqlInsert : " + sqlInsert);
        try{    
            ps = con.prepareStatement(sqlInsert);            
            ps.execute(); 
        }
        catch(SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //2. select MaxSeq from tblDailySales
        int seqKey = 0;
        String sqlSelect = "select isnull(Max(salesSeq),0) as seqKey from tblDailySales;";
        System.out.println("sqlSelect : " + sqlSelect);     
        try {
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sqlSelect);                  
            rs.beforeFirst();            
            if(rs.next()){
                seqKey = Integer.valueOf(rs.getString("seqKey"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //3. insert rows into tblSoldItems with MaxSeq
        try{
            String sql = "INSERT INTO tblSoldItems (salesSeq, seq, seqDep, no, qty, pdtName, price) "
                                      + "values(?,?,?,?,?,?,?);";
            ps = con.prepareStatement(sql);
            for(int i=0; i<rowCount; i++){
                int    tSeq   = (int)data.getValueAt(i, 0);
                int    tSeqDp = (int)data.getValueAt(i, 1);
                String tNo    = (String)data.getValueAt(i, 2);
                int    tQty   = (int)data.getValueAt(i, 3);
                String tDstNm = (String)data.getValueAt(i, 4);
                double tPrice = (double)data.getValueAt(i, 5);
                ps.setInt(1, seqKey);
                ps.setInt(2, tSeq);
                ps.setInt(3, tSeqDp);
                ps.setString(4, tNo);
                ps.setInt(5,tQty);
                ps.setString(6, tDstNm);
                ps.setDouble(7, tPrice);
                ps.addBatch();
                System.out.println("sqlInsert : " + sql);
            }
            ps.executeBatch();
        }catch(SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            //db.close(rs, ps, conn);
            result =1;
        }
        return result;
    }
    
    /**
     * fetching name of list of logon employee
     * @return dataset
     */
    public ArrayList<String> selectTillerCombo(){
        ArrayList<String> data = new ArrayList<>();
        Statement stmt =null;
        ResultSet rs =null;
        
        String sqlSelect = "select name from tblEmp where onOff='1';";
        System.out.println("sqlSelect : " + sqlSelect);        
        try {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sqlSelect);
            
            while(rs.next()){
                String nm = rs.getString("name");
                data.add(nm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
          //  db.close(stmt, con);
        }
        return data;
    }
    
}
