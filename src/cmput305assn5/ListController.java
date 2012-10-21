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
    private JTable contactTable;
    private DefaultTableModel tableModel;
    private Contact newContact;
    
    
    public ListController(ListGUI gui){
        
        contactList = new ArrayList();
        view = gui;
        contactTable = this.view.getCourseTable();
        
        view.addNewButtonListener(new NewListener());
        view.addDeleteButtonListener(new DeleteListener());
        
        initTableModel();
        initListSelectionModel();
        
    }
    
      //creates our local table model
     private void initTableModel() {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Email");
        contactTable.setModel(tableModel);        
    }
     
      private void initListSelectionModel() {
        ListSelectionModel lsm = contactTable.getSelectionModel();
        lsm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsm.addListSelectionListener(new CourseTableListener());
    }
    
    //pops up New Contact menu
    private class NewListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           ContactMenu contactMenu = new ContactMenu();
           contactMenu.addCancelButtonListener(new CancelButtonListener(contactMenu));
           contactMenu.addOKButtonListener(new OkButtonListener(contactMenu));
           contactMenu.setVisible(true);
        }
    }
    
    //removes selected contact from List and clears contact details
    private class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ListSelectionModel lsm = contactTable.getSelectionModel();
            if (!lsm.isSelectionEmpty()) {
                contactList.remove(lsm.getMinSelectionIndex());
                tableModel.removeRow(lsm.getMinSelectionIndex());
                view.setDetailedText("");
                view.setEnabledDeleteButton(false);
            }
        }
    }
    
    //when contact is selected show detailed info and enable delete funtionality
    private class CourseTableListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel) e.getSource();
            if (!e.getValueIsAdjusting()) {
                //if we have selected a contact, enable delete and display details
                if(!lsm.isSelectionEmpty()){
                    view.setEnabledDeleteButton(true);
                    detailedDisplay(contactList.get(e.getFirstIndex()));
                }
            }
        }
    }
    
    //closes new contact menu
    private class CancelButtonListener implements ActionListener {

        private ContactMenu menu; //holds the reference to the created ContactMenu. needed to close frame.
        
        public CancelButtonListener(ContactMenu menu){
            this.menu = menu;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            menu.setVisible(false);
            
        }
        
    }
      
    //verifies and adds a new contact to the list/table
    private class OkButtonListener implements ActionListener {
        
        private ContactMenu menu; //holds the reference to the created ContactMenu. needed to close frame.
        
        private OkButtonListener(ContactMenu menu){
            this.menu = menu;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            menu.setVisible(false);
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