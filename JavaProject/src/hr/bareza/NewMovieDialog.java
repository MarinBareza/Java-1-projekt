/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.bareza;

import hr.bareza.models.Movie;
import hr.bareza.utils.FileUtils;
import hr.bareza.utils.IconUtils;
import hr.bareza.utils.MessageUtils;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Bareza
 */
public class NewMovieDialog extends javax.swing.JDialog {

    private final UserForm parent;
    List<JTextComponent> validationFields;
    List<JLabel> errorLabels;
    
    private static final Random RANDOM = new Random();
    private static final String DIR = "posters";
    
    /**
     * Creates new form NewMovieDialog
     */
    public NewMovieDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.parent = (UserForm) parent;
        initComponents();
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfTitle = new javax.swing.JTextField();
        tfOriginalTitle = new javax.swing.JTextField();
        tfDatePublished = new javax.swing.JTextField();
        tfLink = new javax.swing.JTextField();
        tfDuration = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDescription = new javax.swing.JTextArea();
        lblIcon = new javax.swing.JLabel();
        btnCreate = new javax.swing.JButton();
        btnChoose = new javax.swing.JButton();
        lblTitleError = new javax.swing.JLabel();
        lblOriginalTitleError = new javax.swing.JLabel();
        lblLinkError = new javax.swing.JLabel();
        lblDatePublishedError = new javax.swing.JLabel();
        lblDurationError = new javax.swing.JLabel();
        lblDescriptionError = new javax.swing.JLabel();
        lblPicturePathError = new javax.swing.JLabel();
        tfPicturePath = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New Movie");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel1.setText("Title:");

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel2.setText("Original title:");

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel3.setText("Link:");

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel4.setText("Date published:");

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel5.setText("Duration:");

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel6.setText("Poster path:");

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel7.setText("Description:");

        tfTitle.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N

        tfOriginalTitle.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N

        tfDatePublished.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        tfDatePublished.setText("2011-12-03T10:15:30");

        tfLink.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N

        tfDuration.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N

        taDescription.setColumns(20);
        taDescription.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        taDescription.setRows(5);
        jScrollPane1.setViewportView(taDescription);

        lblIcon.setIcon(new javax.swing.ImageIcon("C:\\Users\\Bareza\\OneDrive - Visoko uciliste Algebra\\4.  Semestar\\Programiranje u Javi 1\\Projektni_zadatak\\JavaProject\\src\\resources\\gradient-bg-1024x576.png")); // NOI18N

