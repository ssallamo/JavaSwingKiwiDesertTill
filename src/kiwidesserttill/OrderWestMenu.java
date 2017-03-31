package kiwidesserttill;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class displays based Pannel for West Order Category panels
 * @author HJS
 * @version 2016. 8. 09.
 */
public class OrderWestMenu extends JPanel implements ActionListener{
    
    JPanel pnlBtns;  
    JPanel pnlPnls;    
    OrderWestMenuIceCream pnlIceCream;
    OrderWestMenuCookies  pnlCookies;
    OrderWestMenuCandies  pnlCandies;
    OrderWestMenuDrinks   pnlDrinks;
    JButton btnIcec; 
    JButton btnCuki; 
    JButton btnCndy; 
    JButton btnDrks; 
    JButton btnBlnk1;
    JButton btnBlnk2;
    
    public OrderWestMenu(OrderEastListTable tbl, JTextField tfdSum ){  
        
        this.setBackground(Color.WHITE);
        
        JLabel lblImg = new JLabel();
        ImageIcon kiwiLogo = new ImageIcon("img/shopLogo4.png");
        lblImg.setIcon(kiwiLogo);
        
        btnIcec = new JButton(new ImageIcon("img/icecream.png"));
        btnCuki = new JButton(new ImageIcon("img/cookies.png"));
        btnCndy = new JButton(new ImageIcon("img/candy.png"));
        btnDrks = new JButton(new ImageIcon("img/coffee.png"));
        btnBlnk1 = new JButton("");//just blank
        btnBlnk2 = new JButton("");//just blank
        
        //remove borders and set background button colors, sizes, locations
        btnIcec.setMargin(new Insets(0, -2, 0, -2));
        btnCuki.setMargin(new Insets(0, -2, 0, -2));
        btnCndy.setMargin(new Insets(0, -2, 0, -2));
        btnDrks.setMargin(new Insets(0, -2, 0, -2));               
        btnIcec.setBackground(Color.WHITE);
        btnCuki.setBackground(Color.WHITE);
        btnCndy.setBackground(Color.WHITE);
        btnDrks.setBackground(Color.WHITE);
        btnBlnk1.setBackground(Color.WHITE);
        btnBlnk2.setBackground(Color.WHITE);
        btnIcec.setBorderPainted(false);
        btnCuki.setBorderPainted(false);
        btnCndy.setBorderPainted(false);
        btnDrks.setBorderPainted(false);        
        btnBlnk1.setBorderPainted(false);//just blank
        btnBlnk2.setBorderPainted(false);//just blank
                       
        pnlBtns = new JPanel(new GridLayout(6,1));      
        pnlBtns.add(btnIcec);
        pnlBtns.add(btnCuki);
        pnlBtns.add(btnCndy);
        pnlBtns.add(btnDrks);
        pnlBtns.add(btnBlnk1);//just blank
        pnlBtns.add(btnBlnk2);//just blank        
                
        pnlIceCream = new OrderWestMenuIceCream(tbl, tfdSum);
	pnlCookies  = new OrderWestMenuCookies(tbl, tfdSum);
	pnlCandies  = new OrderWestMenuCandies(tbl, tfdSum);
	pnlDrinks   = new OrderWestMenuDrinks(tbl, tfdSum); 
        
        pnlPnls = new JPanel(new GridLayout());
        pnlPnls.add(pnlIceCream);
        
        this.setLayout(new BorderLayout());        
        this.add(lblImg, BorderLayout.NORTH);
        this.add(pnlBtns, BorderLayout.WEST);
        this.add(pnlPnls, BorderLayout.CENTER);        
        
        btnIcec.addActionListener(this);
        btnCuki.addActionListener(this);
        btnCndy.addActionListener(this);
        btnDrks.addActionListener(this);                
    }

    @Override
    public void actionPerformed(ActionEvent e) {           
        if(e.getSource().equals(btnIcec)){            
            pnlPnls.remove(pnlCookies);
            pnlPnls.remove(pnlCandies);
            pnlPnls.remove(pnlDrinks);
            pnlPnls.add(pnlIceCream);
            pnlPnls.validate();
            pnlPnls.repaint();            
        }
        else if(e.getSource().equals(btnCuki)){         
            pnlPnls.remove(pnlIceCream);
            pnlPnls.remove(pnlCandies);
            pnlPnls.remove(pnlDrinks);
            pnlPnls.add(pnlCookies);
            pnlPnls.validate();
            pnlPnls.repaint();            
        }
        else if(e.getSource().equals(btnCndy)){
            pnlPnls.remove(pnlIceCream);
            pnlPnls.remove(pnlCookies);
            pnlPnls.remove(pnlDrinks);
            pnlPnls.add(pnlCandies);
            pnlPnls.validate();
            pnlPnls.repaint();            
        }
        else {
            pnlPnls.remove(pnlIceCream);
            pnlPnls.remove(pnlCookies);
            pnlPnls.remove(pnlCandies);
            pnlPnls.add(pnlDrinks);
            pnlPnls.validate();
            pnlPnls.repaint();
        }        
    }    
}
