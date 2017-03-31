package kiwidesserttill;

import kiwidesserttill.DAO.DAOLog;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Create Dialog PopUp window
 * @author HJS
 * @version 2016. 08. 08.
 */
public class LogOnOffCreateIdDialog extends JDialog implements ActionListener,  PropertyChangeListener{
    
    private JOptionPane optionPane;
    private String btnOk     = "OK";
    private String btnCancel = "Cancel";
    JPanel pnlBase;
    JTextField txtid;
    JTextField txtNm;
    JTextField txtpw ;
    JTextField txtpwcf;
    
    public LogOnOffCreateIdDialog(Frame aFrame) {
        super(aFrame, true);
        setTitle("Create ID");
        
        setLocationRelativeTo(aFrame);
     
        //User Dialog Display assignment
        JLabel str = new JLabel("Create ID");
        str.setFont(new Font(Settings.dlgoNmFont, Settings.dlgoNmStyle, Settings.dlgoNmSize));
        txtid = new JTextField(12);
        txtid.setDocument(new JTextfieldLimit(20));
        txtNm = new JTextField(12);
        txtNm.setDocument(new JTextfieldLimit(20));
        txtpw = new JPasswordField(12);
        txtpw.setDocument(new JTextfieldLimit(6));
        txtpwcf = new JPasswordField(12);
        txtpwcf.setDocument(new JTextfieldLimit(6));
        
        pnlBase = new JPanel(new GridLayout(0,1));
        pnlBase.setBorder(BorderFactory.createEmptyBorder(5 , 10 , 5 , 10)); 
        pnlBase.add(str);
        JLabel lblid = new JLabel("ID : ");
        lblid.setFont(new Font(Settings.dlgoNmFont, Settings.dlgoNmStyle, Settings.dlgoTagSize));
        pnlBase.add(lblid);
        pnlBase.add(txtid);
        txtid.addAncestorListener( new RequestFocusListener() );
        JLabel lblnm = new JLabel("Name : ");
        lblnm.setFont(new Font(Settings.dlgoNmFont, Settings.dlgoNmStyle, Settings.dlgoTagSize));
        pnlBase.add(lblnm);
        pnlBase.add(txtNm);
        JLabel lblpw = new JLabel("Password : ");
        lblpw.setFont(new Font(Settings.dlgoNmFont, Settings.dlgoNmStyle, Settings.dlgoTagSize));
        pnlBase.add(lblpw);
        pnlBase.add(txtpw);    
        JLabel lblpwcf = new JLabel("Password Confirm : ");
        lblpwcf.setFont(new Font(Settings.dlgoNmFont, Settings.dlgoNmStyle, Settings.dlgoTagSize));
        pnlBase.add(lblpwcf);
        pnlBase.add(txtpwcf);

        Object[] options = {btnOk, btnCancel};

        //Create the JOptionPane.
        optionPane = new JOptionPane(pnlBase,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.YES_NO_OPTION,
                null, options, options[0]);

        //Make this dialog display it.
        setContentPane(optionPane);

        //Handle window closing correctly.
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        //Ensure the text field always gets the first focus.
        txtid.addAncestorListener( new RequestFocusListener() );

        //Register an event handler that puts the text into the option pane.
        txtid.addActionListener(this);

        //Register an event handler that reacts to option pane state changes.
        optionPane.addPropertyChangeListener(this);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        optionPane.setValue(btnOk);
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        String prop = e.getPropertyName();
        
        if (isVisible()
            && (e.getSource() == optionPane)
            && (JOptionPane.VALUE_PROPERTY.equals(prop)
            || JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) 
        {   
            Object value = optionPane.getValue();
            if (value == JOptionPane.UNINITIALIZED_VALUE) {return;} //ignore reset
          
            //Reset the JOptionPane's value.
            optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);
            
            //user Click Ok
            if (btnOk.equals(value)) {            
                boolean flg = false;
                //All Textfields validation check
                String id = txtid.getText();
                String nm = txtNm.getText();
                String pw = txtpw.getText();
                if(id.equals("")){
                    JOptionPane.showMessageDialog(this, "Please Enter Correct ID", "Warning", JOptionPane.ERROR_MESSAGE);
                    txtid.requestFocusInWindow();
                }
                else if(nm.equals("")){
                    JOptionPane.showMessageDialog(this, "Please Enter Correct Name", "Warning", JOptionPane.ERROR_MESSAGE);
                    txtNm.requestFocusInWindow();
                }
                else if(pw.equals("")){
                    JOptionPane.showMessageDialog(this, "Please Enter Correct Password", "Warning", JOptionPane.ERROR_MESSAGE);
                    txtpw.requestFocusInWindow();
                }
                else{
                    try {
                        //Password validation Check
                        PasswordException.isValidPassword(txtpw.getText());
                        flg = true;
                    } catch (PasswordException ex) {
                        Logger.getLogger(LogOnOffCreateIdDialog.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(this, "Please Enter Correct Password", "Warning", JOptionPane.ERROR_MESSAGE);
                        txtpw.requestFocusInWindow();
                    }
                    finally{
                        if(flg){
                            //after that collect input.- done
                            if(txtpw.getText().equals(txtpwcf.getText())){
                                System.out.println("OK");
                                DAOLog dao = new DAOLog();
                                if(dao.insertNewId(id, nm, pw)==1)  System.out.println("DB updated successfully");
                                JOptionPane.showMessageDialog(this, "Created ID successfully", "Successful", JOptionPane.INFORMATION_MESSAGE);
                                exit();
                            }
                            else{
                                JOptionPane.showMessageDialog(this, "Different Two Passwords..");
                                txtpw.requestFocusInWindow();
                            }
                        }
                    }
                }                
            } else { //user closed dialog or clicked cancel
                JOptionPane.showMessageDialog(this, "Canceled", "Warning", JOptionPane.ERROR_MESSAGE);
                exit();
            }
        }
    }
    public void exit() {
        dispose();
    }   
}