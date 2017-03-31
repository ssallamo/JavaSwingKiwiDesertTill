package kiwidesserttill;

import javax.swing.JComponent;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

/**
 * This class gives Give focuse to textfield on Dialog
 * @author HJS
 * @version 2016. 8. 24.
 */
public class RequestFocusListener implements AncestorListener{    
    private boolean removeListener;
    public RequestFocusListener(){
        this(true); 
    }    
    
    public RequestFocusListener(boolean rmListener){
        this.removeListener = rmListener;
    }
    
    @Override
    public void ancestorAdded(AncestorEvent e) {
        JComponent component = e.getComponent();
        component.requestFocusInWindow();
        if(removeListener)
            component.removeAncestorListener(this);
    }
    
    @Override
    public void ancestorRemoved(AncestorEvent e) {}
    @Override
    public void ancestorMoved(AncestorEvent e) {}    
}
