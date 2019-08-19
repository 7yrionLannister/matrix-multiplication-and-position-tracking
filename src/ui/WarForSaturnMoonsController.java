package ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class WarForSaturnMoonsController implements Initializable{
	
	@FXML
    private ChoiceBox<String> methodChoicer;

    @FXML
    private TextField sizeField;

    @FXML
    private Button multiplyM;

    @FXML
    void multiplyMatrices(ActionEvent event) {

    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> methodsList = FXCollections.observableArrayList("Standard Multiplication","Divide and Conquer","Strassen Algorithm");
		methodChoicer.setItems(methodsList);
	}

}