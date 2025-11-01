
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class StickyNoteScreen extends JPanel implements ActionListener {

    private JButton btnAdd, btnBack, btnShelves;
    private JPanel stickyNotePanel;
    private JComboBox<String> comboBox;
    private int _id;
    private String category;

    public StickyNoteScreen() {
        setLayout(new BorderLayout());

        stickyNotePanel = new JPanel();

        JPanel upperButtons = new JPanel();

        btnBack = new JButton("<");
        btnBack.addActionListener(this);
        btnShelves = new JButton("Shelves");
        btnShelves.addActionListener(this);
        btnAdd = new JButton("+");
        btnAdd.addActionListener(this);

        upperButtons.add(btnBack);
        upperButtons.add(btnShelves);
        upperButtons.add(btnAdd);

        add(upperButtons, BorderLayout.NORTH);
        add(stickyNotePanel, BorderLayout.CENTER);

        startUp();        
    }

    public void getCategory() {
        stickyNotePanel.removeAll();

        String[] items = { "Art", "Programming" };
        comboBox = new JComboBox<>(items);

        comboBox.addActionListener(this);
        comboBox.setBounds(35, 35, 50, 50);
        stickyNotePanel.add(comboBox);

        repaint();
        revalidate();
    }

    public void startUp() {
        // read notes.txt
        String filepath = "resources/notes.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            int id = 0; // id for the stiky notes

            while ((line = reader.readLine()) != null) {
                String shelveName, noted;

                shelveName = line.substring(0, line.indexOf('|'));
                noted = line.substring(line.indexOf('|') + 1);

                stickyNotePanel.add(new StickyNote(shelveName, noted, id));

                repaint();
                revalidate();

                id++;
                _id = id;
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // main buttons
        if (e.getSource() == btnBack) {

            MainFrame.getInstance().showFirstWindow();

        }

        if (e.getSource() == btnShelves) {

        }

        if (e.getSource() == btnAdd) {
            getCategory();
            System.out.println(_id);
        }
        if (e.getSource() == comboBox) {
            startUp(); // fix thbis
            
            category = (String) comboBox.getSelectedItem();

            stickyNotePanel.removeAll();

            stickyNotePanel.add(new StickyNote(category, "", _id));

            startUp();
            
            repaint();
            revalidate();
        }
    }
}
