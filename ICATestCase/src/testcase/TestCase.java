package testcase;

import java.io.PrintWriter;
import java.util.Random;

import org.JMathStudio.DataStructure.Vector.*;
import org.JMathStudio.DataStructure.Iterator.Iterator1D.VectorIterator;
import org.JMathStudio.SignalToolkit.Utilities.SignalGenerator;
import org.fastica.FastICA;
import org.fastica.math.Matrix;


public class TestCase {
	private Vector s1;  // input signal1
	private Vector s2;  // input signal 2
	
	double[][] matrix_S;  //matrix input signal
	double[][] matrix_A;  //matrix for mixing array
	double[][] matrix_X;  //matrix for mixed sources
	double[][] matrix_S_;  //matrix for output of ICA ICVectors
	double[][] matrix_A_;  //matrix for output of ICA mixing array
	double[][] matrix_X_;  //matrix for output of ICA separating matrix
	
	// Strings for printing the matrixes to the file
	String result_S;
	String result_A;
	String result_X;
	String result_S_;
	String result_A_;
	String result_X_;
	
	
	public void setUpSource() throws Exception {
//		Generating signal inputs
		int sampleNumber = 10000;
	    
		Random rand = new Random();
//	    float randNum = rand.nextFloat() / 2;

	    Vector sinc = SignalGenerator.sine( 0.11f, sampleNumber, 0);
	    Vector squarePulses = SignalGenerator.squarePulses(0.2f, sampleNumber);

	    Vector v1 = VectorMath.multiply(1,sinc);
	    Vector v2 = VectorMath.multiply(2,squarePulses);
	    s1 = (VectorMath.add(v1,v2));

	    Vector v3 = VectorMath.multiply(3,sinc);
	    Vector v4 = VectorMath.multiply(4,squarePulses);
	    s2 = (VectorMath.add(v3, v4));

//	    casting s1 and s2 into Cell to generate the source
	    matrix_S = new double[2][v1.length()];
	    
	    VectorIterator i1 = new VectorIterator(s1);
	    VectorIterator i2 = new VectorIterator(s2);
	    
	    for (int i =0; i < s1.length(); i ++) {
	    	matrix_S[0][i] = (double)i1.getAndNext();
	    	matrix_S[1][i] = (double)i2.getAndNext();
	    }
	    
	    System.out.println(matrix_S.length + " " + matrix_S[0].length + " " + matrix_S[1].length);
	}

	
	public void setUpMixingMatrix() throws Exception {
//		matrix_A = new double[2][3];
		matrix_A = new double[2][2];
		matrix_A[0][0] = 1d;
		matrix_A[0][1] = 1d;
//		matrix_A[0][2] = 1d;
		matrix_A[1][0] = 1d;
		matrix_A[1][1] = 2d;
//		matrix_A[1][2] = 1d;
//		 Transpose mixingArr;
		matrix_A = Matrix.transpose(matrix_A); 
		 
		System.out.println(matrix_A.length + " " + matrix_A[0].length + " " + matrix_A[1].length);
	}
	
	
	public void testFastICA() throws Exception{
//		mixing S and A
		double[][] matrix_X = Matrix.mult(matrix_A, matrix_S);
		
//		Applying FastICA library
		FastICA ica = new FastICA(matrix_X, 2);
		
		matrix_S_ = ica.getICVectors();
		matrix_A_ = ica.getMixingMatrix();
		matrix_X_ = ica.getSeparatingMatrix();	
		
//		Output results
		result_S = Matrix.toString(matrix_S);
		result_A = Matrix.toString(matrix_A);
		result_X = Matrix.toString(matrix_X);
		result_S_ = Matrix.toString(matrix_S_);
		result_A_ = Matrix.toString(matrix_A_);
		result_X_ = Matrix.toString(matrix_X_);
		
		PrintWriter writer1 = new PrintWriter("sources.txt", "UTF-8");
		writer1.println("Input source:");
		writer1.println();
		writer1.println(result_S);
		writer1.println();
		writer1.println();
		writer1.println();
		writer1.println("Output ICVectors:");
		writer1.println();
		writer1.print(result_S_);
		writer1.close();
		
		PrintWriter writer2 = new PrintWriter("Mixing_Matrix.txt", "UTF-8");
		writer2.println("Input mixing matrix:");
		writer2.println();
		writer2.println(result_A);
		writer2.println();
		writer2.println();
		writer2.println();
		writer2.println("Output mixing matrix:");
		writer2.println();
		writer2.println(result_A_);
		writer2.println();
		writer2.println();
		writer2.println();
		writer2.println("Output Seperating matrix:");
		writer2.println();
		writer2.println(result_X_);
		writer2.close();
		
		PrintWriter writer3 =  new PrintWriter("Mixtures.txt", "UTF-8");
		writer3.println("Mixed input:");
		writer3.println();
		writer3.println(result_X);
		writer3.close();
		System.out.println(result_A);
		System.out.println(result_A_);		
		
		System.out.println("Test done.");
	}
}
