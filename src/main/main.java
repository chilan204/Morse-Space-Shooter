package main;
import javax.swing.JFrame;

public class main {
	public static void main(String[] args) {
		JFrame window = new JFrame();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Morse Space Shooter");
		
		Game game = new Game();
		window.add(game);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		game.startThread();
	}
}
