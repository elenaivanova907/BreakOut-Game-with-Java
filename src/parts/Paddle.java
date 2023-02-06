package parts;


import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Paddle extends Common{

    
    private int dx;
    
    
    
    public Paddle() {
        initPaddle();
    }

    private void initPaddle() {
        loadImage();
        getImageDimensions();

        resetState();

    }
    
    
    void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 1;
        }

    }

    void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 0;
        }

    }
    void move() {
        x += dx;

        if (x <= 0) {
            x = 0;

        }

        if (x >= Commons.WIDTH - imageWidth) {
            x = Commons.WIDTH - imageWidth;

        }

    }
    private void resetState() {

        x = Commons.INIT_PADDLE_X;
        y = Commons.INIT_PADDLE_Y;
    }
    private void loadImage() {

        var ii = new ImageIcon("src/resources/paddle.png");
        image = ii.getImage();

    }

}
