package hodbrowser;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

public class HodBrowser extends Application {

	private Screen screen = Screen.getPrimary();
	private Rectangle2D bounds = screen.getVisualBounds();
	private TextToSpeech textToSpeech = new TextToSpeech(
			"The browser is opened");
	private WebView webPage;
	private Web hodBrowser;
	

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		
		final Recognizere a = new Recognizere();
		
		this.webPage= new WebView();
		hodBrowser = new Web(webPage);
		
		
		// Title browser
		primaryStage.setTitle("HÖD");

		// Create display with areas
        Scene scene = new Scene(hodBrowser.getBorderPane(), 0, 0);
        primaryStage.setScene(scene);

		// Set to max size the window
		primaryStage.setX(bounds.getMinX());
		primaryStage.setY(bounds.getMinY());
		primaryStage.setWidth(bounds.getWidth());
		primaryStage.setHeight(bounds.getHeight());

		
		

		// Web page call
		hodBrowser.goToPage("http://www.google.com");//Nico it's a gift for you ;)
		
		
		// Toolbar call
		this.hodBrowser.GenerateToolBar();
		
		// Display browser
		primaryStage.show();

		// Confirm vocally browser is launched
		textToSpeech.start();
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(final KeyEvent keyEvent) {
				if (keyEvent.getCode() == KeyCode.F5) {
					System.out.println("F5 pressed");
					// Stop letting it do anything else
					keyEvent.consume();
				}
			}
		});

		final KeyCombination keyComb1 = new KeyCodeCombination(KeyCode.R,
				KeyCombination.CONTROL_DOWN);
		scene.addEventHandler(KeyEvent.KEY_RELEASED,
				new EventHandler<KeyEvent>() {
					@Override
					public void handle(KeyEvent event) {
						if (keyComb1.match(event)) {
							System.out.println("Ctrl+R pressed");
							a.lauchreco();
						}
					}
				});

		
		
	}
	
	
	public void stop() {
		System.exit(0);
	}
	
}


