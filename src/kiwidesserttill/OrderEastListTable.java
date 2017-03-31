package kiwidesserttill;

import kiwidesserttill.Model.Order;
import javax.swing.*;
import java.awt.*;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * This class defines about 1 tables about 
 * Abstract Model, Dao, Display  also style
 * for Purchase List
 * @author HJS
 * @version 2016. 8. 11.
 */
public class OrderEastListTable extends JPanel{   
    
    Order odr; //data model
    JScrollPane scrp;
    DefaultTableCellRenderer celAlignCenter;
    OrderEastListModel ordrTblModel; //abstract model
    
    JTable jtbl;// =  new JTable(defTblModel);  
    ListSelectionModel listSelectionModel; //row selection    
    int selectedRowIndex =0;
    
    public OrderEastListTable(){
        
        ordrTblModel = new OrderEastListModel();  
        //assign Table
        jtbl = new JTable(ordrTblModel); 
        
        this.setLayout(null);        
        //including jtable on jscrollpan
        scrp = new JScrollPane(jtbl);
        scrp.setBounds(0, 0, 280, 460);
        this.add(scrp, BorderLayout.CENTER);
        
        //Declear Cells style
        DefaultTableCellRenderer celAlignLeft = new DefaultTableCellRenderer();
        celAlignLeft.setHorizontalAlignment(JLabel.LEFT);
        DefaultTableCellRenderer celAlignCenter =  new DefaultTableCellRenderer();
        celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
        celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
        
        //Set Cells Size         
        jtbl.getColumn("Seq").setWidth(0);
        jtbl.getColumn("Seq").setMinWidth(0);
        jtbl.getColumn("Seq").setMaxWidth(0);
        jtbl.getColumn("SeqNo").setWidth(0);
        jtbl.getColumn("SeqNo").setMinWidth(0);
        jtbl.getColumn("SeqNo").setMaxWidth(0);
        jtbl.getColumn("No").setPreferredWidth(30);
        jtbl.getColumn("No").setCellRenderer(celAlignCenter);
        jtbl.getColumn("Qty").setPreferredWidth(30);
        jtbl.getColumn("Qty").setCellRenderer(celAlignCenter);
        jtbl.getColumn("Order Items").setPreferredWidth(170);
        jtbl.getColumn("Order Items").setCellRenderer(celAlignLeft);
        jtbl.getColumn("Price").setPreferredWidth(50);
        jtbl.getColumn("Price").setCellRenderer(celAlignRight);
               
        //Set Padding betweddn Rows
        jtbl.setRowHeight(30);
        jtbl.setFont(new Font(Settings.tableListFont, Settings.tableFontStyle, Settings.tableFontSize));

        //set jtable background color
        scrp.getViewport().setBackground(Color.WHITE);        
        
        //selection Lister Assign        
        listSelectionModel = jtbl.getSelectionModel();
        listSelectionModel.addListSelectionListener(new SharedListSelectionHandler());
	jtbl.setSelectionModel(listSelectionModel);
        
        //setTableVeiw
        this.setSize(300,280);
        this.setVisible(true);
    }
    
    /**
     * When West panel order button click, this table row is added
     * @param nm : name of products
     * @param price  : price of products
     */    
    public void tblAddRowOrder(String nm, double price){
        //get last row number from the table 
        int focusLastRow = ordrTblModel.getRowCount();
        //get maxSeq
        int maxSeq = (ordrTblModel.getRowCount()==0)? 1 : (ordrTblModel.getMaxSeqN0()+1);
        
        odr = new Order();
        odr.setSeq(maxSeq);
        odr.setNo(String.valueOf(maxSeq));
        odr.setDependSeq(1);//defualt 1 : not add on, pure order's first dependSeq is 1
        odr.setQty(1); //defualt 1
        odr.setDesertNm(nm);
        odr.setPrice(price);
        
        ordrTblModel.addRowOrder(odr);
        jtbl.setRowSelectionInterval(focusLastRow,focusLastRow);
    }
    
