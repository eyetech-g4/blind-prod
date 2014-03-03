/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package hodbrowser;
 
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
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
	  
	  primaryStage.setScene(new Scene(affichage, 1280, 720));
      
      affichage.setTop(getMenu());
      affichage.setCenter(getPageWeb());
      
      primaryStage.setFullScreen(true);
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