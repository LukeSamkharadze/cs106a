package Other;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import acm.graphics.GImage;
import acm.util.RandomGenerator;

public class Audio 
{
	public static void PlayMouseClickSound()
	{
	 	try 
	    {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("mouse-click.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } 
	    catch(Exception ex) {}
	}
	
	public static void PlayMouseClick2Sound()
	{
	 	try 
	    {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("mouse-click-2.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } 
	    catch(Exception ex) {}
	}
	
	public static void PlayBackgroundMusic() 
	{
        new Thread(() -> 
		{
			try 
	        {
				while(true)
				{
			        AudioInputStream audioInputStreamm = AudioSystem.getAudioInputStream(new File("background-music.wav").getAbsoluteFile());
			        Clip clip = AudioSystem.getClip();
			        clip.open(audioInputStreamm);
			        clip.start();
			        
			        Thread.sleep(86000);
				}
	        } catch(Exception e) {}
		}).start();
	}
}
