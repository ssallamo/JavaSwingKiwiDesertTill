package kiwidesserttill;

import kiwidesserttill.DAO.DAOOrder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class includes purchase list, total amount, current time and change tiller
 * @author HJS
 * @version 2016. 8. 8.
 */ 
public class OrderEastList extends JPanel implements ActionListener, MouseListener{
    
    JPanel pnlTable;
    JPanel pnlOdrBtns;
    OrderEastListTable ordLstTbl;
    JTextField totalPrice;
    JButton btnPls, btnMns, btnRmv;
    JButton btnPayment;
    JLabel lblTm;
    DAOOrder daoOdr;    
    JButton btnTiller;
    KiwiDessertTill screen;
        
    public OrderEastList(OrderEastListTable tbl, JTextField tfdSum,DAOOrder dao, KiwiDessertTill awin){  
        screen = awin;
        
        //About Changeing tiller  : Defualt Admin    
        btnTiller = new JButton("Admin");
        btnTiller.setPreferredSize(new Dimension(137, 40));
        btnTiller.setBackground(new Color(180,209,90));    
        btnTiller.setFont(new Font(Settings.tillerBtnFont, Settings.tillerBtnStyle, Settings.tillerBtnSize));
        btnTiller.addMouseListener(this); //Changing Tiller     
        
        //Display Current time through a thread
        lblTm = new JLabel(new ImageIcon("img/timeLable.png"), JLabel.CENTER);
        lblTm.setText("11-08-2016 2:26 AM");
        lblTm.setVerticalTextPosition(0);
        lblTm.setHorizontalTextPosition(0);
        
        //Top of the east order panel
        JPanel pnl2Lbl = new JPanel(new BorderLayout());  
        pnl2Lbl.add(btnTiller, BorderLayout.WEST);
        pnl2Lbl.add(lblTm, BorderLayout.CENTER);
        
        //Order list image label
        JLabel lblImg = new JLabel();
        ImageIcon orderList = new ImageIcon("img/orderList.png");
        lblImg.setIcon(orderList);   
        
        //Buttons order list update/delete(+,-,x buttons)
        btnPls = new JButton(new ImageIcon("img/plus.png"));
        btnMns = new JButton(new ImageIcon("img/minus.png"));
        btnRmv = new JButton(new ImageIcon("img/remove.png"));
        btnPls.setPreferredSize(new Dimension(40,40));
        btnMns.setPreferredSize(new Dimension(40,40));
        btnRmv.setPreferredSize(new Dimension(40,40));          
        btnPls.setBorderPainted(false);
        btnMns.setBorderPainted(false);
        btnRmv.setBorderPainted(false);
        btnPls.setContentAreaFilled(false);
        btnMns.setContentAreaFilled(false);
        btnRmv.setContentAreaFilled(false);
        btnPls.setFocusPainted(false);       
        btnMns.setFocusPainted(false);      
        btnRmv.setFocusPainted(false);       
        JPanel pnlBtnsBar = new JPanel(new FlowLayout());   
        pnlBtnsBar.add(btnPls);
        pnlBtnsBar.add(btnMns);
        pnlBtnsBar.add(btnRmv);
        btnPls.addActionListener(this);
        btnMns.addActionListener(this);
        btnRmv.addActionListener(this);   
        //Just part of top design
        JPanel imagesBar = new JPanel(new BorderLayout());       
        imagesBar.add(pnl2Lbl, BorderLayout.NORTH);
        imagesBar.add(lblImg, BorderLayout.CENTER);
        imagesBar.add(pnlBtnsBar, BorderLayout.SOUTH);       
        ////////////////////////////////////////////////////////////////////////     
        
        // table, totalprice are decleared outside, like gloval variables
        ordLstTbl = tbl;
        totalPrice =  tfdSum;
        daoOdr = dao;
        totalPrice.setEditable(false);        
        totalPrice.setFont(new Font(Settings.totalAmtFont, Settings.totalAmtStyle, Settings.totalAmtSize));
        totalPrice.setHorizontalAlignment(JTextField.RIGHT);
        
        //Display Total amount and payment button        
        pnlOdrBtns = new JPanel();   
        JLabel lblTotal = new JLabel(new ImageIcon("img/imgLblTotal.png"));
        btnPayment = new JButton(new ImageIcon("img/imgBtnPayment_noEdge.png"));        
        btnPayment.setMargin(new Insets(0, 0, 0, 0));
        btnPayment.addActionListener(this);
        pnlOdrBtns.setLayout(new BorderLayout());
        pnlOdrBtns.add(lblTotal, BorderLayout.WEST);
        pnlOdrBtns.add(totalPrice, BorderLayout.CENTER);
        pnlOdrBtns.add(btnPayment, BorderLayout.SOUTH);
        
        //Assign on This panel
        this.setLayout(new BorderLayout());
        this.add(imagesBar, BorderLayout.NORTH);
        this.add(ordLstTbl, BorderLayout.CENTER);        
        this.add(pnlOdrBtns, BorderLayout.SOUTH);
        
        //Current Time Thread 
        class CurrentTime extends Thread{
            public void run(){
                int i=0;
                while(true){
                    Date dt = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY h:mm a", Locale.ENGLISH);
                    String disp = sdf.format(dt);
                    i++;
                    lblTm.setText(disp);
                    try{ Thread.sleep(1000);}
                    catch(Exception e){e.printStackTrace();}
               }
            }
        }
        CurrentTime ct = new CurrentTime();
        ct.start();        
    }
    
