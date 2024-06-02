package entity.meteor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Score;

public class ExtraPoint {
	private double x, y, cc;
	private int type, point;
	private Font font;
	private Image img;
	
	public ExtraPoint() {
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/assets/heart.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		type = -1;
		font = new Font("Candara", Font.BOLD, 30);
	}
	
	public void update() {
		if (type < 0) return;
		cc += 0.3;
		if (cc > 10) type = -1;
	}
	
	public void draw(Graphics2D g2) {
		if (type < 0) return;
		
		g2.setFont(font);
		
		if (type == 1) {
			if (point < 0) {
				g2.setColor(Color.RED);
				g2.drawString("-" + (-point), (int)x, (int)(y - cc));
			}
			else {
				g2.setColor(Color.GREEN);
				g2.drawString("+" + point, (int)x, (int)(y - cc));
			}
		}
		
		else if (type == 3) {
			if (point == 1) {
				g2.setColor(Color.GREEN);
				g2.drawString("x2", (int)x, (int)(y - cc));
			}
			else {
				g2.setColor(Color.RED);
				g2.drawString("x2", (int)x, (int)(y - cc));
			}
		}
		
		else {
			if (point == 1) {
				g2.setColor(Color.GREEN);
				g2.drawString("+", (int)x, (int)(y - cc));
				g2.drawImage(img, (int)x + 20, (int)(y - cc - 15), 15, 15, null);
			}
			else {
				g2.setColor(Color.RED);
				g2.drawString("-", (int)x, (int)(y - cc));
				g2.drawImage(img, (int)x + 20, (int)(y - cc - 15), 15, 15, null);
			}
		}
	}
	
	public void set(int type, double x, double y, int point) {
		this.type = type; this.x = x; this.y = y; this.point = point;
		cc = 0;
	}
	
	public boolean good() {
		if (type == 1) return (point >= 0);
		else if (type == 2) return (point == 1);
		return (point == 1);
	}
}
