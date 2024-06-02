package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import entity.*;
import entity.map.Map;
import entity.meteor.Exploid;
import entity.meteor.ExtraPoint;
import entity.meteor.MeteorsHandler;
import entity.morse.Morse;
import entity.morse.MorseCode;
import entity.ship.Lazer;
import entity.ship.Ship;

public class Game extends JPanel implements Runnable {
	public static final int WIDTH = 1300;
	public static final int HEIGHT = 750;
	
	int FPS = 60;
	
	Thread thread;
	
	KeyHandler key = new KeyHandler();
	Ship ship = new Ship(key);
	Map map = new Map();
	Score score = new Score();
	Morse morse = new Morse(key);
	MeteorsHandler meteors = new MeteorsHandler();
	Lose lose = new Lose(key);
	MorseCode ms = new MorseCode();
	Guide guide = new Guide(key);
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(key);
		this.setFocusable(true);
	}
	
	public void startThread() {
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		Stage.stage = 0;
		
		while (thread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			if (delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}
	}
	
	public void init() {
		meteors.init();
		score.init();
		ship.init();
		Stage.stage = Stage.play;
	}
	
	public void update() {
		map.update();
		morse.update();
		ship.update();
		meteors.update(morse, score, ship);
		guide.update();
		lose.update();
		
		if (Stage.stage == Stage.nothing) {
			init();
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		map.draw(g2, ship);
		ship.draw(g2);
		meteors.draw(g2);
		lose.draw(g2, score);
		score.draw(g2);
		morse.draw(g2);
		ms.draw(g2);
		guide.draw(g2);
		
		g2.dispose();
	}
}
