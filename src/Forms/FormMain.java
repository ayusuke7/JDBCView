package Forms;

import Forms.Dialogs.DialogAbout;
import Forms.Dialogs.DialogConection;
import Forms.Dialogs.DialogQuery;
import Connection.ConectionJDBC;
import Modell.Renderer.ButtonTabComponent;
import Utils.IconeAplication;
import Utils.LogsEventos;
import Utils.Propriedades;
import java.awt.EventQueue;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author YU7
 */
public class FormMain extends javax.swing.JFrame {

    private ConectionJDBC conexao;
    private final DefaultListModel listLogs;

    public FormMain() {
        initComponents();
        setIconImage(IconeAplication.getIcone());
        listLogs = new DefaultListModel();
        listConsole.setModel(listLogs);
        cbConnection();
    }

    private void cbConnection() {

        String path = System.getProperty("user.dir");
        File[] arquivos = new File(path).listFiles();

        int cont = 0;
        
        cbConections.removeAllItems();
        
        if (arquivos.length > 0) {
            btnConectar.setEnabled(true);
            for (File f : arquivos) {
                if (f.getName().contains(".properties")) {
                    String nome = f.getName().replaceAll(".properties", "");
                    cbConections.addItem(nome);
                    cont++;
                }
            }
        }

        addLogConsole(cont + " conexões encontradas\n");

    }

    private void listarTabelas() {

        ArrayList<String> lista = conexao.listTabelas();
        DefaultListModel model = new DefaultListModel();

        lista.stream().forEach((s) -> {
            model.addElement(s);
        });

        listaTblas.setModel(model);
        addLogConsole(lista.size() + " TABELAS LISTADAS");
    }

    private void listarRegistros(String sql, String titulo) {

        PanelRegistros panel = new PanelRegistros(conexao, sql, listLogs);
        painelAbas.add(panel);
        painelAbas.setSelectedComponent(panel);
        int index = painelAbas.getSelectedIndex();
        painelAbas.setTitleAt(index, titulo);
        painelAbas.setTabComponentAt(index, new ButtonTabComponent(painelAbas));

    }

    private void configureConexao(Properties props) {

        DialogConection dialog = new DialogConection(this, true, props);
        dialog.setVisible(true);

        if (dialog.isConfirma()) {
            cbConnection();
        }

    }

    private void consultaSQL() {

        DialogQuery query = new DialogQuery(this, true);
        query.setVisible(true);

        if (query.isConfirma()) {
            String sql = query.getQuery();
            listarRegistros(sql, "CONSULTA");
        }

    }

    private void exportarTabela() {

        if (listaTblas.getSelectedIndex() > -1) {
            JFileChooser janela = new JFileChooser(".");
            janela.setDialogTitle("Exportar tabela");
            janela.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int op = janela.showSaveDialog(this);

            if (op == JFileChooser.APPROVE_OPTION) {

                File destino = janela.getSelectedFile();
                String selection = listaTblas.getSelectedValue();
                String path = destino.getAbsolutePath() + "\\" + selection + ".csv";

                String retorno = conexao.exportarTabela(selection, path);
                addLogConsole(retorno);

            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "SELECIONE A TABELA PARA EXPORTAR");
        }

    }

