
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class StickyNote extends JPanel implements ActionListener{
    private JTextArea noteTextArea;
    private JButton btnCheck, btnEdit, btnDelete;
    private int _id; 
    private String _category;
    private String notesDirectory = "resources/notes.txt";
    private String finishedNoteDirectory = "resources/finished-notes.txt";

    public StickyNote(String category, String noted, int id) {
        _id = id;
        _category = category;

        setLayout(null);

        JLabel shelveName = new JLabel(category + id);
        

        JPanel rightButton = new JPanel(new GridLayout(0,1));
        
        noteTextArea = new JTextArea();
        noteTextArea.setLineWrap(true);
        noteTextArea.setWrapStyleWord(true);
        noteTextArea.setFont(new Font("Verdana", Font.PLAIN, 16));
        
        noteTextArea.setText(noted.replace('|', '\n'));

        JScrollPane scrollPane = new JScrollPane(noteTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        noteTextArea.setEnabled(false);

        btnEdit = new JButton("e");
        btnEdit.addActionListener(this);
        btnCheck = new JButton("/");
        btnCheck.addActionListener(this);
        btnDelete = new JButton("x");
        btnDelete.addActionListener(this);

        shelveName.setBounds(0,0, category.length()*15, 25);
        add(shelveName);

        scrollPane.setBounds(0, 25, 205, 175);
        add(scrollPane);

        rightButton.add(btnEdit);
    
        rightButton.add(btnCheck);
        rightButton.add(btnDelete);

        rightButton.setBounds(205, 100, 45, 100);
        add(rightButton); 

        setPreferredSize(new Dimension(250,200));
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBorder(null);
        
    }

    
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == btnEdit && !(noteTextArea.isEnabled())){
            noteTextArea.setEnabled(true);
        }
        else if (e.getSource() == btnEdit && (noteTextArea.isEnabled())){
            String noteText = noteTextArea.getText().replace('\n', '|');
        
            ArrayList<String> noteList = new ShelveScreen().getListInFile(notesDirectory);
            
            if (noteList.size() == _id){
                noteList.add(_id, _category + "|" + noteText);
            } else { 
                noteList.set(_id, _category + "|" + noteText);
            }

            try (FileWriter noteWriter = new FileWriter(notesDirectory)) {
                for (int i=0; i<=noteList.size()-1;i++){
                    noteWriter.write(noteList.get(i) + "\n");          
                }
            } catch (IOException e2) {
                System.out.println("Error reading file: " + e2.getMessage());
            }

            noteTextArea.setEnabled(false);
        }
        
        if (e.getSource() == btnCheck) {
            String noteText = noteTextArea.getText().replace('\n', '|');
        
            ArrayList<String> noteList = new ShelveScreen().getListInFile(notesDirectory);
            // remove in the notes.txt
            noteList.remove(_id);

            try (FileWriter noteWriter = new FileWriter(notesDirectory)) {
                for (int i=0; i<=noteList.size()-1;i++){
                    noteWriter.write(noteList.get(i) + "\n");          
                }
            } catch (IOException e2) {
                System.out.println("Error reading file: " + e2.getMessage());
            }

            //write in finish.txt

            try (FileWriter noteWriter = new FileWriter(finishedNoteDirectory,true)) {
                noteWriter.write(_category + '|' + noteText + '\n');          
            } catch (IOException e2) {
                System.out.println("Error reading file: " + e2.getMessage());
            }
            
            MainFrame.getInstance().showStickyNoteScreen();
        }

        if (e.getSource() == btnDelete) {
            ArrayList<String> noteList = new ShelveScreen().getListInFile(notesDirectory);
            noteList.remove(_id);

            try (FileWriter noteWriter = new FileWriter(notesDirectory)) {
                for (int i=0; i<=noteList.size()-1;i++){
                    noteWriter.write(noteList.get(i) + "\n");          
                }
            } catch (IOException e2) {
                System.out.println("Error reading file: " + e2.getMessage());
            }

            MainFrame.getInstance().showStickyNoteScreen();
        }
    }
}
