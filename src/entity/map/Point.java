package entity.map;

import entity.Stage;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import entity.ship.Ship;

public class Point {
	private double x, y;
	private int w, h;
	
	private BufferedImage img, leg;
	
	public Point(int x, int y, int w, int h) {
		Random random = new Random();
		
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/assets/point.png"));
			leg = ImageIO.read(getClass().getResourceAsStream("/assets/leg" + random.nextInt(4) + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.x = random.nextInt(w) + x;
		this.y = random.nextInt(h) + y;
		this.w = this.h = 3;
	}
	
	public void update() {
		y += 2;
		if (y >= main.Game.HEIGHT) y = 0;
	}
	
	public double square(double x) {
		return x * x;
	}
	
	public void draw(Graphics2D g2, Ship ship) {
		g2.drawImage(img, (int)x - w / 2, (int)y - h / 2, w, h, null);
		
		if (Stage.stage != Stage.play) return;
		
		if (square(x - ship.getX()) + square(y - ship.getY()) < square(ship.getW() * 2)) {
			g2.drawImage(leg, (int)x, (int)y, (int)ship.getX(), (int)ship.getY(), 0, 0, leg.getWidth(), leg.getHeight(), null);
		}
	}
}
