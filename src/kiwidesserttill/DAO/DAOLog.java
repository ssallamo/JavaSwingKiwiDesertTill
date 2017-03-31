package kiwidesserttill.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import kiwidesserttill.Model.LogOnOff;

/**
 * Log on off Data Access Object
 * DB Connection, Select, update, insert
 * @author HJS
 * @version 2016. 8.10
 */
public class DAOLog {    
    private Connection con=null;
    DBconn db = new DBconn();//DBConccetion Class    
    
    public DAOLog(){
        con = db.connect();
    }  
    /**
     * Fetching data log off employee
     * @return dataset
     */
    public ArrayList<LogOnOff> selectLogOffEmp(){ 
        ArrayList<LogOnOff> data = new ArrayList<>();
        Statement stmt =null;
        ResultSet rs =null;
                
        String query = "Select id, name, lastLogOnTime, lastLogOffTime, password  From tblEmp where onOff ='0';"; 
        System.out.println("query : " + query);        
        try {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(query);    
            
            int i=0;
            while(rs.next()){
                String id = rs.getString("id");
                String nm = rs.getString("name");
                String ontm  = rs.getString("LastLogOnTime");
                String offtm = rs.getString("lastLogOffTime");
                String pw = rs.getString("password");
                LogOnOff row = new LogOnOff();
                row.setEmpId(id);
                row.setEmpName(nm);
                row.setLastLogOffTm(ontm);
                row.setLastLogOnTm(offtm);
                row.setEmpPassword(pw);
                data.add(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOLog.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
          //  db.close(stmt, con);
        }
        return data;
    }
    
    /**
     * Fetching data log on employee
     * @return 
     */
    public ArrayList<LogOnOff> selectLogOnEmp(){
        ArrayList<LogOnOff> data = new ArrayList<>();         
        Statement stmt =null;
        ResultSet rs =null;       
        
        String query = "Select id, name, lastLogOnTime, lastLogOffTime, password  From tblEmp where onOff ='1' AND id <> '0000';"; 
        System.out.println("query : " + query);        
        try {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(query);
            
            int i=0;
            while(rs.next()){
                String id = rs.getString("id");
                String nm = rs.getString("name");
                String ontm  = rs.getString("LastLogOnTime");
                String offtm = rs.getString("lastLogOffTime");
                String pw = rs.getString("password");
                LogOnOff row = new LogOnOff();
                row.setEmpId(id);
                row.setEmpName(nm);
                row.setLastLogOffTm(ontm);
                row.setLastLogOnTm(offtm);
                row.setEmpPassword(pw);
                data.add(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOLog.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
          //  db.close(stmt, con);
        }
        return data;
    }
    /**
     * Update flag and current time on logonoff field
     * @param id : empid
     * @param f : ON / OFF
     * @return  1 success, -1 fail
     */
    public int updateLonOnStatus(String id, String f){ //Fomat : 0004  OFF
        int result =0;        
        String  flg = (f.equals("OFF")?"0":"1");
        ResultSet rs =null;       
        PreparedStatement ps = null; 
        String strOn  = "update tblEmp Set onOff ='"+flg+"' , lastLogOnTime= getDate() where id ='"+id+"';";            
        String strOff = "update tblEmp Set onOff ='"+flg+"' , lastLogOffTime= getDate() where id ='"+id+"';"; 
            
        try { 
            String sqlUpdate = f.equals("OFF")? strOff: strOn;
            System.out.println("sqlUpdate : " + sqlUpdate);   
            ps = con.prepareStatement(sqlUpdate);
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOLog.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            //db.close(rs, ps, con);
        }
        return result;
    }
    
    /**
     * Insert New ID
     * @param id :id
     * @param nm :name
     * @param pw : password
     * @return  1 success, -1 fail
     */
    public int insertNewId (String id, String nm, String pw){
        int result =-1;        
        PreparedStatement ps = null; 
        ResultSet rs =null;       
        String sqlInsert = "insert into tblEmp (id, name, password, onOff)"
                    + "values ('"+id+"', '"+nm+"', '"+pw+"', '"+0+"');";            
        System.out.println("sqlInsert : " + sqlInsert);
        try{    
            ps = con.prepareStatement(sqlInsert);            
            ps.execute(); 
        }
        catch(SQLException ex) {
            Logger.getLogger(DAOLog.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            //db.close(rs, ps, con);
            result =1;
        }
        return result;
    }
}
