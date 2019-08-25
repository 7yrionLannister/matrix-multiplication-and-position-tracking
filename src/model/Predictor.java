package model;

import java.util.ArrayList;

public class Predictor {
	/**It represents a constant in order to use the only the standard multiply method.
   	 */
	public final static String STANDARD = "Standard";
	/**It represents a constant in order to use the only the divide and conquer multiply method.
   	 */
	public final static String DIVIDE_CONQUER = "Divide and conquer";
	/**It represents a constant in order to use the only the strassen multiply method.
   	 */
	public final static String STRASSEN = "Strassen";
	/**This method generates a random matrix specifying its number of rows and columns and if there may be some repeated
	 * values or not inside it.
	 * @param rows An integer that represents the number of rows for the wished matrix.
	 * @param cols An integer that represents the number of columns for the wished matrix.
	 * @param repeatedElements A boolean that represents if the matrix could have or not repeated values.
	 * @return A matrix of rows*cols dimension.
   	 */
	public int[][] generateRandomMatrix(int rows, int cols, boolean repeatedElements) {
		ArrayList<Integer> nums = new ArrayList<>();
		int[][] rm = new int[rows][cols];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				if(repeatedElements) {
					rm[i][j] = (int)(Math.random()*800+1);
				} else {
					int t = (System.currentTimeMillis()%2==0 ? -1: 1);
					int n = (int)(Math.random()*1000000+1)*t;
					while(nums.contains(n)) {
						n = (int)(Math.random()*1000000+1)*t;
					}
					nums.add(n);
					rm[i][j] = n;
				}
			}
		}
		return rm;
	}
	/**This method adds two sub-matrices A and B.
	 * @param A A sub-matrix from the grand matrix of last positions of 2^nx2^n dimension.
	 * @param B A sub-matrix from the grand predictor matrix of 2^nx2^n dimension.
	 * @return A sub-matrix created from the A and B adding.
   	 */
	public int[][] add(int[][] A, int[][] B) {
		validateDimensionsStandard(A, B);
		int rows = A.length;
		int cols = A[0].length;
		int[][] C = new int[rows][cols];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				C[i][j] = A[i][j] + B[i][j];
			}
		}
		return C;
	}
	/**This method subtracts two sub-matrices A and B.
	 * @param A A sub-matrix from the grand matrix of last positions of 2^nx2^n dimension.
	 * @param B A sub-matrix from the grand predictor matrix of 2^nx2^n dimension.
	 * @return A sub-matrix created from the A and B subtracting.
   	 */
	public int[][] subtract(int[][] A, int[][] B) {
		validateDimensionsStandard(A, B);
		int rows = A.length;
		int cols = A[0].length;
		int[][] C = new int[rows][cols];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				C[i][j] = A[i][j] - B[i][j];
			}
		}
		return C;
	}
	/**This method multiplies two matrices A and B in the standard way.
	 * @param A A matrix of last positions.
	 * @param B A predictor matrix.
	 * @return A matrix with the actual Mars troops positions.
   	 */
	public int[][] standardMultiply(int[][] A, int[][] B) {
		validateDimensionsStandard(A, B);
		int rows = A.length;
		int cols = B[0].length;
		int[][] C = new int[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				for (int k = 0; k < A[0].length; k++) {
					C[i][j] += A[i][k]*B[k][j];
				}
			}
		}
		return C;
	}
	/**This method verifies if the matrix A and B have correct dimensions to be multiplied between them by the standard way.
	 * @param A A matrix of last positions.
	 * @param B A predictor matrix.
   	 */
	public void validateDimensionsStandard(int[][] A, int[][] B) {
		if(A[0].length != B.length) {
			throw new IllegalArgumentException("Incompatible dimensions: A(" + A.length + "," + A[0].length + ") and B(" + B.length + "," + B[0].length + ")");
		}
	}
	/**This method multiplies two matrices A and B in the divide and conquer way.
	 * @param A A matrix of last positions.
	 * @param B A predictor matrix.
	 * @return A matrix with the actual Mars troops positions.
   	 */
	public int[][] divideAndConquerMultiply(int[][] A, int[][] B) {
		validateDimensionsDivideAndConquer(A, B);
		return divideAndConquerMultiplyRecursive(A, B);
	}
	/**This method multiplies two matrices A and B in the divide and conquer way through a recursive structure.
	 * @param A A matrix of last positions.
	 * @param B A predictor matrix.
	 * @return A matrix with the actual Mars troops positions.
   	 */
	private int[][] divideAndConquerMultiplyRecursive(int[][] A, int[][] B) {
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
			
			int[][] C1 = add(divideAndConquerMultiply(A1, B1), divideAndConquerMultiply(A2, B3));
			int[][] C2 = add(divideAndConquerMultiply(A1, B2), divideAndConquerMultiply(A2, B4));
			int[][] C3 = add(divideAndConquerMultiply(A3, B1), divideAndConquerMultiply(A4, B3));
			int[][] C4 = add(divideAndConquerMultiply(A3, B2), divideAndConquerMultiply(A4, B4));
			
			join(C1, result, 0, 0);
			join(C2, result, 0, n/2);
			join(C3, result, n/2, 0);
			join(C4, result, n/2, n/2);
		}
		return result;
	}
	/**This method divide a matrix in four sub-matrices of the same dimension.
	 * @param submatrix A matrix that represent one of the grand matrix quarters.
	 * @param matrix A matrix that represents the grand matrix.
	 * @param row An integer that represents the number of rows for the division.
	 * @param column An integer that represents the number of columns for the division.
	 */
	public void divide(int[][] submatrix, int[][] matrix, int row, int column) {
		int n = submatrix.length;
        for(int i1 = 0, i2 = row; i1 < n; i1++, i2++) {
            for(int j1 = 0, j2 = column; j1 < n; j1++, j2++) {
                submatrix[i1][j1] = matrix[i2][j2];
            }
        }
    }
	/**This method joins a sub-matrix into a grand matrix.
	 * @param submatrix A matrix that represent one of the grand matrix quarters.
	 * @param matrix A matrix that represents the grand matrix.
	 * @param row An integer that represents the number of rows for the joining.
	 * @param column An integer that represents the number of columns for the joining.
	 */
	public void join(int[][] submatrix, int[][] matrix, int row, int column) {
		int n = submatrix.length;
        for(int i1 = 0, i2 = row; i1 < n; i1++, i2++) {
            for(int j1 = 0, j2 = column; j1 < n; j1++, j2++) {
                matrix[i2][j2] = submatrix[i1][j1];
            }
        }
    }
	/**This method verifies if the matrix A and B have 2^nx2^n dimensions to be multiplied between them by divide and conquer or strassen way.
	 * @param A A matrix of last positions.
	 * @param B A predictor matrix.
   	 */
	public void validateDimensionsDivideAndConquer(int[][] A, int[][] B) {
		validateDimensionsStandard(A, B);
		double log2 = Math.log10(A.length)/Math.log10(2);
		if((int)log2 - log2 != 0 ||
				A.length != B[0].length ||
				A[0].length != B.length ||
				A.length != A[0].length) {
			throw new IllegalArgumentException("Incompatible dimensions for divide and conquer algorithm: A(" + A.length + "," + A[0].length + ") and B(" + B.length + "," + B[0].length + ")");
		}
	}
	/**This method multiplies two matrices A and B in the strassen way.
	 * @param A A matrix of last positions.
	 * @param B A predictor matrix.
	 * @return A matrix with the actual Mars troops positions.
   	 */
	public int[][] strassenMultiply(int[][] A, int[][] B) {
		validateDimensionsDivideAndConquer(A, B);
		return strassenMultiplyRecursive(A, B);
	}
	/**This method multiplies two matrices A and B in the strassen way through a recursive structure.
	 * @param A A matrix of last positions.
	 * @param B A predictor matrix.
	 * @return A matrix with the actual Mars troops positions.
   	 */
	private int[][] strassenMultiplyRecursive(int[][] A, int[][] B) {
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

			int[][] P = strassenMultiplyRecursive(add(A1, A4),add(B1, B4));
			int[][] Q = strassenMultiplyRecursive(add(A3, A4), B1);
			int[][] R = strassenMultiplyRecursive(A1, subtract(B2, B4));
			int[][] S = strassenMultiplyRecursive(A4, subtract(B3, B1));
			int[][] T = strassenMultiplyRecursive(add(A1, A2), B4);
			int[][] U = strassenMultiplyRecursive(subtract(A3, A1),add(B1, B2));
			int[][] V = strassenMultiplyRecursive(subtract(A2, A4),add(B3, B4));

			int[][] C1 = add(add(P, subtract(S, T)), V);
			int[][] C2 = add(R, T);
			int[][] C3 = add(Q, S);
			int[][] C4 = add(add(P, subtract(R, Q)), U);

			join(C1, result, 0, 0);
			join(C2, result, 0, n/2);
			join(C3, result, n/2, 0);
			join(C4, result, n/2, n/2);
		}
		return result;
	}
	/**This method verifies if an integer is prime or not.
	 * @param num An integer that represents the value to verify.
	 * @return A boolean showing if the integer arrived as parameter is prime or not.
	 */
	public boolean isPrime(int num) {
		boolean prime = true;
		if(num == 1) {
			prime = false;
		}
		for(int i = 2; i <= num/2 && prime; ++i) {
            if(num % i == 0) {
                prime = false;
            }
        }
		return prime;
	}
}
