import java.util.ArrayList;

public class MatrixOperations {
	
	//multiplicando uma matriz por um vetor
	public static ArrayList<Double> matvet (ArrayList<ArrayList<Double>> mat, ArrayList<Double> vet ){
		ArrayList<Double> c = new ArrayList<Double>();
		double vatual;
				
		for (int i = 0; i < mat.size(); i++) {
			vatual = 0;
			for (int j = 0; j < mat.get(0).size(); j++) {
				vatual += (mat.get(i).get(j)*vet.get(j));
			}
			c.add(i, vatual);
		}
		
		return c;
	}
	
	 //distancia euclidiana
	static double diseuclidiana(ArrayList<Double> vet){
		double result =0;
		for (int i = 0; i < vet.size(); ++i)
		{
			result += Math.pow(vet.get(i),2);
		}

		return Math.sqrt(result);
	}
	
	
	//produto escalar
	static double prodescalar(ArrayList<Double> v1, ArrayList<Double> v2 ){
		double result = 0;
		for (int i = 0; i < v1.size(); ++i)
		{
			result += v1.get(i)*v2.get(i);
		}

		return result;
	}
	//decomposicao lu
	static ArrayList<Double> lu(ArrayList<ArrayList<Double>> A, ArrayList<Double> b){
		ArrayList<ArrayList<Double>> L, U;
		ArrayList<Double> y, x;
		
		L = new ArrayList<ArrayList<Double>>();
		U = new ArrayList<ArrayList<Double>>();
		y = new ArrayList<Double>(); //vetor solucao para L
		x = new ArrayList<Double>(); //vetor solucao para U
		
		double sum = 0;
		
		//dimensionando as matrizes
		for (int i = 0; i < A.size() ; i++) {
			L.add(new ArrayList<Double>());
			U.add(new ArrayList<Double>());
		}
		
		for (int i = 0; i < b.size(); i++) {
			x.add((double)0);
		}
		
		//zerando as matrizes
		for (int i = 0; i < A.size(); i++) {
			for (int j = 0; j < A.size(); j++) {
				U.get(i).add((double)0);
				if(i == j){
					L.get(i).add((double)1);
				}else L.get(i).add((double)0);
			}
		}
		
		for (int i = 0; i < A.size(); i++) {

			for (int j = 0; j < i; j++) {
				sum = 0;
				//Somatorio de LijUjk
				for (int k = 0; k < j; k++) {
					sum += L.get(i).get(k) * U.get(k).get(j);	
				}
				//Lij = ( Aij - SUM (Lik Ukj) )/Ujj
				if(L.get(i).size() >= j)
					L.get(i).remove(j);
				L.get(i).add(j, (A.get(i).get(j) - sum)/U.get(j).get(j));
			}
			
			for (int j1 = i; j1 < A.size(); j1++) {
				sum = 0;
				//Somatorio de LikUkj
				for (int k = 0; k < i; k++) {
					sum += L.get(i).get(k) * U.get(k).get(j1);
				}	
				//Uij = ( Aij - SUM (Lik Ukj) )
				if(U.get(i).size() >= j1){
					U.get(i).remove(j1);
					U.get(i).add(j1, (A.get(i).get(j1) - sum));
				} else 	U.get(i).add((A.get(i).get(j1) - sum));

				
			} 
			
		} 		//fim for externo
		

		//solucao do sistema Ly = b
		for (int i = 0; i < b.size(); i++) {
			sum = 0;
			for (int k = 0; k < i; k++) {
				sum += L.get(i).get(k)*y.get(k);
			}
			y.add(b.get(i) - sum);
		}
		
		//Solucao do sistema Ux = y
		
		for (int i = (b.size() -1); i >= 0; i--) {
			sum = 0;
			for (int k = i+1; k < b.size(); k++) {
				sum += U.get(i).get(k)*x.get(k);
			}
			x.remove(i);
			x.add(i, (y.get(i) - sum)/U.get(i).get(i));
		}
		
		return x;
		
	}			//fim metodo
}
