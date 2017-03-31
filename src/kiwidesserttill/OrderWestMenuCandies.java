package kiwidesserttill;

import kiwidesserttill.Model.Menu;
import kiwidesserttill.DAO.DAOMenu;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * All Product list of Candies
 * Fetch all data from Database
 * @author HJS
 * @version 2016. 8. 09.
 */
public class OrderWestMenuCandies extends JPanel implements MouseListener, KeyListener{    
      
    JPanel pnlCandy;
    JPanel pnlJelly;
    JPanel pnlAddOn;
    DAOMenu daoMenu = new DAOMenu();    
    OrderEastListTable ordLstTbl;
    JTextField totalPrice; //total payment textfield 
    
    //Menu Category divide code
    static String Candy   = "CND_C1";
    static String Jelly   = "CND_C2";
    static String AddOnCD = "CND_AO";   
    //3part candy, jelly, addon get count from each category   
    int cntCandy=daoMenu.selectCountProduct(Candy);
    int cntJelly=daoMenu.selectCountProduct(Jelly);
    int cntAddon=daoMenu.selectCountProduct(AddOnCD);
    JLabel  btnCndy[] = new JLabel[cntCandy];
    JLabel  btnJlly[] = new JLabel[cntJelly];   
    JLabel  btnAddn[] = new JLabel[cntAddon]; 
    
    String aOdrName=null;
    double aPrice=0.0;
    
    public OrderWestMenuCandies(OrderEastListTable tbl, JTextField tfdSum ){    
        ordLstTbl = tbl;     //reference orderListTable
        totalPrice = tfdSum; //erference totalPrice Textfiled
        loadPanel();
    } 
    
    public void loadPanel(){    
        //Button Image
        ImageIcon vIcon = new ImageIcon("img/btnCandy.png");     
                
        //pnl of Candy
        JLabel lbl1 = new JLabel("KIWI SWEET CANDIES");
        lbl1.setPreferredSize(new Dimension(590,30));
        lbl1.setFont(new Font(Settings.menu1LvNmFont, Settings.menu1LvNmStyle, Settings.menu1LvNmSize));  
        pnlCandy = new JPanel(); 
        pnlCandy.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlCandy.setPreferredSize(new Dimension(600,300));
        pnlCandy.setBackground(Settings.CandyPnlCtr);     
                
        //fetch the names of Candy list from db
        List<String> candyList = daoMenu.selectProductList(Candy);
        String[] candiesArray = candyList.toArray(new String[candyList.size()]);        
        for(int i=0 ; i<candiesArray.length ; i++){
            pnlCandy.add(btnCndy[i] = new JLabel(candiesArray[i].toString(), vIcon, JLabel.CENTER));
            //text on label image
            btnCndy[i].setVerticalTextPosition(0);
            btnCndy[i].setHorizontalTextPosition(0);
            btnCndy[i].setFont(new Font(Settings.itemFont, Settings.itemStyle, Settings.itemSize));
            btnCndy[i].setForeground(Settings.itemColorBrown);            
            btnCndy[i].addMouseListener(this);
        }             
        this.add(lbl1);      
        this.add(pnlCandy);
                
        //pnl of Jelly
        JLabel lbl2 = new JLabel("KIWI SWEET JELLIES");
        lbl2.setPreferredSize(new Dimension(590,30));
        lbl2.setFont(new Font(Settings.menu1LvNmFont, Settings.menu1LvNmStyle, Settings.menu1LvNmSize));
        pnlJelly = new JPanel(); 
        pnlJelly.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlJelly.setPreferredSize(new Dimension(600,195));
        pnlJelly.setBackground(Settings.CandyPnlCtr);
        
        //fetch the names of Jelly list from db
        List<String> jellyList = daoMenu.selectProductList(Jelly);
        String[] jellyArray = jellyList.toArray(new String[jellyList.size()]); 
        for(int i=0 ; i<jellyArray.length ; i++){
            pnlJelly.add(btnJlly[i] = new JLabel(jellyArray[i].toString(), vIcon, JLabel.CENTER));
            //text on label image
            btnJlly[i].setVerticalTextPosition(0);
            btnJlly[i].setHorizontalTextPosition(0);
            btnJlly[i].setFont(new Font(Settings.itemFont, Settings.itemStyle, Settings.itemSize));
            btnJlly[i].setForeground(Settings.itemColorBrown);            
            btnJlly[i].addMouseListener(this);
        }        
        this.add(lbl2);      
        this.add(pnlJelly);
        
        //pnl of Addon
        ImageIcon vIcon2 = new ImageIcon("img/addOn.png");        
        JLabel lbl4 = new JLabel("Add On");
        lbl4.setFont(new Font(Settings.menu1LvNmFont, Settings.menu1LvNmStyle, Settings.menu1LvNmSize));
        lbl4.setPreferredSize(new Dimension(590,30));
        pnlAddOn = new JPanel(); 
        pnlAddOn.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlAddOn.setPreferredSize(new Dimension(642,90));
        pnlAddOn.setBackground(Settings.CandyAddonPnlCtr);
        pnlAddOn.add(lbl4);   
        
        //fetch the names of Addon list from db
        List<String> addonList = daoMenu.selectProductList(AddOnCD);
        String[] addonArray = addonList.toArray(new String[addonList.size()]); 
        for(int i=0 ; i<addonArray.length ; i++){
            pnlAddOn.add(btnAddn[i] = new JLabel(addonArray[i].toString(), vIcon2, JLabel.CENTER));
            //text on label image
            btnAddn[i].setVerticalTextPosition(0);
            btnAddn[i].setHorizontalTextPosition(0);
            btnAddn[i].setFont(new Font(Settings.itemFont, Settings.itemStyle, Settings.itemSize));
            btnAddn[i].setForeground(Settings.itemColorYellow);            
            btnAddn[i].addMouseListener(this);
        }           
        this.add(pnlAddOn);
        
        /////////////////////////////////////////////////////////////////////////
        this.setBackground(Settings.CandyPnlCtr);
        this.setVisible(true);        
        /////////////////////////////////////////////////////////////////////////
    }
    