    /**
     * Addon Order is added on the table
     * @param nm : name of add on
     * @param price  : price of add on
     */
    public void tblAddRowOrderAddOn(String nm, double price){   
        //get last row number from the table 
        int focusLastRow = ordrTblModel.getRowCount();    
        odr = new Order();
        int seq = (int)ordrTblModel.getValueAt(selectedRowIndex,0);
        int dependSeq = (int)ordrTblModel.getValueAt(selectedRowIndex,1);
        odr.setSeq(seq);
        odr.setDependSeq((dependSeq+1));
        odr.setNo("+");
        odr.setQty(1);
        odr.setDesertNm(nm);
        odr.setPrice(price);
        ordrTblModel.addRowOrder(odr);
        jtbl.setRowSelectionInterval(focusLastRow,focusLastRow);
    }
    
    //OrderListModel ordrTblModel;    
    protected void init(){      
        OrderEastListModel model = (OrderEastListModel)jtbl.getModel();
        model.setNumRows(0);        
    }
    
    /**
     * when (+ or -) buttons are clicked, it excutes
     * @param c : flag
     */
    protected void updateQty(char c){
        //just return as no data from table
        if(ordrTblModel.getRowCount()==0) return;
        
        int tQty   = (int)ordrTblModel.getValueAt(selectedRowIndex,3);
        double tPrice = (double)ordrTblModel.getValueAt(selectedRowIndex,5);
        double aPrice = (double)tPrice/tQty;
        
        //fetch current value
        odr = new Order(); 
        odr.setSeq((int)ordrTblModel.getValueAt(selectedRowIndex,0));
        odr.setDependSeq((int)ordrTblModel.getValueAt(selectedRowIndex,1));
        odr.setNo((String)ordrTblModel.getValueAt(selectedRowIndex,2));
        odr.setDesertNm((String)ordrTblModel.getValueAt(selectedRowIndex,4));
        if(c=='+'){
            odr.setQty(tQty+1);
            odr.setPrice(tPrice+aPrice);            
        }
        else{
            if(tQty>1){             
                odr.setQty(tQty-1);
                odr.setPrice(tPrice-aPrice);
            }
            else{  
                delteRow();
                return ;
            }
        }
        //update what calculated value
        ordrTblModel.updateRowOrder(odr, selectedRowIndex);      
    }
    
    /**
     * when x button clicks. delete order with addon if there is addon order
     */
    protected void delteRow(){
        //just return as no data from table
        if(ordrTblModel.getRowCount()==0) return;
        
        //fetch current value        
        int seq = (int)ordrTblModel.getValueAt(selectedRowIndex,0);        
        String no = (String)ordrTblModel.getValueAt(selectedRowIndex,2);
        if(no.equals("+")){
            ordrTblModel.removeOrder(selectedRowIndex);
        }
        else{
            int numRows = (ordrTblModel.getRowCount()-1);//numRows will be index
            int i;
            //remove from last row of the selected order
            for (i=numRows; i>=0 ;i--) {                
                int tmpSeq    = (int)ordrTblModel.getValueAt(i,0); 
                if(seq == tmpSeq){
                    ordrTblModel.removeOrder((i));
                }
            }            
        }        
        //give a focus
        if((selectedRowIndex-1)>=0){
            int focusindex = (selectedRowIndex-1);       
            jtbl.setRowSelectionInterval(focusindex,focusindex);
        }
    }
    
    /**
     * calculating total amount
     * @return total value
     */
    protected double getTotalPayment(){        
        double rtnDoubleVal = 0.0;        
        for(int rowi=0; rowi<ordrTblModel.getRowCount(); rowi++){
            double tval = Double.valueOf(String.format("%.2f", (double)jtbl.getValueAt(rowi, 5)));
            rtnDoubleVal += tval;
        }
        return Double.valueOf(String.format("%.02f",rtnDoubleVal)) ;
    }
    
    //Table row selection listener
    class SharedListSelectionHandler implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {            
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();            
            if(lsm.isSelectionEmpty()){
            }else{
                int minIndex = lsm.getMinSelectionIndex();
                int maxIndex = lsm.getMaxSelectionIndex();
                for (int i = minIndex; i <= maxIndex; i++) {
                    if (lsm.isSelectedIndex(i)) { selectedRowIndex = i;}
                }
            }
        }
    }//end of listener     
}
