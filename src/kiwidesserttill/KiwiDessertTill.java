package kiwidesserttill;
/**
 * Kiwi Dessert Till is the system what takes order from customer and
 * recording data into the database.
 * @author HJS
 * @version 2016. 7. 24.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;

public class KiwiDessertTill extends MFrame implements ActionListener, WindowListener{
   
    public OrderBase    pnlOrder;
    public SummaryBase  pnlSummary;
    public LogOnOffBase pnlLogOnOff;
    
    JMenuItem mnitmOrder;
    JMenuItem mnitmSummary;
    JMenuItem mnitmLogOnOff;    

    public KiwiDessertTill(){
        
        //MenuBar
        JMenuBar bar = new JMenuBar();
        JMenu menuO = new JMenu("Order");
        menuO.setMnemonic('O');
        JMenu menuL = new JMenu("LogOnOFF");
        menuL.setMnemonic('L');
        
        //Menu Items DropDown
        mnitmOrder  = new JMenuItem("To Take Orders");
        mnitmSummary  = new JMenuItem("Sales Summary");   
        mnitmOrder.setAccelerator(KeyStroke.getKeyStroke("control O"));  
        mnitmSummary.setAccelerator(KeyStroke.getKeyStroke("control S"));  
        menuO.add(mnitmOrder);
        menuO.add(mnitmSummary);     
        mnitmLogOnOff  = new JMenuItem("Logon/Logoff");    
        mnitmLogOnOff.setAccelerator(KeyStroke.getKeyStroke("control L"));     
        menuL.add(mnitmLogOnOff);          
                
        //Register on the JmenuBar
        bar.add(menuO);
        bar.add(menuL);        
        this.setJMenuBar(bar);
        
        //Create Ordering Panel
        pnlOrder = new OrderBase(this);        
        //Create Summary Panel
        pnlSummary = new SummaryBase(this);            
        //Create LogOn/Off Panel
        pnlLogOnOff = new LogOnOffBase(this);
        
        this.add(pnlSummary);
        this.add(pnlLogOnOff);
        this.add(pnlOrder);
        
        mnitmOrder.addActionListener(this);      
        mnitmSummary.addActionListener(this);       
        mnitmLogOnOff.addActionListener(this); 
    }
    
    public static void main(String[] args) {
        KiwiDessertTill dessert = new  KiwiDessertTill();        
        dessert.setLocationRelativeTo(null);     
        dessert.setVisible(true);
    }
    
    /**
     * Changed pannels
     * @param e : pannel id
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==mnitmOrder){ //order
            this.remove(pnlSummary);
            this.remove(pnlLogOnOff);
            this.getContentPane().add(pnlOrder);
            this.validate();
            this.repaint();
        }
        else if(e.getSource()==mnitmSummary){ //summary
            this.remove(pnlOrder);
            this.remove(pnlLogOnOff);
            this.getContentPane().add(pnlSummary);
            this.validate();
            this.repaint();
        }
        else if(e.getSource()==mnitmLogOnOff){ //logOn/Off
            this.remove(pnlOrder);
            this.remove(pnlSummary);
            this.getContentPane().add(pnlLogOnOff);
            this.validate();
            this.repaint();
        }
        else{
            this.remove(pnlOrder);
            this.remove(pnlSummary);
            this.remove(pnlLogOnOff);
            this.validate();
            this.repaint();
        }            
    }    

    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0); //Window Event
    }
    @Override
    public void windowClosed(WindowEvent e) {}
    @Override
    public void windowIconified(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) {}
}
