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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;

public class HodBrowser extends Application {

	private BorderPane affichage = new BorderPane();
	private ToolBar toolBar = new ToolBar();
	private Button buttonGo = new Button("GO TO");
	private Screen screen = Screen.getPrimary();
	private Rectangle2D bounds = screen.getVisualBounds();
	private TextField addressBar = new TextField();
	private String urlEnCours;

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

		affichage.setTop(getMenu());
		affichage.setCenter(getPageWeb("https://www.google.fr/"));

		primaryStage.show();
	}

	private ToolBar getMenu() {

		addressBar.setPrefWidth(800);
		toolBar.setPrefHeight(100);
		buttonGo.setPrefHeight(90);
		buttonGo.setPrefWidth(90);
		toolBar.getItems().addAll(buttonGo, addressBar);
		buttonGo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				affichage.setCenter(getPageWeb(addressBar.getText()));
			}
		});

		return toolBar;
	}

	private WebView getPageWeb(String url) {

		WebView pageWeb = new WebView();
		WebEngine renduWeb = pageWeb.getEngine();

		renduWeb.load(url);

		return pageWeb;
	}
}