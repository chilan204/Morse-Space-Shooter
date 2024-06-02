package entity.morse;

import entity.Stage;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.KeyHandler;

public class Morse {
	private String[] letter = new String[26];
	private int val;
	private String str;
	
	private KeyHandler key;
	
	private int time, timeRemain;
	
	private BufferedImage Short, Long;
	
	public Morse(KeyHandler key) {
		letter['A' - 'A'] = "01";
		letter['B' - 'A'] = "1000";
		letter['C' - 'A'] = "1010";
		letter['D' - 'A'] = "100";
		letter['E' - 'A'] = "0";
		letter['F' - 'A'] = "0010";
		letter['G' - 'A'] = "110";
		letter['H' - 'A'] = "0000";
		letter['I' - 'A'] = "00";
		letter['J' - 'A'] = "0111";
		letter['K' - 'A'] = "101";
		letter['L' - 'A'] = "0100";
		letter['M' - 'A'] = "11";
		letter['N' - 'A'] = "10";
		letter['O' - 'A'] = "111";
		letter['P' - 'A'] = "0110";
		letter['Q' - 'A'] = "1101";
		letter['R' - 'A'] = "010";
		letter['S' - 'A'] = "000";
		letter['T' - 'A'] = "1";
		letter['U' - 'A'] = "001";
		letter['V' - 'A'] = "0001";
		letter['W' - 'A'] = "011";
		letter['X' - 'A'] = "1001";
		letter['Y' - 'A'] = "1011";
		letter['Z' - 'A'] = "1100";
		
		this.key = key;
		val = -1;
		str = new String("");
		time = 0;
		
		try {
			Short = ImageIO.read(getClass().getResourceAsStream("/assets/short.png"));
			Long = ImageIO.read(getClass().getResourceAsStream("/assets/long.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void init() {
		val = -1;
		str = new String("");
		time = 0;
	}
	
	public void update() {
		if (Stage.stage != Stage.play) return;
		
		if (key.spacePressed == true) time++;
		else if (time != 0) {
			if (time < 8) str = str + '0';
			else str = str + '1';
			time = 0;
		}
		
		if (key.enterPressed == true) {
			for (int i = 0; i < 26; i++) if (str.equals(letter[i]) == true) {
				val = i;
				break;
			}
			str = new String("");
		}
		
		if (val < 0) timeRemain = 0;
		else {
			timeRemain++;
			if (timeRemain > 8) val = -1;
		}
	}
	
	public void draw(Graphics2D g2) {
		if (Stage.stage != Stage.play) return;
		if (str.length() == 0) return;
		
		int len = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '0') len += 30;
			else len += 80;
		}
		
		len += (str.length() - 1) * 8;
		len = 500 - len / 2;
		
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '0') {
				g2.drawImage(Short, len + 150, 650, len + 150 + 30, 650 + 30, 0, 0, Short.getWidth(), Short.getHeight(), null);
				len += 38;
			}
			else {
				g2.drawImage(Long, len + 150, 650, len + 150 + 80, 650 + 30, 0, 0, Long.getWidth(), Long.getHeight(), null);
				len += 88;
			}
		}
	}
	
	public int getVal() {
		return val;
	}
	
	public void setVal(int u) {
		val = u;
	}
}
