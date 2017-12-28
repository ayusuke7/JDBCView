package Forms;

import Connection.Conection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author YU7
 */
public class FormRegistros extends javax.swing.JPanel {

    private final Conection con;

    public FormRegistros(Conection con, String sql) {
        initComponents();
        this.con = con;
        listarRegistros(sql);
    }
    
    private void listarRegistros(String sql) {

        DefaultTableModel modelo = new DefaultTableModel();

        try {

            con.executaSQL(sql);
            ResultSetMetaData rsMeta = con.rs.getMetaData();
            int tam = rsMeta.getColumnCount();
            String[] colunas = new String[tam];

            for (int i = 0; i < tam; i++) {
                colunas[i] = rsMeta.getColumnName(i + 1);
                modelo.addColumn(colunas[i]);
            }

            if (con.rs.first()) {
                do {
                    Object[] registros = new Object[tam];
                    for (int i = 0; i < tam; i++) {
                        registros[i] = con.rs.getString(colunas[i]);
                    }
                    modelo.addRow(registros);
                } while (con.rs.next());
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "ERRO listarRegistros : " + ex,
                    "ERRO", JOptionPane.ERROR_MESSAGE);
        }

        tblaRegistros.setModel(modelo);
        for (int i = 0; i < tblaRegistros.getColumnCount(); i++) {
            tblaRegistros.getColumnModel().getColumn(i).setPreferredWidth(120);
        }
        tblaRegistros.changeSelection(0, 0, false, false);

    }

    private void visualizarRegistro() {

        if (tblaRegistros.getModel().getRowCount() > 0) {
            
            String view = "";

            for (int i = 0; i < tblaRegistros.getColumnCount(); i++) {
                String coluna = tblaRegistros.getColumnName(i);
                view += coluna + " >> " + tblaRegistros.getValueAt(tblaRegistros.getSelectedRow(), i) + "\n";
            }

            if (view.length() > 500) {
                FormDialogView dialog = new FormDialogView(new JFrame(), false, view);
                dialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, view, "Registro", JOptionPane.INFORMATION_MESSAGE);
            }
            
        }

    }

    private void inserirRegistro(){
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblaRegistros = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        btnView = new javax.swing.JButton();

        tblaRegistros.setAutoCreateRowSorter(true);
        tblaRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblaRegistros.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblaRegistros.setRowHeight(20);
        tblaRegistros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblaRegistrosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblaRegistros);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        btnView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Display.png"))); // NOI18N
        btnView.setToolTipText("Visualizar registro");
        btnView.setFocusable(false);
        btnView.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnView.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActionPerformed(evt);
            }
        });
        jToolBar1.add(btnView);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblaRegistrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblaRegistrosMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tblaRegistrosMouseClicked

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        // TODO add your handling code here:
        visualizarRegistro();
    }//GEN-LAST:event_btnViewActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnView;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    public javax.swing.JTable tblaRegistros;
    // End of variables declaration//GEN-END:variables
}
