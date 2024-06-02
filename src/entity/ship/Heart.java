package entity.ship;

import entity.Sound;
import entity.Stage;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Heart {
	private BufferedImage img;
	private int count;
	
	public Heart() {
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/assets/heart.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		count = 5;
	}
	
	public void init() {
		count = 5;
	}
	
	public void update() {
		if (count < 1) Stage.stage = Stage.lose;
	}
	
	public void add(int u) {
		count += u;
	}
	
	public void draw(Graphics2D g2, Ship ship) {
		if (Stage.stage != Stage.play) return;
		if (count < 1) return;
		int w = ship.getW() / 10;
		int s = w / 2;
		int len = w * count + s * (count - 1);
		int x = (int)ship.getX() - len / 2, y = (int)ship.getY() + ship.getH() / 2 + 5;
		for (int i = 0; i < count; i++) {
			g2.drawImage(img, x, y, w, w, null);
			x += (w + s);
		}
	}
	
	public int getCount() {
		return count;
	}
}
