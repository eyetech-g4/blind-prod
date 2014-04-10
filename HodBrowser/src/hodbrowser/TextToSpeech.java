package hodbrowser;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;

import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

public class TextToSpeech extends Thread{
	
	private static Synthesizer synth;
	private final String Read;
	
	public TextToSpeech(String textToRead) {
		//
		this.Read = textToRead;
	}
	
	public void say(String textToRead) {
		try {

			synth = Central
					.createSynthesizer(new SynthesizerModeDesc(Locale.US));
			synth.allocate();
			synth.resume();
			synth.speakPlainText(textToRead, null);
			synth.waitEngineState(Synthesizer.QUEUE_EMPTY);
			synth.deallocate();
		} catch (InterruptedException | IllegalArgumentException
				| EngineException | EngineStateError | AudioException ex) {
			Logger.getLogger("ss").log(Level.SEVERE, null, ex);
		}
	}
	public void run(){
	    this.say(this.Read);
    }   
	
	public Boolean Alive(){
		try {
			TextToSpeech.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.isAlive();
	}
	public void Stop(){
		try {
			TextToSpeech.sleep(200);
			synth.deallocate();
		} catch (EngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EngineStateError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			synth.waitEngineState(Synthesizer.DEALLOCATED);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//synth.cancel();
		this.interrupt();
		System.out.println("cancel");
	}
	
	public void SpeakLater(String str){
		final String stri = str;
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	say(stri);
            }
        });
	}
	
}