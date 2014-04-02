package hodbrowser;

import examples.*;
import examples.synthesis.*;
import examples.recognition.*;

import javax.speech.*;
import javax.speech.recognition.*;
import javax.speech.synthesis.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;

import com.cloudgarden.speech.userinterface.*;

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
public class Recognizere extends DuplexTest{
  
   DuplexTest tss = new DuplexTest();

   public void lionel() {
	   tss.haha();
   }
   
   public void lauchreco() {
	   tss.start();
   }
   
   
   public static void main(String[] args) {
	
	   Recognizere a = new Recognizere();
	   a.lionel();
   }
   
}