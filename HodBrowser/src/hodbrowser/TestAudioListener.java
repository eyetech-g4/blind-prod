package hodbrowser;

import javax.speech.*;
import javax.speech.recognition.*;

/**
 * Prints audio events to System.out
 */
public class TestAudioListener extends RecognizerAudioAdapter {
    
    boolean level;
    
    public TestAudioListener() {
        level = true;
    }
    
    protected TestAudioListener(boolean showLevel) {
        level = showLevel;
    }
    
    public void speechStarted(RecognizerAudioEvent e) {
	System.out.println("Speech started");
    }
    
    public void speechStopped(RecognizerAudioEvent e) {
	System.out.println("Speech stopped");
    }
    
    public void audioLevel(RecognizerAudioEvent e) {
	if(level) System.out.println("AudioLevel "+e.getAudioLevel());
    }
    
}
