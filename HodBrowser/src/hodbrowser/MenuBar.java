package hodbrowser;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.WindowEvent;

public class MenuBar extends ToolBar {

	private Web hodEngine = new Web();
	final ContextMenu contextMenu = new ContextMenu();
	private Button buttonPrevious = new Button("PREVIOUS");
	private Button buttonNext = new Button("NEXT");
	private Button buttonRefresh = new Button("REFRESH");
	private Button buttonStop = new Button("STOP");
	private Button buttonGo = new Button("GO TO");
	private Button buttonHome = new Button("HOME");
	private TextField addressBar = new TextField();
	private TextToSpeech textToSpeech = new TextToSpeech();
	private Navigation navigate = new Navigation();
	private Display display = new Display();
	private String FavHomePage="https://www.google.fr/";

	protected void setAdressBarValue(String url){
		this.addressBar.setText(url);
	}
	
	// TOOLBAR WITH DEFAULT URL
	protected MenuBar() {

		// BUTTON AND TOOLBAR DISPLAY
		display.createToolBar(this, 240);
		display.createButton(buttonHome, 200, 100);
		display.createButton(buttonPrevious, 200, 100);
		display.createButton(buttonNext, 200, 100);
		display.createButton(buttonRefresh, 200, 100);
		display.createButton(buttonStop, 200, 100);
		display.createButton(buttonGo, 200, 100);
		display.createAddress(addressBar, 300, 80, FavHomePage);

		this.getItems().addAll(buttonHome, buttonPrevious, buttonNext,
				buttonRefresh, buttonStop, buttonGo, addressBar);

		// BUTTONS ACTIONS
		buttonGo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				textToSpeech.say("Going to " + addressBar.getText());
				navigate.setURLpath();
				hodEngine.goToPage(addressBar.getText());
			}
		});
		buttonHome.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				textToSpeech.say("Going to the default page");
				System.out.println("toto");
				navigate.HomePage("https://www.google.fr/");
			}
		});
		addressBar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				textToSpeech.say("Going to " + addressBar.getText());
				navigate.setURLpath();
				hodEngine.goToPage(addressBar.getText());
			}
		});

		buttonPrevious.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				textToSpeech.say("Back to the previous page");
				navigate.PreviousPage();
			}
		});
		buttonNext.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				textToSpeech.say("Going to the next page");
				navigate.NextPage();
			}
		});

		// CONTEXT MENU TOOLBAR
		MenuItem item1 = new MenuItem("Increase size");
		item1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				
				textToSpeech.say("Increasing size");
				//display.increaseToolBar(this);
				display.increaseButton(buttonHome);
				display.increaseButton(buttonPrevious);
				display.increaseButton(buttonNext);
				display.increaseButton(buttonRefresh);
				display.increaseButton(buttonStop);
				display.increaseButton(buttonGo);
				display.increaseAddress(addressBar);
			}
		});
		MenuItem item2 = new MenuItem("Decrease size");
		item2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				
				textToSpeech.say("Decreasing size");
				//display.decreaseToolBar(this);
				display.decreaseButton(buttonHome);
				display.decreaseButton(buttonPrevious);
				display.decreaseButton(buttonNext);
				display.decreaseButton(buttonRefresh);
				display.decreaseButton(buttonStop);
				display.decreaseButton(buttonGo);
				display.decreaseAddress(addressBar);
			}
		});
		contextMenu.getItems().addAll(item1, item2);
		this.setContextMenu(contextMenu);
		contextMenu.setOnShown(new EventHandler<WindowEvent>() {
			
			public void handle(WindowEvent e) {
				textToSpeech.say("Context menu opened");
			}
		});
	}
}