    /**
     * When mouse Click,its selected 1 row from db by kewword product name
     * @param e 
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        boolean loopCheck = true;
        aOdrName = null;
        aPrice =0.0;
        Menu amenu = new Menu();        
        if(loopCheck){                 
            for(int i=0 ; i<cntCandy ; i++){            
                if(e.getSource()==btnCndy[i]){
                    if(dialogDisplay(btnCndy[i].getText())==1){                        
                        ordLstTbl.tblAddRowOrder(aOdrName, aPrice);  
                    }
                    loopCheck = false;
                }
            }
        }
        
        if(loopCheck){     
            for(int i=0 ; i<cntJelly ; i++){            
                if(e.getSource()==btnJlly[i]){
                    if(dialogDisplay(btnJlly[i].getText())==1){                        
                        ordLstTbl.tblAddRowOrder(aOdrName, aPrice);  
                    }
                    loopCheck = false;
                }
            }
        }
        
        if(loopCheck){       
            if(Double.valueOf(totalPrice.getText())==0.0){ // in being no order add on              
                //chagne the Laguage
                Locale lng = new Locale("en", "US");
                JOptionPane.setDefaultLocale(lng);  
                JOptionPane.showMessageDialog(this, "No Order Item", "Warning", JOptionPane.ERROR_MESSAGE);
            }
            else{    
                for(int i=0 ; i<cntAddon ; i++){            
                    if(e.getSource()==btnAddn[i]){
                        amenu = daoMenu.selectAProduct(btnAddn[i].getText()); //fetch a row
                        ordLstTbl.tblAddRowOrderAddOn(amenu.getPdtName(), amenu.getPrice1());
                        loopCheck = false;
                    }
                }
            }
        }
        
        //Sum Value Display on order list table and total price textfeild
        System.out.println("totalPrice : "+ ordLstTbl.getTotalPayment());
        totalPrice.setText(String.valueOf(ordLstTbl.getTotalPayment()));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ImageIcon vIcon = new ImageIcon("img/btnCandyPressed.png");        
        boolean loopCheck = true;         
        if(loopCheck){     
            for(int i=0 ; i<cntCandy ; i++){            
                if(e.getSource()==btnCndy[i]){            
                    btnCndy[i].setIcon(vIcon);
                }        
            }
        }
        if(loopCheck){     
            for(int i=0 ; i<cntJelly ; i++){            
                if(e.getSource()==btnJlly[i]){            
                    btnJlly[i].setIcon(vIcon);
                }        
            }
        }
        if(loopCheck){    
            ImageIcon vIcon2 = new ImageIcon("img/addOnPressed.png");
            for(int i=0 ; i<cntAddon ; i++){            
                if(e.getSource()==btnAddn[i]){            
                    btnAddn[i].setIcon(vIcon2);
                }        
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ImageIcon vIcon = new ImageIcon("img/btnCandy.png");        
        boolean loopCheck = true;         
        if(loopCheck){     
            for(int i=0 ; i<cntCandy ; i++){            
                if(e.getSource()==btnCndy[i]){            
                    btnCndy[i].setIcon(vIcon);
                }        
            }
        }
        if(loopCheck){     
            for(int i=0 ; i<cntJelly; i++){            
                if(e.getSource()==btnJlly[i]){            
                    btnJlly[i].setIcon(vIcon);
                }        
            }
        }
        if(loopCheck){    
            ImageIcon vIcon2 = new ImageIcon("img/addOn.png");   
            for(int i=0 ; i<cntAddon ; i++){            
                if(e.getSource()==btnAddn[i]){            
                    btnAddn[i].setIcon(vIcon2);
                }        
            }
        }
    }
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
     
    /**
     * Take detail Orders throuth this user dialog
     * @param cdNm : name of the products
     * @return  confirm order  or cancel order
     * into the global add 1row on order table, assign value of tatal price
     */
    public int dialogDisplay(String cdNm){    
        //chagne the Laguage
        Locale lng = new Locale("en", "US");
        JOptionPane.setDefaultLocale(lng);        
        int returnVal=-1;
        
        //fetch name and price from database
        Menu amenu =new Menu();
        amenu = daoMenu.selectAProduct(cdNm);    
        double perPrice = amenu.getPrice1();
        String resultStr = cdNm + " 1 @ " + perPrice + "/gram.";    
        //pnl user dialog controls
        JTextField txtEnterNum = new JTextField(5);
        txtEnterNum.setDocument(new JTextfieldLimit(3)); //maximum 999 grams
        txtEnterNum.addAncestorListener( new RequestFocusListener() ); //Give a focuse
        txtEnterNum.addKeyListener(this);//only numeric
        txtEnterNum.setPreferredSize(new Dimension(100, 35));
        JPanel pnlBase = new JPanel(new GridLayout(0,1));
        pnlBase.setBorder(BorderFactory.createEmptyBorder(5 , 10 , 5 , 10)); 
        pnlBase.setBackground(Settings.dlgoNmBgColor);
        JLabel lblDirection1  = new JLabel(resultStr); 
        JLabel lblDirection2  = new JLabel("Enter the grams : "); 
        lblDirection1.setFont(new Font(Settings.dlgoNmFont, Settings.dlgoNmStyle, Settings.dlgoNmSize));
        lblDirection2.setFont(new Font(Settings.dlgoNmFont, Settings.dlgoNmStyle, Settings.dlgoTagSize));        
        pnlBase.add(lblDirection1);            
        pnlBase.add(lblDirection2);  
        JPanel pnlDiv = new JPanel(new FlowLayout()); 
        pnlDiv.setBackground(Settings.dlgoNmBgColor);
        JLabel lblGrms = new JLabel("Grams");
        lblGrms.setFont(new Font(Settings.dlgoNmFont, Settings.dlgoNmStyle, Settings.dlgoTagSize));  
        pnlDiv.add(txtEnterNum);
        pnlDiv.add(lblGrms);   
        pnlBase.add(pnlDiv);   
        
        int result = JOptionPane.showConfirmDialog(null, pnlBase, "How much grams would you like?",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        //OK button click - validation check
        if (result == JOptionPane.OK_OPTION) {            
            if(txtEnterNum.getText().toString().equals("")){
                JOptionPane.showMessageDialog(this, "No Entered number of grams..");
            }
            else{
                try{                              
                    int getDx = Integer.valueOf(txtEnterNum.getText().toString());
                    if (getDx==0){
                    }else if (getDx<10){
                        JOptionPane.showMessageDialog(this, "The Number is too SMALL.. Please enter number more then 10 grams!");  
                    }else if (getDx>999){ //Maximum 1kg
                        JOptionPane.showMessageDialog(this, "The Number is too BIG.. Please enter number less then 2000(2kg)grams!");  
                    }else{
                        aOdrName = cdNm +" " + getDx + " @  " + perPrice + "/gram.";
                        aPrice   = perPrice*getDx;
                        returnVal = 1;
                    }
                } catch(Exception e){e.printStackTrace(); }
            }            
        }
        return returnVal;
    }

    /**
     * only number from user
     * @param e 
     */
    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();  
        if (!Character.isDigit(c)) {
            e.consume();
            return;
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}    
}
