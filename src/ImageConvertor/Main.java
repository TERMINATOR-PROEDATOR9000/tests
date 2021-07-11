package ImageConvertor;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;



public class Main {
    public static void main(String... args) throws Exception {
	try {
	    //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    UIManager.setLookAndFeel(new FlatDarkLaf());	    
	} catch (Exception e) {
	}		
	new View();
	//String gs=new BufferedReader(new InputStreamReader(System.in)).readLine();
	//byte[][]arr=new byte[1000*1000][1000*1000];
	
	/**SQUARE MATRIX:
	 * int size=10;
	int [][]matrix=new int[size][];
	for (int i = 0; i < matrix.length; i++) {
	    matrix[i]=new int[size--];
	}
	for (int i = 0; i < matrix.length; i++) {
	    for (int j = 0; j < matrix[i].length; j++) {
		System.out.print(matrix[i][j]);
	    }
	    System.out.println();
	}
	 */
	
    }
}


class Abcd{
    void init(){
	int n=7;
	int matrix[][]=new int[n][n];	
	for (int i = 1; i < matrix.length+1; i++) {
	    for (int j = 1; j < matrix.length+1; j++) {
		matrix[i-1][j-1]=1+((i+j-1+(n-1)/2)%n)*n+((i+2*j+2)%n);
		System.out.print(matrix[i-1][j-1]+" ");
	    }
	    System.out.println();
	}
	int sum1=0;
	for(int i=0;i<matrix.length;i++) {
	    sum1+=matrix[0][i];
	}
	int sum2=0;
	for (int i = 0; i < matrix.length; i++) {	    
		sum2+=matrix[i][i];	    
	}
	System.out.println();
	System.out.println(sum1+" "+sum2);
    }
}