    private void addLogConsole(String log) {

        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String hora = dt.format(cal.getTime());

        listLogs.addElement(hora + " >> " + log);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        cbConections = new javax.swing.JComboBox<>();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        btnConectar = new javax.swing.JButton();
        btnDesconectar = new javax.swing.JButton();
        btnConfConex = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();
        btnConsulta = new javax.swing.JButton();
        btnFinalizaGuia = new javax.swing.JButton();
        btnFinalizarTodas = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnSair = new javax.swing.JButton();
        jSplitPane2 = new javax.swing.JSplitPane();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaTblas = new javax.swing.JList<>();
        painelAbas = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listConsole = new javax.swing.JList<>();
        jToolBar2 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        menuItemConfig = new javax.swing.JMenuItem();
        menuQuery = new javax.swing.JMenuItem();
        menuItemExportar = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JDBC View");

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jSeparator3.setMaximumSize(new java.awt.Dimension(3, 32767));
        jToolBar1.add(jSeparator3);

        cbConections.setMaximumSize(new java.awt.Dimension(200, 32767));
        cbConections.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbConectionsItemStateChanged(evt);
            }
        });
        jToolBar1.add(cbConections);
        jToolBar1.add(jSeparator5);

        btnConectar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Success.png"))); // NOI18N
        btnConectar.setToolTipText("CONECTAR");
        btnConectar.setEnabled(false);
        btnConectar.setMaximumSize(new java.awt.Dimension(25, 30));
        btnConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConectarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnConectar);

        btnDesconectar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Delete.png"))); // NOI18N
        btnDesconectar.setToolTipText("DESCONECTAR");
        btnDesconectar.setEnabled(false);
        btnDesconectar.setMaximumSize(new java.awt.Dimension(25, 30));
        btnDesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesconectarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnDesconectar);

        btnConfConex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Configuration.png"))); // NOI18N
        btnConfConex.setMaximumSize(new java.awt.Dimension(23, 23));
        btnConfConex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfConexActionPerformed(evt);
            }
        });
        jToolBar1.add(btnConfConex);

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Document.png"))); // NOI18N
        btnNovo.setToolTipText("Nova Conexão");
        btnNovo.setFocusable(false);
        btnNovo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNovo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        jToolBar1.add(btnNovo);

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

        jSplitPane2.setDividerLocation(350);
        jSplitPane2.setDividerSize(7);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane2.setResizeWeight(1.0);
        jSplitPane2.setOneTouchExpandable(true);

        jSplitPane1.setDividerLocation(220);
        jSplitPane1.setDividerSize(7);
        jSplitPane1.setOneTouchExpandable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        listaTblas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaTblas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaTblasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(listaTblas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(jPanel1);
        jSplitPane1.setRightComponent(painelAbas);

        jSplitPane2.setTopComponent(jSplitPane1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        listConsole.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(listConsole);

        jToolBar2.setFloatable(false);
        jToolBar2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Delete.png"))); // NOI18N
        jButton1.setToolTipText("Limpar Log");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Save.png"))); // NOI18N
        jButton2.setToolTipText("Salvar Log");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSplitPane2.setRightComponent(jPanel2);

        jMenu3.setText("Opções");

        menuItemConfig.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        menuItemConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Configuration.png"))); // NOI18N
        menuItemConfig.setText("Conexões");
        menuItemConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemConfigActionPerformed(evt);
            }
        });
        jMenu3.add(menuItemConfig);

        menuQuery.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.ALT_MASK));
        menuQuery.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Search.png"))); // NOI18N
        menuQuery.setText("Consultas (Query)");
        menuQuery.setEnabled(false);
        menuQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuQueryActionPerformed(evt);
            }
        });
        jMenu3.add(menuQuery);

        menuItemExportar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK));
        menuItemExportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Folder.png"))); // NOI18N
        menuItemExportar.setText("Exportar Tabela (.csv)");
        menuItemExportar.setEnabled(false);
        menuItemExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemExportarActionPerformed(evt);
            }
        });
        jMenu3.add(menuItemExportar);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem6.setText("Exportar Banco (.sql)");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);
        jMenu3.add(jSeparator6);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Logout.png"))); // NOI18N
        jMenuItem1.setText("Sair");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuBar1.add(jMenu3);

        jMenu2.setText("Ajuda");

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
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
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSplitPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
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

        String nome = cbConections.getSelectedItem() + ".properties";
        Properties props = new Propriedades().loadProperties(nome);
        conexao = new ConectionJDBC(props);
        String retorno = conexao.Conecta();

        if (retorno.equals("true")) {
            addLogConsole("CONEXÃO " + cbConections.getSelectedItem() + " REALIZADA\n");
            listarTabelas();

            btnConectar.setEnabled(false);
            btnNovo.setEnabled(false);
            btnDesconectar.setEnabled(true);
            cbConections.setEnabled(false);
            menuItemConfig.setEnabled(false);
            menuQuery.setEnabled(true);
            menuItemExportar.setEnabled(true);
            btnConfConex.setEnabled(false);
            btnExportar.setEnabled(true);
            btnConsulta.setEnabled(true);

        } else {
            addLogConsole(retorno);
        }


    }//GEN-LAST:event_btnConectarActionPerformed

    private void cbConectionsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbConectionsItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbConectionsItemStateChanged

    private void menuItemConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemConfigActionPerformed
        // TODO add your handling code here:
        String nome = cbConections.getSelectedItem() + ".properties";
        Properties props = new Propriedades().loadProperties(nome);
        configureConexao(props);
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

        String retorno = conexao.Desconecta();

        if (retorno.equals("true")) {
            addLogConsole("CONEXÃO " + cbConections.getSelectedItem() + " FINALIZADA");
            conexao = null;
            listaTblas.setModel(new DefaultListModel<>());
            painelAbas.removeAll();
            btnConectar.setEnabled(true);
            btnNovo.setEnabled(true);
            btnDesconectar.setEnabled(false);
            cbConections.setEnabled(true);
            menuItemConfig.setEnabled(true);
            menuQuery.setEnabled(false);
            btnConfConex.setEnabled(true);
            menuItemExportar.setEnabled(false);
            btnExportar.setEnabled(false);
            btnConsulta.setEnabled(false);

        } else {
            addLogConsole(retorno + "\n");
        }

    }//GEN-LAST:event_btnDesconectarActionPerformed

    private void btnConfConexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfConexActionPerformed
        // TODO add your handling code here:
        String nome = cbConections.getSelectedItem() + ".properties";
        Properties props = new Propriedades().loadProperties(nome);
        configureConexao(props);
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
        new DialogAbout(this, false).setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

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

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
        configureConexao(new Properties());
    }//GEN-LAST:event_btnNovoActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        listLogs.removeAllElements();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        LogsEventos.OpenLog();        
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException 
                | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            LogsEventos.Gravar("LOOK AND FEEL - "+ex.getMessage());
        }

        EventQueue.invokeLater(() -> {
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
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSair;
    private javax.swing.JComboBox<String> cbConections;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JList<String> listConsole;
    private javax.swing.JList<String> listaTblas;
    private javax.swing.JMenuItem menuItemConfig;
    private javax.swing.JMenuItem menuItemExportar;
    private javax.swing.JMenuItem menuQuery;
    private javax.swing.JTabbedPane painelAbas;
    // End of variables declaration//GEN-END:variables
}
