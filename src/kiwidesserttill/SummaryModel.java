package kiwidesserttill;

import kiwidesserttill.Model.Summary;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 * This class is an abtract model for Summary list table
 * @author HJS
 * @version 2016. 8. 10.
 */
public class SummaryModel extends AbstractTableModel{
    
    private String[] columNames={"Order No", "Date", "Total Price", "Tax", "PaidPrice", "Tiller Name", "Time"};
    private Vector data = new Vector<Summary>();
    
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Summary ds = (Summary)data.elementAt(rowIndex);
             if(columnIndex==0) return ds.getSalesSeq();
        else if(columnIndex==1) return ds.getDt();
        else if(columnIndex==2) return ds.getTotalPrice();
        else if(columnIndex==3) return ds.getTax();
        else if(columnIndex==4) return ds.getPaidPrice();
        else if(columnIndex==5) return ds.getTillerNm();
        else if(columnIndex==6) return ds.getTm();
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
    
    /**
     * Table reset
     * @param rowCount 
     */
    public void setNumRows(int rowCount) {
        int old = getRowCount();
        if (old == rowCount) return;
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
