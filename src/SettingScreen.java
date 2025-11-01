
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class SettingScreen extends JPanel implements ActionListener{
    JButton btnBack;
    public SettingScreen() {
        setLayout(new BorderLayout());
        JPanel upperButtons = new JPanel();

        btnBack = new JButton("<");
        btnBack.addActionListener(this);

        upperButtons.add(btnBack);

        add(upperButtons, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == btnBack){
            MainFrame.getInstance().showFirstWindow();

        }
    }
}
