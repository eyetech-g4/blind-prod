/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package hodbrowser;
 
import java.awt.Dimension;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class HodBrowser extends Application {
	
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
      launch(args);
  }
 
  @Override
  public void start(Stage primaryStage) {
	  primaryStage.setTitle("HÖD");
	  BorderPane affichage = new BorderPane();
	  
	  Screen screen = Screen.getPrimary();
	  Rectangle2D bounds = screen.getVisualBounds();

	  primaryStage.setScene(new Scene(affichage, 0, 0));
	  
	  primaryStage.setX(bounds.getMinX());
	  primaryStage.setY(bounds.getMinY());
	  primaryStage.setWidth(bounds.getWidth());
	  primaryStage.setHeight(bounds.getHeight());
	  

	  
      affichage.setTop(getMenu());
      affichage.setCenter(getPageWeb());
      

      primaryStage.show();
  }

  private ToolBar getMenu() {
	  
     ToolBar toolBar = new ToolBar();
     Button button1 = new Button("Grosseur");
     Button button2 = new Button("Contraste");
     toolBar.getItems().addAll(button1, button2);

     return toolBar;
  }
  
  private WebView getPageWeb() {
	  
	WebView pageWeb = new WebView();
	WebEngine myWebEngine = pageWeb.getEngine();
	myWebEngine.load("https://www.google.fr/");
	
	return pageWeb;
  }
}