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
	private Navigation Navigate= new Navigation();
	private String url="https://www.google.fr/";
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

		affichage.setTop(setMenu(url));
		affichage.setCenter(getPageWeb("https://www.google.fr/"));
		Navigate.setURLpath();
		primaryStage.show();

	}



	private ToolBar setMenu(String urlAffichee) {

		// Debut Style et positionnement des différents boutons ect....
				addressBar.setText(urlAffichee);
				addressBar.setPrefSize(950, 50);
				addressBar.setStyle("-fx-font-size: 30;");
				
				toolBar.setPrefHeight(200);
				toolBar.setStyle("-fx-base: #424242;" );
				
				buttonGo.setPrefHeight(90);
				
				buttonPrevious.setPrefSize(200,100);
				buttonPrevious.setStyle("-fx-base: #ffffff; -fx-font-size: 30;" );
				buttonPrevious.setTranslateY(-40);
				
				buttonNext.setPrefSize(200,100);
				buttonNext.setStyle("-fx-base: #ffffff; -fx-font-size: 30;" );
				buttonNext.setTranslateY(-40);
				
				buttonRefresh.setPrefHeight(90);
				buttonStop.setPrefHeight(90);
				
				addressBar.setTranslateX(-500);
				addressBar.setTranslateY(70);
				// Fin
				
				toolBar.getItems().addAll(buttonPrevious, buttonNext, buttonRefresh,
						buttonStop, buttonGo, addressBar);
				
				// Debut Action du Bouton Go
				buttonGo.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						affichage.setCenter(getPageWeb(addressBar.getText()));
					}
				}); 
				// Fin
				
				// Debut Action de l'adresse Bar
				addressBar.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						affichage.setCenter(getPageWeb(addressBar.getText()));
						Navigate.setURLpath();
					}
				});

				buttonPrevious.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						affichage.setCenter(getPageWeb(Navigate.getPreviousURLpath()));
					}
				});
				buttonNext.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						affichage.setCenter(getPageWeb(Navigate.getNextURLpath()));
					}
				});
				return toolBar;
	}

	public static WebEngine getWebEngine() {
		return renduWeb;
	}
	
	public void setUrlAdress(String adress_url)
	{
		addressBar.setText(adress_url);
	}

	private WebView getPageWeb(String url) {

		pageWeb = new WebView();
		renduWeb = pageWeb.getEngine();
		renduWeb.load(url);
		url=renduWeb.getLocation();
		setUrlAdress(url);
		
		affichage.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				Navigate.setURLpath();
				affichage.setTop(setMenu(renduWeb.getLocation()));
			}
		});

		return pageWeb;
	}
}