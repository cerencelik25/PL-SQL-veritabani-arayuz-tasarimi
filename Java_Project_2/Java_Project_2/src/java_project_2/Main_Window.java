package java_project_2;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ceren
 */
public class Main_Window extends javax.swing.JFrame {
    PreparedStatement ps = null;
    
    public Main_Window() {
        initComponents();
        getConnection();
    }
    public Connection getConnection()
    {
        try{
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","CEREN","ceren2019");
            JOptionPane.showMessageDialog(null, "BAGLANDİ");
            return conn;
        }
        catch(SQLException ex){
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "BAGLANTI HATASI");
         }
        return null;
    }
    
    
    
    
    String ImgPath = null;
    
    public boolean checkInputs(){
        if(txt_name.getText()== null || txt_price.getText() == null){
            return false;
        }
        else{
            try{
                Float.parseFloat(txt_price.getText());
                return true;
            }
            catch(Exception ex) {
                return false;
            }
        }
    }
    
    //Resize Image
    public ImageIcon ResizeImage(String imagePath,byte[] pic){
        ImageIcon myImage = null;
        if(imagePath != null){
            myImage = new ImageIcon(imagePath);
        }
        else{
            myImage = new ImageIcon(pic);
        }
        Image img = myImage.getImage();
        Image img2 = img.getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(),Image.SCALE_SMOOTH );
        ImageIcon image = new ImageIcon(img2);
        return image;
    }
    // Verileri tablosa göstertme
    public ArrayList<Product> getProductList(){
        ArrayList <Product> productList = new ArrayList<Product>();
        String sql = "SELECT * FROM SATIS";
        Statement st;
        ResultSet rs;
         try {    
         try {
               Class.forName("oracle.jdbc.driver.OracleDriver");
           } catch (ClassNotFoundException ex) {
               Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
           }      
   Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "CEREN","ceren2019");
   st = conn.createStatement();
   rs= st.executeQuery(sql);
   Product product;
   while(rs.next()){
   product = new Product(rs.getInt("id"),rs.getString("name"),Float.parseFloat(rs.getString("price")),rs.getBytes("image"));
   productList.add(product);
  }
        } catch (SQLException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
         return productList;
    }
    public void Show_Product_In_JTable(){
        ArrayList<Product> list = getProductList();
        DefaultTableModel model = (DefaultTableModel)JTable_Products.getModel();       
        Object[] row = new Object[3];
        for(int i =0 ; i<list.size();i++){
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getName();
            row[2] = list.get(i).getPrice();
            model.addRow(row);}
    }
        public void ShowItem(int index){
            txt_id.setText(Integer.toString(getProductList().get(index).getId()));
            txt_name.setText(getProductList().get(index).getName());
            txt_price.setText(Float.toString(getProductList().get(index).getPrice()));
            lbl_image.setIcon(ResizeImage(null, getProductList().get(index).getPicture()));
        }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_name = new javax.swing.JTextField();
        txt_price = new javax.swing.JTextField();
        lbl_image = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_Products = new javax.swing.JTable();
        Btn_ınsert = new javax.swing.JButton();
        Btn_update = new javax.swing.JButton();
        BTN_DELETE_ = new javax.swing.JButton();
        Btn_First = new javax.swing.JButton();
        Btn_Next = new javax.swing.JButton();
        Btn_Previous = new javax.swing.JButton();
        Btn_Last = new javax.swing.JButton();
        Btn_Choose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.OverlayLayout(getContentPane()));

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("ID:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("NAME:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("PRICE:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("IMAGE:");

        txt_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nameActionPerformed(evt);
            }
        });

        lbl_image.setBackground(new java.awt.Color(255, 255, 255));
        lbl_image.setOpaque(true);

        JTable_Products.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NAME", "PRICE"
            }
        ));
        JTable_Products.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_ProductsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTable_Products);

        Btn_ınsert.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_ınsert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_project_2/insert.png"))); // NOI18N
        Btn_ınsert.setText("Insert");
        Btn_ınsert.setIconTextGap(10);
        Btn_ınsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ınsertActionPerformed(evt);
            }
        });

        Btn_update.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_project_2/update.png"))); // NOI18N
        Btn_update.setText("Update");
        Btn_update.setIconTextGap(10);
        Btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_updateActionPerformed(evt);
            }
        });

        BTN_DELETE_.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BTN_DELETE_.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_project_2/delete.jpg"))); // NOI18N
        BTN_DELETE_.setText("Delete");
        BTN_DELETE_.setIconTextGap(10);
        BTN_DELETE_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_DELETE_ActionPerformed(evt);
            }
        });

        Btn_First.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_First.setIcon(new javax.swing.ImageIcon("C:\\Users\\ceren\\OneDrive\\Masaüstü\\resim\\first.jpg")); // NOI18N
        Btn_First.setText("First");
        Btn_First.setToolTipText("");
        Btn_First.setIconTextGap(15);
        Btn_First.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_FirstActionPerformed(evt);
            }
        });

        Btn_Next.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_Next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_project_2/next.jpg"))); // NOI18N
        Btn_Next.setText("Next");
        Btn_Next.setIconTextGap(10);
        Btn_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_NextActionPerformed(evt);
            }
        });

        Btn_Previous.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_Previous.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_project_2/previous.jpg"))); // NOI18N
        Btn_Previous.setText("Previous");
        Btn_Previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_PreviousActionPerformed(evt);
            }
        });

        Btn_Last.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_Last.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_project_2/last.jpg"))); // NOI18N
        Btn_Last.setText("Last");
        Btn_Last.setIconTextGap(10);
        Btn_Last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_LastActionPerformed(evt);
            }
        });

        Btn_Choose.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_Choose.setText("Choose Image");
        Btn_Choose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ChooseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Btn_ınsert, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BTN_DELETE_, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Btn_First, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Btn_Next, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Btn_Previous, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(Btn_Last, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4)
                        .addComponent(jLabel3)))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Btn_Choose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_name)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addComponent(Btn_Choose, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Btn_Next, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Btn_Previous, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Btn_Last, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(Btn_First, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                        .addComponent(BTN_DELETE_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Btn_update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Btn_ınsert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        Btn_update.getAccessibleContext().setAccessibleDescription("");
        BTN_DELETE_.getAccessibleContext().setAccessibleDescription("");
        Btn_Next.getAccessibleContext().setAccessibleDescription("");

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nameActionPerformed

    private void Btn_ChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ChooseActionPerformed
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));    
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.image","png","jmp");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if(result ==JFileChooser.APPROVE_OPTION){
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            lbl_image.setIcon(ResizeImage(path,null));
            ImgPath = path; 
            
        }
        
        else{
            System.out.println("No");
        }
    }//GEN-LAST:event_Btn_ChooseActionPerformed

    private void Btn_ınsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ınsertActionPerformed
        if(checkInputs() && ImgPath != null ){
            try{
            Connection conn = getConnection();
            String sql = "INSERT INTO SATIS(NAME, PRICE,IMAGE) VALUES(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,txt_name.getText());
            ps.setString(2, txt_price.getText());           
            InputStream img = null;
            try {
                img = new FileInputStream(new File(ImgPath));} 
            catch (FileNotFoundException ex) {
                Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);}
            ps.setBlob(3, img);
            int rows =ps.executeUpdate();
            if(rows>0){
               System.out.println("olusturuldu");
            }
            Show_Product_In_JTable();
            }
                catch(SQLException e){
                    System.out.println("hata var");
                    e.printStackTrace();
                }           
        }
        else{
            JOptionPane.showMessageDialog(null, "bos");
        }       
    }//GEN-LAST:event_Btn_ınsertActionPerformed

    private void Btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_updateActionPerformed
      if(checkInputs() && txt_id.getText() != null){
            //update with image
            if(ImgPath != null){
                try{         
           try {
               Class.forName("oracle.jdbc.driver.OracleDriver");
           } catch (ClassNotFoundException ex) { 
               Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
           }
           Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "CEREN","ceren2019");          
           InputStream img = img = new FileInputStream(new File(ImgPath));         
           String sql = "UPDATE SATIS SET NAME=?, PRICE=?, IMAGE= ? WHERE ID=?";           
           PreparedStatement ps = conn.prepareStatement(sql);
           ps.setString(1,txt_name.getText());
           ps.setString(2, txt_price.getText());
           ps.setBlob(3, img);
           ps.setInt(4, Integer.parseInt(txt_id.getText()));
           System.out.println("oldu");  
           int rows =ps.executeUpdate();
           ps.close();
           conn.close(); }
       catch(SQLException e){
            System.out.println("hata var");
           e.printStackTrace(); 
       }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } 
            }      
            
            
             //update without image
            else{                    
                try{
           try {
               Class.forName("oracle.jdbc.driver.OracleDriver");
           } catch (ClassNotFoundException ex) {
               Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
           }
           Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "CEREN","ceren2019");
           String sql = "UPDATE SATIS SET NAME=?, PRICE=? WHERE ID=?";
           PreparedStatement ps = conn.prepareStatement(sql);
           ps.setString(1,txt_name.getText());
           ps.setString(2, txt_price.getText());
           ps.setInt(3, Integer.parseInt(txt_id.getText()));
           int rows =ps.executeUpdate();
          
               System.out.println("olusturuldu");
           
           ps.close();
           conn.close();
       }
       catch(SQLException e){
            System.out.println("hata var");
           e.printStackTrace();
       }
    }
                
        }
        else{
            JOptionPane.showMessageDialog(null, "One or more fileds are empty or wrong");
        }
       
    }//GEN-LAST:event_Btn_updateActionPerformed

    private void BTN_DELETE_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DELETE_ActionPerformed
        try{
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
            }
          Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","CEREN","ceren2019");
          String sql = "DELETE FROM SATIS WHERE ID =?";
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setInt(1, Integer.parseInt(txt_id.getText()));
          int rows = ps.executeUpdate();
          System.out.println("oldus");
          ps.close();
          conn.close();
        }catch(SQLException e){
           System.out.println("hata var");
        }
    }//GEN-LAST:event_BTN_DELETE_ActionPerformed
    int pos = 0;
    private void JTable_ProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_ProductsMouseClicked
        int index = JTable_Products.getSelectedRow(); ShowItem(index);
        ShowItem(pos);
    }//GEN-LAST:event_JTable_ProductsMouseClicked

    private void Btn_FirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_FirstActionPerformed
        pos = 0; ShowItem(pos);
    }//GEN-LAST:event_Btn_FirstActionPerformed

    private void Btn_LastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_LastActionPerformed
       pos= getProductList().size()-1; ShowItem(pos);
    }//GEN-LAST:event_Btn_LastActionPerformed

    private void Btn_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_NextActionPerformed
       pos++;
       if( pos >= getProductList().size()){
           pos = getProductList().size()-1;
       }ShowItem(pos);
    }//GEN-LAST:event_Btn_NextActionPerformed

    private void Btn_PreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_PreviousActionPerformed
        pos--;
        if(pos <0){
            pos = 0;}
        ShowItem(pos);
    }//GEN-LAST:event_Btn_PreviousActionPerformed
    
    
    
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
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_Window().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_DELETE_;
    private javax.swing.JButton Btn_Choose;
    private javax.swing.JButton Btn_First;
    private javax.swing.JButton Btn_Last;
    private javax.swing.JButton Btn_Next;
    private javax.swing.JButton Btn_Previous;
    private javax.swing.JButton Btn_update;
    private javax.swing.JButton Btn_ınsert;
    private javax.swing.JTable JTable_Products;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_image;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_price;
    // End of variables declaration//GEN-END:variables
}
