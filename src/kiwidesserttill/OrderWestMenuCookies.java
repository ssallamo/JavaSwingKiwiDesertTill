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
 * @version 2016. 8. 10.
 */
public class OrderWestMenuCookies extends JPanel implements MouseListener, KeyListener{
    
    JPanel pnlCookies;
    JPanel pnlBakery;
    JPanel pnlAddOn;        
    DAOMenu daoMenu  = new DAOMenu();      
    OrderEastListTable ordLstTbl;
    JTextField totalPrice; //total payment textfield
    
    //Menu Category divide code
    static String Cookies    = "CKY_C1";
    static String Bakery     = "CKY_C2";
    static String AddOnCK    = "CKY_AO";          
    //3part Cookies, Bakery and addon get count from each category   
    int cntCooky =daoMenu.selectCountProduct(Cookies);
    int cntBakery=daoMenu.selectCountProduct(Bakery);
    int cntAddon =daoMenu.selectCountProduct(AddOnCK);
    JLabel  btnCks[] = new JLabel[cntCooky];
    JLabel  btnBkr[] = new JLabel[cntBakery];   
    JLabel  btnAddn[] = new JLabel[cntAddon]; 
    
    String aOdrName=null;
    double aPrice=0.0;
    
    public OrderWestMenuCookies(OrderEastListTable tbl, JTextField tfdSum ){        
        ordLstTbl = tbl;     //reference orderListTable
        totalPrice = tfdSum; //erference totalPrice Textfiled
        loadPanel();
    } 
    
    public void loadPanel(){    
        //Button Image
        ImageIcon vIcon = new ImageIcon("img/btnCookie.png");     
                
        //pnlCookies
        JLabel lbl1 = new JLabel("KIWI SWEEET COOKIES");
        lbl1.setPreferredSize(new Dimension(590,30));
        lbl1.setFont(new Font(Settings.menu1LvNmFont, Settings.menu1LvNmStyle, Settings.menu1LvNmSize));  
        pnlCookies = new JPanel(); 
        pnlCookies.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlCookies.setPreferredSize(new Dimension(600,250));
        pnlCookies.setBackground(Settings.CookiesPnlCtr);
        
        //fetch the names of Cookies list from db
        List<String> cookyList = daoMenu.selectProductList(Cookies);
        String[] cookiesArray = cookyList.toArray(new String[cookyList.size()]);   
        for(int i=0 ; i<cookiesArray.length ; i++){
            pnlCookies.add(btnCks[i] = new JLabel(cookiesArray[i].toString(), vIcon, JLabel.CENTER));
            //text on label image
            btnCks[i].setVerticalTextPosition(0);
            btnCks[i].setHorizontalTextPosition(0);
            btnCks[i].setFont(new Font(Settings.itemFont, Settings.itemStyle, Settings.itemSize));
            btnCks[i].setForeground(Settings.itemColorBrown);            
            btnCks[i].addMouseListener(this);
        }             
        this.add(lbl1);      
        this.add(pnlCookies);
        
         //pnl of Bakery  
        JLabel lbl2 = new JLabel("KIWI SWEET BAKERY");
        lbl2.setPreferredSize(new Dimension(590,30));
        lbl2.setFont(new Font(Settings.menu1LvNmFont, Settings.menu1LvNmStyle, Settings.menu1LvNmSize));
        pnlBakery = new JPanel(); 
        pnlBakery.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlBakery.setPreferredSize(new Dimension(600,245));
        pnlBakery.setBackground(Settings.CookiesPnlCtr);
        
        //fetch the names of Bakery list from db
        List<String> bakeryList = daoMenu.selectProductList(Bakery);
        String[] bakeryArray = bakeryList.toArray(new String[bakeryList.size()]); 
        for(int i=0 ; i<bakeryArray.length ; i++){
            pnlBakery.add(btnBkr[i] = new JLabel(bakeryArray[i].toString(), vIcon, JLabel.CENTER));
            //text on label image
            btnBkr[i].setVerticalTextPosition(0);
            btnBkr[i].setHorizontalTextPosition(0);
            btnBkr[i].setFont(new Font(Settings.itemFont, Settings.itemStyle, Settings.itemSize));
            btnBkr[i].setForeground(Settings.itemColorBrown);            
            btnBkr[i].addMouseListener(this);
        }        
        this.add(lbl2);      
        this.add(pnlBakery);
        
        //pnl Of AddOn
        ImageIcon vIcon2 = new ImageIcon("img/addOn.png");        
        JLabel lbl4 = new JLabel("Add On");
        lbl4.setFont(new Font(Settings.menu1LvNmFont, Settings.menu1LvNmStyle, Settings.menu1LvNmSize));
        lbl4.setPreferredSize(new Dimension(590,30));
        pnlAddOn = new JPanel(); 
        pnlAddOn.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlAddOn.setPreferredSize(new Dimension(642,90));
        pnlAddOn.setBackground(Settings.CookiesAddonPnlCtr);
        pnlAddOn.add(lbl4);   
        
        //fetch the names of Addon list from db
        List<String> addonList = daoMenu.selectProductList(AddOnCK);
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
        this.setBackground(Settings.CookiesPnlCtr);
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
            for(int i=0 ; i<cntCooky ; i++){            
                if(e.getSource()==btnCks[i]){
                    if(dialogDisplay(btnCks[i].getText())==1){                        
                        ordLstTbl.tblAddRowOrder(aOdrName, aPrice);  
                    }
                    loopCheck = false;
                }
            }
        }
        
