package model;

public class Strassen {
	
	public static int[][] multiply(int[][] A, int[][] B) {
		DivideAndConquer.validateDimensions(A, B);
		return multiplyRecursive(A, B);
	}
	
	private static int[][] multiplyRecursive(int[][] A, int[][] B) {
		int n = A.length;
		int[][] result = new int[n][n];
		if(n == 1) {
			result[0][0] = A[0][0] * B[0][0];
		} else {
			int[][] A1 = new int[n/2][n/2];
			int[][] A2 = new int[n/2][n/2];
			int[][] A3 = new int[n/2][n/2];
			int[][] A4 = new int[n/2][n/2];
			
			int[][] B1 = new int[n/2][n/2];
			int[][] B2 = new int[n/2][n/2];
			int[][] B3 = new int[n/2][n/2];
			int[][] B4 = new int[n/2][n/2];
			
			DivideAndConquer.divide(A1, A, 0, 0);
			DivideAndConquer.divide(A2, A, 0, n/2);
			DivideAndConquer.divide(A3, A, n/2, 0);
			DivideAndConquer.divide(A4, A, n/2, n/2);
			
			DivideAndConquer.divide(B1, B, 0, 0);
			DivideAndConquer.divide(B2, B, 0, n/2);
			DivideAndConquer.divide(B3, B, n/2, 0);
			DivideAndConquer.divide(B4, B, n/2, n/2);
			
			int[][] P = multiply(StandardMatrixOperations.add(A1, A4),StandardMatrixOperations.add(B1, B4));
			int[][] Q = multiply(StandardMatrixOperations.add(A3, A4), B1);
			int[][] R = multiply(A1, StandardMatrixOperations.substract(B2, B4));
			int[][] S = multiply(A4, StandardMatrixOperations.substract(B3, B1));
			int[][] T = multiply(StandardMatrixOperations.add(A1, A2), B4);
			int[][] U = multiply(StandardMatrixOperations.substract(A3, A1),StandardMatrixOperations.add(B1, B2));
			int[][] V = multiply(StandardMatrixOperations.substract(A2, A4),StandardMatrixOperations.add(B3, B4));
			
			int[][] C1 = StandardMatrixOperations.add(StandardMatrixOperations.add(P, StandardMatrixOperations.substract(S, T)), V);
			int[][] C2 = StandardMatrixOperations.add(R, T);
			int[][] C3 = StandardMatrixOperations.add(Q, S);
			int[][] C4 = StandardMatrixOperations.add(StandardMatrixOperations.add(P, StandardMatrixOperations.substract(R, Q)), U);
			
			DivideAndConquer.join(C1, result, 0, 0);
			DivideAndConquer.join(C2, result, 0, n/2);
			DivideAndConquer.join(C3, result, n/2, 0);
			DivideAndConquer.join(C4, result, n/2, n/2);
		}
		return result;
	}
}
