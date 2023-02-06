package parts;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel{
	
	private String messageGO= "Gave Over";
	private Ball ball;
	private Paddle paddle;
	private Brick[] bricks;
	private Timer timer;
	private boolean game = true;
	
	public Board() {
		initBoard();
	}

	private void initBoard() {
		addKeyListener(new TAdapter());
        setFocusable(true);
        setPreferredSize(new Dimension(Commons.WIDTH, Commons.HEIGHT));
		initGame();
		
	}
	//creating the objects for the board
	private void initGame() {
		bricks = new Brick[Commons.N_OF_BRICKS];
		ball = new Ball();
		paddle = new Paddle();
		
		int a=0;
		for(int i=0; i<5;i++) {
			for(int j=0; j<6;j++) {
				bricks[a] = new Brick(j*40+30, i*10+50);
				a++;
			}
		}
		timer = new Timer(Commons.PERIOD, new Game());
		timer.start();
	}
	//@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		var graphics = (Graphics2D) g; //Graphics to Graphics2D casting
		
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
		
		if(game) {
			drawObject(graphics);
		}else {
			gameFinished(graphics);
		}
		Toolkit.getDefaultToolkit().sync();
	}
	//drawing the objects
	private void drawObject(Graphics2D graphics) {
		
		//drawing the ball
		graphics.drawImage(ball.getImage(), ball.getX(), ball.getY(),
                ball.getImageWidth(), ball.getImageHeight(), this);
		//drawing the paddle
		graphics.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(),
                paddle.getImageWidth(), paddle.getImageHeight(), this);
		
		//drawing the bricks
		for(int i=0;i<Commons.N_OF_BRICKS;i++) {
			if(!bricks[i].destroyed()) {
				graphics.drawImage(bricks[i].getImage(), bricks[i].getX(),
                        bricks[i].getY(), bricks[i].getImageWidth(),
                        bricks[i].getImageHeight(), this);
			}
		}
	}
	private void gameFinished(Graphics2D graphics) {
		var font = new Font("Calibri", Font.BOLD, 20);
		FontMetrics fontMetrics = this.getFontMetrics(font);
		
		graphics.setColor(Color.BLACK);
		graphics.setFont(font);
		graphics.drawString(messageGO,
                (Commons.WIDTH - fontMetrics.stringWidth(messageGO)) / 2,
                Commons.WIDTH / 2);
	}

	private void doGame() {
		ball.move();
		paddle.move();
		collision();
		repaint();
	
	}
	private void endGame() {
		game = false;
		timer.stop();
	}
	private void collision() {
		if (ball.getRect().getMaxY() > Commons.BOTTOM_EDGE) {
			endGame();
		}
		for(int i=0, j=0; i<Commons.N_OF_BRICKS;i++) {
			if(bricks[i].destroyed()) {
				j++;
			}
			if(j==Commons.N_OF_BRICKS) {
				messageGO = "CONGRADULATIONS! YOU WON!";
				endGame();
			}
		}
		if((ball.getRect().intersects(paddle.getRect()))) {
			int paddleNewPos = (int) paddle.getRect().getMinX(); //double to int 
			int ballNewPos = (int) ball.getRect().getMinX();
			
			int one = paddleNewPos + 8;
			int two = paddleNewPos + 16;
			int three = paddleNewPos + 24;
			int four = paddleNewPos + 32;
			
			if(ballNewPos < one) {
				ball.setXDir(-1);
                ball.setYDir(-1);
			}
			if (ballNewPos >= one && ballNewPos < two) {

                ball.setXDir(-1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballNewPos >= two && ballNewPos < three) {

                ball.setXDir(0);
                ball.setYDir(-1);
            }

            if (ballNewPos >= three && ballNewPos < four) {

                ball.setXDir(1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballNewPos > four) {

                ball.setXDir(1);
                ball.setYDir(-1);
            }
		}
		
		for(int i =0; i<Commons.N_OF_BRICKS;i++) {
			if((ball.getRect().intersects(bricks[i].getRect()))) {
				
				int ballLeft = (int) ball.getRect().getMinX(); // double to int
				int ballHeight = (int) ball.getRect().getHeight();
				int ballWidth = (int) ball.getRect().getWidth();
				int ballTop = (int) ball.getRect().getMinY();
				
				var pointTop = new Point(ballLeft, ballTop - 1);
				var pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);
				var pointLeft = new Point(ballLeft - 1, ballTop);
				var pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
				
				if(!bricks[i].destroyed()) {
					if (bricks[i].getRect().contains(pointRight)) {
						ball.setXDir(-1);
					}
					else if (bricks[i].getRect().contains(pointLeft)) {

                        ball.setXDir(1);
                    }

                    if (bricks[i].getRect().contains(pointTop)) {

                        ball.setYDir(1);
                    } else if (bricks[i].getRect().contains(pointBottom)) {

                        ball.setYDir(-1);
                    }

                    bricks[i].setDestroyed(true);
				}
	
			}
		}
	}
	
	private class TAdapter extends KeyAdapter{
		
		@Override
		public void keyPressed(KeyEvent kEvent) {
			paddle.keyPressed(kEvent);
		}
		@Override
		public void keyReleased(KeyEvent kEvent) {
			paddle.keyReleased(kEvent);
		}
	}
	private class Game implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			doGame();
			
		}
		
	}
	
	
	
	
	

}
