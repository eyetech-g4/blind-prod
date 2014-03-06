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

	final BorderPane affichage = new BorderPane();
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

	// TOOLBAR WITH DEFAULT URL
	public MenuBar() {

		// BUTTON AND TOOLBAR DISPLAY
		this.setPrefHeight(240);
		this.setStyle("-fx-base: #424242;");

		buttonHome.setPrefSize(200, 100);
		buttonHome.setStyle("-fx-base: #ffffff; -fx-font-size: 30;");
		buttonHome.setTranslateY(-55);

		buttonPrevious.setPrefSize(200, 100);
		buttonPrevious.setStyle("-fx-base: #ffffff; -fx-font-size: 30;");
		buttonPrevious.setTranslateY(-55);

		buttonNext.setPrefSize(200, 100);
		buttonNext.setStyle("-fx-base: #ffffff; -fx-font-size: 30;");
		buttonNext.setTranslateY(-55);

		buttonRefresh.setPrefSize(200, 100);
		buttonRefresh.setStyle("-fx-base: #ffffff; -fx-font-size: 30;");
		buttonRefresh.setTranslateY(-55);

		buttonStop.setPrefSize(200, 100);
		buttonStop.setStyle("-fx-base: #ffffff; -fx-font-size: 30;");
		buttonStop.setTranslateY(-55);

		buttonGo.setPrefSize(200, 100);
		buttonGo.setStyle("-fx-base: #ffffff; -fx-font-size: 30;");
		buttonGo.setTranslateX(-1020);
		buttonGo.setTranslateY(55);

		addressBar.setText("https://www.google.fr/");
		addressBar.setStyle("-fx-font-size: 30;");
		addressBar.setTranslateX(-820);
		addressBar.setTranslateY(55);
		addressBar.setPrefSize(300, 100);

		this.getItems().addAll(buttonHome, buttonPrevious, buttonNext,
				buttonRefresh, buttonStop, buttonGo, addressBar);

		// BUTTONS ACTIONS
		buttonGo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				textToSpeech.say("Going to " + addressBar.getText());
				affichage.setCenter(Web.goToPage(addressBar.getText()));
			}
		});
		buttonHome.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				textToSpeech.say("Going to the default page");
				affichage.setCenter(Web.goToPage("https://www.google.fr/"));
			}
		});
		addressBar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				textToSpeech.say("Going to " + addressBar.getText());
				affichage.setCenter(Web.goToPage(addressBar.getText()));
			}
		});

		buttonPrevious.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				textToSpeech.say("Back to the previous page");
				navigate.previousPage();
			}
		});
		buttonNext.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				textToSpeech.say("Going to the next page");
				navigate.nextPage();
			}
		});

		// CONTEXT MENU TOOLBAR
		MenuItem item1 = new MenuItem("Decrease size");
		item1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println("Decreasing size");
				textToSpeech.say("Size decreased");
			}
		});
		MenuItem item2 = new MenuItem("Increase size");
		item2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println("Increasing size");
				textToSpeech.say("Size increased");
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

		buttonHome.setPrefSize(200, 100);
		buttonHome.setStyle("-fx-base: #ffffff; -fx-font-size: 30;");
		buttonHome.setTranslateY(-55);

		buttonPrevious.setPrefSize(200, 100);
		buttonPrevious.setStyle("-fx-base: #ffffff; -fx-font-size: 30;");
		buttonPrevious.setTranslateY(-40);

		buttonNext.setPrefSize(200, 100);
		buttonNext.setStyle("-fx-base: #ffffff; -fx-font-size: 30;");
		buttonNext.setTranslateY(-40);

		buttonRefresh.setPrefSize(200, 100);
		buttonRefresh.setStyle("-fx-base: #ffffff; -fx-font-size: 30;");
		buttonRefresh.setTranslateY(-40);

		buttonStop.setPrefSize(200, 100);
		buttonStop.setStyle("-fx-base: #ffffff; -fx-font-size: 30;");
		buttonStop.setTranslateY(-40);

		buttonGo.setPrefSize(200, 100);
		buttonGo.setStyle("-fx-base: #ffffff; -fx-font-size: 30;");
		buttonGo.setTranslateY(-40);

		addressBar.setText(displayedUrl);
		addressBar.setStyle("-fx-font-size: 30;");
		addressBar.setTranslateX(-820);
		addressBar.setTranslateY(55);
		addressBar.setPrefSize(300, 100);

		this.getItems().addAll(buttonHome, buttonPrevious, buttonNext,
				buttonRefresh, buttonStop, buttonGo, addressBar);

		// BUTTONS ACTIONS
		buttonHome.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				textToSpeech.say("Going to the default page");
				affichage.setCenter(Web.goToPage("https://www.google.fr/"));
			}
		});
		buttonGo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				textToSpeech.say("Going to " + addressBar.getText());
				affichage.setCenter(Web.goToPage(addressBar.getText()));
			}
		});
		addressBar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				textToSpeech.say("Going to " + addressBar.getText());
				affichage.setCenter(Web.goToPage(addressBar.getText()));
			}
		});

		buttonPrevious.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				textToSpeech.say("Back to the previous page");
				navigate.previousPage();
			}
		});
		buttonNext.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				textToSpeech.say("Going to the next page");
				navigate.nextPage();
			}
		});

		// CONTEXT MENU TOOLBAR
		MenuItem item1 = new MenuItem("About");
		item1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println("Decrease size");
			}
		});
		MenuItem item2 = new MenuItem("Preferences");
		item2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println("Increase size");
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
