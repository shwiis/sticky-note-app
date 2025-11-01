
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;


public class ShelveScreen extends JPanel implements ActionListener{
    private JButton btnBack, btnShelve;
    private JPanel lowerPanel;
    private String categoryDirectory = "resources/categories.txt";
    private String finishedNoteDirectory = "resources/notes.txt";
    private JButton btnCheckNaming;
    private JButton btnExitNaming;

    public ShelveScreen() {
        setLayout(new BorderLayout());

        ArrayList<String> categoryList = getListInFile(categoryDirectory);

        JPanel upperButtons = new JPanel();

        lowerPanel = new JPanel(new GridLayout(0,categoryList.size()-1));

        btnBack = new JButton("<");
        btnBack.addActionListener(this);
        btnShelve = new JButton("Add Another Shelve");
        btnShelve.addActionListener(this);

        upperButtons.add(btnBack);
        upperButtons.add(btnShelve);

        for( int i = 0; i <= categoryList.size() - 1; i++ ) {
            lowerPanel.add(shelvePanel(categoryList.get(i)));
        }

        add(lowerPanel, BorderLayout.CENTER);
        add(upperButtons, BorderLayout.NORTH);
    }


    // does not work - need a JTextArea

    public JPanel shelvePanel(String labelName) {
        JPanel shelvePanel = new JPanel();
        shelvePanel.setLayout(null);

        JLabel shelveLabel = new JLabel();
        shelveLabel.setText(labelName);

        JTextArea noteText = new JTextArea();
        noteText.setEditable(false);
        noteText.setCaretColor(noteText.getBackground());
        
        
        noteText.setText("Finished\nUnFinshed");
        // 

        shelveLabel.setOpaque(true);
        shelveLabel.setBackground(Color.white);
        shelveLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        shelveLabel.setBounds(0, 0, labelName.length() * 8, 20);
        shelvePanel.add(shelveLabel);

        noteText.setBounds(20, 20, 200, 200);
        shelvePanel.add(noteText);

        return shelvePanel;
    }

    public ArrayList<String> getListInFile(String fileName) {  
            ArrayList<String> categoryList = new ArrayList<String>();

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    categoryList.add(line);
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            } 
            return categoryList;
    }

    public void addShelveName() {

    }

    @Override
    public void actionPerformed(ActionEvent e){

        if (e.getSource() == btnBack){
            MainFrame.getInstance().showFirstWindow();
        }
        if (e.getSource() == btnShelve) {
            lowerPanel.removeAll();
            getListInFile(categoryDirectory);

            lowerPanel.setLayout(new FlowLayout());
            JTextField categoryText = new JTextField(15);
            btnCheckNaming = new JButton("/");
            btnCheckNaming.addActionListener(this);
            btnExitNaming = new JButton("X");        
            btnExitNaming.addActionListener(this);

            lowerPanel.add(categoryText);
            lowerPanel.add(btnCheckNaming);
            lowerPanel.add(btnExitNaming);
            
            revalidate(); repaint();
        }

        if (e.getSource() == btnCheckNaming) {
            lowerPanel.removeAll();            
            ArrayList<String> categoryList = getListInFile(categoryDirectory);
            lowerPanel = new JPanel(new GridLayout(0,categoryList.size()-1));
            
            MainFrame.getInstance().showShelveScreen();

        }
        if (e.getSource() == btnExitNaming) {
            lowerPanel.removeAll();

            ArrayList<String> categoryList = getListInFile(categoryDirectory);
            lowerPanel = new JPanel(new GridLayout(0,categoryList.size()-1));

            MainFrame.getInstance().showShelveScreen();
        }

    }
}
