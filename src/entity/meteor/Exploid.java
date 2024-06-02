package entity.meteor;

import entity.Sound;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Exploid {
	private BufferedImage[] img = new BufferedImage[10];
	
	private int x, y, w, h;
	private double c;
	
	private Sound sound;
	private int soundType;
	
	public Exploid() {
		try {
			for (int i = 0; i < 10; i++) {
				img[i] = ImageIO.read(getClass().getResourceAsStream("/assets/exploid" + i + ".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		c = -1;
		
		sound = new Sound();
		soundType = Sound.exploid;
	}
	
	public void update() {
		if (c < 0) { sound.stop(); return; }
		sound.play(soundType);
		c += 0.3;
		if (c >= 10) c = -1;
	}
	
	public void draw(Graphics2D g2) {
		if (c >= 0) g2.drawImage(img[(int)c], x - w / 2, y - h / 2, w, h, null);
	}
	
	public void set(int x, int y, int w, int h) {
		this.c = 0;
		this.x = x; this.y = y; this.w = w; this.h = h;
	}
	
	public boolean isExploding() {
		return (c >= 0);
	}
	
	public void stop() {
		c = -1;
		sound.stop();
	}
	
	public void setSoundType(int u) {
		soundType = u;
	}
}
