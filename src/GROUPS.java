
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
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
public class GROUPS extends javax.swing.JFrame {
Connection conn=null;
ResultSet rs=null;
 PreparedStatement pst=null;
Statement stmt;
String Group_Name,activity="",Location,Date;
int ro;
Date d;
    /**
     * Creates new form GROUPS
     */
    public GROUPS() {
        initComponents();
          conn=Dbconnection.Dbconnection();
          Date d=new Date();
          SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
          jDateChoosergroups.setDate(d);
           showtablegroups();
             jButton5.setVisible(false);

    }
    private void showtablegroups(){
        try{
          String sql="select*from groups";
          pst=conn.prepareStatement(sql);
          rs=pst.executeQuery();
          jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void updaatee(){
        int ro=jTable1.getSelectedRow();
        String va=jTable1.getValueAt(jTable1.getSelectedRow(),3).toString();
        try{
       int p =JOptionPane.showConfirmDialog(null, "Are you sure you want to save these changes?","UpdateWarning",JOptionPane.YES_NO_OPTION);
            if(p==0){
                String s1=txtgroupname.getText();
                String s2=txtlocation.getText();
                String s3= ((JTextField)jDateChoosergroups.getDateEditor().getUiComponent()).getText();
               String sql="update groups set Group_Name='"+s1+"',Location='"+s2+"',Date='"+s3+"'where Rec_Id='"+Integer.parseInt(va)+"'";
              pst=conn.prepareStatement(sql);
              pst.execute();
              showtablegroups();
          
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void enter(){
        try{
             Group_Name=txtgroupname.getText();
             Location=txtlocation.getText();
            String d=((JTextField)jDateChoosergroups.getDateEditor().getUiComponent()).getText();      
                  if(null==activity){
              JOptionPane.showMessageDialog(null,"enter a record");
          }
          else{
              switch(activity){
                  case"":
                            String insert="insert into `groups`(`Group_Name`,`Location`,`Date`)values(?,?,?)";
                           pst=conn.prepareStatement(insert);
                           pst.setString(1, Group_Name);
                            pst.setString(2, Location);
                           pst.setString(3, d);
                            pst.execute();
                            showtablegroups();
                            JOptionPane.showMessageDialog(null,"row inserted");
                      break;
                      case"update":
                       int ro=jTable1.getSelectedRow();
                        String va=jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString();
                        String update="update  `groups`set`Group_Name`='"+Group_Name+"',`Location`='"+Location+"',`Date`='"+d+"' where Rec_Id='"+Integer.parseInt(va)+"'";
                      pst=conn.prepareStatement(update);
                        pst.setString(1, Group_Name);
                        pst.setString(2, Location);
                        pst.setString(3,d);
                           pst.execute();
                        showtablegroups();
                        JOptionPane.showMessageDialog(null,"row updated");    
                            break;
                           
                             }
              pst.execute();
          }
          
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }   
    private void insertorupdate(){
     
        try{
              stmt = (Statement) conn.createStatement();
              Group_Name=txtgroupname.getText();
              Location=txtlocation.getText();
        String d=((JTextField)jDateChoosergroups.getDateEditor().getUiComponent()).getText();      
            if(null==activity){
           JOptionPane.showMessageDialog(null,"you've nt entered any record yet");
            }
            else{
                switch(activity){
                    case"":
                String insert="insert into `groups`(`Group_Name`,`Location`,`Date`)values('"+Group_Name+"','"+Location+"','"+d+"')";
               stmt.executeUpdate(insert);
                showtablegroups();
                JOptionPane.showMessageDialog(null,"row inserted"); 
              break;
                    
                    case"k":
         int r=jTable1.getSelectedRow();
         String va=jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString();
         String update="update  `groups`set`Group_Name`='"+Group_Name+"',`Location`='"+Location+"',`Date`='"+d+"' where Rec_Id='"+Integer.parseInt(va)+"'";
           stmt.executeUpdate(update);
           showtablegroups();
           JOptionPane.showMessageDialog(null,"row updated");
           break;
                }
            
            }
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void enteer(){
        try{
             stmt = (Statement) conn.createStatement();
             Group_Name=txtgroupname.getText();
            Location=txtlocation.getText();
//          da=jDateChoosergroups.getDateFormatString(); 
            String d=((JTextField)jDateChoosergroups.getDateEditor().getUiComponent()).getText();      
         String insert="insert into `groups`(`Group_Name`,`Location`,`Date`)values('"+Group_Name+"','"+Location+"','"+d+"')";
           stmt.executeUpdate(insert);
           JOptionPane.showMessageDialog(null,"row inserted");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
     private void uppdate(){
         int r=jTable1.getSelectedRow();
         String va=jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString();
        try{
             stmt = (Statement) conn.createStatement();
             Group_Name=txtgroupname.getText();
            Location=txtlocation.getText();
//          da=jDateChoosergroups.getDateFormatString(); 
            String d=((JTextField)jDateChoosergroups.getDateEditor().getUiComponent()).getText();      
         String update="update  `groups`set`Group_Name`='"+Group_Name+"',`Location`='"+Location+"',`Date`='"+d+"' where Rec_Id='"+Integer.parseInt(va)+"'";
           stmt.executeUpdate(update);
           showtablegroups();
           JOptionPane.showMessageDialog(null,"row updated");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    
     private void saveorupdate(){
        try{
            String insert="insert into `groups`(`Group_Name`,`Location`,`Date`)values(?,?,?)";
             pst=conn.prepareStatement(insert);
             pst.setString(1, txtgroupname.getText());
              pst.setString(2, txtlocation.getText());
              pst.setString(3, ((JTextField)jDateChoosergroups.getDateEditor().getUiComponent()).getText());
              pst.execute();
              showtablegroups();
              JOptionPane.showMessageDialog(null,"row inserted");
             
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
     }
     private void delete(){
           int row=jTable1.getSelectedRow();
//     JOptionPane.showMessageDialog(null,row);
        String ro=jTable1.getModel().getValueAt(row, 3).toString();
        int confirmdelete=JOptionPane.showConfirmDialog(null, "Do you really want to delete this row?","Delete",JOptionPane.YES_NO_OPTION);
        if(confirmdelete==0){
            String delete="delete from groups where Rec_Id='"+Integer.parseInt(ro)+"'";
      try{
          pst=conn.prepareStatement(delete);
          pst.execute();
          showtablegroups();
//                  JOptionPane.showMessageDialog(null,"deleted successfully");
      }catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);
      }
         }  
        else {
            JOptionPane.showMessageDialog(null,"you havent selected any row");
        }
     }
     private void filter(){
         try{
             
         }catch(Exception ex){
             JOptionPane.showMessageDialog(null,ex);
         }
     }
     private void reset(){
           try{
            txtgroupname.setText(null);
            txtlocation.setText(null);
            jDateChoosergroups.setDate(null);
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
     }
     private void mouseclicked(){
         try{
             int ro=jTable1.getSelectedRow();
             String va=jTable1.getModel().getValueAt(ro,3).toString();
             String select="select*from groups where Rec_Id='"+va+"'";
             pst=conn.prepareStatement(select);
           rs=pst.executeQuery();
           if(rs.next()){
               String add1=rs.getString("Group_Name");
               txtgroupname.setText(add1);
               String add2=rs.getString("Location");
               txtlocation.setText(add2);
               Date add3=rs.getDate("Date");
               jDateChoosergroups.setDate(add3);
                setedisabled();
           }
         }catch(Exception ex){
             JOptionPane.showMessageDialog(null,ex);
         }
     }
     private void setedisabled(){
         try{
             txtgroupname.setEditable(false);
             txtlocation.setEditable(false);
             jDateChoosergroups.setEnabled(false);
             
         }catch(Exception ex){
             JOptionPane.showMessageDialog(null,ex);
         }
     }
     private void setenabled(){
         try{
             txtgroupname.setEditable(true);
             txtlocation.setEditable(true);
             jDateChoosergroups.setEnabled(true);
         }catch(Exception ex){
             JOptionPane.showMessageDialog(null,ex);
         }
     }
     private void update(){
         try{
             
             int ro=jTable1.getSelectedRow();
             String va=jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString();
            String va1=txtgroupname.getText();
            String va2=txtlocation.getText();
//            Date d=jDateChoosergroups.getDate();
//            rs.setDate(d);
             String sql="update groups set Group_Name='"+va1+"',Location='"+va2+"'where Rec_Id='"+Integer.parseInt(va)+"'";
           pst=conn.prepareStatement(sql);
           JOptionPane.showMessageDialog(null,"record updated");
//             System.out.println("record updated" +ro);
             pst.execute();
             showtablegroups();
         }catch(Exception ex){
             JOptionPane.showMessageDialog(null,ex);
         }
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtgroupname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtlocation = new javax.swing.JTextField();
        jDateChoosergroups = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setDoubleBuffered(false);

        jPanel2.setBackground(new java.awt.Color(255, 204, 255));

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel1.setText("Group_Name");

        jLabel2.setText("Location");

        jDateChoosergroups.setDateFormatString("yyyy-MM-dd");

        jLabel3.setText("Date");

        jLabel4.setText("Search");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel3)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jDateChoosergroups, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(txtlocation, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtgroupname, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtgroupname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtlocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jDateChoosergroups, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButton2.setBackground(new java.awt.Color(255, 102, 0));
        jButton2.setText("Edit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 102, 0));
        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Reset");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("Save");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton5))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(jButton2)
                .addGap(57, 57, 57)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try{
            
//             saveorupdate();
//            enter();
//            insertorupdate();
            enteer();
              showtablegroups();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
       
//       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try{
            reset();
            jButton1.setVisible(true);
            jButton5.setVisible(false);
//            jButton5.setVisible(false);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try{
//            uppdate();
//            update();
//            editorupdate();
             setenabled();
//            showtablegroups();
        }catch(Exception ex){
//            JOptionPane.showMessageDialog(null,ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try{
            delete();
            showtablegroups();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
         
        int ro=jTable1.getSelectedRow();
        String va=(jTable1.getModel().getValueAt(ro,3).toString());
        String sql="select*from groups where Rec_Id='"+Integer.parseInt(va)+"'";
        try{
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                String gro=rs.getString("Group_Name");
                txtgroupname.setText(gro);
                String loca=rs.getString("Location");
                txtlocation.setText(loca); 
                Date d=rs.getDate("Date");
                jDateChoosergroups.setDate(d);
                setedisabled();
                jButton1.setVisible(false);
                jButton5.setVisible(true);
//              jButton1.setEnabled(false);
//                jButton5.setEnabled(true);
            } 
            
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }//GEN-LAST:event_jTable1MouseClicked
    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        DefaultTableModel table=(DefaultTableModel)jTable1.getModel();
        String search=jTextField1.getText();
        TableRowSorter<DefaultTableModel>tr= new TableRowSorter<DefaultTableModel>(table);
        jTable1.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        try{
            uppdate();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed
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
            java.util.logging.Logger.getLogger(GROUPS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GROUPS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GROUPS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GROUPS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GROUPS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private com.toedter.calendar.JDateChooser jDateChoosergroups;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField txtgroupname;
    private javax.swing.JTextField txtlocation;
    // End of variables declaration//GEN-END:variables
}
