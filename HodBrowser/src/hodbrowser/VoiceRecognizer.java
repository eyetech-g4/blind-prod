package hodbrowser;


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
public class VoiceRecognizer extends DuplexTest{
  
   DuplexTest tss = new DuplexTest();
   
   public void lauchRecognizer() {
	   tss.start();
   }
   
   public void stopRecognizer() {
	   tss.interrupt();
   }
   
   public void allocate() {
	   tss.Allocate();
   }
   
   public void deallocate() {
	   tss.Deallocate();
   }
}