/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms.Dialogs;

import Utils.LogsEventos;
import java.awt.Desktop;
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
public class DialogQuery extends javax.swing.JDialog {

    private boolean confirma = false;
    private String query;

    private final File arquivo;
    private ArrayList<String> listQuerys;

    public DialogQuery(java.awt.Frame parent, boolean modal) {
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
            LogsEventos.Gravar(ex.getMessage());
        } catch (IOException ex) {
            LogsEventos.Gravar(ex.getMessage());
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
            LogsEventos.Gravar(ex.getMessage());
        }

    }

    private boolean verificaQuery() {

        String sql = txtQuery.getText().toLowerCase();
        return !(sql.contains("create") || sql.contains("drop")
                || sql.contains("insert") || sql.contains("delete")
                || sql.contains("update"));

    }

    private void executar() {
        
        if (!txtQuery.getText().equals("")) {
            if (verificaQuery()) {
                setConfirma(true);
                setQuery(txtQuery.getText());
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null,
                        "INSTRUÇÃO INVALIDA!\n"
                        + "Módulo apenas para consultas,\n"
                        + "edições devem ser feitas diretamento no registro!",
                        "INVALIDO", JOptionPane.WARNING_MESSAGE);
            }

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnExecutar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtQuery = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        listConsultas = new javax.swing.JList<>();
        btnSalvar1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta SQL (Query)");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        jSplitPane1.setDividerLocation(150);
        jSplitPane1.setDividerSize(7);
        jSplitPane1.setOneTouchExpandable(true);

        txtQuery.setColumns(20);
        txtQuery.setLineWrap(true);
        txtQuery.setRows(5);
        txtQuery.setWrapStyleWord(true);
        txtQuery.setBorder(javax.swing.BorderFactory.createTitledBorder("SQL"));
        jScrollPane1.setViewportView(txtQuery);

        jSplitPane1.setRightComponent(jScrollPane1);

        listConsultas.setBorder(javax.swing.BorderFactory.createTitledBorder("Consultas"));
        listConsultas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listConsultas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listConsultasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listConsultas);

        jSplitPane1.setLeftComponent(jScrollPane2);

        btnSalvar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Document.png"))); // NOI18N
        btnSalvar1.setText("ARQUIVO DE CONSULTAS");
        btnSalvar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnExecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalvar1, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExecutar)
                    .addComponent(btnSalvar)
                    .addComponent(jButton1)
                    .addComponent(btnSalvar1))
                .addContainerGap())
        );

        jMenu1.setText("Atalhos");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jMenuItem1.setText("Executar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        jMenuItem2.setText("Salvar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        jMenuItem3.setText("Sair");
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

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
        executar();
    }//GEN-LAST:event_btnExecutarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:

        String nome = JOptionPane.showInputDialog(this,
                "INFORME UM NOME PARA A CONSULTA",
                "SALVAR CONSULTA",
                JOptionPane.INFORMATION_MESSAGE);

        if (nome != null) {
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

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        executar();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        String nome = JOptionPane.showInputDialog(this,
                "INFORME UM NOME PARA A CONSULTA",
                "SALVAR CONSULTA",
                JOptionPane.INFORMATION_MESSAGE);

        if (nome != null) {
            salvarConsulta(nome);
        }

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btnSalvar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvar1ActionPerformed
        // TODO add your handling code here:
        File file = new File("consultas.txt");

        if (file.exists()) {
            try {
                this.dispose();
                Desktop.getDesktop().open(file);
            } catch (IOException ex) {
                LogsEventos.Gravar(ex.getMessage());
            }
        }

    }//GEN-LAST:event_btnSalvar1ActionPerformed

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
    private javax.swing.JButton btnSalvar1;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JList<String> listConsultas;
    private javax.swing.JTextArea txtQuery;
    // End of variables declaration//GEN-END:variables
}
