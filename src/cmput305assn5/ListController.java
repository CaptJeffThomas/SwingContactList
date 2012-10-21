// Jeff Thomas
// CMPUT 305 Assn 5 - Swing and You!

package cmput305assn5;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;


public class ListController {
    
    private List<Contact> contactList;
    private ListGUI view;
    private JTable contactTable;
    private DefaultTableModel tableModel;
    private Contact newContact;
    int extreme = 0;
    
    
    public ListController(ListGUI gui){
        
        contactList = new ArrayList();
        view = gui;
        contactTable = this.view.getCourseTable();
        
        view.addNewButtonListener(new NewListener());
        view.addDeleteButtonListener(new DeleteListener());
        view.addExtremeButtonListener(new ExtremeListener());
        
        initTableModel();
        initListSelectionModel();
        
    }
    
      //creates our local table model
     private void initTableModel() {
         //disables direct editing of cells within the table.
        tableModel = new DefaultTableModel(){
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
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
           if(extreme == 1){
                extremeSpawn();
           }
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
            if(extreme == 1){
                extremeSpawn();
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
                    detailedDisplay(contactList.get(lsm.getMinSelectionIndex()));
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
            if(extreme == 1){
                extremeSpawn();
           }
            
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
                
                //perhaps not the most elegant solution to having mandatory fields
                String email, fName, lName;
                fName = menu.getFirstNameField();
                lName = menu.getLastNameField();
                email = menu.getEmailField(); 
                if( !email.equals("")  && !fName.equals("") && !lName.equals("")){
                    int sortedPosition = lastNameSort(lName);
                    contactList.add(sortedPosition, new Contact(fName, lName, email, menu.getHomePhone(), menu.getWorkPhone()));
                    Object[] rowData = {fName + " " + lName, email};
                    tableModel.insertRow(sortedPosition, rowData);
                    menu.setVisible(false);
                    if(extreme == 1){
                        extremeSpawn();
                    }
                }
                //required fields need to be filled.  pop-up a warning to user.
                else{     
                     JOptionPane.showMessageDialog(new JFrame(), " At least a name and email must be entered. ", "Error Message",JOptionPane.ERROR_MESSAGE);
                }
        }
    }

    //returns the alphabetically sorted index where the parameter belongs.  Ran each time a new contact is added and finishes in constant runtime.
    private int lastNameSort(String name){
        
        int home;
        for(home = 0; home < contactList.size(); home++){
            //if the last name at index matches our parameter name, add it after.
            if(name.compareTo(contactList.get(home).getLastName()) == 0){
                return home + 1;
            }
            //if the last name at index is greater than our parameter name, we're home.
            if(name.compareTo(contactList.get(home).getLastName()) < 0){
                return home;
            }
            //if the last name at index is less than our parameter name, we can ignore that case as our loop will push us ahead
        }
        
        //if we have not returned a value yet, home is the largest value and belongs at the end.
        return home;
        
    }
    //fills the detailed tab with selected contact info
    private void detailedDisplay(Contact c){
        
        String eol = System.getProperty("line.separator");   //used to counter OS bias for newline character
        String details = "First Name: "+ c.getFirstName() + eol + "Last Name: " + c.getLastName() + eol
                + "Email: " + c.getEmail()+ eol;
        if( !c.getHomePhone().equals("")){
            details +=  "Home Phone: " + c.getHomePhone() + eol; 
        }
        
        if (!c.getWorkPhone().equals("")){
            details += "Work Phone: " + c.getWorkPhone();
        }
        this.view.setDetailedText(details);
        
    }
    
      
    //HARD TO LISTEN OVER ALL THIS EXTREME!  GOOD THING WE HAVE THIS! 
    private class ExtremeListener implements ItemListener{
          
        @Override 
        public void itemStateChanged(ItemEvent e){
             if(e.getStateChange() == ItemEvent.SELECTED){
                UIManager.put("OptionPane.background", randColor());
                UIManager.put("Panel.background", randColor());
                view.getContentPane().setBackground(Color.red);
                view.setTitle("EXTREME Address BOOK");
                extreme = 1;
            }
            else{
                UIManager.put("OptionPane.background", new Color(240,240,240));
                UIManager.put("Panel.background", new Color(240,240,240));
                view.getContentPane().setBackground(new Color(240,240,240));
                view.setTitle("Address Book");
                extreme = 0;
            } 
        }
        
    }
    
    //when extreme mode is turned on, all buttons generate windows with random positions, msgs, and colors
    private void extremeSpawn(){
   
        //ImageIcon icon = new ImageIcon("neon-snowboard.jpg");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Random r = new Random(System.currentTimeMillis());
        int x =  r.nextInt((int)screenSize.getWidth());
        int y = r.nextInt((int)screenSize.getHeight());
        JOptionPane johnny = new JOptionPane();
        johnny.setSize(500, 500);
        johnny.setLocation(x,y);
        UIManager.put("OptionPane.background", randColor());
        UIManager.put("Panel.background", randColor());
       
        //johnny.setIcon(icon);
        //johnny.setVisible(true);
        JOptionPane.showMessageDialog(johnny, randMsg(), randMsg(), JOptionPane.INFORMATION_MESSAGE, new ImageIcon("neon-snowboard.gif"));
    }
    
    private Color randColor(){
        Color[] colors = {Color.CYAN,Color.MAGENTA,Color.PINK,Color.RED,Color.GREEN,Color.YELLOW};
        Random r = new Random();
        return colors[r.nextInt(colors.length)];
    }
    
    private String randMsg(){
        String[] msgs = {"Alpha-numeric!", "ERROR ERROR THE PARTY IS TOO HARD.  ", "Off the hook!", "Mad props son!", "I'd bisect that angle!", "Not cool...PSYCHE!  Radtastic!",
        "Word up g.", "Whoop dere it is!", "Wu Tang Clan Ain't Nothing To Trifle With In Test of Wits  ", "Diversify your bonds.", "Far out!", "Slam dunk the funk!", " SLAMMA JAMMA",
        "BOOM SHAKALAKA!", "Vonnegut is much more fatalistic than Heller, though his humour alleviates any sense of dread.  "};
        Random r = new Random();
        return msgs[r.nextInt(msgs.length)];
    }
    
   
    
    
}