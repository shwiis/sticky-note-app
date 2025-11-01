
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class FirstWindow extends JPanel implements ActionListener {

    private JButton btnNotes, btnShelves, btnSettings;
    
    public FirstWindow() {

        setOpaque(false);
        setLayout(new GridLayout(3,1,50,50));
        setBorder(BorderFactory.createEmptyBorder(100,100,100,100));

        // Create buttons
        btnNotes = createButton("Sticky Note");
        btnShelves = createButton("Shelves");
        btnSettings = createButton("Settings");

        // Add buttons to panel
        add(btnNotes);
        add(btnShelves);
        add(btnSettings);
    }

    /**
     * Creates and styles a reusable JButton.
     */
    private JButton createButton(String text) {
        ImageIcon btnDesign = new ImageIcon("src/btnDesign.png");
        
        JButton btn = new JButton(text, btnDesign);

        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        btn.setVerticalTextPosition(SwingConstants.CENTER);
        
        // Remove focus/border effects for cleaner UI
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        
        // Font style
        btn.setFont(new Font("Arial", Font.PLAIN, 30));

        // Connect button to ActionListener (this class)
        btn.addActionListener(this);

        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNotes) {
            MainFrame.getInstance().showStickyNoteScreen();
        }

        if (e.getSource() == btnShelves) {
            MainFrame.getInstance().showShelveScreen();
        }
        
        if (e.getSource() == btnSettings) {
            MainFrame.getInstance().showSettingScreen();
        }
    }

    
    
}
 
    

