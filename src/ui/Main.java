package ui;

import model.DivideAndConquer;
import model.Strassen;

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
	   long d1 = System.currentTimeMillis();				     
	   int[][] C = DivideAndConquer.multiply(A, B);
	   System.out.println((System.currentTimeMillis()-d1) + "DIVIDE");
	   for (int i = 0; i < C.length; i++) {
		   for (int j = 0; j < C[i].length; j++) {
			   System.out.print(C[i][j] + " ");
		   }
		   System.out.println();
	   }
	   System.out.println("****************************************");
	   d1 = System.currentTimeMillis();
	   C = Strassen.multiply(A, B);
	   System.out.println((System.currentTimeMillis()-d1)+"Strassen");
	   
	   for (int i = 0; i < C.length; i++) {
		   for (int j = 0; j < C[i].length; j++) {
			   System.out.print(C[i][j] + " ");
		   }
		   System.out.println();
	   }
	}

}
