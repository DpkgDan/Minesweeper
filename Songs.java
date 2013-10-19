import java.applet.Applet;
import java.applet.AudioClip;

public class Songs {
	public static final AudioClip song1 
		= Applet.newAudioClip(FrontEnd.class.getResource("song/song1.wav"));
	public static final AudioClip song2
		= Applet.newAudioClip(FrontEnd.class.getResource("song/song2.wav"));
}
