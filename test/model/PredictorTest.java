package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class PredictorTest {
	private Predictor predictor; 
	private long[][] A;
	private long[][] B;

	public void setupStage1() {
		predictor = new Predictor();
		A = new long[3][3];
		B = new long[3][3];
		A[0][0] = -18;     B[0][0] = 4;
		A[1][0] = 8;	   B[1][0] = 5;
		A[2][0] = 11;	   B[2][0] = 11;
		A[0][1] = 15;      B[0][1] = 31;
		A[1][1] = 3;       B[1][1] = -13;
		A[2][1] = 2;       B[2][1] = 2;
		A[0][2] = 7;       B[0][2] = 73;
		A[1][2] = 4;       B[1][2] = 4;
		A[2][2] = 13;      B[2][2] = 1;
	}

	public void setupStage2() {
		predictor = new Predictor();
		A = new long[4][3];
		B = new long[4][3];
		A[0][0] = 4;       B[0][0] = 8;
		A[1][0] = 9;	   B[1][0] = 4;
		A[2][0] = 3;	   B[2][0] = 1;
		A[3][0] = 2;       B[3][0] = 11;
		A[0][1] = 20;      B[0][1] = 9;
		A[1][1] = 4;       B[1][1] = 3;
		A[2][1] = 1;       B[2][1] = 13;
		A[3][1] = 2;       B[3][1] = 5;
		A[0][2] = -5;      B[0][2] = 2;
		A[1][2] = 0;       B[1][2] = 0;
		A[2][2] = 8;       B[2][2] = 7;
		A[3][2] = 3;       B[3][2] = 0;
	}

	public void setupStage3() {
		predictor = new Predictor();
		A = new long[4][4];
		B = new long[4][4];
		A[0][0] = 16;      B[0][0] = 5;
		A[1][0] = -6;	   B[1][0] = 5;
		A[2][0] = 2;	   B[2][0] = 13;
		A[3][0] = 0;       B[3][0] = 0;
		A[0][1] = 9;       B[0][1] = 3;
		A[1][1] = 0;       B[1][1] = 6;
		A[2][1] = 3;       B[2][1] = 8;
		A[3][1] = 58;      B[3][1] = 8;
		A[0][2] = 7;       B[0][2] = 7;
		A[1][2] = 8;       B[1][2] = 4;
		A[2][2] = 5;       B[2][2] = 2;
		A[3][2] = 1;       B[3][2] = 0;
		A[0][3] = 6;       B[0][3] = 9;
		A[1][3] = 11;      B[1][3] = 6;
		A[2][3] = 4;       B[2][3] = 6;
		A[3][3] = 6;       B[3][3] = 1;
	}

	@Test
	public void generateRandomMatrixWithoutRepeatedValues() {
		setupStage1();
		int rows = 60;
		int cols = 60;
		long [][] C = predictor.generateRandomMatrix(rows, cols, false);
		ArrayList<Long> trail = new ArrayList<>();
		for(int i = 0; i < rows; i++) 
			for(int j = 0; j< cols; j++) {
				if(!trail.contains(C[i][j])) {
					trail.add(C[i][j]);
				} else {
					fail("A repeated element was found: " + C[i][j]);
				}
			}
		assertTrue(trail.size() == (rows*cols) && C.length == rows && C[0].length == cols, "The matrix was created without repeated values but the size does not correspond to the requested");
	}

	@Test
	public void standardMultiplyWithValidInput() {
		setupStage1();
		long[][] result = new long[3][3];
		result [0][0] = 80;
		result [1][0] = 91;
		result [2][0] = 197;
		result [0][1] = -739;
		result [1][1] = 217;
		result [2][1] = 341;
		result [0][2] = -1247;
		result [1][2] = 600;
		result [2][2] = 824;	
		long[][] C = predictor.standardMultiply(A, B);

		assertTrue(C != null, "A matrix with the Mars troops locations was expected");
		assertTrue(C.length == A.length && C[0].length == B[0].length, "The matrix is not of the right size");
		for(int i = 0; i < C.length; i++) {
			for(int j = 0; j< C[i].length; j++) {
				if(C[i][j] != result[i][j]) {
					fail("The resulting matrix does not contain the correct answer");
				}
			}
		}
	}

	@Test
	public void standardMultiplyWithInvalidInput(){
		setupStage2();
		try {
			predictor.standardMultiply(A, B);
			fail("A exception was waited");
		} catch (Exception e) {
		}
	}
	
	@Test
	public void divideAndConquerMultiplyWithValidInput(){
		setupStage3();
		int [][] result = new int[4][4];
		result [0][0] = 216;
		result [1][0] = 74;
		result [2][0] = 90;
		result [3][0] = 303;
		result [0][1] = 206;
		result [1][1] = 134;
		result [2][1] = 96;
		result [3][1] = 404;
		result [0][2] = 162;
		result [1][2] = -26;
		result [2][2] = 36;
		result [3][2] = 234;
		result [0][3] = 246;
		result [1][3] = 5;
		result [2][3] = 70;
		result [3][3] = 360;
		long[][] C = predictor.divideAndConquerMultiply(A, B);
		
		assertTrue(C != null, "A matrix with the Mars troops locations was expected");
		assertTrue(C.length == A.length && C[0].length == B[0].length, "The matrix is not of the right size");
		
		for(int i = 0; i < C.length; i++) {
			for(int j = 0; j< C[i].length; j++) {
				if(C[i][j] != result[i][j]) {
					fail("The resulting matrix does not contain the correct answer");
				}
			}
		}
	}
	
	@Test
	public void divideAndConquerMultiplyWithInvalidInput(){
		setupStage2();
		try {
			predictor.divideAndConquerMultiply(A, B);
			fail("A exception was waited");
		} catch (Exception e) {
		}
		
		setupStage1();
		try {
			predictor.divideAndConquerMultiply(A, B);
			fail("A exception was waited");
		} catch (Exception e) {			
		}
	}
	@Test
	public void strassenMultiplyWithValidInput() {
		setupStage3();
		long [][] result = new long[4][4];
		result [0][0] = 216;
		result [1][0] = 74;
		result [2][0] = 90;
		result [3][0] = 303;
		result [0][1] = 206;
		result [1][1] = 134;
		result [2][1] = 96;
		result [3][1] = 404;
		result [0][2] = 162;
		result [1][2] = -26;
		result [2][2] = 36;
		result [3][2] = 234;
		result [0][3] = 246;
		result [1][3] = 5;
		result [2][3] = 70;
		result [3][3] = 360;
		long[][] C = predictor.strassenMultiply(A, B);
		
		assertTrue(C != null, "A matrix with the Mars troops locations was expected");
		assertTrue(C.length == A.length && C[0].length == B[0].length, "The matrix is not of the right size");
		
		for(int i = 0; i < C.length; i++) {
			for(int j = 0; j< C[i].length; j++) {
				if(C[i][j] != result[i][j]) {
					fail("The resulting matrix does not contain the correct answer");
				}
			}
		}
	}
	
	@Test
	public void strassenMultiplyWithInvalidInput() {
		setupStage2();
		try {
			predictor.strassenMultiply(A, B);
			fail("A exception was waited");
		} catch (Exception e) {
		}
		
		setupStage1();
		try {
			predictor.strassenMultiply(A, B);
			fail("A exception was waited");
		} catch (Exception e) {			
		}
	}
}	
