package kiwidesserttill;

import kiwidesserttill.Model.Menu;
import kiwidesserttill.DAO.DAOMenu;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * All Product list of Drinks
 * @author HJS
 * @version 2016. 8. 09.
 */
public class OrderWestMenuDrinks extends JPanel implements MouseListener{
    
    JPanel pnlCoffee;
    JPanel pnlColdDrinks;
    JPanel pnlSoftDrinks;
    JPanel pnlAddOn;    
    DAOMenu daoMenu  = new DAOMenu();      
    OrderEastListTable ordLstTbl;
    JTextField totalPrice; //total payment textfield
    
    //Menu Category divide code
    static String Coffee     = "DRK_D1";
    static String ColdDrink  = "DRK_D2";
    static String SoftDrink  = "DRK_D3";
    static String AddOnDR    = "DRK_AO";
    //Coffee, ColdDrink, SoftDrink and Addon
    int cntCoffee     = daoMenu.selectCountProduct(Coffee);
    int cntColdDrinks = daoMenu.selectCountProduct(ColdDrink);
    int cntSoftDrinks = daoMenu.selectCountProduct(SoftDrink);
    int cntAddon      = daoMenu.selectCountProduct(AddOnDR);
    JLabel  btnCffe[] = new JLabel[cntCoffee];
    JLabel  btnCldD[] = new JLabel[cntColdDrinks];
    JLabel  btnSftD[] = new JLabel[cntSoftDrinks];
    JLabel  btnAddn[] = new JLabel[cntAddon];
    
    String aOdrName=null;
    double aPrice=0.0;
    
    public OrderWestMenuDrinks(OrderEastListTable tbl, JTextField tfdSum ){
        ordLstTbl = tbl;     //reference orderListTable
        totalPrice = tfdSum; //erference totalPrice Textfiled
        loadPanel();
    } 
    
