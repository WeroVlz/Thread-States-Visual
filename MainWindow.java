import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.event.*;

/**
 * Main
 */
public class MainWindow extends JFrame{

    private static int WIDTH = 400;
    private static int HEIGHT = 300;

    public MainWindow(){
        setTitle("Main Window");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);

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
        while(true){
            System.out.println("Threads n:" + Thread.activeCount());
        }
    }
}