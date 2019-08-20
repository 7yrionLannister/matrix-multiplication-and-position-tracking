package model;

public class DivideAndConquer {
	
	public static int[][] multiply(int[][] A, int[][] B) {
		validateDimensions(A, B);
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
			
			divide(A1, A, 0, 0);
			divide(A2, A, 0, n/2);
			divide(A3, A, n/2, 0);
			divide(A4, A, n/2, n/2);
			
			divide(B1, B, 0, 0);
			divide(B2, B, 0, n/2);
			divide(B3, B, n/2, 0);
			divide(B4, B, n/2, n/2);
			
			int[][] C1 = StandardMatrixOperations.add(multiply(A1, B1), multiply(A2, B3));
			int[][] C2 = StandardMatrixOperations.add(multiply(A1, B2), multiply(A2, B4));
			int[][] C3 = StandardMatrixOperations.add(multiply(A3, B1), multiply(A4, B3));
			int[][] C4 = StandardMatrixOperations.add(multiply(A3, B2), multiply(A4, B4));
			
			join(C1, result, 0, 0);
			join(C2, result, 0, n/2);
			join(C3, result, n/2, 0);
			join(C4, result, n/2, n/2);
		}
		return result;
	}
	
	public static void divide(int[][] submatrix, int[][] matrix, int row, int column) {
		int n = submatrix.length;
        for(int i1 = 0, i2 = row; i1 < n; i1++, i2++) {
            for(int j1 = 0, j2 = column; j1 < n; j1++, j2++) {
                submatrix[i1][j1] = matrix[i2][j2];
            }
        }
    }
	
	public static void join(int[][] submatrix, int[][] matrix, int row, int column) {
		int n = submatrix.length;
        for(int i1 = 0, i2 = row; i1 < n; i1++, i2++) {
            for(int j1 = 0, j2 = column; j1 < n; j1++, j2++) {
                matrix[i2][j2] = submatrix[i1][j1];
            }
        }
    }
	
	public static void validateDimensions(int[][] A, int[][] B) {
		double log2 = Math.log10(A.length)/Math.log10(2);
		if((int)log2 - log2 != 0 ||
				A.length != B[0].length ||
				A[0].length != B.length ||
				A.length != A[0].length) {
			throw new IllegalArgumentException("Incompatible dimensions for divide and conquer: A(" + A.length + "," + A[0].length + ") and B(" + B.length + "," + B[0].length + ")");
		}
	}
}
