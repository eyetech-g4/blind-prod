package hodbrowser;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Display {

	public Display() {
		//
	}

	public void setButtonSize(Button myButton, Integer width, Integer height) {

		myButton.setPrefSize(width, height);
		myButton.setStyle("-fx-base: #ffffff; -fx-font-size: 30;");
		myButton.setTranslateY(-55);
	}

	public void setAddressSize(TextField addressBar, Integer width,
			Integer height, String displayedUrl) {

		addressBar.setText(displayedUrl);
		addressBar.setStyle("-fx-font-size: 30;");
		addressBar.setTranslateX(-820);
		addressBar.setTranslateY(55);
		addressBar.setPrefSize(width, height);
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
	
	public void increaseTextField(TextField myTextField) {

		Double width = myTextField.getPrefWidth();
		Double height = myTextField.getPrefHeight();
		myTextField.setPrefSize(width + 30, height + 15);
	}

	public void decreaseTextField(TextField myTextField) {
		
		Double width = myTextField.getPrefWidth();
		Double height = myTextField.getPrefHeight();
		myTextField.setPrefSize(width - 30, height - 15);
	}
}