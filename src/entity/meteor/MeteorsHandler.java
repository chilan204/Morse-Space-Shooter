package entity.meteor;

import java.awt.Graphics2D;
import java.util.Random;
import java.util.Vector;

import entity.Score;
import entity.Stage;
import entity.morse.Morse;
import entity.ship.Lazer;
import entity.ship.Ship;

public class MeteorsHandler {
	private Vector<Vector<Meteor>> vec = new Vector<Vector<Meteor>>();
	private double time, cnt;
	private Meteor meteor;
	private Random random;
	
	public MeteorsHandler() {
		for (int i = 0; i < 26; i++) vec.add(new Vector<Meteor>());
		init();
		random = new Random();
	}
	
	public void clear() {
		for (int i = 0; i < vec.size(); i++) {
			for (Meteor cc : vec.elementAt(i)) cc.stop();
			vec.elementAt(i).clear();
		}
	}
	
	public void init() {
		clear();
		time = 15;
		cnt = 0;
	}
	
	public void generate() {
		cnt += 0.1;
		if (cnt >= time) {
			cnt = 0;
			meteor = new Meteor();
			vec.elementAt(meteor.getVal()).add(meteor);
			time -= 0.5;
			if (time <= 5) time = 5;
		}
	}
	
	public void update(Morse morse, Score score, Ship ship) {
		if (Stage.stage != Stage.play) { clear(); return; }
		
		generate();

		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < vec.elementAt(i).size(); j++) {
				Meteor temp = vec.elementAt(i).elementAt(j);
				if (temp.removeAble()) {
					vec.elementAt(i).remove(j);
					j--;
				}
			}
		}
		
		for (int i = 0; i < 26; i++) {
			for (Meteor cc : vec.elementAt(i)) {
				cc.update(ship, morse, score);
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		if (Stage.stage != Stage.play) return;
		for (int i = 0; i < 26; i++) {
			for (Meteor cc : vec.elementAt(i)) cc.draw(g2);
		}
	}
}
