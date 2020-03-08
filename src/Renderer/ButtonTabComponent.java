package Modell.Renderer;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;

public class ButtonTabComponent extends JPanel {

    private final JTabbedPane pane;

    public ButtonTabComponent(final JTabbedPane pane) {
        //unset default FlowLayout' gaps
        super(new FlowLayout(FlowLayout.CENTER, 0, 0));
        
        if (pane == null) {
            throw new NullPointerException("TabbedPane is null");
        }
        
        this.pane = pane;
        
        setOpaque(false);
        //faz a JLabel ler o título do JTabbedPane
        JLabel label = new JLabel() {
            @Override
            public String getText() {
                int i = pane.indexOfTabComponent(ButtonTabComponent.this);
                if (i != -1) {
                    return pane.getTitleAt(i);
                }
                return null;
            }

        };        
        
        add(label);
        //adiciona mais espaço entre a label e o botão
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        JButton button = new TabButton();
        add(button);
        //adiciona mais espaço para o topo do componente
        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
        
    }

    
    private class TabButton extends JButton implements ActionListener {

        public TabButton() {
            int size = 20;
            setPreferredSize(new Dimension(size, size));
            setToolTipText("Fechar");
            //Faz o botão ser igual para todas as Laf's
            setUI(new BasicButtonUI());
            //Torna-o transparente
            setContentAreaFilled(false);
            //Não necessidade de estar com focusable
            setFocusable(false);
            setBorder(BorderFactory.createEtchedBorder());
            setBorderPainted(false);
            //Fazendo um efeito de rolagem
            //usamos o mesmo listener para todos os botões
            addMouseListener(buttonMouseListener);
            setRolloverEnabled(true);
            //Fecha a guia apropriada, clicando no botão
            addActionListener(this);            
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int i = pane.indexOfTabComponent(ButtonTabComponent.this);
            if (i != -1) {
                pane.remove(i);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            //mudança na imagem para botões pressionados
            if (getModel().isPressed()) {
                g2.translate(1, 1);
            }
            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.BLACK);
            if (getModel().isRollover()) {
                g2.setColor(Color.MAGENTA);
            }
            int delta = 6;
            g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
            g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);
            g2.dispose();
        }
    }
    
    private final static MouseListener buttonMouseListener = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            Component component = e.getComponent();
            if (component instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) component;
                button.setBorderPainted(true);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            Component component = e.getComponent();
            if (component instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) component;
                button.setBorderPainted(false);
            }
        }
    };
}
