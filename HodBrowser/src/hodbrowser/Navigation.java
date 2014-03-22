package hodbrowser;

import java.util.ArrayList;

import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;

public class Navigation {

	private int i = 0;
	private Web HODEngine;
	private WebEngine webEngine;
	
	public Navigation(final Web webhodengine) {
//		super();
		this.HODEngine = webhodengine;
		this.webEngine=this.HODEngine.getWebEngine();
		
	}

	protected void PreviousPage() {

		webEngine.getHistory().go(-1);
		// go to the previous entry in the history list
	}

	protected void NextPage() {

		webEngine.getHistory().go(1);
		// go to the next entry in the history list 
	}
	
	protected void HomePage(String FavHomePage){
		
		HODEngine.goToPage(FavHomePage);
		// load the Home page
	}

	protected void Refresh(){
		
		webEngine.reload();
		//reload the current page.
	}

	protected void Stop(){
		
		webEngine.getLoadWorker().cancel();
		// will work only with a thread
		
	}
	
	protected String getURL(){
		
		return webEngine.getLocation().toString();
		// return the current page url.
	}

}
