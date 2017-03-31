package kiwidesserttill;

import kiwidesserttill.DAO.DAOOrder;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class displays based Pannel for Order base
 * @author HJS
 * @version 2016. 8. 8.
 */ 
public class OrderBase extends JPanel{
    
    KiwiDessertTill awin;
    OrderWestMenu westPan;
    OrderEastList eastPan;    
    OrderEastListTable ordLstTbl; //order list table
    JTextField tfdSum; //total payment textfield
    DAOOrder daoOdr;
    
    public OrderBase(KiwiDessertTill awin){
        this.awin = awin;
        
        ordLstTbl = new OrderEastListTable();   
        tfdSum = new JTextField("0.00");
        daoOdr = new DAOOrder();
        
        westPan = new OrderWestMenu(ordLstTbl, tfdSum);
        eastPan = new OrderEastList(ordLstTbl, tfdSum, daoOdr, awin);
        this.setLayout(new BorderLayout());
        
        this.add(westPan, BorderLayout.CENTER);
        this.add(eastPan, BorderLayout.EAST);     
    }
}
