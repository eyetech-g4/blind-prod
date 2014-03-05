package hodbrowser;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;

public class HodBrowser extends Application {

	private BorderPane affichage = new BorderPane();
	private Screen screen = Screen.getPrimary();
	private Rectangle2D bounds = screen.getVisualBounds();
	private WebView pageWeb;
	private static WebEngine renduWeb;
	private TextToSpeech textToSpeech = new TextToSpeech();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("HÖD");

		primaryStage.setScene(new Scene(affichage, 0, 0));

		primaryStage.setX(bounds.getMinX());
		primaryStage.setY(bounds.getMinY());
		primaryStage.setWidth(bounds.getWidth());
		primaryStage.setHeight(bounds.getHeight());

		affichage.setTop(new MenuBar());
		affichage.setCenter(this.goToPage("https://www.google.fr/"));

		primaryStage.show();
		textToSpeech.say("The browser is opened");
	}

	// RETURN WEB PAGE
	public WebView goToPage(String url) {

		pageWeb = new WebView();
		renduWeb = pageWeb.getEngine();

		renduWeb.load(url);
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