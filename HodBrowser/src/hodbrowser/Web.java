package hodbrowser;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Web {

	private  BorderPane affichage = new BorderPane();
	private  WebView webPage;
	private  WebEngine webEngine;

	public Web() {
		//
	}

	protected void GenerateToolBar(){
		affichage.setTop(new MenuBar());
	}
	
	// RETURN WEB PAGE
	protected WebView goToPage(String url) {

		webPage = new WebView();
		webEngine = webPage.getEngine();
		webEngine.load(url);
		
		// Refresh displayed URL when changing page
		affichage.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				affichage.setTop(new MenuBar(webEngine.getLocation()));
			}
		});

		return webPage;
	}

	protected BorderPane getBorderPane(){
		return this.affichage;
	}
	
	// RETURN WEB ENGINE
	protected WebEngine getWebEngine() {
		return webEngine;
	}
}
