package kiwidesserttill;

import kiwidesserttill.Model.LogOnOff;
import kiwidesserttill.DAO.DAOLog;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * This class defines about 2 tables about
 * Abstract Model, Dao, Display  also style
 * @author HJS
 * @version 2016. 8. 13.
 */
public class LogOnOffTables extends JPanel{
    
    LogOnOff tiller; //data model
    JScrollPane scrpOff;
    JScrollPane scrpOn;
    JTable offTbl;
    JTable onTbl;
    LogOnOffModel offMdl; //abstract model
    LogOnOffModel onMdl; //abstract model
    DAOLog looDao;
    
    public LogOnOffTables(DAOLog dao){
        super(new FlowLayout());
      
        //assign Models
        offMdl = new LogOnOffModel();
        onMdl = new LogOnOffModel();
        //assign Tables
        offTbl = new JTable(offMdl);
        onTbl = new JTable(onMdl);
        //set Size of Tables
	offTbl.setPreferredScrollableViewportSize(new Dimension(350,450));
	onTbl.setPreferredScrollableViewportSize(new Dimension(350,450));
	offTbl.setFillsViewportHeight(true);	
	onTbl.setFillsViewportHeight(true);
        //assign scroll panel
	scrpOff = new JScrollPane(offTbl);
	scrpOn = new JScrollPane(onTbl);
        JLabel lblLogImg = new JLabel(new ImageIcon("img/arrows.png"));
	this.add(scrpOff);
        this.add(lblLogImg);
	this.add(scrpOn);
        
        //Table Style 
        DefaultTableCellRenderer celAlignLeft = new DefaultTableCellRenderer();
        celAlignLeft.setHorizontalAlignment(JLabel.LEFT);
        DefaultTableCellRenderer celAlignCenter =  new DefaultTableCellRenderer();
        celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
        celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
        //Set Cells Size         
        //right Table
        offTbl.getColumnModel().getColumn(0).setPreferredWidth(50);
        offTbl.getColumnModel().getColumn(0).setCellRenderer(celAlignCenter);
        offTbl.getColumnModel().getColumn(1).setPreferredWidth(70);
        offTbl.getColumnModel().getColumn(1).setCellRenderer(celAlignLeft); 
        offTbl.getColumnModel().getColumn(2).setPreferredWidth(180);
        offTbl.getColumnModel().getColumn(2).setCellRenderer(celAlignCenter);       
        offTbl.getColumnModel().getColumn(3).setWidth(0);
        offTbl.getColumnModel().getColumn(3).setMinWidth(0);
        offTbl.getColumnModel().getColumn(3).setMaxWidth(0);
        offTbl.getColumnModel().getColumn(4).setWidth(0);
        offTbl.getColumnModel().getColumn(4).setMinWidth(0);
        offTbl.getColumnModel().getColumn(4).setMaxWidth(0);
        //left Table
        onTbl.getColumnModel().getColumn(0).setPreferredWidth(50);
        onTbl.getColumnModel().getColumn(0).setCellRenderer(celAlignCenter);
        onTbl.getColumnModel().getColumn(1).setPreferredWidth(70);
        onTbl.getColumnModel().getColumn(1).setCellRenderer(celAlignLeft);   
        onTbl.getColumnModel().getColumn(2).setWidth(0);
        onTbl.getColumnModel().getColumn(2).setMinWidth(0);
        onTbl.getColumnModel().getColumn(2).setMaxWidth(0);   
        onTbl.getColumnModel().getColumn(3).setPreferredWidth(180);
        onTbl.getColumnModel().getColumn(3).setCellRenderer(celAlignCenter);        
        onTbl.getColumnModel().getColumn(4).setWidth(0);
        onTbl.getColumnModel().getColumn(4).setMinWidth(0);
        onTbl.getColumnModel().getColumn(4).setMaxWidth(0);
        
        //Set Padding betweddn Rows
        offTbl.setRowHeight(30);
        onTbl.setRowHeight(30);
        offTbl.setFont(new Font(Settings.tableListFont, Settings.tableFontStyle, Settings.tableFontSize));
        onTbl.setFont(new Font(Settings.tableListFont, Settings.tableFontStyle, Settings.tableFontSize));
        scrpOff.getViewport().setBackground(Color.WHITE);      
        scrpOn.getViewport().setBackground(Color.WHITE);       
        looDao = dao;
        
        //Listener
        MouseAdapter mml = new MouseAdapter(){
            public void mouseClicked(MouseEvent e){    
                String nm, id, pw = null;
                String flg = "";
        
                JTable table = (JTable)e.getSource();
                int row = table.getSelectedRow();
                System.out.println("row : "+ row);
                if(offTbl==(JTable)e.getComponent()){
                    id = (String)offMdl.getValueAt(row, 0);
                    nm = (String)offMdl.getValueAt(row, 1);
                    pw = (String)offMdl.getValueAt(row, 4);
                    flg= "ON";
                }
                else { //if(onTbl==(JTable)e.getComponent()){
                    id = (String)onMdl.getValueAt(row, 0);
                    nm = (String)onMdl.getValueAt(row, 1);
                    pw = (String)onMdl.getValueAt(row, 4);
                    flg= "OFF";
                }
                LogOnOffDialog dlg = new LogOnOffDialog(flg, nm, id, pw );
                displayLogOffTiller();
                displayLogOnTiller();
            }
        };
        
        offTbl.addMouseListener(mml);
        onTbl.addMouseListener(mml);        
        displayLogOffTiller();
        displayLogOnTiller();
    }
    
    /**
     * Display on the left side table
     */
    public void displayLogOffTiller(){        
        offMdl.setNumRows(0);//table clear        
        ArrayList<LogOnOff> dsList = looDao.selectLogOffEmp();        
        for(int i = 0 ; i < dsList.size() ; i++){
            LogOnOff ds = dsList.get(i);
            offMdl.addRow(ds);
        }
    }
    
    /**
     * Display on the Right side table
     */
    public void displayLogOnTiller(){        
        onMdl.setNumRows(0);//table clear        
        ArrayList<LogOnOff> dsList = looDao.selectLogOnEmp();        
        for(int i = 0 ; i < dsList.size() ; i++){
            LogOnOff ds = dsList.get(i);
            onMdl.addRow(ds);
        }
    } 
}
