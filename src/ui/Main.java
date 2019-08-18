package ui;

import model.DivideAndConquer;

public class Main {

	public static void main(String[] args) {
		int[][] A = new int[][] {{1,2,3,4,5,6,7,8},
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
	}

}
