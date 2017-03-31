package kiwidesserttill;

import javax.swing.JFrame;

/**
 * This class is frame on this system
 * Main class inherits this class
 * @author HJS
 * @version 2016. 7. 24.
 */
public class MFrame extends JFrame{
    
    public MFrame(){
        this.setTitle(Settings.title); //title
        this.setIconImage(Settings.titleIcon.getImage()); //icon
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //process exit
        this.setSize(1024,764);
        this.setResizable(false); 
    }
}
