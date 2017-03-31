package kiwidesserttill;

import kiwidesserttill.DAO.DAOSummary;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class displays based Pannel for Summary base
 * @author HJS
 * @version 2016. 8. 8.
 */ 
public class SummaryBase extends JPanel{
    
    KiwiDessertTill awin;
    SummaryTables sst;
    
    public SummaryBase(KiwiDessertTill awin){
        this.awin = awin;
        DAOSummary dao = new DAOSummary();
        JPanel pnlTop = new JPanel(new FlowLayout());
        JPanel pnlMid = new JPanel(new FlowLayout());    
        JLabel lblLogImg = new JLabel(new ImageIcon("img/shopLogoFull.png"));
        JComboBox combo = new JComboBox(); 
        combo.setPreferredSize(new Dimension(100, 30));
        combo.setFont(new Font(Settings.tableListFont, Settings.tableFontStyle, Settings.tableFontSize));
        pnlTop.add(lblLogImg);
        pnlMid.add(combo);
        dao.selectDateCombo(combo);
        sst = new SummaryTables(dao);  
        
        //background color setting
        this.setBackground(Settings.summmaryBg);   
        pnlTop.setBackground(Settings.summmaryBg);        
        pnlMid.setBackground(Settings.summmaryBg);  
        sst.setBackground(Settings.summmaryBg);   
        //assign components on the base panel
        this.setLayout(new FlowLayout());
        this.add(pnlTop);
        this.add(pnlMid);
        this.add(sst);
        
        ItemListener il = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {               
                if (e.getStateChange() == ItemEvent.SELECTED) { 
                    System.out.println("itemStateChanged selected : " + e.getItem());
                    sst.setClearTables();
                    sst.displayDailySalesTable(e.getItem().toString());
                }
            }
        };
        combo.addItemListener(il);
    }
}
