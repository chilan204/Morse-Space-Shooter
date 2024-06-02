package entity.map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import entity.ship.Ship;

public class Map {
	private BufferedImage img;
	
	private Vector<Point> vec = new Vector<Point>();
	
	public Map() {
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/assets/map.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int w = 90, h = 70;
		for (int i = 0; i < 1300 / w; i++) {
			for (int j = 0; j < 750 / h; j++) {
				vec.add(new Point(i * w, j * h, w, h));
			}
		}
	}
	
	public void update() {
		for (Point aa : vec) aa.update();
	}
	
	public void draw(Graphics2D g2, Ship ship) {

		g2.drawImage(img, 0, 0, main.Game.WIDTH, main.Game.HEIGHT, null);
		for (Point aa : vec) aa.draw(g2, ship);
	}
}
