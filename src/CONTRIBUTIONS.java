
import com.mysql.jdbc.Statement;
import com.sun.imageio.plugins.png.RowFilter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ndirituedwin
 */
public class CONTRIBUTIONS extends javax.swing.JFrame {
  Statement stmt;
  PreparedStatement pst;
  Connection conn;
  ResultSet rs;
  String Member_Id,type,description,amount;
    /**
     * Creates new form CONTRIBUTIONS
     */
    public CONTRIBUTIONS() {
        initComponents();
        conn=Dbconnection.Dbconnection(); 
          Date d=new Date();
          SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
           jDateChoosercontributions.setDate(d);
           showtablecontributions();
           cmbmemerid();
    }
     private void showtablecontributions(){
        try{
//            String sql="SELECT membership.Name,contributions.Member_Id,contributions.Type,contributions.Description,contributions.Amount,contributions.Date,contributions.Rec_Id FROM membership,contributions WHERE membership.Rec_Id=contributions.Member_Id";
//            String sql="select*from contributions";
            String sql="SELECT (CONCAT(membership.Name,' , ',contributions.Member_Id))member,contributions.Type,contributions.Description,contributions.Amount,contributions.Date,contributions.Rec_Id FROM membership,contributions WHERE membership.Rec_Id=contributions.Member_Id ";
//           String sql="select*from ccontributions";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
     private void enter(){
         try{
   String enter="insert into ccontributions(Member_and_member_id,Type,Description,Amount,Date)values(?,?,?,?,?)";
// String enter="insert into contributions (Member_Id,Type,Description,Amount,Date) values(?,?,?,?,?)";
     pst=conn.prepareStatement(enter);
     String cmbmem=cmbmemberid.getSelectedItem().toString();
     pst.setString(1, cmbmem); 
     String cmbty=cmbtype.getSelectedItem().toString();
     pst.setString(2, cmbty);
     pst.setString(3,txtdescription.getText());
     pst.setString(4, txtmount.getText());
     pst.setString(5, ((JTextField)jDateChoosercontributions.getDateEditor().getUiComponent()).getText());
     pst.execute();
         }catch(Exception ex){
             JOptionPane.showMessageDialog(null,ex);
         }
     }
      private void cmbmemerid(){
          try{
////              String select="Select CONCAT(Name,'',Member_Id)AS ui from contributions";
////           String select="select concat(Name,'',Member_Id)from membership,contributions where membership.Rec_Id=contributions.Member_Id";
////              String select="select concat(Name,'',Member_Id)from membership,contributions where membership.Rec_Id=contributions.Member_Id";
////              String select="select membership.Name,contributions.Member_Id from membership,contributions where Membership.Rec_Id=contributions.Member_Id";
/////             String select="SELECT CONCAT(membership.Name,',',contributions.Member_Id)FROM membership,contributions WHERE membership.Rec_Id=contributions.Member_Id";
//       String select="SELECT CONCAT(kontributions.Name,'  ,  ',kontributions.Member_Id )AS member from kontributions order by Member_Id";    
              String select="select*From ccontributions";
                pst=conn.prepareStatement(select);
                pst.execute();
              rs=pst.executeQuery();
              while(rs.next()){
                 String name=rs.getString("member");
                  cmbmemberid.addItem(name);  
//                  String Member_Id=rs.getString("Member_Id");
//                  cmbmemberid.addItem(Member_Id);
              }
          }catch(Exception ex){
              JOptionPane.showMessageDialog(null,ex);
             
          }
          
          
          
          
          
//      try{
//          Statement stmt=(Statement)conn.createStatement();
//      String select="SELECT CONCAT(Name,',',Member_Id)FROM membership,contributions WHERE membership.Rec_Id=contributions.Member_Id";
////          String select="SELECT membership.Name,contributions.Member_Id FROM membership,contributions WHERE membership.Rec_Id=contributions.Member_Id";
////          String select="select Rec_Id from membership";
//          
//          ResultSet rs=stmt.executeQuery(select);
////          while(rs.next()){
//////              String n=rs.getString("Name");
//////              cmbmemberid.addItem(n);
////              String memberid=rs.getString("Member_Id");
////              cmbmemberid.addItem(memberid);
////          }
//
//          
//      }catch(Exception ex){
//          JOptionPane.showMessageDialog(null,ex);
//      }
 }
      private void reset(){
            try{
            cmbmemberid.setSelectedIndex(0);
            cmbtype.setSelectedIndex(0);
            txtdescription.setText("");
            txtmount.setText("");
            jDateChoosercontributions.setDate(null);
            showtablecontributions();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
      }
      private void delete(){
          try{
          int ro=jTable1.getSelectedRow();
        String va=jTable1.getModel().getValueAt(ro,5).toString();
        int confirmdelete=JOptionPane.showConfirmDialog(null, "Do you really want to delete this row?","Delete",JOptionPane.YES_NO_OPTION);
       String delete="delete from ccontributions where Rec_Id='"+Integer.parseInt(va)+"'";
        try{
            pst=conn.prepareStatement(delete);
            pst.execute();
            showtablecontributions();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
          }catch(Exception ex){
              JOptionPane.showMessageDialog(null,ex);
          }
      }
      private void updateoredit(){
          int ro=jTable1.getSelectedRow();
        String v=jTable1.getModel().getValueAt(ro,5).toString();
        JOptionPane.showMessageDialog(null,ro+"  "+v);
        try{  
            String value1=cmbmemberid.getSelectedItem().toString();
            String value2=cmbtype.getSelectedItem().toString();
            String value3=txtdescription.getText();
            String value4=txtmount.getText();
            String sql="update contributions set Description='"+value1+"',Amount='"+value2+"',Description='"+value3+"',Amount='"+value4+"' where Rec_Id='"+Integer.parseInt(v)+"'";
       pst=conn.prepareStatement(sql);
            pst.executeUpdate();
            pst.execute();
            JOptionPane.showMessageDialog(null,"updated");
            showtablecontributions();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }  
              
      }
      private void mouseclicked(){
          try{
              int ro=jTable1.getSelectedRow();
              String va=jTable1.getModel().getValueAt(ro,5).toString();
              String select="select*from ccontributions where Rec_Id='"+Integer.parseInt(va)+"'";
              pst=conn.prepareStatement(select);
              rs=pst.executeQuery();
              if(rs.next()){
                    String add1=rs.getString("member");
                  cmbmemberid.setSelectedItem(add1);
                  String add2=rs.getString("Type");
                  cmbtype.setSelectedItem(add2);
                  String add3=rs.getString("Description");
                  txtdescription.setText(add3);
                  String add4=rs.getString("Amount");
                  txtmount.setText(add4);
                  Date add5=rs.getDate("Date");
                  jDateChoosercontributions.setDate(add5);
              }
             
          }catch(Exception ex){
              JOptionPane.showMessageDialog(null,ex);
          }
      }
       private void filter(String query){
      DefaultTableModel dm = null;
         TableRowSorter<DefaultTableModel>tr=new TableRowSorter<>(dm);
         jTable1.setRowSorter(tr);
         tr.setRowFilter(javax.swing.RowFilter.regexFilter(query));
     }
     
/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jtextfieldsearch = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDateChoosercontributions = new com.toedter.calendar.JDateChooser();
        cmbmemberid = new javax.swing.JComboBox<String>();
        cmbtype = new javax.swing.JComboBox<String>();
        txtdescription = new javax.swing.JTextField();
        txtmount = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 102, 0));

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jPanel3.setBackground(new java.awt.Color(51, 255, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 255, 204), new java.awt.Color(204, 51, 0), new java.awt.Color(153, 0, 102), new java.awt.Color(153, 153, 255)));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jtextfieldsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtextfieldsearchActionPerformed(evt);
            }
        });
        jtextfieldsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtextfieldsearchKeyReleased(evt);
            }
        });

        jLabel6.setText("Search");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(37, 37, 37)
                        .addComponent(jtextfieldsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jtextfieldsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jPanel4.setBackground(new java.awt.Color(153, 255, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Member_Id");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel3.setText("Description");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 125, -1, -1));

        jLabel1.setText("Type");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel5.setText("Date");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 212, -1, -1));

        jLabel4.setText("Amount");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 167, -1, -1));

        jDateChoosercontributions.setDateFormatString("yyyy-MM-dd");
        jPanel4.add(jDateChoosercontributions, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 212, 206, -1));

        cmbmemberid.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "select memberid" }));
        cmbmemberid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbmemberidActionPerformed(evt);
            }
        });
        jPanel4.add(cmbmemberid, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 206, -1));

        cmbtype.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Registration fee", "Monthly contribution", "Penalty fee" }));
        jPanel4.add(cmbtype, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 206, -1));
        jPanel4.add(txtdescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 122, 206, -1));
        jPanel4.add(txtmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 164, 206, -1));

        jPanel5.setBackground(new java.awt.Color(255, 204, 153));

        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(29, 29, 29)
                .addComponent(jButton2)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 153, 0));

        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setText("Edit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addGap(60, 60, 60)
                .addComponent(jButton4)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 915, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 421, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbmemberidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbmemberidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbmemberidActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try{
            delete();
            showtablecontributions();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try{
            updateoredit();
            showtablecontributions();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        try{
            mouseclicked();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try{
            reset();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try{
            enter();
            showtablecontributions();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jtextfieldsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtextfieldsearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtextfieldsearchActionPerformed

    private void jtextfieldsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtextfieldsearchKeyReleased
        // TODO add your handling code here:
//        DefaultTableModel dm=(DefaultTableModel)
    }//GEN-LAST:event_jtextfieldsearchKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CONTRIBUTIONS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CONTRIBUTIONS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CONTRIBUTIONS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CONTRIBUTIONS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CONTRIBUTIONS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbmemberid;
    private javax.swing.JComboBox<String> cmbtype;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private com.toedter.calendar.JDateChooser jDateChoosercontributions;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jtextfieldsearch;
    private javax.swing.JTextField txtdescription;
    private javax.swing.JTextField txtmount;
    // End of variables declaration//GEN-END:variables
}
