//Jeff Thomas
//CMPUT 305 Assn 5 - Swing & You!

package cmput305assn5;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.TableModel;


public class ListGUI extends javax.swing.JFrame {

    public ListGUI() {
        initComponents();
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
    
    public JTable getCourseTable() {
        return courseTable;
    }
     
    //fills our bottom feld with detailed information about the contact selected
    public void setDetailedText(String details){
         detailsTextField.setText(details);
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        detailsTextField = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        courseTable = new javax.swing.JTable();
        newButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        deleteButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Address Book");
        setResizable(false);

        jSplitPane1.setDividerLocation(270);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        detailsTextField.setColumns(20);
        detailsTextField.setRows(5);
        jScrollPane1.setViewportView(detailsTextField);

        jSplitPane1.setBottomComponent(jScrollPane1);

        courseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        courseTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(courseTable);
        courseTable.getColumnModel().getColumn(0).setResizable(false);
        courseTable.getColumnModel().getColumn(1).setResizable(false);

        jSplitPane1.setTopComponent(jScrollPane2);

        newButton.setText("New Contact");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        deleteButton.setText("Delete");
        deleteButton.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(newButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(newButton)
                        .addComponent(deleteButton, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable courseTable;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextArea detailsTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JButton newButton;
    // End of variables declaration//GEN-END:variables
}
