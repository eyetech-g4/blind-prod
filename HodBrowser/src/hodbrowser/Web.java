package hodbrowser;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Web {

	private static BorderPane affichage = new BorderPane();
	private static WebView pageWeb;
	private static WebEngine renduWeb;

	public Web() {
		//
	}

	// RETURN WEB PAGE
	public static WebView goToPage(String url) {

		pageWeb = new WebView();
		renduWeb = pageWeb.getEngine();
		renduWeb.load(url);
		
		// Refresh toolbar on mouse click
		affichage.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				affichage.setTop(new MenuBar(renduWeb.getLocation()));
			}
		});

		return pageWeb;
	}

	// RETURN WEB ENGINE
	public static WebEngine getWebEngine() {
		return renduWeb;
	}
}
