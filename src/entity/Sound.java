package entity;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	public static final int guide = 0;
	public static final int move = 1;
	public static final int exploid = 2;
	public static final int lose = 3;
	public static final int point = 4;
	
	private Clip clip;
	private URL soundURL[] = new URL[30];
	private boolean play = false;
	
	public Sound() {
		soundURL[0] = getClass().getResource("/assets/guide.wav");
		soundURL[1] = getClass().getResource("/assets/move.wav");
		soundURL[2] = getClass().getResource("/assets/exploid.wav");
		soundURL[3] = getClass().getResource("/assets/lose.wav");
		soundURL[4] = getClass().getResource("/assets/point.wav");
	}
	
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void play(int i) {
		if (play) return;
		play = true;
		setFile(i);
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		if (!play) return;
		clip.stop();
		play = false;
	}
}
