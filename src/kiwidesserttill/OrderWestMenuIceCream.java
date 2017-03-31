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
 * All Product list of Ice Cream
 * @author HJS
 * @version 2016. 8. 25.
 */
public class OrderWestMenuIceCream extends JPanel implements MouseListener{
    
    JPanel pnlChocIce;
    JPanel pnlVanilla;
    JPanel pnlFruits;
    JPanel pnlAddOn;
    DAOMenu daoMenu = new DAOMenu();   
    OrderEastListTable ordLstTbl;
    JTextField totalPrice; //total payment textfield
    
    //Menu Category divide code
    static String ChocIce    = "ICE_I1";
    static String VanillaIce = "ICE_I2";    
    static String FuitsIce   = "ICE_I3";  
    static String AddOnIC    = "ICE_AO";  
    //ChocIce, VanillaIce, FuitsIce and Addon
    int cntChocIc =daoMenu.selectCountProduct(ChocIce);
    int cntVanila =daoMenu.selectCountProduct(VanillaIce);
    int cntFruits =daoMenu.selectCountProduct(FuitsIce);
    int cntAddon  =daoMenu.selectCountProduct(AddOnIC);    
    JLabel  btnChoc[] = new JLabel[cntChocIc];
    JLabel  btnVani[] = new JLabel[cntVanila];
    JLabel  btnFrts[] = new JLabel[cntFruits];
    JLabel  btnAddn[] = new JLabel[cntAddon];
    
    String aOdrName=null;
    double aPrice=0.0;
    
    public OrderWestMenuIceCream(OrderEastListTable tbl, JTextField tfdSum ){  
        ordLstTbl = tbl;     //reference orderListTable
        totalPrice = tfdSum; //erference totalPrice Textfiled
        loadPanel();
    } 
    
