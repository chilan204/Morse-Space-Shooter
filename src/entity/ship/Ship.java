package entity.ship;

import entity.Stage;
import entity.Sound;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.KeyHandler;
import entity.meteor.Meteor;

public class Ship {
	private double x, y, speed;
	private int w, h;
	private BufferedImage img;
	private KeyHandler key;
	private boolean move;
	
	private Heart heart;
	
	private Sound sound;
	
	private Lazer lazer;
	
	public Ship(KeyHandler key) {
		x = 650; y = 500;
		w = 100; h = 100;
		speed = 6;
		
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/assets/ship.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.key = key;
		
		heart = new Heart();
		
		sound = new Sound();
		
		lazer = new Lazer();
	}
	
	public void init() {
		x = 650; y = 500;
		heart.init();
	}
	
	public void setLazer(Meteor meteor) {
		lazer.set((int)x, (int)y, (int)meteor.getX(), (int)meteor.getY());
	}
	
	public void update() {
		if (Stage.stage != Stage.play) { clear(); return; }
		
		move = false;
		if (key.up == true)    { y -= speed; move = true; }
		if (key.down == true)  { y += speed; move = true; }
		if (key.left == true)  { x -= speed; move = true; }
		if (key.right == true) { x += speed; move = true; }
		
		if (x < w / 2 + 150) x = w / 2 + 150;
		if (x > main.Game.WIDTH - w / 2 - 150) x = main.Game.WIDTH - w / 2 - 150;
		if (y < h / 2) y = h / 2;
		if (y > main.Game.HEIGHT - h / 2) y = main.Game.HEIGHT - h / 2;
		
		if (move) sound.play(Sound.move);
		else sound.stop();
		
		heart.update();
		
		lazer.update();
	}
	
	public void draw(Graphics2D g2) {
		if (Stage.stage == Stage.play) {
			g2.drawImage(this.img, (int)x - w / 2, (int)y - h / 2, w, h, null);
			heart.draw(g2, this);
			lazer.draw(g2);
		}
	}
	
	public void addHeart(int u) {
		heart.add(u);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public int getW() {
		return w;
	}
	
	public int getH() {
		return h;
	}
	
	public int getHeart() {
		return heart.getCount();
	}
	
	public void clear() {
		sound.stop();
		lazer.stop();
	}
}
