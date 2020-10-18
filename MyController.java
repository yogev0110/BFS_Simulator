package BFS;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class MyController {
	@FXML
	protected StackPane stack;

	@FXML
	protected Button reset;

	@FXML
	protected TextField widthT;

	@FXML
	protected TextField heigthT;

	@FXML
	protected RadioButton creation_button;

	@FXML
	protected RadioButton random_button;
	
	@FXML
	protected TextField sizeTf;
	
	@FXML
	void EnteredHeigth(InputMethodEvent event) {

	}

	@FXML
	void EnteredWidth(InputMethodEvent event) {

	}

	@FXML
	void PressedReset(MouseEvent event) {

	}
}
