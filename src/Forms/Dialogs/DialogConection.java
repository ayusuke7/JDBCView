package Forms.Dialogs;

import Utils.LogsEventos;
import Utils.Propriedades;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author YU7
 */
public class DialogConection extends javax.swing.JDialog {

    private final Propriedades propriedades = new Propriedades();
    private final Properties prop;
    private String _DRIVER;

    private boolean confirma = false;

    public DialogConection(java.awt.Frame parent, boolean modal, Properties props) {
        super(parent, modal);
        initComponents();
        this.prop = props;

        if (props.isEmpty()) {
            this.setTitle("NOVA CONEXÃO");
            tfNome.setEditable(true);
            selectDriver();
        } else {
            getConfiguracoes();
            btnExcluir.setEnabled(true);
        }
    }

    private void getConfiguracoes() {
        
        _DRIVER = prop.getProperty("prop.server.driver");
        
        if(_DRIVER.contains("mysql")){
            cbDrivers.setSelectedIndex(0);
        }
        if(_DRIVER.contains("postgres")){
            cbDrivers.setSelectedIndex(1);
        }
        if(_DRIVER.contains("access")){
            cbDrivers.setSelectedIndex(2);
        }

        
        tfNome.setText(prop.getProperty("prop.server.nome"));
        tfEndereco.setText(prop.getProperty("prop.server.endereco"));
        spnPort.setValue(Integer.parseInt(prop.getProperty("prop.server.porta")));
        tfDatabase.setText(prop.getProperty("prop.server.database"));
        tfUser.setText(prop.getProperty("prop.server.usuario"));
        tfPass.setText(prop.getProperty("prop.server.senha"));
        tfUrl.setText(prop.getProperty("prop.server.url"));
        this.setTitle("Conexão: " + tfNome.getText());

    }

    private void setConfiguracao() {

        prop.setProperty("prop.server.driver", _DRIVER);
        prop.setProperty("prop.server.nome", tfNome.getText().trim());
        prop.setProperty("prop.server.endereco", tfEndereco.getText().trim());
        prop.setProperty("prop.server.porta", spnPort.getValue().toString());
        prop.setProperty("prop.server.database", tfDatabase.getText().trim());
        prop.setProperty("prop.server.usuario", tfUser.getText().trim());
        prop.setProperty("prop.server.senha", tfPass.getText());
        prop.setProperty("prop.server.url", tfUrl.getText().trim());

        propriedades.setProperties("", prop);

    }

    private void selectDriver() {

        int index = cbDrivers.getSelectedIndex();

        switch (index) {
            case 0:
                _DRIVER = "com.mysql.jdbc.Driver";
                tfUrl.setText("jdbc:mysql://localhost:3306/" + tfDatabase.getText());
                spnPort.setValue(Integer.parseInt("3306"));
                tfUser.setEditable(true);
                tfUser.setText("root");
                spnPort.setEnabled(true);
                tfEndereco.setEditable(true);
                tfEndereco.setText("");
                tfDatabase.setText("");
                break;
            case 1:
                _DRIVER = "org.postgresql.Driver";
                tfUrl.setText("jdbc:postgresql://localhost:5432/" + tfDatabase.getText());
                spnPort.setValue(Integer.parseInt("5432"));
                tfUser.setEditable(true);
                tfUser.setText("postgres");                
                spnPort.setEnabled(true);
                tfEndereco.setEditable(true);
                tfEndereco.setText("");
                tfDatabase.setText("");
                break;
            case 2:
                _DRIVER = "net.ucanaccess.jdbc.UcanaccessDriver"; 
                tfEndereco.setText("Selecione o arquivo...");
                tfEndereco.setEditable(false);                
                tfUrl.setText("");
                tfUser.setEditable(false);
                tfUser.setText("");
                spnPort.setValue(0);
                spnPort.setEnabled(false);
                tfDatabase.setText("");
                break;
        }

    }

    private void selecionarArquivoAccess() {

        JFileChooser busca = new JFileChooser(".");
        FileNameExtensionFilter filtro = new FileNameExtensionFilter(
                "Arquivos MS Access",
                new String[]{"mdb", "accdb"});
        busca.setFileFilter(filtro);
        busca.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if (busca.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File arquivo = busca.getSelectedFile();
            String path = arquivo.getAbsolutePath().replace("\\", "//");
            
            tfEndereco.setText(path);
            tfDatabase.setText(arquivo.getName());   
            
            tfUrl.setText("jdbc:ucanaccess://" + path + ";memory=false");
        }

    }

