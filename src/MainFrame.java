
import javax.swing.JFrame;


public class MainFrame extends JFrame{
    private static MainFrame instance;
    public int state = 0;

    private MainFrame() {
        setTitle("STICKY NOTES");
        setSize(640, 700);
        setLocation(900, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        showFirstWindow();
    }

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }

    public void showFirstWindow() {
        setContentPane(new FirstWindow());
        revalidate();
        repaint();
    }

    public void showShelveScreen() {
        setContentPane(new ShelveScreen());
        revalidate();
        repaint();
    }

    public void showSettingScreen() {
        setContentPane(new SettingScreen());
        revalidate();
        repaint();
    }

    public void showStickyNoteScreen() {
        setContentPane(new StickyNoteScreen());
        revalidate();
        repaint();
    }
}
