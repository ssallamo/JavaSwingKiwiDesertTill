package kiwidesserttill;

import kiwidesserttill.DAO.DAOLog;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Dialog for log on/off update with password checking
 * @author HJS
 * @version 2016. 8. 10.
 */
public class LogOnOffDialog extends JDialog{    
    JLabel jlb3 = new JLabel("");
    
    public LogOnOffDialog(String f, String nm, String id, String pw){        
        loadDialog(f, nm, id, pw);        
    }
    
    public void loadDialog(String f, String nm, String id, String pw){
        //chagne the Laguage
        Locale lng = new Locale("en", "US");
        JOptionPane.setDefaultLocale(lng);
        
        JLabel str = new JLabel("Hi! "+ nm + "!!!");        
        str.setFont(new Font(Settings.dlgoNmFont, Settings.dlgoNmStyle, Settings.dlgoNmSize));
        
        //logon off combobox
        String[] items = {"LogOn", "LogOff"};
        JComboBox combo = new JComboBox(items);  
        if(f.equals("ON")) combo.setSelectedIndex(0);
        else               combo.setSelectedIndex(1);
        combo.setEnabled(false);
        combo.setForeground(Color.BLACK);
        //Display id
        JTextField txtid = new JTextField(id);      
        txtid.setEditable(false);
        //Password check
        JTextField txtpw = new JPasswordField(12);
        txtpw.setDocument(new JTextfieldLimit(6));
        txtpw.addAncestorListener( new RequestFocusListener() ); //Give a focuse
        //Display panel assign
        JPanel pnlBase = new JPanel(new GridLayout(0,1));
        pnlBase.setBorder(BorderFactory.createEmptyBorder(5 , 10 , 5 , 10)); 
        pnlBase.add(str);
        pnlBase.add(combo);
        JLabel lblid = new JLabel("ID : ");
        lblid.setFont(new Font(Settings.dlgoNmFont, Settings.dlgoNmStyle, Settings.dlgoTagSize));
        pnlBase.add(lblid);
        pnlBase.add(txtid);
        JLabel lblpw = new JLabel("PASSWORD : ");
        lblpw.setFont(new Font(Settings.dlgoNmFont, Settings.dlgoNmStyle, Settings.dlgoTagSize));
        pnlBase.add(lblpw);
        pnlBase.add(txtpw);    
                
        int result = JOptionPane.showConfirmDialog(null, pnlBase, ("LOG"+f),
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if(pw.equals(txtpw.getText())){
                System.out.println("OK");
                DAOLog dao = new DAOLog();
                if(dao.updateLonOnStatus(id, f)==1){
                    System.out.println("DB updated successfully");
                }
            }
            else{
                 JOptionPane.showMessageDialog(this, "Invalid Password..");
                 return;
            }            
        } else {
            JOptionPane.showMessageDialog(this, "Canceled..");
        }
    }      
}
