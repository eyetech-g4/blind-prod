package hodbrowser;

import javafx.application.Application;
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
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;

public class HodBrowser extends Application {

	private BorderPane affichage = new BorderPane();
	private ToolBar toolBar = new ToolBar();
	private Button buttonGo = new Button("GO TO");
	private Button buttonPrevious = new Button("PREVIOUS");
	private Button buttonNext = new Button("NEXT");
	private Button buttonRefresh = new Button("REFRESH");
	private Button buttonStop = new Button("STOP");
	private Separator separateur1 = new Separator();
	private Separator separateur2 = new Separator();
	private Screen screen = Screen.getPrimary();
	private Rectangle2D bounds = screen.getVisualBounds();
	private TextField addressBar = new TextField();
	private WebView pageWeb;
	private static WebEngine renduWeb;
	private Navigation toto= new Navigation();

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

		affichage.setTop(setMenu());
		affichage.setCenter(getPageWeb("https://www.google.fr/"));

		primaryStage.show();

	}

	private ToolBar setMenu() {

		addressBar.setText("https://www.google.fr/");
		addressBar.setPrefWidth(800);
		toolBar.setPrefHeight(100);
		buttonGo.setPrefHeight(90);
		buttonPrevious.setPrefHeight(90);
		buttonNext.setPrefHeight(90);
		buttonRefresh.setPrefHeight(90);
		buttonStop.setPrefHeight(90);
		toolBar.getItems().addAll(buttonPrevious, buttonNext, separateur1,
				buttonRefresh, buttonStop, separateur2, buttonGo, addressBar);
		buttonGo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				affichage.setCenter(getPageWeb(addressBar.getText()));
			}
		});
		addressBar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				affichage.setCenter(getPageWeb(addressBar.getText()));
				toto.setURLpath();
			}
		});

		buttonPrevious.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				affichage.setCenter(getPageWeb(toto.getPreviousURLpath()));
			}
		});
		buttonNext.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				affichage.setCenter(getPageWeb(toto.getNextURLpath()));
			}
		});
		return toolBar;
	}

	private ToolBar setMenu(String urlAffichee) {

		addressBar.setText(urlAffichee);
		addressBar.setPrefWidth(800);
		addressBar.setPrefHeight(30);
		toolBar.setPrefHeight(100);
		buttonGo.setPrefHeight(90);
		buttonPrevious.setPrefHeight(90);
		buttonNext.setPrefHeight(90);
		buttonRefresh.setPrefHeight(90);
		buttonStop.setPrefHeight(90);
		toolBar.getItems().addAll(buttonPrevious, buttonNext, separateur1,
				buttonRefresh, buttonStop, separateur2, buttonGo, addressBar);
		buttonGo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				affichage.setCenter(getPageWeb(addressBar.getText()));
			}
		});
		
		addressBar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				affichage.setCenter(getPageWeb(addressBar.getText()));
				toto.setURLpath();
			}
		});


		return toolBar;
	}

	public static WebEngine getWebEngine() {
		return renduWeb;
	}

	private WebView getPageWeb(String url) {

		pageWeb = new WebView();
		renduWeb = pageWeb.getEngine();

		renduWeb.load(url);
		affichage.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				toto.setURLpath();
				//affichage.setTop(setMenu(renduWeb.getLocation()));
			}
		});

		return pageWeb;
	}
}