    public void loadPanel(){        
        //Button Image
        ImageIcon vIcon = new ImageIcon("img/btnIceCream.png");
        
        //pnl of ChocIce
        JLabel lbl1 = new JLabel("CHOCOLATE ICE CREAM");
        lbl1.setPreferredSize(new Dimension(590,30));
        lbl1.setFont(new Font(Settings.menu1LvNmFont, Settings.menu1LvNmStyle, Settings.menu1LvNmSize));  
        pnlChocIce = new JPanel(); 
        pnlChocIce.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlChocIce.setPreferredSize(new Dimension(600,120));
        pnlChocIce.setBackground(Settings.IceCreamPnlClr);
        
        //fetch the names of ChocIce list from db
        List<String> chocList = daoMenu.selectProductList(ChocIce);
        String[] chocArray = chocList.toArray(new String[chocList.size()]);   
        for(int i=0 ; i<cntChocIc ; i++){
            pnlChocIce.add(btnChoc[i] = new JLabel(chocArray[i], vIcon, JLabel.CENTER));
            //text on label image
            btnChoc[i].setVerticalTextPosition(0);
            btnChoc[i].setHorizontalTextPosition(0);
            btnChoc[i].setFont(new Font(Settings.itemFont, Settings.itemStyle, Settings.itemSize));
            btnChoc[i].setForeground(Settings.itemColorYellow);            
            btnChoc[i].addMouseListener(this);
        }             
        this.add(lbl1);      
        this.add(pnlChocIce);
        
        //pnl of vianilla ice Cream
        JLabel lbl2 = new JLabel("VILIRA ICE CREAM");
        lbl2.setPreferredSize(new Dimension(590,30));
        lbl2.setFont(new Font(Settings.menu1LvNmFont, Settings.menu1LvNmStyle, Settings.menu1LvNmSize));
        pnlVanilla = new JPanel(); 
        pnlVanilla.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlVanilla.setPreferredSize(new Dimension(600,120));
        pnlVanilla.setBackground(Settings.IceCreamPnlClr);
        
        //fetch the names of vianilla list from db
        List<String> vanilaList = daoMenu.selectProductList(VanillaIce);
        String[] vanilaArray = vanilaList.toArray(new String[vanilaList.size()]); 
        for(int i=0 ; i<cntVanila ; i++){
            pnlVanilla.add(btnVani[i] = new JLabel(vanilaArray[i], vIcon, JLabel.CENTER));
            //text on label image
            btnVani[i].setVerticalTextPosition(0);
            btnVani[i].setHorizontalTextPosition(0);
            btnVani[i].setFont(new Font(Settings.itemFont, Settings.itemStyle, Settings.itemSize));
            btnVani[i].setForeground(Settings.itemColorYellow);            
            btnVani[i].addMouseListener(this);
        }        
        this.add(lbl2);      
        this.add(pnlVanilla);
        
        //pnl of fruit ice Cream
        JLabel lbl3 = new JLabel("FUITES ICE CREAM");
        lbl3.setPreferredSize(new Dimension(590,30));
        lbl3.setFont(new Font(Settings.menu1LvNmFont, Settings.menu1LvNmStyle, Settings.menu1LvNmSize));
        pnlFruits = new JPanel(); 
        pnlFruits.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlFruits.setPreferredSize(new Dimension(600,120));
        pnlFruits.setBackground(Settings.IceCreamPnlClr);
        
        //fetch the names of fruit ice Cream list from db        
        List<String> fruitList = daoMenu.selectProductList(VanillaIce);
        String[] fruitsArray = fruitList.toArray(new String[fruitList.size()]); 
        for(int i=0 ; i<cntFruits ; i++){
            pnlFruits.add(btnFrts[i] = new JLabel(fruitsArray[i], vIcon, JLabel.CENTER));
            //text on label image
            btnFrts[i].setVerticalTextPosition(0);
            btnFrts[i].setHorizontalTextPosition(0);
            btnFrts[i].setFont(new Font(Settings.itemFont, Settings.itemStyle, Settings.itemSize));
            btnFrts[i].setForeground(Settings.itemColorYellow);            
            btnFrts[i].addMouseListener(this);
        }        
        this.add(lbl3);      
        this.add(pnlFruits);
        
        //pnl of addon
        ImageIcon vIcon2 = new ImageIcon("img/addOn.png");        
        JLabel lbl4 = new JLabel("Add On");
        lbl4.setFont(new Font(Settings.menu1LvNmFont, Settings.menu1LvNmStyle, Settings.menu1LvNmSize));
        lbl4.setPreferredSize(new Dimension(590,30));
        pnlAddOn = new JPanel(); 
        pnlAddOn.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlAddOn.setPreferredSize(new Dimension(642,192));
        pnlAddOn.setBackground(Settings.iceCreamAddonPnlClr);
        pnlAddOn.add(lbl4);   
        
        //fetch the names of addon list from db        
        List<String> addonList = daoMenu.selectProductList(AddOnIC);
        String[] addonArray = addonList.toArray(new String[addonList.size()]); 
        for(int i=0 ; i<cntAddon ; i++){
            pnlAddOn.add(btnAddn[i] = new JLabel(addonArray[i], vIcon2, JLabel.CENTER));
            //text on label image
            btnAddn[i].setVerticalTextPosition(0);
            btnAddn[i].setHorizontalTextPosition(0);
            btnAddn[i].setFont(new Font(Settings.itemFont, Settings.itemStyle, Settings.itemSize));
            btnAddn[i].setForeground(Settings.itemColorYellow);            
            btnAddn[i].addMouseListener(this);
        }           
        this.add(pnlAddOn);
        
        this.setBackground(Settings.IceCreamPnlClr);
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
            for(int i=0 ; i<cntChocIc ; i++){            
                if(e.getSource()==btnChoc[i]){
                    if(dialogDisplay(btnChoc[i].getText())==1){                        
                        ordLstTbl.tblAddRowOrder(aOdrName, aPrice);  
                    }
                    loopCheck = false;
                }
            }
        }        
        if(loopCheck){     
            for(int i=0 ; i<cntVanila ; i++){            
                if(e.getSource()==btnVani[i]){
                    if(dialogDisplay(btnVani[i].getText())==1){                        
                        ordLstTbl.tblAddRowOrder(aOdrName, aPrice);  
                    }
                    loopCheck = false;
                }
            }
        }        
        if(loopCheck){     
            for(int i=0 ; i<cntFruits ; i++){            
                if(e.getSource()==btnFrts[i]){
                    if(dialogDisplay(btnFrts[i].getText())==1){                        
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
        ImageIcon vIcon = new ImageIcon("img/btnIceCreamPressed.png");
        boolean loopCheck = true; 
        if(loopCheck){     
            for(int i=0 ; i<cntChocIc ; i++){            
                if(e.getSource()==btnChoc[i]){            
                    btnChoc[i].setIcon(vIcon);
                }        
            }
        }
        if(loopCheck){     
            for(int i=0 ; i<cntVanila ; i++){            
                if(e.getSource()==btnVani[i]){            
                    btnVani[i].setIcon(vIcon);
                }        
            }
        }        
        if(loopCheck){     
            for(int i=0 ; i<cntFruits ; i++){            
                if(e.getSource()==btnFrts[i]){            
                    btnFrts[i].setIcon(vIcon);
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
        ImageIcon vIcon = new ImageIcon("img/btnIceCream.png");        
        boolean loopCheck = true;         
        if(loopCheck){     
            for(int i=0 ; i<cntChocIc ; i++){            
                if(e.getSource()==btnChoc[i]){            
                    btnChoc[i].setIcon(vIcon);
                }        
            }
        }        
        if(loopCheck){    
            for(int i=0 ; i<cntVanila ; i++){            
                if(e.getSource()==btnVani[i]){            
                    btnVani[i].setIcon(vIcon);
                }        
            }
        }        
        if(loopCheck){    
            for(int i=0 ; i<cntFruits ; i++){            
                if(e.getSource()==btnFrts[i]){            
                    btnFrts[i].setIcon(vIcon);
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
     * @param icNm : name of the products
     * @return  confirm order  or cancel order
     * into the global add 1row on order table, assign value of tatal price
     */
    public int dialogDisplay(String icNm){
        Menu amenu =new Menu();
        amenu = daoMenu.selectAProduct(icNm);           
        String resultStr = icNm + " 1 Scoop : " + amenu.getPrice1() + "\n"
                         + icNm + " 2 Scoops: " + amenu.getPrice2() + "\n"
                         + icNm + " 3 Scoops: " + amenu.getPrice3();
        double perPrice = amenu.getPrice1();
        Object[] options = {"1 Scoop", "2 Scoops", "3 Scoops"};      
         int selectedNum = JOptionPane.showOptionDialog(null, resultStr, "How Many Scoops would you like?",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(), options, options[0]);
        
        if (selectedNum == JOptionPane.CLOSED_OPTION) {
            return -1;
        } else {
            aOdrName = icNm +" " + (selectedNum + 1) + " @  " + perPrice + "/Scoop.";
            aPrice   = perPrice*(selectedNum + 1);
            return 1;
        } 
    }
}
