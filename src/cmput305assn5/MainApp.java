// Jeff Thomas
// CMPUT 305 Assn 5 - Swing and You!

package cmput305assn5;


import javax.swing.SwingUtilities;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ListGUI gui = new ListGUI();
                ListController controller = new ListController(gui);
                gui.setVisible(true);
            }
        });
    }
}