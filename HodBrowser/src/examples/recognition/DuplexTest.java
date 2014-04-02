package examples.recognition;

import examples.*;
import examples.synthesis.*;
import hodbrowser.Navigation;
import javafx.application.Platform;

import javax.speech.*;
import javax.speech.recognition.*;
import javax.speech.synthesis.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;

import com.cloudgarden.speech.userinterface.*;
import com.sun.webpane.platform.WebPage;


/**
 * Tests whether a duplex sound card is being used - keep saying one of the
 * five commands ("Nice day", "Hello", "How are you" etc) while the computer
 * is replying - the computer should hear what you said while it was still talking
 * and reply when it has finished it's current reply. Note that only Rules are
 * used and the replies are unlike the rules so that the computer won't interpret
 * it's replies (which it will hear via the microphone) as commands! If the
 * DictationGrammar is enabled the computer can quite happily speak to itself
 * the whole day.
 */
public class DuplexTest extends Thread{
    public static Recognizer rec = null;
    public static Synthesizer synth = null;
    
    public void haha(){
    	System.out.println("ok");
    }
    
    
    public void run(){
    	this.reco();
    }
    
    public void refreshpage(){
    	System.out.println("refresh");
    	Platform.runLater(new Runnable() {
            @Override
            public void run() {
            	Navigation.getInstance().Refresh();
            }
        });
    }
    public void nextpage(){
    	System.out.println("next page");
    	Platform.runLater(new Runnable() {
            @Override
            public void run() {
            	Navigation.getInstance().NextPage();
            }
        });
    }
    public void prevpage(){
    	System.out.println("prev page");
    	Platform.runLater(new Runnable() {
            @Override
            public void run() {
            	Navigation.getInstance().PreviousPage();
            }
        });
    }
    public void homepage(){
    	System.out.println("home");
    	Platform.runLater(new Runnable() {
            @Override
            public void run() {
            	Navigation.getInstance().HomePage("http://www.minecraft-france.net");
            }
        });
    }
    public void stoppage(){
    	System.out.println("stop");
    	Platform.runLater(new Runnable() {
            @Override
            public void run() {
            	Navigation.getInstance().Stop();
            }
        });
    }
    
    public void googlepage(){
    	System.out.println("google");
    	Platform.runLater(new Runnable() {
            @Override
            public void run() {
            	Navigation.getInstance().HomePage("https://google.fr");
            }
        });
    }
    
    
    public void reco() {
        try {
            SpeechEngineChooser chooser = SpeechEngineChooser.getAllEnginesDialog();
            chooser.show();
            RecognizerModeDesc desc = chooser.getRecognizerModeDesc();
 
            rec = Central.createRecognizer(desc);
            rec.addResultListener(new ResultAdapter() {
                public void resultAccepted(ResultEvent e) {
                    try {
                        FinalResult r =(FinalResult)(e.getSource());
                        Grammar gram = r.getGrammar();
                        //This is the only way to find out if it is a FinalRuleResult or a FinalDictationResult
                        if(gram instanceof RuleGrammar) {
                            //It's a FinalRuleResult
                            String tags[] = ((FinalRuleResult)r).getTags();
                            if(tags == null || tags.length == 0) return;
                            if(tags[0].equals("REFRESH")) refreshpage();
                            if(tags[0].equals("NEXT")) nextpage();
                            if(tags[0].equals("PREV")) prevpage();
                            if(tags[0].equals("HOME")) homepage();
                            if(tags[0].equals("STOP")) stoppage();
                            if(tags[0].equals("SEARCH")) googlepage();
                            if(tags[0].equals("QUIT")) {
                                synth.speak("Farewell and prosper",null);
                                rec.deallocate();
                            }
                        } else {
                            //It's a FinalDictationResult (but if DictationGrammar is not enabled we won't ever get here)
                            ResultToken[] toks = r.getBestTokens();
                            String result = "";
                            for(int i = 0; i<toks.length; i++) result+=" "+toks[i].getSpokenText();
                            synth.speak(result,null);
                        }
                    } catch(Exception e1) {
                        e1.printStackTrace(System.out);
                    }
                }
            }
            );
            
            RecognizerAudioAdapter raud = new TestAudioListener();
            rec.getAudioManager().addAudioListener(raud);
            rec.addEngineListener(new TestEngineListener());
            
            rec.allocate();
            SpeakerProfile prof = chooser.getSpeakerProfile();
            rec.getSpeakerManager().setCurrentSpeaker(prof);
            
            rec.waitEngineState(Recognizer.ALLOCATED);
            RuleSequence rs1 = new RuleSequence();
            RuleTag rt1 = new RuleTag(new RuleToken("inspecteur"),"SEARCH");
            RuleTag rt2 = new RuleTag(new RuleToken("refresh"),"REFRESH");
            RuleTag rt3 = new RuleTag(new RuleToken("suivant"),"NEXT");
            RuleTag rt4 = new RuleTag(new RuleToken("precedant"),"PREV");
            RuleTag rt5 = new RuleTag(new RuleToken("home page"),"HOME");
            RuleTag rt6 = new RuleTag(new RuleToken("stop"),"STOP");
            RuleTag rt7 = new RuleTag(new RuleToken("rechairche"),"SEARCH");
            RuleAlternatives ra = new RuleAlternatives();
            ra.append(rt1);
            ra.append(rt2);
            ra.append(rt3);
            ra.append(rt4);
            ra.append(rt5);
            ra.append(rt6);
            
            RuleGrammar gram = rec.newRuleGrammar("Test");
            gram.setRule("testRule",ra,true);
            gram.setEnabled(true);
            
            //Don't enable dictation or the computer will start talking to itself!
            //DictationGrammar dictGram = rec.getDictationGrammar(null);
            //dictGram.setEnabled(true);
            
            synth = Central.createSynthesizer(null);
            synth.addEngineListener(new TestEngineListener());
            
            synth.allocate();
            synth.waitEngineState(Synthesizer.ALLOCATED);
            synth.resume();
            
            SynthesizerProperties sprops = synth.getSynthesizerProperties();
            sprops.setVoice(chooser.getVoice());
            
            SpeakableListener spList = new TestSpeakableListener();
            synth.addSpeakableListener(spList);
            
            SynthesizerProperties props = synth.getSynthesizerProperties();
            sprops.setVolume(1.0f);
            sprops.setSpeakingRate(200.0f);
            
            rec.commitChanges();
            
            rec.requestFocus();
            rec.resume();
            System.out.println("Tests whether a duplex sound card is being used.\n"+
            "Keep saying one of the five commands 'Nice day', 'Hello', 'How are you' \n"+
            "'What time is it', 'Would you like a cup of tea' or  'Nice day'. \n"+
            "While the computer is replying the computer should still hear what you are saying\n"+
            "and reply when it has finished it's current reply");
            rec.waitEngineState(Recognizer.DEALLOCATED);
            //after recognizing "QUIT" we deallocate in resultAccepted method

            synth.waitEngineState(Synthesizer.QUEUE_EMPTY);
            
        } catch(Exception e) {
            e.printStackTrace(System.out);
        } catch(Error e1) {
            e1.printStackTrace(System.out);
        } finally {
            try {
                synth.deallocate();
                rec.deallocate();
                rec.waitEngineState(Synthesizer.DEALLOCATED);
                synth.waitEngineState(Synthesizer.DEALLOCATED);
            } catch(Exception e2) {
                e2.printStackTrace(System.out);
            }
            System.exit(0);
        }
    }
}