        if(loopCheck){     
            for(int i=0 ; i<cntBakery ; i++){            
                if(e.getSource()==btnBkr[i]){
                    amenu = daoMenu.selectAProduct(btnBkr[i].getText()); //fetch a row
                    ordLstTbl.tblAddRowOrder(amenu.getPdtName(), amenu.getPrice1());                    
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
        ImageIcon vIcon = new ImageIcon("img/btnCookiePressed.png");        
        boolean loopCheck = true;         
        if(loopCheck){     
            for(int i=0 ; i<cntCooky ; i++){            
                if(e.getSource()==btnCks[i]){            
                    btnCks[i].setIcon(vIcon);
                }        
            }
        }
        if(loopCheck){     
            for(int i=0 ; i<cntBakery ; i++){            
                if(e.getSource()==btnBkr[i]){            
                    btnBkr[i].setIcon(vIcon);
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
        ImageIcon vIcon = new ImageIcon("img/btnCookie.png");        
        boolean loopCheck = true;         
        if(loopCheck){     
            for(int i=0 ; i<cntCooky ; i++){            
                if(e.getSource()==btnCks[i]){            
                    btnCks[i].setIcon(vIcon);
                }        
            }
        }
        if(loopCheck){     
            for(int i=0 ; i<cntBakery ; i++){            
                if(e.getSource()==btnBkr[i]){            
                    btnBkr[i].setIcon(vIcon);
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
     * @param ckNm : name of the products
     * @return  confirm order  or cancel order
     * into the global add 1row on order table, assign value of tatal price
     */
    public int dialogDisplay(String ckNm){ 
        //chagne the Laguage
        Locale lng = new Locale("en", "US");
        JOptionPane.setDefaultLocale(lng);        
        int returnVal=-1;
        
        //fetch name and price from database
        Menu amenu =new Menu();
        amenu = daoMenu.selectAProduct(ckNm);    
        double perPrice = amenu.getPrice1();        
        String resultStr = ckNm + " 1 @ " + perPrice + "/dz."; 
        //pnl user dialog controls       
        JTextField txtEnterNum = new JTextField(5);
        txtEnterNum.setDocument(new JTextfieldLimit(2)); //maximum 99 grams
        txtEnterNum.addAncestorListener( new RequestFocusListener() ); //Give a focuse
        txtEnterNum.addKeyListener(this);
        txtEnterNum.setPreferredSize(new Dimension(100, 35));
        JPanel pnlBase = new JPanel(new GridLayout(0,1));
        pnlBase.setBorder(BorderFactory.createEmptyBorder(5 , 10 , 5 , 10)); 
        pnlBase.setBackground(Settings.dlgoNmBgColor);
        JLabel lblDirection1  = new JLabel(resultStr); 
        JLabel lblDirection2  = new JLabel("Enter the dozens : "); 
        lblDirection1.setFont(new Font(Settings.dlgoNmFont, Settings.dlgoNmStyle, Settings.dlgoNmSize));
        lblDirection2.setFont(new Font(Settings.dlgoNmFont, Settings.dlgoNmStyle, Settings.dlgoTagSize));        
        pnlBase.add(lblDirection1);            
        pnlBase.add(lblDirection2);
        JPanel pnlDiv = new JPanel(new FlowLayout()); 
        pnlDiv.setBackground(Settings.dlgoNmBgColor);
        JLabel lblDzns = new JLabel("Dozens");
        lblDzns.setFont(new Font(Settings.dlgoNmFont, Settings.dlgoNmStyle, Settings.dlgoTagSize));  
        pnlDiv.add(txtEnterNum);
        pnlDiv.add(lblDzns);   
        pnlBase.add(pnlDiv);   
        
        int result = JOptionPane.showConfirmDialog(null, pnlBase, "How Many dozens would you like?",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        //OK button click - validation check
        if (result == JOptionPane.OK_OPTION) {
            if(txtEnterNum.getText().toString().equals("")){
                JOptionPane.showMessageDialog(this, "No Entered number of dozens..");
            }
            else{                
                try{                
                    int getDx = Integer.valueOf(txtEnterNum.getText().toString());
                    if (getDx==0){
                    }else if (getDx>10){
                        JOptionPane.showMessageDialog(this, "The Number is too big.. pleas enter dozen less 10");  
                    }else{
                        aOdrName = ckNm +" " + getDx + " @  " + perPrice + "/dz.";
                        aPrice   = perPrice*getDx;
                        returnVal = 1;
                    }
                } catch(Exception e){e.printStackTrace();}
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
