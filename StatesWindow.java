import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StatesWindow extends Thread{

    static final Color RED = new Color(238, 65, 49);
    static final Color GREY = new Color(34,34,36);

    private static int WIDTH = 400;
    private static int HEIGHT = 400;
    private static JTextArea threadState;
    public ImageHandler imageHandler;
    public Thread imageHandlerThread;

    StatesWindow(){
        JFrame frame = new JFrame("Thread States");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);

        ImageBackgroundPanel imagePanel = new ImageBackgroundPanel("PokeballLogo.png");
        imagePanel.setBackground(GREY);

        threadState = new JTextArea();
        threadState.setRows(2);

        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(RED);

        try{
            BufferedImage originalIcon = ImageIO.read(new File("pokeballicon.png"));

            ImageIcon newIcon = resizeImage(originalIcon, 30, 30);
            JLabel pokeballLabel = new JLabel(newIcon);
            controlPanel.add(pokeballLabel);
        }catch(IOException e){
            e.printStackTrace();
        }

        JButton createButton = new JButton("Create Thread");
        JButton startButton = new JButton("Run thread");
        startButton.setEnabled(false);
        controlPanel.add(createButton);
        controlPanel.add(startButton);

        frame.getContentPane().add(imagePanel, BorderLayout.CENTER);
        frame.getContentPane().add(controlPanel, BorderLayout.NORTH);
        frame.getContentPane().add(threadState, BorderLayout.SOUTH);
        
        createButton.addActionListener((e) -> {
            imageHandler = new ImageHandler("PokeballLogo.png", this);
            imageHandlerThread = new Thread(imageHandler);

            frame.getContentPane().add(imageHandler, BorderLayout.CENTER);
            createButton.setEnabled(false);
            startButton.setEnabled(true);
        });

        startButton.addActionListener((e) -> {
            imageHandlerThread.start();
        });
        
        frame.setVisible(true);
    }
    
    public void run(){
        while(true){
            System.out.flush();
            if(imageHandlerThread != null){
                String threadStateText = "Thread State: " + imageHandlerThread.getState();
                
                threadState.setText(threadStateText);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private ImageIcon resizeImage(BufferedImage originalImage, int width, int height){

        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        
        return new ImageIcon(resizedImage);
    }
}