        btnCreate.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 24)); // NOI18N
        btnCreate.setText("Create movie");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnChoose.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        btnChoose.setText("Choose picture");
        btnChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseActionPerformed(evt);
            }
        });

        lblTitleError.setForeground(new java.awt.Color(255, 0, 0));

        lblOriginalTitleError.setForeground(new java.awt.Color(255, 0, 0));

        lblLinkError.setForeground(new java.awt.Color(255, 0, 0));

        lblDatePublishedError.setForeground(new java.awt.Color(255, 0, 0));

        lblDurationError.setForeground(new java.awt.Color(255, 0, 0));

        lblDescriptionError.setForeground(new java.awt.Color(255, 0, 0));

        lblPicturePathError.setForeground(new java.awt.Color(255, 0, 0));

        tfPicturePath.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        tfPicturePath.setMaximumSize(new java.awt.Dimension(2147483647, 257));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfPicturePath, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(56, 56, 56)
                        .addComponent(lblPicturePathError, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfDatePublished, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfLink, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfOriginalTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitleError, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblOriginalTitleError, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLinkError, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDatePublishedError, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDurationError, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescriptionError, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tfDatePublished, tfDuration, tfLink, tfOriginalTitle, tfTitle});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(jLabel1)
                                                            .addComponent(tfTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(lblTitleError, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(jLabel2)
                                                            .addComponent(tfOriginalTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addComponent(lblOriginalTitleError, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel3)
                                                    .addComponent(tfLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(lblLinkError, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(tfDatePublished, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4)))
                                    .addComponent(lblDatePublishedError, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)))
                            .addComponent(lblDurationError, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDescriptionError, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(lblPicturePathError, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfPicturePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnChoose)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseActionPerformed
        File file = FileUtils.uploadFile("Images", "jpg", "jpeg", "png");
        if (file == null) {
            return;
        }
        
        tfPicturePath.setText(file.getAbsolutePath());
        setIcon(lblIcon, file);
    }//GEN-LAST:event_btnChooseActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        if (formValid()) {
            try {
                String PicturePath = uploadPicture();
                
                Movie movie = new Movie(
                tfTitle.getText().trim(),
                LocalDateTime.parse(tfDatePublished.getText().trim(), Movie.DATE_FORMATTER),
                taDescription.getText().trim(),
                tfOriginalTitle.getText().trim(),
                Integer.valueOf(tfDuration.getText().trim()),
                PicturePath.trim(),
                tfLink.getText().trim());
                
                if (parent.createNewMovie(movie)) {
                    dispose();
                    MessageUtils.showInformationMessage("Information", "Movie created successfully!");
                }else{
                    MessageUtils.showErrorMessage("Error", "Movie already exists!");
                }
                this.dispose();
                
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
                MessageUtils.showErrorMessage("Error", "Unable to create new movie!");
            }
        }
    }//GEN-LAST:event_btnCreateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChoose;
    private javax.swing.JButton btnCreate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDatePublishedError;
    private javax.swing.JLabel lblDescriptionError;
    private javax.swing.JLabel lblDurationError;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblLinkError;
    private javax.swing.JLabel lblOriginalTitleError;
    private javax.swing.JLabel lblPicturePathError;
    private javax.swing.JLabel lblTitleError;
    private javax.swing.JTextArea taDescription;
    private javax.swing.JTextField tfDatePublished;
    private javax.swing.JTextField tfDuration;
    private javax.swing.JTextField tfLink;
    private javax.swing.JTextField tfOriginalTitle;
    private javax.swing.JTextField tfPicturePath;
    private javax.swing.JTextField tfTitle;
    // End of variables declaration//GEN-END:variables
    
    private void init() {
        errorLabels = Arrays.asList(lblTitleError, lblOriginalTitleError, lblDatePublishedError, 
                lblLinkError, lblPicturePathError, lblDurationError, lblDescriptionError);
        validationFields = Arrays.asList(tfTitle, tfOriginalTitle, tfDatePublished, tfLink, tfPicturePath, tfDuration,  taDescription);
    }
    
    private boolean formValid() {
        boolean ok = true;
        
         for (int i = 0; i < validationFields.size(); i++) {
            ok &= !validationFields.get(i).getText().trim().isEmpty();
            errorLabels.get(i).setText(validationFields.get(i).getText().trim().isEmpty() ? "*" : "");

            if ("Date".equals(validationFields.get(i).getName())) {
                try {
                    LocalDate.parse(validationFields.get(i).getText().trim(), Movie.DATE_FORMATTER);
                    errorLabels.get(i).setText("");
                } catch (Exception e) {
                    ok = false;
                    errorLabels.get(i).setText("*");
                }
            }
        }
         
        return ok;
    }

    private String uploadPicture() throws IOException, IOException {
        String picturePath = tfPicturePath.getText().trim();
        String ext = picturePath.substring(picturePath.lastIndexOf("."));
        String pictureName = Math.abs(RANDOM.nextInt()) + ext;
        String localPicturePath = DIR + File.separator + pictureName + ext;
        FileUtils.copy(picturePath, localPicturePath);
        return localPicturePath;
    }

    private void clearForm() {
        validationFields.forEach(f -> f.setText(""));
        errorLabels.forEach(l -> l.setText(""));
        taDescription.setText("");
        lblIcon.setIcon(new ImageIcon(getClass().getResource("/resources/gradient-bg-1024x576.png")));

    }

    private void setIcon(JLabel label, File file) {
        try {
            label.setIcon(IconUtils.createIcon(file.getAbsolutePath(),label.getWidth(), label.getHeight()));
        } catch (IOException ex) {
            Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to set the move poster!");
        }
    }
}