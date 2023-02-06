package parts;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Game extends JFrame implements Commons{
	
	public Game() {
		board();
	}
	public void board() {
		add(new Board());
		setTitle("Breakout Game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(Commons.WIDTH, Commons.HEIGHT);
		setLocationRelativeTo(null);
		setResizable(false);
		pack();
	}

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				var game = new Game();
				game.setVisible(true);
			}
		});
		
		

	}

}
