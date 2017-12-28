/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author YU7
 */
public class FormDialogQuery extends javax.swing.JDialog {

    private boolean confirma = false;
    private String query;

    private final File arquivo;
    private ArrayList<String> listQuerys;

    public FormDialogQuery(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.arquivo = new File("consultas.txt");
        listarConsultas();
    }

    private void listarConsultas() {

        try {
            if (arquivo.exists()) {
                FileReader filer = new FileReader(arquivo);
                DefaultListModel modelo = new DefaultListModel();
                try (BufferedReader buff = new BufferedReader(filer)) {
                    listQuerys = new ArrayList<>();
                    while (buff.ready()) {
                        String linha = buff.readLine();
                        listQuerys.add(linha);
                        modelo.addElement(linha.subSequence(0, linha.indexOf('#')));
                    }
                }
                listConsultas.setModel(modelo);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FormDialogQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FormDialogQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void salvarConsulta(String nome) {

        try {
            if (arquivo.exists()) {

                FileWriter filer = new FileWriter(arquivo, true);
                try (BufferedWriter buffw = new BufferedWriter(filer)) {
                    buffw.write(nome + "#" + txtQuery.getText());
                    buffw.newLine();
                }
                listarConsultas();

            } else {
                arquivo.createNewFile();
                salvarConsulta(nome);
            }

        } catch (IOException ex) {
            Logger.getLogger(FormDialogQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean verificaQuery() {

        String sql = txtQuery.getText().toLowerCase();
        return !(sql.contains("create") || sql.contains("drop")   ||
                 sql.contains("insert") || sql.contains("delete") || 
                 sql.contains("update"));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtQuery = new javax.swing.JTextArea();
        btnSalvar = new javax.swing.JButton();
        btnExecutar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listConsultas = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta SQL (Query)");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtQuery.setColumns(20);
        txtQuery.setLineWrap(true);
        txtQuery.setRows(5);
        txtQuery.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtQuery);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Save.png"))); // NOI18N
        btnSalvar.setText("SALVAR (F4)");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnExecutar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Symbol_Play.png"))); // NOI18N
        btnExecutar.setText("EXECUTAR (F5)");
        btnExecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExecutarActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Logout.png"))); // NOI18N
        jButton1.setText("SAIR (ESC)");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        listConsultas.setBorder(javax.swing.BorderFactory.createTitledBorder("Consultas"));
        listConsultas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listConsultas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listConsultasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listConsultas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnExecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnExecutar)
                    .addComponent(jButton1))
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnExecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExecutarActionPerformed
        // TODO add your handling code here:

        if (!txtQuery.getText().equals("")) {
            if (verificaQuery()) {
                setConfirma(true);
                setQuery(txtQuery.getText());
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(null, 
                        "INSTRUÇÃO INVALIDA!\n"+
                        "Módulo apenas para consultas,\n"+
                        "edições devem ser feitas diretamento no registro!",
                        "INVALIDO",JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_btnExecutarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:

        String nome = JOptionPane.showInputDialog(this, "NOME DA CONSULTA");

        if (!nome.isEmpty()) {
            salvarConsulta(nome);
        }

    }//GEN-LAST:event_btnSalvarActionPerformed

    private void listConsultasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listConsultasMouseClicked
        // TODO add your handling code here:

        if (evt.getClickCount() > 1 && listConsultas.getModel().getSize() > 0) {

            int index = listConsultas.getSelectedIndex();
            String consulta = listQuerys.get(index);

            txtQuery.setText(consulta.substring(consulta.indexOf('#') + 1));

        }

    }//GEN-LAST:event_listConsultasMouseClicked

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

    /**
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(String query) {
        this.query = query;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExecutar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listConsultas;
    private javax.swing.JTextArea txtQuery;
    // End of variables declaration//GEN-END:variables
}
