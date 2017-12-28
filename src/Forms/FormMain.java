package Forms;

import Connection.Conection;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;

/**
 *
 * @author YU7
 */
public class FormMain extends javax.swing.JFrame {

    private Conection con;

    public FormMain() {
        initComponents();
        setIcone();
        cbConnection();
    }

    private void setIcone() {
        // coloca uma figura na barra de título da janela
        URL url = getClass().getResource("icone.png");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        setIconImage(imagemTitulo);

    }

    private void cbConnection() {

        String path = System.getProperty("user.dir");
        File[] arquivos = new File(path).listFiles();

        int cont = 0;

        cbConections.removeAllItems();
        cbConections.addItem("Selecione...");

        for (File f : arquivos) {
            if (f.getName().contains(".properties")) {
                String nome = f.getName().replaceAll(".properties", "");
                cbConections.addItem(nome);
                cont++;
            }
        }

        txtConsole.append(getTimeLog() + cont + " conexões encontradas\n");

    }

    private void listarTabelas() {

        ArrayList<String> lista = con.listTabelas();
        DefaultListModel model = new DefaultListModel();

        for (String s : lista) {
            model.addElement(s);
        }

        listaTblas.setModel(model);
        txtConsole.append(getTimeLog() + lista.size() + " TABELAS LISTADAS\n");
    }

    private void listarRegistros(String sql, String titulo) {

        FormRegistros panel = new FormRegistros(con, sql);
        painelAbas.add(panel);
        int cont = painelAbas.getTabCount() - 1;

        painelAbas.setTitleAt(cont, titulo);
        painelAbas.setSelectedIndex(cont);

    }

    private void getConections() {

        String nome = "";

        if (cbConections.getSelectedIndex() > 0) {
            nome = cbConections.getSelectedItem().toString();
        }

        FormDialogConection dialog = new FormDialogConection(this, true, nome);
        dialog.setVisible(true);

        if (dialog.isConfirma()) {
            cbConnection();
        }

    }

    private void consultaSQL() {

        FormDialogQuery query = new FormDialogQuery(this, true);
        query.setVisible(true);

        if (query.isConfirma()) {
            String sql = query.getQuery();
            listarRegistros(sql, "CONSULTA");
        }

    }

