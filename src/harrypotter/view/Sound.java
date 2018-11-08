package harrypotter.view;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	private Clip clip;

	public void play(String path, int delay) {
		while (true) {
			new Thread() {
				@Override
				public void run() {
					try {
						File file = new File(path);
						clip = AudioSystem.getClip();
						clip.open(AudioSystem.getAudioInputStream(file));
						clip.start();
						Thread.sleep(clip.getMicrosecondLength());

					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			}.start();
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void play(String path) {
		try {
			File file = new File(path);
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
			clip.start();
			Thread.sleep(clip.getMicrosecondLength());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void stop() {
		clip.stop();
	}
}
