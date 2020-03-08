package Renderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

public class LinhaTableTextArea extends JTextArea implements TableCellRenderer {

    public LinhaTableTextArea() {
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(true);
    }

    public void setFonte(Font font) {
        setFont(font);
    }

    @Override
    public Component getTableCellRendererComponent(JTable jTable,
            Object obj, boolean isSelected, boolean hasFocus, int row,
            int column) {

        setText((String) obj);

        if (isSelected) {
            setForeground(jTable.getSelectionForeground());
            setBackground(jTable.getSelectionBackground());
        } else {
            setForeground(jTable.getForeground());
            if (row % 2 == 0) {
                setBackground(new Color(204, 204, 204));
            } else {
                setBackground(new Color(255, 255, 255));
            }
        }

        int larg = jTable.getColumnModel().getColumn(column).getWidth();
        int altu = getPreferredSize().height;

        setSize(larg, altu);

        if (jTable.getRowHeight(row) != getPreferredSize().height) {
            jTable.setRowHeight(row, getPreferredSize().height);
        }

        return this;

    }

}
