package model;

public class Predictor {
	public final static String STANDARD = "Standard";
	public final static String DIVIDE_CONQUER = "Divide and conquer";
	public final static String STRASSEN = "Strassen";
	
	private int[][] lastAmatrix;
	private int[][] lastBmatrix;
	private int[][] lastResultMatrix;
	
	public void multiply(int[][] A, int[][] B, String desiredMethod) {
		int[][] C = null;
		switch(desiredMethod) {
		case STANDARD:
			C = StandardMatrixOperations.multiply(A, B);
			break;
		case DIVIDE_CONQUER:
			C = DivideAndConquer.multiply(A, B);
			break;
		case STRASSEN:
			C = Strassen.multiply(A, B);
			break;
		}
		//If it arises this point then the matrices were multiplied without problems, so save the history
		lastAmatrix = A;
		lastBmatrix = B;
		lastResultMatrix = C;
	}
	
	public int[][] randomMatrix(int rows, int cols, boolean repeatedElements) {
		//TODO implementme
		return null;
	}

	public int[][] getLastAmatrix() {
		return lastAmatrix;
	}

	public void setLastAmatrix(int[][] lastAmatrix) {
		this.lastAmatrix = lastAmatrix;
	}

	public int[][] getLastBmatrix() {
		return lastBmatrix;
	}

	public void setLastBmatrix(int[][] lastBmatrix) {
		this.lastBmatrix = lastBmatrix;
	}

	public int[][] getLastResultMatrix() {
		return lastResultMatrix;
	}

	public void setLastResultMatrix(int[][] lastResultMatrix) {
		this.lastResultMatrix = lastResultMatrix;
	}
}
