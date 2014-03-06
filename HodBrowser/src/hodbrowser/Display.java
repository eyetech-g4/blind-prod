package hodbrowser;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;

public class Display {

	public Display() {
		//
	}

	// TOOLBAR FUNCTIONS
	public void createToolBar(ToolBar myToolBar, Integer height) {

		myToolBar.setPrefHeight(height);
		myToolBar.setStyle("-fx-base: #424242;");
	}

	public void increaseToolBar(ToolBar myToolBar) {

		Double height = myToolBar.getPrefHeight();
		myToolBar.setPrefHeight(height + 30);
	}

	public void decreaseToolBar(ToolBar myToolBar) {

		Double height = myToolBar.getPrefHeight();
		myToolBar.setPrefHeight(height - 30);
	}

	// ADRESSBAR FUNCTIONS
	public void createAddress(TextField addressBar, Integer width,
			Integer height, String displayedUrl) {

		addressBar.setText(displayedUrl);
		addressBar.setStyle("-fx-font-size: 30;");
		addressBar.setTranslateX(-820);
		addressBar.setTranslateY(55);
		addressBar.setPrefSize(width, height);
	}
	
	public void increaseAddress(TextField myTextField) {

		Double width = myTextField.getPrefWidth();
		Double height = myTextField.getPrefHeight();
		myTextField.setPrefSize(width + 30, height + 15);
	}

	public void decreaseAddress(TextField myTextField) {

		Double width = myTextField.getPrefWidth();
		Double height = myTextField.getPrefHeight();
		myTextField.setPrefSize(width - 30, height - 15);
	}

	// BUTTONS FUNCTIONS
	public void createButton(Button myButton, Integer width, Integer height) {

		myButton.setPrefSize(width, height);
		myButton.setStyle("-fx-base: #ffffff; -fx-font-size: 30;");
		myButton.setTranslateY(-55);
	}

	public void increaseButton(Button myButton) {

		Double width = myButton.getPrefWidth();
		Double height = myButton.getPrefHeight();
		myButton.setPrefSize(width + 30, height + 15);
	}

	public void decreaseButton(Button myButton) {

		Double width = myButton.getPrefWidth();
		Double height = myButton.getPrefHeight();
		myButton.setPrefSize(width - 30, height - 15);
	}
}