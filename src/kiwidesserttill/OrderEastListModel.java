package kiwidesserttill;

import kiwidesserttill.Model.Order;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 * This class is an abtract model for order list table
 * @author HJS
 * @version 2016. 8. 10.
 */
public class OrderEastListModel extends AbstractTableModel{
    
    private String[] columNames = {"Seq","SeqNo","No", "Qty", "Order Items", "Price"};
    private Vector data = new Vector<Order>();
    
    @Override
    public int getRowCount() {
        return data.size();//Seq Start from 1
    }

    @Override
    public int getColumnCount() {
        return columNames.length;
    }

    @Override
    public Object getValueAt(int rowi, int coli) {
        Order ord = (Order)data.elementAt(rowi);
             if(coli==0) return ord.getSeq();
        else if(coli==1) return ord.getDependSeq();
        else if(coli==2) return ord.getNo();
        else if(coli==3) return ord.getQty();
        else if(coli==4) return ord.getDesertNm();
        else if(coli==5) return ord.getPrice();
        else return null;
    }

    @Override
    public String getColumnName(int i) {
        return columNames[i];
    }
        
    public void addRow(Object obj){
        data.addElement(obj);      
        fireTableRowsInserted(data.size()-1, data.size()-1);
        
    }    
    
    //Table add 1 row on the end of the table 
    //object : data model
    public void addRowOrder(Object value){  
        data.addElement(value);      
        fireTableRowsInserted(data.size()-1, data.size()-1);
    }
    
    //Table update
    public void insertRowOrder(Object value, int rowindex){
        data.addElement(value);
        //fireTableCellUpdated(row, col);
        fireTableRowsInserted(rowindex, rowindex);
        System.out.println("Model : insertRowOrder - datasize()"+(rowindex));        
    }
       
    //Table update
    public void updateRowOrder(Object value, int rowindex){
        //data[rowindex][3]=value;
        data.setElementAt(value,rowindex);
        fireTableCellUpdated(rowindex, 3);
        fireTableCellUpdated(rowindex, 5);
        
        System.out.println("Model : updateRowOrder - datasize()"+(rowindex));
    }
    //Table update
    public void removeOrder(int rowindex){        
        data.remove(rowindex);
        fireTableRowsDeleted(rowindex,rowindex);
        System.out.println("Model : removeOrder - datasize()"+(rowindex));
    }   
    
    //get Max Seq No
    public int getMaxSeqN0(){
        int tmpMaxVal=1;
        for(int i=0 ; i<getRowCount(); i++){
            Order ord = (Order)data.elementAt(i);
            if(tmpMaxVal<ord.getSeq())
                tmpMaxVal=ord.getSeq();
        }
        return tmpMaxVal;
    }
    
    //Finding Index
    public int getIndex(int seq){
        for(int i=0; i< data.size(); i++){
            Order ord = (Order)data.elementAt(i);
            if((seq==ord.getSeq())){
                return i;
            }
        }
        return 9999;
    }
    
    //Table reset up to rowCount
    public void setNumRows(int rowCount) {
        int old = getRowCount();
        if (old == rowCount) {
            return;
        }
        data.setSize(rowCount);
        if (rowCount <= old) {
            fireTableRowsDeleted(rowCount, old-1);
        }
        else {            
            for (int i = rowCount; i < old; i++) {
                if (data.elementAt(i) == null) {
                    data.setElementAt(new Vector(), i);
                }
                ((Vector)data.elementAt(i)).setSize(getColumnCount());
            }
            fireTableRowsInserted(old, rowCount-1);
        }
    }
}
