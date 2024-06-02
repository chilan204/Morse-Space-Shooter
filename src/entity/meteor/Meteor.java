package entity.meteor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import entity.ship.Ship;
import main.Game;
import entity.Score;
import entity.Sound;
import entity.morse.Morse;

public class Meteor {
	private BufferedImage img;
	
	private double x, y;
	private int w, h;
	private int type;
	private int val;
	private double dx, dy;
	private double angle, dau;
	
	private Font font;
	
	private boolean destroyed;
	
	private Exploid exploid;
	private boolean exploidDone;
	
	private ExtraPoint ep;
	
	Random random;
	
	public Meteor() {
		random = new Random();
		
		val = random.nextInt(26);
		w = h = random.nextInt(31) + 70;
		type = random.nextInt(7);
		if (type > 4) type = 4;
		
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/assets/meteor" + type + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int aaa = random.nextInt(10);
		if (aaa == 0) {
			x = 100;
			y = (double) random.nextInt(200);
		}
		else if (aaa == 1) {
			x = 1200;
			y = (double) random.nextInt(200);
		}
		else {
			y = -50;
			x = (double) random.nextInt(1001) + 150;
		}
		
		double speed = (double) (random.nextInt(10)) / 10.0 + 1.2;
		double nx = (double) random.nextInt(1001) + 150, ny = 750;
		
		dx = nx - x; dy = ny - y;
		double dd = Math.sqrt(dx * dx + dy * dy);
		dx /= dd; dy /= dd;
		dx *= speed; dy *= speed;
		
		font = new Font("Candara", Font.BOLD, 24);
		
		dau = (double) (random.nextInt(2) * 2 - 1);
		
		destroyed = false;
		exploidDone = false;
		
		exploid = new Exploid();
		
		ep = new ExtraPoint();
	}
	
	public void destroy() {
		exploid.set((int)x, (int)y, w + 20, h + 20);
		destroyed = true;
	}
	
	public void setExtraPoint(Ship ship, Score score) {
		if (type == 4) {
			int aaa = random.nextInt(4);
			if (aaa == 0) {
				if (ship.getHeart() == 1) {
					ep.set(2, x, y, 1);
					ship.addHeart(1);
				}
				else {
					aaa = random.nextInt(2);
					ep.set(2, x, y, aaa);
					ship.addHeart(aaa * 2 - 1);
				}
			}
			
			else if (aaa == 1) {
				int ppp = (score.getScore() >= 0) ? 1 : 0;
				ep.set(3, x, y, ppp);
				score.add(score.getScore());
			}
			
			else {
				aaa = random.nextInt(31) - 20;
				ep.set(1, x, y, aaa);
				score.add(aaa);
			}
		}
		else {
			ep.set(1, x, y, 1);
			score.add(1);
		}
		
		if (ep.good()) exploid.setSoundType(Sound.point);
		else exploid.setSoundType(Sound.exploid);
	}
	
	public void update(Ship ship, Morse morse, Score score) {
		if (destroyed) {
			if (!exploid.isExploding()) exploidDone = true;
			
			exploid.update();
			ep.update();
			
			return;
		}
		
		x += dx; y += dy;
		angle += 0.02 * dau;
		
		if (square(x - ship.getX()) + square(y - ship.getY()) < square(w / 2 + ship.getW() / 2 - 5)) {
			ship.addHeart(-1);
			destroy();
			return;
		}
		
		if (morse.getVal() == val) {
			setExtraPoint(ship, score);
			
			ship.setLazer(this);
			destroy();
			
			morse.setVal(-1);
			
			return;
		}
		
		if (y - h / 2 > Game.HEIGHT) destroyed = true;
	}
	
	public void draw(Graphics2D g2) {
		if (!destroyed) {
			AffineTransform originalTransform = g2.getTransform();
			
			g2.translate(x, y);
			g2.rotate(angle);
			g2.drawImage(img, (int)-w/2, (int)-h/2, w, h, null);
			g2.setTransform(originalTransform);
			
			g2.setColor(Color.white);
			g2.setFont(font);
			g2.drawString(Character.toString((char)(val + 'A')), (int)x - 6, (int)y + h/2 + 20);
		}
		else {
			exploid.draw(g2);
			ep.draw(g2);
		}
	}
	
	public int getVal() {
		return val;
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
	
	public boolean isDestroyed() {
		return destroyed;
	}
	
	public boolean removeAble() {
		return exploidDone;
	}
	
	public int getType() {
		return type;
	}
	
	public void stop() {
		exploid.stop();
		destroyed = true;
		exploidDone = true;
	}
	
	public double square(double x) {
		return x * x;
	}
}
