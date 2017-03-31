package kiwidesserttill;

import kiwidesserttill.Model.Summary;
import kiwidesserttill.Model.Order;
import kiwidesserttill.DAO.DAOSummary;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * This class defines about 2 tables about Daily Sales Summary 
 * Defines Abstract Model, Dao, Display  also style
 * for Purchase List
 * @author HJS
 * @version 2016. 8. 11.
 */
public class SummaryTables extends JPanel{
    Summary dailySales;
    Order odr;
    JScrollPane scrpS;
    JScrollPane scrpO;
    SummaryModel salSmrMdl;
    OrderEastListModel odrLstMdl;
    JTable smrTbl;
    JTable ordTbl;
    DAOSummary ssDao;
    
    public SummaryTables(DAOSummary dao){        
        super(new FlowLayout());
        //assign Models
        salSmrMdl = new SummaryModel();
        odrLstMdl = new OrderEastListModel();
        //assign Tables
        smrTbl = new JTable(salSmrMdl);
        ordTbl = new JTable(odrLstMdl);
        //set Size of Tables
	smrTbl.setPreferredScrollableViewportSize(new Dimension(495,575));
	ordTbl.setPreferredScrollableViewportSize(new Dimension(495,575));
	smrTbl.setFillsViewportHeight(true);	
	ordTbl.setFillsViewportHeight(true);
        //assign scroll panel
	scrpS = new JScrollPane(smrTbl);
	scrpO = new JScrollPane(ordTbl);
	this.add(scrpS);
	this.add(scrpO);
        
        //Table Style
        DefaultTableCellRenderer celAlignLeft = new DefaultTableCellRenderer();
        celAlignLeft.setHorizontalAlignment(JLabel.LEFT);
        DefaultTableCellRenderer celAlignCenter =  new DefaultTableCellRenderer();
        celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
        celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
        //Right table
        smrTbl.getColumnModel().getColumn(0).setPreferredWidth(80);
        smrTbl.getColumnModel().getColumn(0).setCellRenderer(celAlignCenter);      
        smrTbl.getColumnModel().getColumn(1).setWidth(0);
        smrTbl.getColumnModel().getColumn(1).setMinWidth(0);
        smrTbl.getColumnModel().getColumn(1).setMaxWidth(0);
        smrTbl.getColumnModel().getColumn(2).setPreferredWidth(80);
        smrTbl.getColumnModel().getColumn(2).setCellRenderer(celAlignRight);    
        smrTbl.getColumnModel().getColumn(3).setPreferredWidth(80);
        smrTbl.getColumnModel().getColumn(3).setCellRenderer(celAlignRight);    
        smrTbl.getColumnModel().getColumn(4).setWidth(0);
        smrTbl.getColumnModel().getColumn(4).setMinWidth(0);
        smrTbl.getColumnModel().getColumn(4).setMaxWidth(0); 
        smrTbl.getColumnModel().getColumn(5).setPreferredWidth(100);
        smrTbl.getColumnModel().getColumn(5).setCellRenderer(celAlignCenter);    
        smrTbl.getColumnModel().getColumn(6).setPreferredWidth(155);
        smrTbl.getColumnModel().getColumn(6).setCellRenderer(celAlignCenter);         
        //Set Padding betweddn Rows
        smrTbl.setRowHeight(25);
        smrTbl.setFont(new Font(Settings.tableListFont, Settings.tableFontStyle, Settings.tableFontSize));

        //Left table
        ordTbl.getColumnModel().getColumn(0).setWidth(0);
        ordTbl.getColumnModel().getColumn(0).setMinWidth(0);
        ordTbl.getColumnModel().getColumn(0).setMaxWidth(0);          
        ordTbl.getColumnModel().getColumn(1).setWidth(0);
        ordTbl.getColumnModel().getColumn(1).setMinWidth(0);
        ordTbl.getColumnModel().getColumn(1).setMaxWidth(0);     
        ordTbl.getColumnModel().getColumn(2).setPreferredWidth(30);
        ordTbl.getColumnModel().getColumn(2).setCellRenderer(celAlignCenter);   
        ordTbl.getColumnModel().getColumn(3).setPreferredWidth(50);
        ordTbl.getColumnModel().getColumn(3).setCellRenderer(celAlignCenter);       
        ordTbl.getColumnModel().getColumn(4).setPreferredWidth(315);
        ordTbl.getColumnModel().getColumn(4).setCellRenderer(celAlignLeft);  
        ordTbl.getColumnModel().getColumn(5).setPreferredWidth(80);
        ordTbl.getColumnModel().getColumn(5).setCellRenderer(celAlignRight);       
        //Set Padding betweddn Rows
        ordTbl.setRowHeight(25);
        ordTbl.setFont(new Font(Settings.tableListFont, Settings.tableFontStyle, Settings.tableFontSize));

        
        scrpS.getViewport().setBackground(Color.WHITE);      
        scrpO.getViewport().setBackground(Color.WHITE); 
        
        ssDao = dao;
        
        //Listener
        MouseAdapter mml = new MouseAdapter(){
            public void mouseClicked(MouseEvent e){             
                JTable table = (JTable)e.getSource();
                int row = table.getSelectedRow();
                int searchSeq = (int)salSmrMdl.getValueAt(row, 0); //seq
                displayOrderItemsTable(searchSeq);
            }
        };
        smrTbl.addMouseListener(mml);
    }
    
    /**
     * Display on the left side table
     * @param dt : to search date keyword from combobox
     */
    public void displayDailySalesTable(String dt){        
        salSmrMdl.setNumRows(0);//table clear         
        if(dt.equals("-")) return; //- the firstCombobox
        ArrayList<Summary> dsList = ssDao.selectDailySales(dt);        
        for(int i = 0 ; i < dsList.size() ; i++){
            Summary ds = dsList.get(i);
            salSmrMdl.addRow(ds);
        }
    }
    
    /**
     * Display on th right side table
     * @param searchSeq : got keyword from left side one row
     */
    public void displayOrderItemsTable(int searchSeq){           
        odrLstMdl.setNumRows(0); //table clear 
        ArrayList<Order> dsList = ssDao.selectSoldItems(searchSeq);        
        for(int i = 0 ; i < dsList.size() ; i++){
            Order ds = dsList.get(i);
            odrLstMdl.addRow(ds);
        }
    }
    
    /**
     * Tables initialization
     */
    public void setClearTables(){        
        salSmrMdl.setNumRows(0);          
        odrLstMdl.setNumRows(0);
    }
}
