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
	public void PlayBackgroundMusic()
	{
	 	try 
	    {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("backgroundmusic2.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } 
	    catch(Exception ex) {}
	}
	
	public void PlayMouseClickSound()
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
	
	public void PlayCollisionSound()
	{
		try 
	    {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("collision.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } 
	    catch(Exception ex) {}
	}

	public void PlayStartMusic() 
	{
        new Thread(() -> 
		{
			try 
	        {
				while(true)
				{
			        AudioInputStream audioInputStreamm = AudioSystem.getAudioInputStream(new File("start-music2.wav").getAbsoluteFile());
			        Clip clip = AudioSystem.getClip();
			        clip.open(audioInputStreamm);
			        clip.start();
			        
			        Thread.sleep(125000);
				}
	        } catch(Exception e) {}
		}).start();
	}
}
