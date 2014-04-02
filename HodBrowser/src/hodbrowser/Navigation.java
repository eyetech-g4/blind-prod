package hodbrowser;

import java.util.ArrayList;

import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;

public class Navigation {

	private int i = 0;
	private Web HODEngine;
	private WebEngine webEngine;
	private String location;
	private TextToSpeech textToSpeech = new TextToSpeech();
	
	public Navigation(final Web webhodengine) {
//		super();
		this.HODEngine = webhodengine;
		this.webEngine=this.HODEngine.getWebEngine();
		webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
		            public void changed(ObservableValue ov, State oldState, State newState) {
		                if (newState == State.SUCCEEDED) {
		                	
		                    location = webEngine.getTitle();
		                    speek("You're now on "+location);
		                }
		            }
		        });
		
	}

	protected void PreviousPage() {
//		go to the previous entry in the history list
		webEngine.getHistory().go(-1);
		 
	}

	protected void NextPage() {
//		go to the next entry in the history list 
		webEngine.getHistory().go(1);
		 
	}
	
	protected void HomePage(String FavHomePage){
//		load the Home page
		
		HODEngine.goToPage(FavHomePage);
	}

	protected void Refresh(){
//		reload the current page.
		
		webEngine.reload();
	}

	protected void Stop(){
//		stop the web page loading.
		webEngine.getLoadWorker().cancel();// will work only with a thread
	}
	
	protected String getURL(){
//		return the current web page URL
		return webEngine.getLocation().toString();
	}

	protected void speek(String text){
//		use the speek synthesis to say any text
		textToSpeech.say(text);
	}
	
}
