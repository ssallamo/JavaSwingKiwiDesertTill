package kiwidesserttill;

import java.awt.BorderLayout;
import kiwidesserttill.DAO.DAOLog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

/**
 * This class displays based Pannel for Log on/off
 * @author HJS
 * @version 2016. 7. 24.
 */
public class LogOnOffBase extends JPanel implements MouseListener{
    
    KiwiDessertTill awin;    
    private LogOnOffTables LogOnTblOffTbl;
    
    public LogOnOffBase(KiwiDessertTill awin){
        this.awin = awin;
        DAOLog dao = new DAOLog();
        JPanel pnlTop = new JPanel(new BorderLayout());
        JPanel pnlBottom = new JPanel(new GridLayout());        
        
        //part of Top : Title image, left and right table titles
        JLabel lblLogImg = new JLabel(new ImageIcon("img/shopLogoFull.png"));
        JLabel lblBeingOff = new JLabel(new ImageIcon("img/OfferList.png"));
        JLabel lblBeingOn = new JLabel(new ImageIcon("img/OnerList.png"));
        pnlTop.add(lblLogImg, BorderLayout.NORTH);
        pnlTop.add(lblBeingOff, BorderLayout.WEST);
        lblBeingOff.setBorder(new EmptyBorder(30, 140, 0, 0)); 
        pnlTop.add(lblBeingOn, BorderLayout.CENTER);
        lblBeingOn.setBorder(new EmptyBorder(30, -30, 0, 0)); 
        
        //2Tables memory assign
        LogOnTblOffTbl = new LogOnOffTables(dao); 
        LogOnTblOffTbl.setBorder(new EmptyBorder(0, 0, 0, 0)); 
        
        //this pannel base style
        this.setBackground(Settings.logOnOffBg);   
        pnlTop.setBackground(Settings.logOnOffBg);      
        LogOnTblOffTbl.setBackground(Settings.logOnOffBg);       
        pnlBottom.setBackground(Settings.logOnOffBg);   
                
        //Clreate ID Circle button
        JButton createId = new JButton(new ImageIcon("img/CreateId.png"));
        createId.setPreferredSize(new Dimension(84,77));     
        createId.setBorderPainted(false);        
        createId.setContentAreaFilled(false);
        createId.setFocusPainted(false);
        createId.setHorizontalAlignment(SwingConstants.LEFT);
        pnlBottom.add(createId);
        pnlBottom.setPreferredSize(new Dimension(800,77));
        createId.addMouseListener(this);
        
        //add containts on base panel
        this.setLayout(new FlowLayout());
        this.add(pnlTop);
        this.add(LogOnTblOffTbl);
        this.add(pnlBottom);
    }

    @Override
    public void mouseClicked(MouseEvent e) {      
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LogOnOffCreateIdDialog(null).setVisible(true);
            }
        });
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {
        LogOnTblOffTbl.displayLogOffTiller(); //table refresh
        LogOnTblOffTbl.displayLogOnTiller(); //table refresh
    }
}
