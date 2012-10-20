// Jeff Thomas
// CMPUT 305 Assn 5 - Swing and You!

package cmput305assn5;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class ListController {
    
    private List<Contact> contactList;
    private ListGUI view;
    
    public ListController(ListGUI gui){
        
        contactList = new ArrayList();
        this.view = gui;
   
        this.view.addNewButtonListener(new NewListener());
        this.view.addDeleteButtonListener(new DeleteListener());
        
        
    }
    
    
    //pops up New Contact menu
    private class NewListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           
        }
    }
    
    //removes selected contact from List
    private class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           
        }
    }
    
    //fills the detailed tab with selected contact info
    private void detailedDisplay(Contact c){
        
        String eol = System.getProperty("line.separator");   //used to counter OS bias for newline character
        String details = "First Name: "+ c.getFirstName() + eol + "Last Name: " + c.getLastName() + eol
                + "Email: " + c.getEmail()+ eol;
        if( c.getHomePhone() != null){
            details +=  "Home Phone: " + c.getHomePhone() + eol; 
        }
        
        if (c.getWorkPhone() != null){
            details += "Work Phone: " + c.getWorkPhone();
        }
        this.view.setDetailedText(details);
        
    }
   
    
    
}