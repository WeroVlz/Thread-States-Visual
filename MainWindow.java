import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.event.*;

/**
 * Main
 */
public class MainWindow extends JFrame{

    private static int WIDTH = 400;
    private static int HEIGHT = 580;

    public MainWindow(){
        setTitle("Main Window");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(new GridLayout(2, 1));

        JPanel labelPanel = new JPanel(new GridLayout(8,1));
        JLabel label1 = new JLabel("Universidad Panamericana");
        JLabel label2 = new JLabel("Fundamentos de programacion en paralelo");
        JLabel label3 = new JLabel("Edgar Velazquez, Jorge Vargas");
        JLabel label4 = new JLabel("0217557, 0237032");
        JLabel label5 = new JLabel("Dr. Juan Carlos Lopez Pimentel");
        JLabel label6 = new JLabel("26 de septiembre del 2023");
        ImageIcon imgIcon = new ImageIcon("logo-UP.png");
        JLabel label7 = new JLabel(imgIcon);
        labelPanel.add(label7);
        labelPanel.add(label1);
        labelPanel.add(label2);
        labelPanel.add(label3);
        labelPanel.add(label4);
        labelPanel.add(label5);
        labelPanel.add(label6);

        ImageBackgroundPanel panel = new ImageBackgroundPanel("pokeball.jpg");
        panel.setLayout(null);

        CircularButton button = new CircularButton("Start");
        button.setBounds(160, 86, 80, 90);

        button.addActionListener((e) ->{
            StatesWindow tw = new StatesWindow();
            tw.start();
        });  

        panel.add(button);

        
        getContentPane().add(panel);
        getContentPane().add(labelPanel);
        setVisible(true);
    }


    
    private class CircularButton extends JButton{

        private boolean hovered = false;
        private boolean pressed = false;

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            Shape ovalShape  = new Ellipse2D.Double(0, 0, width, height);

            if (hovered) {
                if (pressed){
                    g2d.setColor(getBackground().darker().darker());
                }else{
                    g2d.setColor(getBackground().darker());
                }
            } else {
                g2d.setColor(getBackground());
            }
            g2d.fill(ovalShape);

            g2d.setColor(getForeground());
            FontMetrics metrics = g.getFontMetrics(getFont());
            int x = (width - metrics.stringWidth(getText())) / 2;
            int y = ((height - metrics.getHeight()) / 2) + metrics.getAscent();
            g2d.drawString(getText(), x, y);
        }

        
        public CircularButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorder(new EmptyBorder(0, 0, 0, 0));

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    hovered = true;
                    repaint();
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    hovered = false;
                    repaint();
                }

                @Override
                public void mousePressed(java.awt.event.MouseEvent e){
                    pressed = true;
                    repaint();
                }
                @Override
                public void mouseReleased(java.awt.event.MouseEvent e){
                    pressed = false;
                    repaint();
                }
            });
        }
    }

    public static void main(String[] args) {
        new MainWindow();

    }
}