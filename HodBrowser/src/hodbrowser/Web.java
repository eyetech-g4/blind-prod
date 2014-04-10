package hodbrowser;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Web {

	private WebView webPage;
	private Display window;
	private Navigation hod;
	private VoiceRecognizer temp;

	public Web(final WebView webpage,VoiceRecognizer test) {
		
		this.temp=test;
		this.webPage = webpage;
		this.initialize();

	}

	private void initialize(){
		this.hod = new Navigation(this.webPage,this.temp);
		
		this.window = new Display(this.webPage, this.hod);
		
		window.setVisible(true);
		this.hod.initialize();
	}
//	protected void GenerateToolBar() {
//		
////		this.affichage.setTop(toolBar);
//	}
//	
//	// Return a BorderPane
//	protected BorderPane getBorderPane(){
//		
//		return this.affichage;
//	}
//	
//	// RETURN WEB ENGINE
//	protected WebEngine getWebEngine() {
//		
//		return webEngine;
//	}
}
