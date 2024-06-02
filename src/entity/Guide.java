package entity;

import entity.Sound;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.Delayed;

import javax.imageio.ImageIO;

public class Guide {
	private BufferedImage[] img = new BufferedImage[5];
	private int cur = 0;
	
	private KeyHandler key;
	private int enter_release = 0;
	
	private Sound sound;
	
	public Guide(KeyHandler key) {
		try {
			for (int i = 0; i < 5; i++) {
				img[i] = ImageIO.read(getClass().getResourceAsStream("/assets/guide" + i + ".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.key = key;
		sound = new Sound();
	}
	
	public void update() {
		if (Stage.stage != Stage.guide) { sound.stop(); return; }
		
		sound.play(Sound.guide);
		
		if (key.enterPressed == true) enter_release = 1;
		if (key.enterPressed == false && enter_release == 1) {
			enter_release = 0;
			cur++;
			if (cur == 5) Stage.stage = Stage.play;
		}
	}
	
	public void draw(Graphics2D g2) {
		if (Stage.stage == Stage.guide) {
			g2.drawImage(img[cur], 0, 0, null);
		}
	}
}