    public void loadPanel(){        
        //Button Image
        ImageIcon vIcon = new ImageIcon("img/btnCoffee.png");
        
        //pnl of coffees
        JLabel lbl1 = new JLabel("COFFEES");
        lbl1.setPreferredSize(new Dimension(590,30));
        lbl1.setFont(new Font(Settings.menu1LvNmFont, Settings.menu1LvNmStyle, Settings.menu1LvNmSize));  
        pnlCoffee = new JPanel(); 
        pnlCoffee.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlCoffee.setPreferredSize(new Dimension(600,120));
        pnlCoffee.setBackground(Settings.DrinkPnlCtr);
        
        //fetch the names of coffee list from db
        List<String> coffeeList = daoMenu.selectProductList(Coffee);
        String[] coffeeArray = coffeeList.toArray(new String[coffeeList.size()]);        
        for(int i=0 ; i<coffeeArray.length ; i++){
            pnlCoffee.add(btnCffe[i] = new JLabel(coffeeArray[i].toString(), vIcon, JLabel.CENTER));
            //text on label image
            btnCffe[i].setVerticalTextPosition(0);
            btnCffe[i].setHorizontalTextPosition(0);
            btnCffe[i].setFont(new Font(Settings.itemFont, Settings.itemStyle, Settings.itemSize));
            btnCffe[i].setForeground(Settings.itemColorWhite);            
            btnCffe[i].addMouseListener(this);
        }             
        this.add(lbl1);      
        this.add(pnlCoffee);
        
        //pnl of Cold Drinks
        JLabel lbl2 = new JLabel("COLD DRINKS");
        lbl2.setPreferredSize(new Dimension(590,30));
        lbl2.setFont(new Font(Settings.menu1LvNmFont, Settings.menu1LvNmStyle, Settings.menu1LvNmSize));
        pnlColdDrinks = new JPanel(); 
        pnlColdDrinks.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlColdDrinks.setPreferredSize(new Dimension(600,120));
        pnlColdDrinks.setBackground(Settings.DrinkPnlCtr);

        //fetch the names of cloddrinks list from db
        List<String> coldDrinkList = daoMenu.selectProductList(ColdDrink);
        String[] coldDrinkArray = coldDrinkList.toArray(new String[coldDrinkList.size()]);  
        for(int i=0 ; i<coldDrinkArray.length ; i++){
            pnlColdDrinks.add(btnCldD[i] = new JLabel(coldDrinkArray[i].toString(), vIcon, JLabel.CENTER));
            //text on label image
            btnCldD[i].setVerticalTextPosition(0);
            btnCldD[i].setHorizontalTextPosition(0);
            btnCldD[i].setFont(new Font(Settings.itemFont, Settings.itemStyle, Settings.itemSize));
            btnCldD[i].setForeground(Settings.itemColorWhite);            
            btnCldD[i].addMouseListener(this);
        }        
        this.add(lbl2);      
        this.add(pnlColdDrinks);
        
        //pnl of SoftDrinks
        JLabel lbl3 = new JLabel("SOFT DRINKS");
        lbl3.setPreferredSize(new Dimension(590,30));
        lbl3.setFont(new Font(Settings.menu1LvNmFont, Settings.menu1LvNmStyle, Settings.menu1LvNmSize));
        pnlSoftDrinks = new JPanel(); 
        pnlSoftDrinks.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlSoftDrinks.setPreferredSize(new Dimension(600,120));
        pnlSoftDrinks.setBackground(Settings.DrinkPnlCtr);
        
        //fetch the names of softDrinks list from db
        List<String> softDrinkList = daoMenu.selectProductList(SoftDrink);
        String[] softDrinkArray = softDrinkList.toArray(new String[softDrinkList.size()]);  
        for(int i=0 ; i<softDrinkArray.length ; i++){
            pnlSoftDrinks.add(btnSftD[i] = new JLabel(softDrinkArray[i].toString(), vIcon, JLabel.CENTER));
            //text on label image
            btnSftD[i].setVerticalTextPosition(0);
            btnSftD[i].setHorizontalTextPosition(0);
            btnSftD[i].setFont(new Font(Settings.itemFont, Settings.itemStyle, Settings.itemSize));
            btnSftD[i].setForeground(Settings.itemColorWhite);            
            btnSftD[i].addMouseListener(this);
        }        
        this.add(lbl3);      
        this.add(pnlSoftDrinks);
                
        //pnl of addon
        ImageIcon vIcon2 = new ImageIcon("img/addOn.png");        
        JLabel lbl4 = new JLabel("Add On");
        lbl4.setFont(new Font(Settings.menu1LvNmFont, Settings.menu1LvNmStyle, Settings.menu1LvNmSize));
        lbl4.setPreferredSize(new Dimension(590,30));
        pnlAddOn = new JPanel(); 
        pnlAddOn.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlAddOn.setPreferredSize(new Dimension(642,192));
        pnlAddOn.setBackground(Settings.DrinkAddonPnlCtr);
        pnlAddOn.add(lbl4);   
       
        //fetch the names of addon list from db     
        List<String> addonList = daoMenu.selectProductList(AddOnDR);
        String[] addonArray = addonList.toArray(new String[addonList.size()]);  
        for(int i=0 ; i<addonArray.length ; i++){
            pnlAddOn.add(btnAddn[i] = new JLabel(addonArray[i].toString(), vIcon2, JLabel.CENTER));
            //text on label image
            btnAddn[i].setVerticalTextPosition(0);
            btnAddn[i].setHorizontalTextPosition(0);
            btnAddn[i].setFont(new Font(Settings.itemFont, Settings.itemStyle, Settings.itemSize));
            btnAddn[i].setForeground(Settings.itemColorWhite);            
            btnAddn[i].addMouseListener(this);
        }           
        this.add(pnlAddOn);
        
        this.setBackground(Settings.DrinkPnlCtr);
        this.setVisible(true);  
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
            for(int i=0 ; i<cntCoffee ; i++){            
                if(e.getSource()==btnCffe[i]){
                    if(dialogDisplay(btnCffe[i].getText())==1){                        
                        ordLstTbl.tblAddRowOrder(aOdrName, aPrice);  
                    }
                    loopCheck = false;
                }
            }
        }
        if(loopCheck){     
            for(int i=0 ; i<cntColdDrinks ; i++){            
                if(e.getSource()==btnCldD[i]){
                    if(dialogDisplay(btnCldD[i].getText())==1){                        
                        ordLstTbl.tblAddRowOrder(aOdrName, aPrice);  
                    }
                    loopCheck = false;
                }
            }
        }
        if(loopCheck){     
            for(int i=0 ; i<cntSoftDrinks ; i++){            
                if(e.getSource()==btnSftD[i]){
                    amenu = daoMenu.selectAProduct(btnSftD[i].getText()); //fetch a row
                    ordLstTbl.tblAddRowOrder(btnSftD[i].getText(), amenu.getPrice1());
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
                        ordLstTbl.tblAddRowOrderAddOn(btnAddn[i].getText(), amenu.getPrice1());
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
        ImageIcon vIcon = new ImageIcon("img/btnCoffeePressed.png");        
        boolean loopCheck = true;         
        if(loopCheck){     
            for(int i=0 ; i<cntCoffee ; i++){            
                if(e.getSource()==btnCffe[i]){            
                    btnCffe[i].setIcon(vIcon);
                }        
            }
        }
        if(loopCheck){     
            for(int i=0 ; i<cntColdDrinks ; i++){            
                if(e.getSource()==btnCldD[i]){            
                    btnCldD[i].setIcon(vIcon);
                }        
            }
        }        
        if(loopCheck){     
            for(int i=0 ; i<cntSoftDrinks ; i++){            
                if(e.getSource()==btnSftD[i]){            
                    btnSftD[i].setIcon(vIcon);
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
        ImageIcon vIcon = new ImageIcon("img/btnCoffee.png");        
        boolean loopCheck = true;         
        if(loopCheck){     
            for(int i=0 ; i<cntCoffee ; i++){            
                if(e.getSource()==btnCffe[i]){            
                    btnCffe[i].setIcon(vIcon);
                }        
            }
        }        
        if(loopCheck){    
            for(int i=0 ; i<cntColdDrinks ; i++){            
                if(e.getSource()==btnCldD[i]){            
                    btnCldD[i].setIcon(vIcon);
                }        
            }
        }        
        if(loopCheck){    
            for(int i=0 ; i<cntSoftDrinks ; i++){            
                if(e.getSource()==btnSftD[i]){            
                    btnSftD[i].setIcon(vIcon);
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
     * Take detail Orders throuth this dialog
     * @param drNm : name of the products
     * @return  confirm order  or cancel order
     * into the global add 1row on order table, assign value of tatal price
     */
    public int dialogDisplay(String drNm){
        Menu amenu =new Menu();
        amenu = daoMenu.selectAProduct(drNm);           
        String resultStr = drNm + " Small  : " + amenu.getPrice1() + "\n"
                         + drNm + " Regular: " + amenu.getPrice2() + "\n"
                         + drNm + " Large  : " + amenu.getPrice3();
        double perPrice = amenu.getPrice1();        
        Object[] options = {"Small", "Regular", "Large"};      
         int selectedNum = JOptionPane.showOptionDialog(null, resultStr, "Which size would you like?",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(), options, options[0]);
        
        if (selectedNum == JOptionPane.CLOSED_OPTION) {
            return -1;
        } else {
            if(selectedNum==0){//small - 0.60cents                
                aOdrName = drNm +" - Small";
                aPrice   = amenu.getPrice1();
            }
            else if(selectedNum==1){//Regular                             
                aOdrName = drNm +" - Regular";
                aPrice   = amenu.getPrice2();
            }
            else{//Large + 0.90cents                                          
                aOdrName = drNm +" - Large";
                aPrice   = amenu.getPrice3();
            }
            return 1;
        } 
    }
}
