package entity.morse;

import entity.Stage;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MorseCode {
	private BufferedImage left, right;
	
	public MorseCode() {
		try {
			left = ImageIO.read(getClass().getResourceAsStream("/assets/left.png"));
			right = ImageIO.read(getClass().getResourceAsStream("/assets/right.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		if (Stage.stage != Stage.play) return;
		g2.drawImage(left, 0, 0, null);
		g2.drawImage(right, main.Game.WIDTH - right.getWidth(), 0, null);
	}
}
