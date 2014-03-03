/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package hodbrowser;
 
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

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
     
      WebView myBrowser = new WebView();
      WebEngine myWebEngine = myBrowser.getEngine();
      myWebEngine.load("https://www.google.fr/");
     
      StackPane root = new StackPane();
      root.getChildren().add(myBrowser);
      primaryStage.setScene(new Scene(root, 1280, 720));
      primaryStage.show();
  }
}