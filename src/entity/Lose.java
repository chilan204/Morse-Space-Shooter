package entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.meteor.Exploid;

public class Lose {
	private BufferedImage img;
	
	private KeyHandler key;
	
	private Exploid exploid;
	private int exploidDone;
	
	private Font font;
	
	private Sound sound;
	
	
	public Lose(KeyHandler key) {
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/assets/over.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.key = key;
		
		exploid = new Exploid();
		exploidDone = 0;
		
		font = new Font("Candara", Font.BOLD, 50);
		
		sound = new Sound();
	}
	
	public void update() {
		if (Stage.stage == Stage.lose) {
			if (exploidDone == 0) {
				exploidDone = 1;
				exploid.set(main.Game.WIDTH / 2, main.Game.HEIGHT / 2, 1000, 750);
			}
			else if (exploidDone == 1) {
				if (!exploid.isExploding()) { exploidDone = 2; sound.play(Sound.lose); }
			}
			else if (exploidDone == 2) {
				if (key.enterPressed == true) {
					Stage.stage = Stage.nothing;
					sound.stop();
				}
			}
		}
		else exploidDone = 0;
		
		exploid.update();
	}
	
	public void draw(Graphics2D g2, Score score) {
		
		if (Stage.stage == Stage.lose) {
			if (exploidDone == 1) exploid.draw(g2);
			else if (exploidDone == 2) {
				g2.drawImage(img, 150, 0, null);
				
				g2.setColor(Color.cyan);
				g2.setFont(font);
				g2.drawString("score: " + Integer.toString(score.getScore()), 550, 600);
			}
		}
	}
}
