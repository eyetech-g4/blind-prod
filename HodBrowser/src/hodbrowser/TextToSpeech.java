package hodbrowser;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

public class TextToSpeech {
	
	private static Synthesizer synth;
	
	public TextToSpeech() {
		//
	}
	
	// Read the given text
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
}