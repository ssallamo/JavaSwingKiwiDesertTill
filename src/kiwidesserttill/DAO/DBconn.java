package kiwidesserttill.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Database connection Class
 * @author HJS
 * @version 2016. 8. 25.
 */
public class DBconn {  
    /**
     * Connect DataBase
     * @return dbconnection
     */
    public Connection connect(){        
        Connection con=null;
        try{
            //Loading Driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Ceonnection1 loaded successfully!");  
        }
        catch(ClassNotFoundException e){e.printStackTrace();
        }catch(Exception e){e.printStackTrace();
        }finally{          
        }
        
        //Get Connection
        try{
            String url = "jdbc:sqlserver://localhost:1433;DatabaseName=kiwiTill";
            con = DriverManager.getConnection(url,"till","till");
            System.out.println("Ceonnection1 Established successfully!");  
        }catch(Exception e){e.printStackTrace();
        }finally{          
        }
        return con;
    }
    
    /**
     * closing db
     * @param stmt excuted sql statement
     * @param conn dbconnection
     */
    public void close(Statement stmt, Connection conn){
        try{
            if(stmt!=null){stmt.close();}
            if(conn!=null){conn.close();}
        }catch(SQLException se){se.printStackTrace();    
        }catch(Exception e){e.printStackTrace();
        }finally{
            System.out.println("Ceonnection1 Closed successfully!");            
        }
    }
    
    /**
     * closing db
     * @param rs  : result set memory
     * @param pstmt : excuted sql statement
     * @param conn : dbconnection
     */
    public void close(ResultSet rs, PreparedStatement pstmt, Connection conn){
        try{
            if(rs!=null){rs.close();}
            if(pstmt!=null){ pstmt.close();}
            if(conn!=null){ conn.close();}
        }catch(SQLException se){se.printStackTrace();    
        }catch(Exception e){e.printStackTrace();
        }finally{
            System.out.println("Ceonnection1 Closed successfully!");            
        }
    }
}
