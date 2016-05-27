import java.util.ArrayList;

public class Teste {

	public static void main(String[] args) {
		ArrayList<ArrayList<Double>> A, B, AModificada;
		A = new ArrayList<ArrayList<Double>>();
		B = new ArrayList<ArrayList<Double>>();
		
		A.add( new ArrayList<Double>());
		A.add( new ArrayList<Double>());
		A.add( new ArrayList<Double>());
		
		B.add( new ArrayList<Double>());
		B.add( new ArrayList<Double>());
		B.add( new ArrayList<Double>());

		
		A.get(0).add(0,(double)5);
		A.get(0).add(1,(double)7);
		A.get(0).add(2,(double)-1);
	
		A.get(1).add(0,(double)6);
		A.get(1).add(1,(double)0);
		A.get(1).add(2,(double)-3);

		A.get(2).add(0,(double)-4);
		A.get(2).add(1,(double)3);
		A.get(2).add(2,(double)0);


		
		B.get(0).add(0,(double)0);
		B.get(0).add(1,(double)3);
		B.get(0).add(2,(double)-5);
		
		B.get(1).add(0,(double)2);
		B.get(1).add(1,(double)0);
		B.get(1).add(2,(double)0);

		B.get(2).add(0,(double)-1);
		B.get(2).add(1,(double)-5);
		B.get(2).add(2,(double)3);
		
		
		System.out.println( A);
				
		ArrayList<ArrayList<Double>> I = MatrixOperations.criarIdentidade(3);
		ArrayList<ArrayList<Double>> aux;
		
		double mi = 10;
		System.out.println("I = " + I);
		aux = MatrixOperations.matEscalar(I, mi);
		System.out.println("I = " + I);

		System.out.println("aux = " + aux);
		AModificada = MatrixOperations.subMatrizes(A, aux );
		
		System.out.println("AModificada = " + AModificada);
		
	}
}
