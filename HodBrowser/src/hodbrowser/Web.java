package hodbrowser;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Web {

	private static BorderPane affichage = new BorderPane();
	private static WebView webPage;
	private static WebEngine webEngine;

	public Web() {
		//
	}

	// RETURN WEB PAGE
	public static WebView goToPage(String url) {

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

	// RETURN WEB ENGINE
	public static WebEngine getWebEngine() {
		return webEngine;
	}
}
