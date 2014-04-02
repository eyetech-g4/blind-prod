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
	private static Navigation instance;
	private String location;
	private TextToSpeech textToSpeech;
	
	public Navigation(final Web webhodengine) {
//		super();
		this.HODEngine = webhodengine;
		this.webEngine=this.HODEngine.getWebEngine();
		instance = this;
		webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
		            public void changed(ObservableValue ov, State oldState, State newState) {
		                if (newState == State.SUCCEEDED) {
		                	
		                    location = webEngine.getTitle();
		                   /* try {
		    					textToSpeech.interrupt();
		    					textToSpeech.Stop();
		    				} catch (Exception e1) {
		    					// TODO Auto-generated catch block
		    					e1.printStackTrace();
		    				}
		                    textToSpeech = new TextToSpeech("You're now on "+location);
		                    textToSpeech.start();*/
		                }
		            }
		        });
		
	}
	
	public static Navigation getInstance() {
		return instance;
	}

	public void PreviousPage() {
//		go to the previous entry in the history list
		webEngine.getHistory().go(-1);
		 
	}

	public void NextPage() {
//		go to the next entry in the history list 
		webEngine.getHistory().go(1);
		 
	}
	
	public void HomePage(String FavHomePage){
//		load the Home page
		
		HODEngine.goToPage(FavHomePage);
	}

	public void Refresh(){
//		reload the current page.
		
		webEngine.reload();
	}

	public void Stop(){
//		stop the web page loading.
		webEngine.getLoadWorker().cancel();// will work only with a thread
	}
	
	public String getURL(){
//		return the current web page URL
		return webEngine.getLocation().toString();
	}

	
}
