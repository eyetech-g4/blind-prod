package hodbrowser;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Web {

	private WebView webPage;
	private WebEngine webEngine;
	private BorderPane affichage;
	// private MenuBar toolBar= new MenuBar();

	public Web(final WebView webpage) {
		//
		this.webPage = webpage;
		this.webEngine=this.webPage.getEngine();
		System.out.println(webEngine.getLocation());
		affichage = new BorderPane();
	}

	protected void GenerateToolBar() {
		
		this.affichage.setTop(new MenuBar(this));
	}
	
	// RETURN WEB PAGE
	protected void goToPage(String url) {


		this.webEngine.load(url);
		this.affichage.setCenter(webPage);
		// Refresh displayed URL when changing page
		this.affichage.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// affichage.setTop(toolBar);
			}
		});

	}
	
	protected BorderPane getBorderPane(){
		
		return this.affichage;
	}
	
	// RETURN WEB ENGINE
	protected WebEngine getWebEngine() {
		
		return webEngine;
	}
}
