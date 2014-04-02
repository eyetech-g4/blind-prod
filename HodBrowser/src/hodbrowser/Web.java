package hodbrowser;

import java.io.File;
import java.net.MalformedURLException;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


public class Web {

	private WebView webPage;
	private WebEngine webEngine;
	private BorderPane affichage;
	 private MenuBar toolBar;

	public Web(final WebView webpage) {
		//
		this.webPage = webpage;
		this.webEngine=this.webPage.getEngine();
		this.affichage = new BorderPane();
		this.toolBar= new MenuBar(this,affichage);
		
		this.webEngine.setUserStyleSheetLocation(getClass().getResource("style.css").toExternalForm());

		
	}

	protected void GenerateToolBar() {
		
		this.affichage.setTop(toolBar);
	}
	
	// RETURN WEB PAGE
	protected void goToPage(String url) {


		this.webEngine.load(url);
		this.affichage.setCenter(webPage);

	}
	
	// Return a BorderPane
	protected BorderPane getBorderPane(){
		
		return this.affichage;
	}
	
	// RETURN WEB ENGINE
	protected WebEngine getWebEngine() {
		
		return webEngine;
	}
	
	// RETURN WEB VIEW
		protected WebView getWebPage() {
			
			return webPage;
		}
}
