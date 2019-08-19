package model;

public class StandardMatrixOperations {
	
	public static int[][] add(int[][] A, int[][] B) {
		validateDimensions(A, B);
		int n = A.length;
		int[][] C = new int[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				C[i][j] = A[i][j] + B[i][j];
			}
		}
		return C;
	}
	
	public static int[][] substract(int[][] A, int[][] B) {
		validateDimensions(A, B);
		int n = A.length;
		int[][] C = new int[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				C[i][j] = A[i][j] - B[i][j];
			}
		}
		return C;
	}
	
	public static int[][] multiply(int[][] A, int[][] B) {
		validateDimensions(A, B);
		int n = A.length;
		int[][] C = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					C[i][j] += A[i][k]*B[k][j];
				}
			}
		}
		return C;
	}
	
	public static void validateDimensions(int[][] A, int[][] B) {
		if(A[0].length != B.length) {
			throw new IllegalArgumentException("Incompatible dimensions: A(" + A.length + "," + A[0].length + ") and B(" + B.length + "," + B[0].length + ")");
		}
	}
}
