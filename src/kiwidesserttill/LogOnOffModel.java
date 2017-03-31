package kiwidesserttill;

import kiwidesserttill.Model.LogOnOff;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 * This class is an abtract model for log On/Off tables
 * @author HJS
 * @version 2016. 8. 12.
 */
public class LogOnOffModel extends AbstractTableModel{
    //colunm header
    private String[] columNames={"ID", "Name", "Last Log Off", "Last Log On", "PW"};
    private Vector data = new Vector<LogOnOff>();

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
        LogOnOff emp = (LogOnOff)data.elementAt(rowIndex);
             if(columnIndex==0) return emp.getEmpId();
        else if(columnIndex==1) return emp.getEmpName();
        else if(columnIndex==2) return emp.getLastLogOnTm();
        else if(columnIndex==3) return emp.getLastLogOffTm();
        else if(columnIndex==4) return emp.getEmpPassword();
        else return null;
    }
        
    @Override
    public String getColumnName(int i) {
     return columNames[i];
    }
    
    //adding a row 
    public void addRow(Object obj){
        data.addElement(obj);      
        fireTableRowsInserted(data.size()-1, data.size()-1);
        
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
            fireTableRowsInserted(old, rowCount-1); //applicatin into jtable
        }
    }
}
