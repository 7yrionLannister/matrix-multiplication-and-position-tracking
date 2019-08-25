package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class DivideAndConquerTest {
	private Predictor predictor; 
	
	public void setupStage1() {
		predictor = new Predictor();
		int [][] A = new int[3][3];
		int [][] B = new int[3][3];
		A[0][0] = -18;     B[0][0] = 4;
		A[0][1] = 8;	   B[0][1] = 5;
		A[0][2] = 11;	   B[0][2] = 11;
		A[1][0] = 15;      B[1][0] = 31;
		A[1][1] = 3;       B[1][1] = -13;
		A[1][2] = 2;       B[1][2] = 2;
		A[2][0] = 7;       B[2][0] = 73;
		A[2][1] = 4;       B[2][1] = 4;
		A[2][2] = 13;      B[2][1] = 1;
	}
	public void setupStage2() {
		predictor = new Predictor();
		int [][] A = new int[4][3];
		int [][] B = new int[4][3];
		A[0][0] = 4;       B[0][0] = 8;
		A[0][1] = 9;	   B[0][1] = 4;
		A[0][2] = 3;	   B[0][2] = 1;
		A[1][0] = 2;       B[1][0] = 11;
		A[1][1] = 20;      B[1][1] = 9;
		A[1][2] = 4;       B[1][2] = 3;
		A[2][0] = 1;       B[2][0] = 13;
		A[2][1] = 2;       B[2][1] = 5;
		A[2][2] = -5;      B[2][2] = 2;
		A[3][0] = 0;       B[3][0] = 0;
		A[3][1] = 8;       B[3][1] = 7;
		A[3][2] = 3;       B[3][2] = 0;
	}
	public void setupStage3() {
		predictor = new Predictor();
		int [][] A = new int[4][4];
		int [][] B = new int[4][4];
		A[0][0] = 16;      B[0][0] = 5;
		A[0][1] = -6;	   B[0][1] = 5;
		A[0][2] = 2;	   B[0][2] = 13;
		A[0][3] = 0;       B[0][3] = 0;
		A[1][0] = 9;       B[1][0] = 3;
		A[1][1] = 0;       B[1][1] = 6;
		A[1][2] = 3;       B[1][2] = 8;
		A[1][3] = 58;      B[1][3] = 8;
		A[2][0] = 7;       B[2][0] = 7;
		A[2][1] = 8;       B[2][1] = 4;
		A[2][2] = 5;       B[2][2] = 2;
		A[2][3] = 1;       B[2][3] = 0;
		A[3][0] = 6;       B[3][0] = 9;
		A[3][1] = 11;      B[3][1] = 6;
		A[3][2] = 4;       B[3][2] = 6;
		A[3][3] = 6;       B[3][3] = 1;
	}
	@Test
	void randomMatrix() {
		setupStage1();
		int [][] C = predictor.randomMatrix(100, 100, false);
		int totalElements = 0;
		int repeatedElements = 0;
		for(int i = 0; i < C.length; i++) 
			for(int j = 0; j< C[i].length; j++) {
				ArrayList<Integer> repeated = new ArrayList<>();
				if(!repeated.contains(C[i][j])) {
					repeated.add(C[i][j]);
				} else {
					repeatedElements++;
				}
				System.out.println(C[i][j]);
				totalElements++;
			}
		System.out.println(repeatedElements);
		assertTrue(totalElements == 10000 && repeatedElements == 0, "A matrix 3x3 was created with repeated values");
		}
	@Test
	void standardMultiply1() {
		
	}

}
