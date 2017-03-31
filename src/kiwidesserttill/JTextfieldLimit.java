package kiwidesserttill;

import javax.swing.text.AttributeSet;
import javax.swing.text .BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * This class limit size of enterd text on jtextfield
 * @author HJS
 * @version 2016. 8. 24.
 */
public class JTextfieldLimit extends PlainDocument {
    private int limit;
    public JTextfieldLimit(int limit){
        super();
        this.limit =  limit;
    }
    
    public void insertString(int offset, String str, AttributeSet attr) 
            throws BadLocationException{
        if(str==null) return;
        if(getLength()+str.length()<=limit)
            super.insertString(offset, str, attr);
    }    
}