    private void exportarTabela() {

        JFileChooser janela = new JFileChooser(".");
        janela.setDialogTitle("Exportar tabela");
        janela.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (janela.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {

            File destino = janela.getSelectedFile();
            String selection = listaTblas.getSelectedValue();
            String path = destino.getAbsolutePath() + "\\" + selection + ".csv";

            String retorno = con.exportarTabela(selection, path);
            txtConsole.append(getTimeLog() + retorno + "\n");

        }
    }

    public String getTimeLog() {

        SimpleDateFormat dt = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String hora = dt.format(cal.getTime());
        return "LOG " + hora + " >> ";

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelAbas = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cbConections = new javax.swing.JComboBox<>();
        btnConectar = new javax.swing.JButton();
        btnDesconectar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaTblas = new javax.swing.JList<>();
        btnConfConex = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtConsole = new javax.swing.JTextArea();
        jToolBar1 = new javax.swing.JToolBar();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnExportar = new javax.swing.JButton();
        btnConsulta = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnFinalizaGuia = new javax.swing.JButton();
        btnFinalizarTodas = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnSair = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuArquivo = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuItemConfig = new javax.swing.JMenuItem();
        menuQuery = new javax.swing.JMenuItem();
        menuItemExportar = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JDBC Tools v1.0");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setText("Conexões:");

        cbConections.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbConectionsItemStateChanged(evt);
            }
        });

        btnConectar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Success.png"))); // NOI18N
        btnConectar.setText("CONECTAR");
        btnConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConectarActionPerformed(evt);
            }
        });

        btnDesconectar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Delete.png"))); // NOI18N
        btnDesconectar.setText("DESCONECTAR");
        btnDesconectar.setEnabled(false);
        btnDesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesconectarActionPerformed(evt);
            }
        });

        listaTblas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaTblas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaTblasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(listaTblas);

        btnConfConex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Configuration.png"))); // NOI18N
        btnConfConex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfConexActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnConectar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDesconectar))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbConections, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConfConex, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnConfConex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbConections))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConectar)
                    .addComponent(btnDesconectar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtConsole.setEditable(false);
        txtConsole.setBackground(new java.awt.Color(204, 204, 204));
        txtConsole.setColumns(20);
        txtConsole.setFont(new java.awt.Font("Microsoft JhengHei", 0, 12)); // NOI18N
        txtConsole.setLineWrap(true);
        txtConsole.setRows(5);
        txtConsole.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtConsole);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.add(jSeparator3);

        btnExportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Folder.png"))); // NOI18N
        btnExportar.setToolTipText("Consultas SQL");
        btnExportar.setEnabled(false);
        btnExportar.setFocusable(false);
        btnExportar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExportar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnExportar);

        btnConsulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Search.png"))); // NOI18N
        btnConsulta.setToolTipText("Consultas SQL");
        btnConsulta.setEnabled(false);
        btnConsulta.setFocusable(false);
        btnConsulta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnConsulta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaActionPerformed(evt);
            }
        });
        jToolBar1.add(btnConsulta);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Document.png"))); // NOI18N
        jButton1.setToolTipText("Limpar Console");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton1);
        jToolBar1.add(jSeparator1);

        btnFinalizaGuia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Symbol_Pause.png"))); // NOI18N
        btnFinalizaGuia.setToolTipText("Finalizar Guia");
        btnFinalizaGuia.setFocusable(false);
        btnFinalizaGuia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFinalizaGuia.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFinalizaGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizaGuiaActionPerformed(evt);
            }
        });
        jToolBar1.add(btnFinalizaGuia);

        btnFinalizarTodas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Symbol_Stop.png"))); // NOI18N
        btnFinalizarTodas.setToolTipText("Finalizar todas");
        btnFinalizarTodas.setFocusable(false);
        btnFinalizarTodas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFinalizarTodas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFinalizarTodas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarTodasActionPerformed(evt);
            }
        });
        jToolBar1.add(btnFinalizarTodas);
        jToolBar1.add(jSeparator2);

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Logout.png"))); // NOI18N
        btnSair.setToolTipText("Sair");
        btnSair.setFocusable(false);
        btnSair.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSair.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSair);

        jMenuArquivo.setText("Arquivo");

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Document.png"))); // NOI18N
        jMenuItem2.setText("Limpar LOG");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenuArquivo.add(jMenuItem2);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Logout.png"))); // NOI18N
        jMenuItem1.setText("Sair");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuArquivo.add(jMenuItem1);

        jMenuBar1.add(jMenuArquivo);

        jMenu3.setText("Ferramentas");

        menuItemConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Configuration.png"))); // NOI18N
        menuItemConfig.setText("Conexões");
        menuItemConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemConfigActionPerformed(evt);
            }
        });
        jMenu3.add(menuItemConfig);

        menuQuery.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Search.png"))); // NOI18N
        menuQuery.setText("Consultas (Query)");
        menuQuery.setEnabled(false);
        menuQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuQueryActionPerformed(evt);
            }
        });
        jMenu3.add(menuQuery);

        menuItemExportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Folder.png"))); // NOI18N
        menuItemExportar.setText("Exportar Tabela (.csv)");
        menuItemExportar.setEnabled(false);
        menuItemExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemExportarActionPerformed(evt);
            }
        });
        jMenu3.add(menuItemExportar);

        jMenuItem6.setText("Exportar Banco (.sql)");
        jMenu3.add(jMenuItem6);

        jMenuBar1.add(jMenu3);

        jMenu5.setText("Guias");

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Symbol_Pause.png"))); // NOI18N
        jMenuItem3.setText("Finalizar Atual ");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem3);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Symbol_Stop.png"))); // NOI18N
        jMenuItem4.setText("Finalizar Todas");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem4);

        jMenuBar1.add(jMenu5);

        jMenu2.setText("Ajuda");

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/FAQ.png"))); // NOI18N
        jMenuItem5.setText("Sobre");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelAbas, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelAbas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btnConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConectarActionPerformed
        // TODO add your handling code here:

        if (cbConections.getSelectedIndex() > 0) {

            String connection = cbConections.getSelectedItem().toString();
            con = new Conection(connection);

            String retorno = con.Conecta();

            if (retorno.equals("true")) {
                txtConsole.append(getTimeLog() + "CONEXÃO " + cbConections.getSelectedItem() + " REALIZADA\n");
                listarTabelas();

                btnConectar.setEnabled(false);
                btnDesconectar.setEnabled(true);
                cbConections.setEnabled(false);
                menuItemConfig.setEnabled(false);
                menuQuery.setEnabled(true);
                menuItemExportar.setEnabled(true);
                btnConfConex.setEnabled(false);
                btnExportar.setEnabled(true);
                btnConsulta.setEnabled(true);

            } else {
                txtConsole.append(getTimeLog() + retorno + "\n");
            }

        }

    }//GEN-LAST:event_btnConectarActionPerformed

    private void cbConectionsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbConectionsItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbConectionsItemStateChanged

    private void menuItemConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemConfigActionPerformed
        // TODO add your handling code here:

        getConections();


    }//GEN-LAST:event_menuItemConfigActionPerformed

    private void listaTblasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaTblasMouseClicked
        // TODO add your handling code here:

        String tabela = listaTblas.getSelectedValue();

        if (evt.getClickCount() > 1 && listaTblas.getModel().getSize() > 0) {
            listarRegistros("SELECT * FROM " + tabela, tabela.toUpperCase());
        }

    }//GEN-LAST:event_listaTblasMouseClicked

    private void btnDesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesconectarActionPerformed
        // TODO add your handling code here:

        String retorno = con.Desconecta();

        if (retorno.equals("true")) {
            txtConsole.append(getTimeLog() + "CONEXÃO " + cbConections.getSelectedItem() + " FINALIZADA\n");
            con = null;
            listaTblas.setModel(new DefaultListModel<>());
            painelAbas.removeAll();
            btnConectar.setEnabled(true);
            btnDesconectar.setEnabled(false);
            cbConections.setEnabled(true);
            menuItemConfig.setEnabled(true);
            menuQuery.setEnabled(false);
            btnConfConex.setEnabled(true);
            menuItemExportar.setEnabled(false);
            btnExportar.setEnabled(false);
            btnConsulta.setEnabled(false);

        } else {
            txtConsole.append(getTimeLog() + retorno + "\n");
        }

    }//GEN-LAST:event_btnDesconectarActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        painelAbas.removeAll();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:

        if (painelAbas.getTabCount() > 0) {
            int index = painelAbas.getSelectedIndex();
            painelAbas.remove(index);
        }

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void btnConfConexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfConexActionPerformed
        // TODO add your handling code here:
        getConections();
    }//GEN-LAST:event_btnConfConexActionPerformed

    private void menuQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuQueryActionPerformed
        // TODO add your handling code here:

        consultaSQL();

    }//GEN-LAST:event_menuQueryActionPerformed

    private void menuItemExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemExportarActionPerformed
        // TODO add your handling code here:

        exportarTabela();

    }//GEN-LAST:event_menuItemExportarActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        new FormDialogAbout(this, false).setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        txtConsole.setText("");
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaActionPerformed
        // TODO add your handling code here:
        consultaSQL();
    }//GEN-LAST:event_btnConsultaActionPerformed

    private void btnFinalizaGuiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizaGuiaActionPerformed
        // TODO add your handling code here:
        if (painelAbas.getTabCount() > 0) {
            int index = painelAbas.getSelectedIndex();
            painelAbas.remove(index);
        }
    }//GEN-LAST:event_btnFinalizaGuiaActionPerformed

    private void btnFinalizarTodasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarTodasActionPerformed
        // TODO add your handling code here:
        painelAbas.removeAll();
    }//GEN-LAST:event_btnFinalizarTodasActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        // TODO add your handling code here:
        exportarTabela();
    }//GEN-LAST:event_btnExportarActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FormMain().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConectar;
    private javax.swing.JButton btnConfConex;
    private javax.swing.JButton btnConsulta;
    private javax.swing.JButton btnDesconectar;
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnFinalizaGuia;
    private javax.swing.JButton btnFinalizarTodas;
    private javax.swing.JButton btnSair;
    private javax.swing.JComboBox<String> cbConections;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenuArquivo;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JList<String> listaTblas;
    private javax.swing.JMenuItem menuItemConfig;
    private javax.swing.JMenuItem menuItemExportar;
    private javax.swing.JMenuItem menuQuery;
    private javax.swing.JTabbedPane painelAbas;
    private javax.swing.JTextArea txtConsole;
    // End of variables declaration//GEN-END:variables
}