    //initialization about Total amount field
    private void init(){
        ordLstTbl.init();
        totalPrice.setText("0.00");
    }

    //Buttons Action Events
    @Override
    public void actionPerformed(ActionEvent e) {
        //Payment button Click event
        if(e.getSource()==btnPayment){
            double totPrice = Double.valueOf(totalPrice.getText().toString());
            if(totPrice==0.0) return;
            if(dialogDisplay()==0){//Confirm Dialog -> OK : 0 Cancel:2     
                daoOdr.insertOrder(ordLstTbl.ordrTblModel, totPrice, btnTiller.getText().toString()); //data, totalPrice, tiller
                init();
            }
            else{
                return;
            }
        }
        else if(e.getSource()==btnPls){
            ordLstTbl.updateQty('+');
            totalPrice.setText(String.valueOf(ordLstTbl.getTotalPayment()));//total Payment calculate again
        }
        else if(e.getSource()==btnMns){            
            ordLstTbl.updateQty('-');
            totalPrice.setText(String.valueOf(ordLstTbl.getTotalPayment()));//total Payment calculate again
        
        }
        else if(e.getSource()==btnRmv){     
            ordLstTbl.delteRow();
            totalPrice.setText(String.valueOf(ordLstTbl.getTotalPayment()));//total Payment calculate again
        }
    }
    
    /**
     * Payment Confirm Dialog
     * @return OK : 0 Cancel:2     
     */
    public int dialogDisplay(){
        //chagne the Laguage
        Locale lng = new Locale("en", "US");
        JOptionPane.setDefaultLocale(lng);
        
        //Dialog Display Panel
        JPanel pnlBase = new JPanel(new GridLayout());        
        JPanel pnlReport = new JPanel(new GridLayout(0,1));   
        pnlReport.setBorder(BorderFactory.createEmptyBorder(5 , 10 , 5 , 10)); 
        JLabel lblTime1   = new JLabel("Order Time : ");        
        JLabel lblTime2   = new JLabel(lblTm.getText().toString());
        JLabel lblTiller1 = new JLabel("Tiller : ");
        JLabel lblTiller2 = new JLabel(btnTiller.getText().toString());
        JLabel lblPrice1  = new JLabel("Total Payment : ");
        JLabel lblPrice2  = new JLabel(totalPrice.getText().toString());
        pnlReport.add(lblTime1);
        pnlReport.add(lblTime2);
        pnlReport.add(lblTiller1);
        pnlReport.add(lblTiller2);
        pnlReport.add(lblPrice1);
        pnlReport.add(lblPrice2);  
        pnlReport.setBackground(Color.white);
        lblTime1.setFont(new Font(Settings.dlgoCfNmFont, Settings.dlgoCfNmStyle, Settings.dlgoCfTagSize));
        lblTiller1.setFont(new Font(Settings.dlgoCfNmFont, Settings.dlgoCfNmStyle, Settings.dlgoCfTagSize));
        lblPrice1.setFont(new Font(Settings.dlgoCfNmFont, Settings.dlgoCfNmStyle, Settings.dlgoCfTagSize));
        lblTime2.setFont(new Font(Settings.dlgoCfNmFont, Settings.dlgoCfNmStyle, Settings.dlgoCfNmSize));
        lblTiller2.setFont(new Font(Settings.dlgoCfNmFont, Settings.dlgoCfNmStyle, Settings.dlgoCfNmSize));
        lblPrice2.setFont(new Font(Settings.dlgoCfNmFont, Settings.dlgoCfNmStyle, Settings.dlgoCfNmSize));
            
        pnlBase.add(pnlReport);
        int result = JOptionPane.showConfirmDialog(screen, pnlBase, ("Payment Confirm!!"),
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);        
        return result;//OK : 0 Cancel:2     
    }

    /**
     * When Tiller button Click, this dialog popup
     * It display 1combobox for list of being logOn People
     * @param e 
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        //Fetching Data from db by array fomation
        List<String> strList = daoOdr.selectTillerCombo();
        String[] strArray = strList.toArray(new String[strList.size()]);
        String str = (String)JOptionPane.showInputDialog(screen, "Tiller List in Log on", "Take Chager of Till System", 
                JOptionPane.PLAIN_MESSAGE, null, strArray, strArray[0]);
        if(str == null) return;
        else btnTiller.setText(str);            
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {
        btnTiller.setBackground(Color.GREEN);
    }
    @Override
    public void mouseExited(MouseEvent e) {
        btnTiller.setBackground(new Color(180,209,90));    
    }    
}
