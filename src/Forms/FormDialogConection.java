/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author YU7
 */
public class FormDialogConection extends javax.swing.JDialog {


    private final Properties prop;
    private String _DRIVER;

    private boolean confirma = false;
    
    public FormDialogConection(java.awt.Frame parent, boolean modal, String nome) {
        super(parent, modal);
        initComponents();
        this.prop = new Properties();

        if (!nome.equals("")) {
            getConfiguracao(nome);
            btnExcluir.setEnabled(true);
        } else {
            tfNome.setEditable(true);
            selectDriver();
        }
    }

    private void getConfiguracao(String nome) {

        try {
            File arquivo = new File(nome + ".properties");
            try (FileInputStream file = new FileInputStream(arquivo)) {
                prop.load(file);
                tfNome.setText(nome);
                _DRIVER = prop.getProperty("prop.server.driver");
                tfEndereco.setText(prop.getProperty("prop.server.endereco"));
                tfPorta.setText(prop.getProperty("prop.server.porta"));
                tfDatabase.setText(prop.getProperty("prop.server.database"));
                tfUser.setText(prop.getProperty("prop.server.usuario"));
                tfPass.setText(prop.getProperty("prop.server.senha"));
                tfUrl.setText(prop.getProperty("prop.server.url"));
                file.close();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FormDialogConection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FormDialogConection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setConfiguracao() {

        try {
            File arquivo = new File(tfNome.getText().trim() + ".properties");
            if (arquivo.exists()) {
                try (FileOutputStream out = new FileOutputStream(arquivo)) {
                    prop.setProperty("prop.server.driver", _DRIVER);
                    prop.setProperty("prop.server.endereco", tfEndereco.getText().trim());
                    prop.setProperty("prop.server.porta", tfPorta.getText().trim());
                    prop.setProperty("prop.server.database", tfDatabase.getText().trim());
                    prop.setProperty("prop.server.usuario", tfUser.getText().trim());
                    prop.setProperty("prop.server.senha", tfPass.getText());
                    prop.setProperty("prop.server.url", tfUrl.getText().trim());
                    prop.store(out, null);
                }
                JOptionPane.showMessageDialog(null, "Salvo");
                setConfirma(true);
                this.dispose();
            } else {
                criarArquivoConfiguracao(arquivo);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FormDialogConection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FormDialogConection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void selectDriver() {

        int index = cbDrivers.getSelectedIndex();

        if (index > 0) {

            _DRIVER = "org.postgresql.Driver";
            tfUrl.setText("jdbc:postgresql://localhost:5432/");
            tfPorta.setText("5432");
            tfUser.setText("postgres");

        } else {

            _DRIVER = "com.mysql.jdbc.Driver";
            tfUrl.setText("jdbc:mysql://localhost:3306/");
            tfPorta.setText("3306");
            tfUser.setText("root");
        }
    }

    private void criarArquivoConfiguracao(File file) {

        try {
            file.createNewFile();
            try (BufferedWriter buff = new BufferedWriter(new FileWriter(file))) {
                buff.write("prop.server.driver = " + _DRIVER);
                buff.newLine();
                buff.write("prop.server.endereco = " + tfEndereco.getText().trim());
                buff.newLine();
                buff.write("prop.server.porta = " + tfPorta.getText().trim());
                buff.newLine();
                buff.write("prop.server.database = " + tfDatabase.getText().trim());
                buff.newLine();
                buff.write("prop.server.usuario = " + tfUser.getText());
                buff.newLine();
                buff.write("prop.server.senha = " + tfPass.getText());
                buff.newLine();
                buff.write("prop.server.url = " + tfUrl.getText().trim());
                buff.newLine();
                buff.close();
            }
            JOptionPane.showMessageDialog(null, "Nova Conexão Criada");
            setConfirma(true);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(FormDialogConection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void montaUrl() {

        String tipo = cbDrivers.getSelectedItem().toString();
        String ende = tfEndereco.getText().trim();
        String port = tfPorta.getText().trim();
        String data = tfDatabase.getText().trim();

        if (!ende.equals("") && !port.equals("") && !data.equals("")) {
            tfUrl.setText("jdbc:" + tipo.toLowerCase() + "://" + ende + ":" + port + "/" + data);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cbDrivers = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tfEndereco = new javax.swing.JTextField();
        tfPorta = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfDatabase = new javax.swing.JTextField();
        tfUser = new javax.swing.JTextField();
        tfPass = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        tfNome = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tfUrl = new javax.swing.JTextField();
        btnExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Conexões");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setText("Tipo");

        cbDrivers.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MySQL", "PostgreSQL" }));
        cbDrivers.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbDriversItemStateChanged(evt);
            }
        });

        jLabel7.setText("Endereço");

        jLabel8.setText("Porta");

        tfEndereco.setText("localhost");
        tfEndereco.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfEnderecoFocusLost(evt);
            }
        });

        tfPorta.setText("3306");
        tfPorta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfPortaFocusLost(evt);
            }
        });

        jLabel2.setText("Database");

        tfDatabase.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfDatabaseFocusLost(evt);
            }
        });

        tfUser.setText("root");

        jLabel4.setText("Senha");

        jLabel3.setText("Usuario ");

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Save.png"))); // NOI18N
        btnSalvar.setText("SALVAR");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        tfNome.setEditable(false);
        tfNome.setBackground(new java.awt.Color(255, 255, 255));
        tfNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfNomeFocusLost(evt);
            }
        });

        jLabel9.setText("Nome");

        tfUrl.setEditable(false);
        tfUrl.setBackground(new java.awt.Color(255, 255, 255));

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Delete.png"))); // NOI18N
        btnExcluir.setText("EXCLUIR");
        btnExcluir.setEnabled(false);
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfUrl, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfPass)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tfDatabase)
                                .addComponent(tfUser, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfPorta, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                    .addComponent(tfEndereco)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfNome)
                                    .addComponent(cbDrivers, 0, 180, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfNome)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbDrivers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfPass)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addComponent(tfUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnExcluir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbDriversItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbDriversItemStateChanged
        // TODO add your handling code here:

        selectDriver();
    }//GEN-LAST:event_cbDriversItemStateChanged

    private void tfEnderecoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfEnderecoFocusLost
        // TODO add your handling code here:
        montaUrl();
    }//GEN-LAST:event_tfEnderecoFocusLost

    private void tfPortaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfPortaFocusLost
        // TODO add your handling code here:
        montaUrl();
    }//GEN-LAST:event_tfPortaFocusLost

    private void tfDatabaseFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfDatabaseFocusLost
        // TODO add your handling code here:
        montaUrl();
    }//GEN-LAST:event_tfDatabaseFocusLost

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:

        if (!tfNome.getText().equals("") && !tfDatabase.getText().equals("")) {
            setConfiguracao();
        } 
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void tfNomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNomeFocusLost
        // TODO add your handling code here:
        if (!tfNome.getText().equals("")) {
            tfNome.setBackground(Color.white);
        }
    }//GEN-LAST:event_tfNomeFocusLost

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:

        File arquivo = new File(tfNome.getText() + ".properties");

        if (arquivo.exists()) {
            int op = JOptionPane.showConfirmDialog(null,
                    "DESEJA EXCLUIR A CONEXÃO?", "EXCLUIR", JOptionPane.YES_NO_OPTION);
            if (op == JOptionPane.OK_OPTION) {
                if (arquivo.delete()) {
                    setConfirma(true);
                    this.dispose();
                }
            }
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

      /**
     * @return the confirma
     */
    public boolean isConfirma() {
        return confirma;
    }

    /**
     * @param confirma the confirma to set
     */
    public void setConfirma(boolean confirma) {
        this.confirma = confirma;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbDrivers;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tfDatabase;
    private javax.swing.JTextField tfEndereco;
    private javax.swing.JTextField tfNome;
    private javax.swing.JPasswordField tfPass;
    private javax.swing.JTextField tfPorta;
    private javax.swing.JTextField tfUrl;
    private javax.swing.JTextField tfUser;
    // End of variables declaration//GEN-END:variables
}
