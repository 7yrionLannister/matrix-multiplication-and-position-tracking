package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DivideAndConquer;
import model.StandardMatrixOperations;
import model.Strassen;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("WarForSaturnMoons.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root,900,600);
			primaryStage.setScene(scene);
			primaryStage.setTitle("SATURN MOONS WAR");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
		/*int[][] A = new int[][] {{1,2,3,4,5,6,7,8},
			{8,7,6,5,4,3,2,1},
			{2,2,2,2,2,2,2,2},
			{3,3,3,3,3,3,3,3},
			{4,4,4,4,4,4,4,4},
			{5,5,5,5,5,5,5,5},
			{6,6,6,6,6,6,6,6},
			{7,7,7,7,7,7,7,7}};
		System.out.println(A.length+" "+A[0].length);
		int[][] B = new int[][] {{1,2,3,4,5,6,7,8},
			{8,7,6,5,4,3,2,1},
			{2,2,2,2,2,2,2,2},
			{3,3,3,3,3,3,3,3},
			{4,4,4,4,4,4,4,4},
			{5,5,5,5,5,5,5,5},
			{6,6,6,6,6,6,6,6},
			{7,7,7,7,7,7,7,7}};
		int[][] C = DivideAndConquer.multiply(A, B);
		for (int i = 0; i < C.length; i++) {
			for (int j = 0; j < C[i].length; j++) {
				System.out.print(C[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("****************************************");
		C = Strassen.multiply(A, B);
		for (int i = 0; i < C.length; i++) {
			for (int j = 0; j < C[i].length; j++) {
				System.out.print(C[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("****************************************");
		C = StandardMatrixOperations.multiply(A, B);
		for (int i = 0; i < C.length; i++) {
			for (int j = 0; j < C[i].length; j++) {
				System.out.print(C[i][j] + " ");
			}
			System.out.println();
		}*/
	}
}





