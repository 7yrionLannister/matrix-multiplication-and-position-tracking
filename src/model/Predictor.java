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
	public long[][] generateRandomMatrix(int rows, int cols, boolean repeatedElements) {
		ArrayList<Integer> nums = new ArrayList<>();
		long[][] rm = new long[rows][cols];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				int t = (System.nanoTime()%2==0 ? -1: 1);
				if(repeatedElements) {
					rm[i][j] = (int)(Math.random()*800+1)*t;
				} else {
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
	public long[][] add(long[][] A, long[][] B) {
		validateDimensionsStandard(A, B);
		int rows = A.length;
		int cols = A[0].length;
		long[][] C = new long[rows][cols];
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
	public long[][] subtract(long[][] A, long[][] B) {
		validateDimensionsStandard(A, B);
		int rows = A.length;
		int cols = A[0].length;
		long[][] C = new long[rows][cols];
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
	public long[][] standardMultiply(long[][] A, long[][] B) {
		validateDimensionsStandard(A, B);
		int rows = A.length;
		int cols = B[0].length;
		long[][] C = new long[rows][cols];
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
	public void validateDimensionsStandard(long[][] A, long[][] B) {
		if(A[0].length != B.length) {
			throw new IllegalArgumentException("Incompatible dimensions: A(" + A.length + "," + A[0].length + ") and B(" + B.length + "," + B[0].length + ")");
		}
	}
	/**This method multiplies two matrices A and B in the divide and conquer way.
	 * @param A A matrix of last positions.
	 * @param B A predictor matrix.
	 * @return A matrix with the actual Mars troops positions.
   	 */
	public long[][] divideAndConquerMultiply(long[][] A, long[][] B) {
		validateDimensionsDivideAndConquer(A, B);
		return divideAndConquerMultiplyRecursive(A, B);
	}
	/**This method multiplies two matrices A and B in the divide and conquer way through a recursive structure.
	 * @param A A matrix of last positions.
	 * @param B A predictor matrix.
	 * @return A matrix with the actual Mars troops positions.
   	 */
	private long[][] divideAndConquerMultiplyRecursive(long[][] A, long[][] B) {
		int n = A.length;
		long[][] result = new long[n][n];
		if(n == 1) {
			result[0][0] = A[0][0] * B[0][0];
		} else {
			long[][] A1 = new long[n/2][n/2];
			long[][] A2 = new long[n/2][n/2];
			long[][] A3 = new long[n/2][n/2];
			long[][] A4 = new long[n/2][n/2];
			
			long[][] B1 = new long[n/2][n/2];
			long[][] B2 = new long[n/2][n/2];
			long[][] B3 = new long[n/2][n/2];
			long[][] B4 = new long[n/2][n/2];
			
			divide(A1, A, 0, 0);
			divide(A2, A, 0, n/2);
			divide(A3, A, n/2, 0);
			divide(A4, A, n/2, n/2);
			
			divide(B1, B, 0, 0);
			divide(B2, B, 0, n/2);
			divide(B3, B, n/2, 0);
			divide(B4, B, n/2, n/2);
			
			long[][] C1 = add(divideAndConquerMultiply(A1, B1), divideAndConquerMultiply(A2, B3));
			long[][] C2 = add(divideAndConquerMultiply(A1, B2), divideAndConquerMultiply(A2, B4));
			long[][] C3 = add(divideAndConquerMultiply(A3, B1), divideAndConquerMultiply(A4, B3));
			long[][] C4 = add(divideAndConquerMultiply(A3, B2), divideAndConquerMultiply(A4, B4));
			
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
	public void divide(long[][] submatrix, long[][] matrix, int row, int column) {
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
	public void join(long[][] submatrix, long[][] matrix, int row, int column) {
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
	public void validateDimensionsDivideAndConquer(long[][] A, long[][] B) {
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
	public long[][] strassenMultiply(long[][] A, long[][] B) {
		validateDimensionsDivideAndConquer(A, B);
		return strassenMultiplyRecursive(A, B);
	}
	/**This method multiplies two matrices A and B in the strassen way through a recursive structure.
	 * @param A A matrix of last positions.
	 * @param B A predictor matrix.
	 * @return A matrix with the actual Mars troops positions.
   	 */
	private long[][] strassenMultiplyRecursive(long[][] A, long[][] B) {
		int n = A.length;
		long[][] result = new long[n][n];
		if(n == 1) {
			result[0][0] = A[0][0] * B[0][0];
		} else {
			long[][] A1 = new long[n/2][n/2];
			long[][] A2 = new long[n/2][n/2];
			long[][] A3 = new long[n/2][n/2];
			long[][] A4 = new long[n/2][n/2];

			long[][] B1 = new long[n/2][n/2];
			long[][] B2 = new long[n/2][n/2];
			long[][] B3 = new long[n/2][n/2];
			long[][] B4 = new long[n/2][n/2];

			divide(A1, A, 0, 0);
			divide(A2, A, 0, n/2);
			divide(A3, A, n/2, 0);
			divide(A4, A, n/2, n/2);

			divide(B1, B, 0, 0);
			divide(B2, B, 0, n/2);
			divide(B3, B, n/2, 0);
			divide(B4, B, n/2, n/2);

			long[][] P = strassenMultiplyRecursive(add(A1, A4),add(B1, B4));
			long[][] Q = strassenMultiplyRecursive(add(A3, A4), B1);
			long[][] R = strassenMultiplyRecursive(A1, subtract(B2, B4));
			long[][] S = strassenMultiplyRecursive(A4, subtract(B3, B1));
			long[][] T = strassenMultiplyRecursive(add(A1, A2), B4);
			long[][] U = strassenMultiplyRecursive(subtract(A3, A1),add(B1, B2));
			long[][] V = strassenMultiplyRecursive(subtract(A2, A4),add(B3, B4));

			long[][] C1 = add(add(P, subtract(S, T)), V);
			long[][] C2 = add(R, T);
			long[][] C3 = add(Q, S);
			long[][] C4 = add(add(P, subtract(R, Q)), U);

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
	public boolean isPrime(long num) {
		boolean prime = true;
		if(num <= 1) {
			prime = false;
		}
		for(long i = 2; i <= num/2 && prime; ++i) {
            if(num % i == 0) {
                prime = false;
            }
        }
		return prime;
	}
}
