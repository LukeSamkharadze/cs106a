package Audio;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio 
{
	static public boolean isTurnedOn = false;
	
	public static void PlayBackgroundMusic() 
	{
        new Thread(() -> 
		{
			try 
	        {
				isTurnedOn=true;
				while(true)
				{
			        AudioInputStream audioInputStreamm = AudioSystem.getAudioInputStream(new File("casino-background-music.wav").getAbsoluteFile());
			        Clip clip = AudioSystem.getClip();
			        clip.open(audioInputStreamm);
			        clip.start();
			        
			        for (int i = 0; i < 24600; i++) 
			        {
			        	if(isTurnedOn==false)
			        	{
			        		clip.stop();
			        		return;
			        	}
			        	Thread.sleep(10);
					}
			        
			        audioInputStreamm = AudioSystem.getAudioInputStream(new File("casino-background-music2.wav").getAbsoluteFile());
			        clip = AudioSystem.getClip();
			        clip.open(audioInputStreamm);
			        clip.start();
			        
			        for (int i = 0; i < 19100; i++) 
			        {
			        	if(isTurnedOn==false)
			        	{
			        		clip.stop();
			        		return;
			        	}
			        	Thread.sleep(10);
					}
				}
	        } catch(Exception e) {}
		}).start();
	}
}
