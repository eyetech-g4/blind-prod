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

	// TOOLBAR WITH DEFAULT URL
	public MenuBar() {

		// BUTTON AND TOOLBAR DISPLAY
		this.setPrefHeight(240);
		this.setStyle("-fx-base: #424242;");

		display.setButtonSize(buttonHome, 200, 100);
		display.setButtonSize(buttonPrevious, 200, 100);
		display.setButtonSize(buttonNext, 200, 100);
		display.setButtonSize(buttonRefresh, 200, 100);
		display.setButtonSize(buttonStop, 200, 100);
		display.setButtonSize(buttonGo, 200, 100);
		display.setAddressSize(addressBar, 300, 100, "https://www.google.fr/");

		this.getItems().addAll(buttonHome, buttonPrevious, buttonNext,
				buttonRefresh, buttonStop, buttonGo, addressBar);

		// BUTTONS ACTIONS
		buttonGo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				textToSpeech.say("Going to " + addressBar.getText());
				hodEngine.goToPage(addressBar.getText());
			}
		});
		buttonHome.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				textToSpeech.say("Going to the default page");
				navigate.HomePage("https://www.google.fr/");
			}
		});
		addressBar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				textToSpeech.say("Going to " + addressBar.getText());
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
				display.increase(buttonHome);
				display.increase(buttonPrevious);
				display.increase(buttonNext);
				display.increase(buttonRefresh);
				display.increase(buttonStop);
				display.increase(buttonGo);
			}
		});
		MenuItem item2 = new MenuItem("Decrease size");
		item2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				textToSpeech.say("Decreasing size");
				display.decrease(buttonHome);
				display.decrease(buttonPrevious);
				display.decrease(buttonNext);
				display.decrease(buttonRefresh);
				display.decrease(buttonStop);
				display.decrease(buttonGo);
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

	// TOOLBAR WITH CURRENT URL
	public MenuBar(String displayedUrl) {

		// BUTTON AND TOOLBAR DISPLAY
		this.setPrefHeight(200);
		this.setStyle("-fx-base: #424242;");

		display.setButtonSize(buttonHome, 200, 100);
		display.setButtonSize(buttonPrevious, 200, 100);
		display.setButtonSize(buttonNext, 200, 100);
		display.setButtonSize(buttonRefresh, 200, 100);
		display.setButtonSize(buttonStop, 200, 100);
		display.setButtonSize(buttonGo, 200, 100);
		display.setAddressSize(addressBar, 300, 100, displayedUrl);

		this.getItems().addAll(buttonHome, buttonPrevious, buttonNext,
				buttonRefresh, buttonStop, buttonGo, addressBar);

		// BUTTONS ACTIONS
		buttonHome.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				textToSpeech.say("Going to the default page");
				hodEngine.goToPage("https://www.google.fr/");
			}
		});
		buttonGo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				textToSpeech.say("Going to " + addressBar.getText());
				hodEngine.goToPage(addressBar.getText());
				navigate.setURLpath();
			}
		});
		addressBar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				textToSpeech.say("Going to " + addressBar.getText());
				hodEngine.goToPage(addressBar.getText());
				navigate.setURLpath();
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
				display.increase(buttonHome);
				display.increase(buttonPrevious);
				display.increase(buttonNext);
				display.increase(buttonRefresh);
				display.increase(buttonStop);
				display.increase(buttonGo);
				hodEngine.GenerateToolBar();
			}
		});
		MenuItem item2 = new MenuItem("Decrease size");
		item2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				textToSpeech.say("Decreasing size");
				display.increase(buttonHome);
				display.increase(buttonPrevious);
				display.increase(buttonNext);
				display.increase(buttonRefresh);
				display.increase(buttonStop);
				display.increase(buttonGo);
				hodEngine.GenerateToolBar();
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
