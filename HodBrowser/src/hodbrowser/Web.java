package hodbrowser;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Web {

	private WebView webPage;
	private WebEngine webEngine;
	private BorderPane affichage = new BorderPane();
	private MenuBar toolBar= new MenuBar();

	public Web() {
		//
	}

	protected void GenerateToolBar() {
		
		this.affichage.setTop(new MenuBar());
	}
	
	// RETURN WEB PAGE
	protected WebView goToPage(String url) {

		this.webPage = new WebView();
		this.webEngine = webPage.getEngine();
		this.webEngine.load(url);
		this.affichage.setCenter(webPage);
		// Refresh displayed URL when changing page
		this.affichage.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				toolBar.setAdressBarValue(webEngine.getLocation().toString());
				affichage.setTop(toolBar);
				
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
