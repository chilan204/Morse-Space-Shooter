package entity.ship;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Lazer {
	private BufferedImage[] img = new BufferedImage[4];
	
	private int x1, y1, x2, y2;
	private double x, cc;
	
	public Lazer() {
		try {
			for (int i = 0; i < 4; i++) {
				img[i] = ImageIO.read(getClass().getResourceAsStream("/assets/lazer" + i + ".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		x = -1;
	}
	
	public void update() {
		if (x < 0) return;
		x += cc;
		if (x >= 3) cc *= -1;
	}
	
	public void draw(Graphics2D g2) {
		if (x >= 0) g2.drawImage(img[(int)x], x1, y1, x2, y2, 0, 0, 100, 100, null);
	}
	
	public void set(int x1, int y1, int x2, int y2) {
		this.x = 0; this.x1 = x1; this.y1 = y1; this.x2 = x2; this.y2 = y2;
		cc = 0.4;
	}
	
	public void stop() {
		x = -1;
	}
}
