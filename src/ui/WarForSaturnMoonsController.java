package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Predictor;

public class WarForSaturnMoonsController {

    @FXML private TextField rowsATextField;
    @FXML private TextField colsATextField;
    @FXML private ScrollPane matrixAScrollPane;
    @FXML private RadioButton repeatARadioButton;
    @FXML private ToggleGroup switchA;
    @FXML private RadioButton notrepeatARadioButton;
    @FXML private TextField rowsBTextField;
    @FXML private TextField colsBTextField;
    @FXML private ScrollPane matrixBScrollPane;
    @FXML private RadioButton repeatBRadioButton;
    @FXML private ToggleGroup switchB;
    @FXML private RadioButton notrepeatBRadioButton;
    @FXML private RadioButton smmRadioButton;
    @FXML private ToggleGroup multiplicationMethod;
    @FXML private RadioButton dcaRadioButton;
    @FXML private RadioButton saRadioButton;
    
    /**It represents the grid where the last Mars troops positions reported will be.
   	 */
    private GridPane matrixA;
    /**It represents the grid where the matrix which we will be able to find the new actual Mars troops positions will be.
   	 */
    private GridPane matrixB;
    /**It represents the matrix with last Mars troops positions reported.
   	 */
    private long[][] A;
    /**It represents the matrix which we will be able to find the new actual Mars troops positions.
   	 */
    private long[][] B;
    /**It represents an instance of Predictor class.
   	 */
    private Predictor predictor;
    
    /**This method initializes all the objects needed.
   	 */
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
		
		matrixA = new GridPane();
		matrixAScrollPane.setContent(matrixA);
		matrixB = new GridPane();
		matrixBScrollPane.setContent(matrixB);
	}
	/**This method generates the matrix A specifying its dimension through the interface.
	 * @param event An ActionEvent that represents the event when the associated generateMatrix button is pressed.
   	 */
	@FXML
	public void generateMatrixAButtonPressed(ActionEvent event) {
		matrixA.getChildren().clear();
		if(!rowsATextField.getText().isEmpty() && !colsATextField.getText().isEmpty()) {
			A = predictor.generateRandomMatrix(Integer.parseInt(rowsATextField.getText()), Integer.parseInt(colsATextField.getText()), (boolean)switchA.getSelectedToggle().getUserData());
			Label box;
			for(int i = 0; i < A.length; i++) {
				int evenOrOdd = i;
				for(int j = 0; j < A[i].length; j++) {
					evenOrOdd += 1;
					box = new Label(""+A[i][j]);
					box.setMinWidth(30);
					if(evenOrOdd%2 == 0) {
						box.setStyle("-fx-background-color: White");
					}
					else {
						box.setStyle("-fx-background-color: BurlyWood");
					}
					matrixA.add(box, j, i);
					boolean prime = predictor.isPrime(A[i][j]);
					if(prime) {
						box.setTextFill(Color.RED);
						box.setStyle(box.getStyle()+";-fx-font-weight: bold;");
					}
				}
			}
		}
		else {
			showWarning("Please provide all the data required to generate the matrix");
		}
	}
	/**This method generates the matrix B specifying its dimension through the interface.
	 * @param event An ActionEvent that represents the event when the associated generateMatrix button is pressed.
   	 */
	@FXML
	public void generateMatrixBButtonPressed(ActionEvent event) {
		matrixB.getChildren().clear();
		if(!rowsBTextField.getText().isEmpty() && !colsBTextField.getText().isEmpty()) {
			B = predictor.generateRandomMatrix(Integer.parseInt(rowsBTextField.getText()), Integer.parseInt(colsBTextField.getText()), (boolean)switchB.getSelectedToggle().getUserData());
			Label box;
			for(int i = 0; i < B.length; i++) {
				int evenOrOdd = i;
				for(int j = 0; j < B[i].length; j++) {
					evenOrOdd += 1;
					box = new Label(""+B[i][j]);
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
			showWarning("Please provide all the data required to generate the matrix");
		}
	}
	/**This method multiplies the matrices A and B which were generated previously.
	 * @param event An ActionEvent that represents the event when the associated multiply button is pressed.
   	 */
	@FXML
	public void multiplyButtonPressed(ActionEvent event) {
		try {
			long[][] result = null;
			switch((String)multiplicationMethod.getSelectedToggle().getUserData()) {
			case Predictor.STANDARD:
				result = predictor.standardMultiply(A, B);
				break;
			case Predictor.DIVIDE_CONQUER:
				result = predictor.divideAndConquerMultiply(A, B);
				break;
			case Predictor.STRASSEN:
				result = predictor.strassenMultiply(A, B);
				break;
			}
			showResult(result);
		} catch(IllegalArgumentException iae) {
			showWarning(iae.getMessage());
		} catch(NullPointerException npe) {
			System.out.println(npe.getStackTrace());
			showWarning("Please make sure you have generated both matrices");
		} 
	}
	/**This method shows a warning message indicating such that the matrices A and B have incorrect dimensions or one or
	 * both matrices are empty.
	 * @param msg A String that represents the event when the associated generateMatrix button is pressed.
   	 */
	public void showWarning(String msg) {
		Alert a = new Alert(AlertType.WARNING);
		a.setContentText(msg);
		a.showAndWait();
	}
	/**This method shows the resulting matrix after multiplying A and B with the actual Mars troops coordinates underlined.
	 * @param result A matrix that was yield after multiplying A and B.
   	 */
	public void showResult(long[][] result) {
		GridPane g = new GridPane();
		ScrollPane sp = new ScrollPane(g);
		Scene s = new Scene(sp);
		Stage st = new Stage();
		st.setScene(s);
		st.initOwner(matrixA.getParent().getScene().getWindow());
		st.initModality(Modality.WINDOW_MODAL);
		
		Label box;
		for(int i = 0; i < result.length; i++) {
			int evenOrOdd = i;
			for(int j = 0; j < result[i].length; j++) {
				evenOrOdd += 1;
				box = new Label(""+result[i][j]);
				box.setMinWidth(30);
				if(evenOrOdd%2 == 0) {
					box.setStyle("-fx-background-color: White");
				}
				else {
					box.setStyle("-fx-background-color: BurlyWood");
				}
				g.add(box, j, i);
				boolean prime = predictor.isPrime(result[i][j]);
				if(prime) {
					box.setTextFill(Color.RED);
					box.setStyle(box.getStyle()+";-fx-font-weight: bold;");
				}
			}
		}
		st.setTitle("We've caught you, Martians!");
		st.setWidth(360);
		st.showAndWait();
	}
}