    private void criarArquivoConfiguracao() {

        try {
            File file = new File(tfNome.getText().trim() + ".properties");
            try (BufferedWriter buff = new BufferedWriter(new FileWriter(file))) {
                buff.write("prop.server.driver = " + _DRIVER);
                buff.newLine();
                buff.write("prop.server.nome = " + tfNome.getText().trim());
                buff.newLine();
                buff.write("prop.server.endereco = " + tfEndereco.getText().trim());
                buff.newLine();
                buff.write("prop.server.porta = " + spnPort.getValue());
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
            JOptionPane.showMessageDialog(null,
                    "NOVA CONEXÃO CRIADA",
                    "AVISO",
                    JOptionPane.INFORMATION_MESSAGE);
            setConfirma(true);
            this.dispose();
        } catch (IOException ex) {
            LogsEventos.Gravar(ex.getMessage());
        }
    }

    private void montaUrl() {

        if (cbDrivers.getSelectedIndex() < 2) {

            String tipo = cbDrivers.getSelectedItem().toString();
            String ende = tfEndereco.getText().trim();
            String port = spnPort.getValue().toString();
            String data = tfDatabase.getText().trim();

            if (!ende.equals("") && !port.equals("") && !data.equals("")) {
                tfUrl.setText("jdbc:" + tipo.toLowerCase() + "://" + ende + ":" + port + "/" + data);
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cbDrivers = new javax.swing.JComboBox<>();
        tfEndereco = new javax.swing.JTextField();
        tfDatabase = new javax.swing.JTextField();
        tfUser = new javax.swing.JTextField();
        tfPass = new javax.swing.JPasswordField();
        tfNome = new javax.swing.JTextField();
        tfUrl = new javax.swing.JTextField();
        spnPort = new javax.swing.JSpinner();
        jToolBar1 = new javax.swing.JToolBar();
        btnSalvar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Conexões");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cbDrivers.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MySQL", "PostgreSQL", "MS Access" }));
        cbDrivers.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbDriversItemStateChanged(evt);
            }
        });

        tfEndereco.setText("localhost");
        tfEndereco.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "URL"));
        tfEndereco.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfEnderecoFocusLost(evt);
            }
        });
        tfEndereco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfEnderecoMouseClicked(evt);
            }
        });

        tfDatabase.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Database"));
        tfDatabase.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfDatabaseFocusLost(evt);
            }
        });

        tfUser.setText("root");
        tfUser.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Usuario"));

        tfPass.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Senha"));

        tfNome.setEditable(false);
        tfNome.setBackground(new java.awt.Color(255, 255, 255));
        tfNome.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Nome"));
        tfNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfNomeFocusLost(evt);
            }
        });

        tfUrl.setEditable(false);
        tfUrl.setBackground(new java.awt.Color(255, 255, 255));
        tfUrl.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "URL de Conexão"));

        spnPort.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spnPort.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Porta"));
        spnPort.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnPortStateChanged(evt);
            }
        });

        jToolBar1.setFloatable(false);
        jToolBar1.setOpaque(false);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Save.png"))); // NOI18N
        btnSalvar.setText("SALVAR");
        btnSalvar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSalvar.setMaximumSize(new java.awt.Dimension(250, 35));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSalvar);

        jLabel1.setText("  ");
        jToolBar1.add(jLabel1);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Delete.png"))); // NOI18N
        btnExcluir.setText("EXCLUIR");
        btnExcluir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnExcluir.setEnabled(false);
        btnExcluir.setMaximumSize(new java.awt.Dimension(250, 35));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        jToolBar1.add(btnExcluir);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cbDrivers, javax.swing.GroupLayout.Alignment.LEADING, 0, 251, Short.MAX_VALUE)
                            .addComponent(tfNome)
                            .addComponent(tfEndereco, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(spnPort, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfDatabase, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfUrl, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfPass, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfUser, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(13, 13, 13))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbDrivers, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnPort, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfUser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfPass, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfUrl, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void tfDatabaseFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfDatabaseFocusLost
        // TODO add your handling code here:
        montaUrl();
    }//GEN-LAST:event_tfDatabaseFocusLost

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:

        if (!tfNome.getText().equals("")
                && !tfDatabase.getText().equals("")) {

            if (this.getTitle().equals("NOVA CONEXÃO")) {
                criarArquivoConfiguracao();
            } else {
                setConfiguracao();
            }

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
                System.gc();
                if (arquivo.delete()) {
                    setConfirma(true);
                    this.dispose();
                }
            }
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void spnPortStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnPortStateChanged
        // TODO add your handling code here:
        montaUrl();
    }//GEN-LAST:event_spnPortStateChanged

    private void tfEnderecoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfEnderecoMouseClicked
        // TODO add your handling code here:
        if(!tfEndereco.isEditable()){
            selecionarArquivoAccess();
        }
    }//GEN-LAST:event_tfEnderecoMouseClicked

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JSpinner spnPort;
    private javax.swing.JTextField tfDatabase;
    private javax.swing.JTextField tfEndereco;
    private javax.swing.JTextField tfNome;
    private javax.swing.JPasswordField tfPass;
    private javax.swing.JTextField tfUrl;
    private javax.swing.JTextField tfUser;
    // End of variables declaration//GEN-END:variables
}
