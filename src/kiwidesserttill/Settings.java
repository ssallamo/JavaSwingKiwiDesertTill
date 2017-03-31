package kiwidesserttill;

import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 * This class defines reputatied valuables
 * Style, color, font, size, image
 * @author HJS
 * @version 2016. 8. 16
 */ 
public interface Settings {
    
    String title = "Kiwi Dessert Till System";
    ImageIcon titleIcon = new ImageIcon("img/kiwiDessertIcon.png");
    
    String menu1LvNmFont  = "Ebrima";
    int    menu1LvNmSize  = 30;
    int    menu1LvNmStyle = Font.LAYOUT_LEFT_TO_RIGHT;
    Color  menu1LvNmColor = Color.RED;    
    
    String tableListFont  = "Lao UI";
    int    tableFontStyle = Font.PLAIN;
    int    tableFontSize  = 15;
    
    String totalAmtFont  = "Lao UI";
    int    totalAmtStyle = Font.BOLD;
    int    totalAmtSize  = 22;
    
    String itemFont  = "Rockwell";
    int    itemStyle = Font.PLAIN;
    int    itemSize  = 13;
    Color  itemColorYellow = new Color(248, 216,115); 
    Color  itemColorBrown = new Color(116, 73,92);    
    Color  itemColorWhite = new Color(251, 248,243);   
    
    //Tiller
    String tillerBtnFont  = "Rockwell";
    int    tillerBtnSize  = 20;
    int    tillerBtnStyle = Font.LAYOUT_LEFT_TO_RIGHT;
    
    //Panel colors
    Color IceCreamPnlClr = new Color(49,125,177);
    Color iceCreamAddonPnlClr = new Color(82,154,202); 
    Color CookiesPnlCtr = new Color(79,172,143);
    Color CookiesAddonPnlCtr = new Color(105,194,174);
    Color CandyPnlCtr = new Color(210,57,41);
    Color CandyAddonPnlCtr = new Color(238,75,60);
    Color DrinkPnlCtr = new Color(235,170,42);
    Color DrinkAddonPnlCtr = new Color(255,214,98);
    
    String dlgoNmFont  = "Book Antiqua";
    int    dlgoNmSize  = 20;
    int    dlgoTagSize = 13;
    int    dlgoNmStyle = Font.LAYOUT_LEFT_TO_RIGHT;
    Color  dlgoNmBgColor = new Color(205,232,246);    
        
    String dlgoCfNmFont  = "Book Antiqua";
    int    dlgoCfNmSize  = 22;
    int    dlgoCfTagSize = 15;
    int    dlgoCfNmStyle = Font.LAYOUT_LEFT_TO_RIGHT;
    
    Color summmaryBg = new Color(245,231,198);
    Color logOnOffBg = new Color(247,190,212);
}
