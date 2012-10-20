//Jeff Thomas
//CMPUT 305 Assn 5 - Swing & You!

package cmput305assn5;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class ListGUI extends javax.swing.JFrame {

    public ListGUI() {
        initComponents();
        newButton.setEnabled(true);
    }
    
    public void setContactTable(TableModel tm) {
        contactTable.setModel(tm);
    }
    

    public void addNewButtonListener(ActionListener al) {
        newButton.addActionListener(al);
    }

    public void addDeleteButtonListener(ActionListener al) {
        deleteButton.addActionListener(al);
     }
    
    public void setEnabledDeleteButton(boolean status) {
        deleteButton.setEnabled(status);
    }
     
    //fills our top table with name and email values
    public void setTable(){
         
    }
     
    //fills our bottom feld with detailed information about the contact selected
    public void setDetailedText(String details){
         detailTextField.setText(details);
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
