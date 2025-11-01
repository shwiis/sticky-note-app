

import javax.swing.SwingUtilities;

public class MainWindow {

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() ->MainFrame.getInstance());
    }  

}
    