package entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Score {
	private int score;
	private Font font;
	
	public Score() {
		score = 0;
		font = new Font("Candara", Font.BOLD, 40);
	}
	
	public void init() {
		score = 0;
	}
	
	public void draw(Graphics2D g2) {
		if (Stage.stage == Stage.play) {
			g2.setColor(Color.cyan);
			g2.setFont(font);
			
			score = Math.max(score, -100000000);
			
			g2.drawString("score: " + score, 150, 730);
		}
	}
	
	public void add(int u) {
		score += u;
	}
	
	public int getScore() {
		return score;
	}
}
