/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hodbrowser;

import java.awt.Dimension;

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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineList;
import javax.speech.EngineStateError;
import javax.speech.recognition.RecognizerModeDesc;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

import hodbrowser.voiceRecognizer;


public class test extends Application {
	BorderPane affichage = new BorderPane();
	/**
	 * @param args
	 *            the command line arguments
	 */
	private SynthesizerModeDesc required;
	private static Synthesizer synth;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("HÖD");
		

		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();

		primaryStage.setScene(new Scene(affichage, 0, 0));

		primaryStage.setX(bounds.getMinX());
		primaryStage.setY(bounds.getMinY());
		primaryStage.setWidth(bounds.getWidth());
		primaryStage.setHeight(bounds.getHeight());

		affichage.setTop(getMenu());
		affichage.setCenter(getPageWeb("https://www.google.fr/"));

		primaryStage.show();
	}

	private ToolBar getMenu() {

		ToolBar toolBar = new ToolBar();
		Button buttonGo = new Button("Go");
		final TextField addressBar = new TextField();
		addressBar.setPrefWidth(800);
		toolBar.getItems().addAll(buttonGo, addressBar);
		buttonGo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					   
				    synth = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
		            System.out.println(Locale.US);
		            synth.allocate();
		            synth.resume();
		            
		            synth.speakPlainText("You type "+addressBar.getText(),null);
		            
		            
		            synth.waitEngineState(Synthesizer.QUEUE_EMPTY);
		            synth.deallocate();
		            
		            System.out.println(voiceRecognizer.isRecognitionEngine());
		            
		        } catch (InterruptedException | IllegalArgumentException | EngineException | EngineStateError | AudioException ex) {
		            Logger.getLogger("ss").log(Level.SEVERE, null, ex);
		        }
				affichage.setCenter(getPageWeb(addressBar.getText()));
			}
		});

		return toolBar;
	}

	private WebView getPageWeb(String url) {

		WebView pageWeb = new WebView();
		WebEngine myWebEngine = pageWeb.getEngine();
		myWebEngine.load(url);

		return pageWeb;
	}
}