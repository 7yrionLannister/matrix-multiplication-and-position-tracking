package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import model.Predictor;

public class WarForSaturnMoonsController {

    @FXML
    private TextField rowsATextField;

    @FXML
    private TextField colsATextField;

    @FXML
    private GridPane matrixA;

    @FXML
    private RadioButton repeatARadioButton;

    @FXML
    private ToggleGroup switchA;

    @FXML
    private RadioButton notrepeatARadioButton;

    @FXML
    private TextField rowsBTextField;

    @FXML
    private TextField colsBTextField;

    @FXML
    private GridPane matrixB;

    @FXML
    private RadioButton repeatBRadioButton;

    @FXML
    private ToggleGroup switchB;

    @FXML
    private RadioButton notrepeatBRadioButton;

    @FXML
    private RadioButton smmRadioButton;

    @FXML
    private ToggleGroup multiplicationMethod;

    @FXML
    private RadioButton dcaRadioButton;

    @FXML
    private RadioButton saRadioButton;
    
    private Predictor predictor;
    
	@FXML
	public void initialize() {
		predictor = new Predictor();
		
		repeatARadioButton.setUserData(true);
		notrepeatARadioButton.setUserData(false);
		
		repeatBRadioButton.setUserData(true);
		notrepeatBRadioButton.setUserData(false);
		
		smmRadioButton.setUserData(Predictor.STANDARD);
		dcaRadioButton.setUserData(Predictor.DIVIDE_CONQUER);
		saRadioButton.setUserData(Predictor.STRASSEN);
	}
	
	@FXML
	public void generateMatrixAButtonPressed(ActionEvent event) {
		matrixA.getChildren().clear();
		if(!rowsATextField.getText().isEmpty() && !colsATextField.getText().isEmpty()) {
			int mtrx[][] = predictor.randomMatrix(Integer.parseInt(rowsATextField.getText()), Integer.parseInt(colsATextField.getText()), (boolean)switchA.getSelectedToggle().getUserData());
			Label box;
			int evenOrOdd = -1;
			for(int i = 0; i < mtrx.length; i++) {
				for(int j = 0; j < mtrx[i].length; j++) {
					evenOrOdd += 1;
					box = new Label(""+mtrx[i][j]);
					box.setMinWidth(30);
					if(evenOrOdd%2 == 0) {
						box.setStyle("-fx-background-color: White");
					}
					else {
						box.setStyle("-fx-background-color: BurlyWood");
					}
					matrixA.add(box, j, i);
				}
			}
		}
		else {
			//TODO popup some warning
		}
	}
	
	@FXML
	public void generateMatrixBButtonPressed(ActionEvent event) {
		matrixB.getChildren().clear();
		if(!rowsBTextField.getText().isEmpty() && !colsBTextField.getText().isEmpty()) {
			int mtrx[][] = predictor.randomMatrix(Integer.parseInt(rowsBTextField.getText()), Integer.parseInt(colsBTextField.getText()), (boolean)switchB.getSelectedToggle().getUserData());
			Label box;
			int evenOrOdd = -1;
			for(int i = 0; i < mtrx.length; i++) {
				for(int j = 0; j < mtrx[i].length; j++) {
					evenOrOdd += 1;
					box = new Label(""+mtrx[i][j]);
					box.setMinWidth(30);
					if(evenOrOdd%2 == 0) {
						box.setStyle("-fx-background-color: White");
					}
					else {
						box.setStyle("-fx-background-color: BurlyWood");
					}
					matrixB.add(box, j, i);
				}
			}
		}
		else {
			//TODO popup some warning
		}
	}
}