import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageHandler extends ImageBackgroundPanel implements Runnable, MouseListener{

    static final Color GREY = new Color(34,34,36);

    private JLabel pokemonGifLabel;
    private boolean running = true;
    public boolean interrupt = false;
    private StatesWindow win;

    public ImageHandler(String imagePath, StatesWindow window){
        super(imagePath);
        setBackground(GREY);
        setLayout(null);
        addMouseListener(this);
        win = window;
        ImageIcon newImageIcon = new ImageIcon("egg.gif");
        pokemonGifLabel = new JLabel(newImageIcon);
        pokemonGifLabel.setBounds(147, 90, 100,100);
        add(pokemonGifLabel);
    }



    @Override
    public void run() {
        while(running){
            ImageIcon runnableImageIcon = new ImageIcon("runnable.gif");
            pokemonGifLabel.setIcon(runnableImageIcon);
            
            try{
                if(interrupt){
                    ImageIcon sleepImageIcon = new ImageIcon("timed_waiting.png");
                    pokemonGifLabel.setIcon(sleepImageIcon);
                    Thread.sleep(99999999);
                }
            }catch(InterruptedException e){
                    if(win.imageHandlerThread.isInterrupted()){
                        System.out.println("Continue");
                    }
            }
        }
        ImageIcon terminatedImageIcon = new ImageIcon("terminated.png");
        pokemonGifLabel.setIcon(terminatedImageIcon);
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON3){
            running = false;
        }else if (e.getButton() == MouseEvent.BUTTON2){
            interrupt = true;
        }else if (e.getButton() == MouseEvent.BUTTON1){
            if(interrupt){
                win.imageHandlerThread.interrupt();
                interrupt = false;
            }
        }
    }



    @Override
    public void mousePressed(MouseEvent e) {

    }



    @Override
    public void mouseReleased(MouseEvent e) {

    }



    @Override
    public void mouseEntered(MouseEvent e) {

    }



    @Override
    public void mouseExited(MouseEvent e) {

    }
    
}
