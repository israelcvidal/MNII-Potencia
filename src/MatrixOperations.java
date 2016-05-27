import java.util.ArrayList;

public class MatrixOperations {
	
	
	
	//multiplicando uma matri por um escalar
	public static ArrayList<ArrayList<Double>> matEscalar(ArrayList<ArrayList<Double>> A ,  double b ){
		double aux;
		ArrayList<ArrayList<Double>> resultado = new ArrayList<ArrayList<Double>>();
		
		//dimensionando a matriz resultado
		for (int i = 0; i < A.size(); i++) {
			resultado.add(new ArrayList<Double>());	
		}
		
		
		for (int i = 0; i < A.size(); i++) {
			for (int j = 0; j < A.get(0).size(); j++) {
				aux = A.get(i).get(j);
				resultado.get(i).add(j, aux * b);
			}
		}
		
		return resultado;
	}
	
	
	
	
	
	
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


	//retorno o intervalo gerado de acordo com os discos de gerschgorin.
	public static ArrayList<Double> discosDeGerschgorin( ArrayList<ArrayList<Double>> matriz){
		double n = matriz.size(), menor, maior, diagonal = 0, soma = 0;
		double menortemp = 0, maiortemp = 0;
		ArrayList<Double> resposta = new ArrayList<Double>();
		
		//pegando o primeiro valor para menor e maior
		diagonal = matriz.get(0).get(0);
		for (int i = 1; i < n; i++) {
			soma += matriz.get(0).get(i);  
		}
		menortemp = menor = diagonal - soma;
		maiortemp = maior = diagonal + soma;
		
		//calculando a partir da segunda linha
		for (int i = 1; i < n; i++) {
			soma = 0;
			for (int j = 0; j < n; j++) {
				if(i == j ){
					diagonal = matriz.get(i).get(j); 
				}else{
					soma += Math.abs(matriz.get(i).get(j)); 	
				}
			}
			//calculando o menor e o maior da linha corrente
			menortemp = diagonal - soma;
			maiortemp = diagonal + soma;
			
			//comparando para pegar o menor e maior global. 
			if(menortemp < menor){
				menor = menortemp;
			}
			
			if(maiortemp > maior){
				maior = maiortemp;
			}
		}
		
		//retornando o intervalo.
		resposta.add(0, menor);
		resposta.add(1, maior);
	
		return resposta;
	}

	
	//Subtração de uma matriz por um escalar
	public static ArrayList<ArrayList<Double>> subMatrizEscalar(ArrayList<ArrayList<Double>> A, double b){
		double aux;
		
		for (int i = 0; i < A.size(); i++) {
			for (int j = 0; j < A.get(0).size(); j++) {
				aux = A.get(i).get(j);
				A.get(i).remove(j);
				A.get(i).add(j, aux - b);
			}
		}
		
		return A;
	}
	
	
	//criar a matriz identidade
	public static ArrayList<ArrayList<Double>> criarIdentidade(double n){
		ArrayList<ArrayList<Double>> I = new ArrayList<ArrayList<Double>>(); 

		
		//dimensionando a matriz identidade
		for (int i = 0; i < n; i++) {
			I.add(new ArrayList<Double>());
		}
	
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(i==j){
					I.get(i).add(j, (double)1);
				}else{
					I.get(i).add(j, (double)0);
				}
			}
		}
				
		return I;
	}
	
	
	//Multiplicar duas matrizes
	public static ArrayList<ArrayList<Double>> multiplicarMatrizes(ArrayList<ArrayList<Double>> A, ArrayList<ArrayList<Double>> B){
		ArrayList<ArrayList<Double>> matResultado = new ArrayList<ArrayList<Double>>();
		double sum = 0;
		
		//so posso multiplicar duas matrizes se o numero de colunas da primeira for igual ao numero de linhas da segunda
		if(A.get(0).size() != B.size()){
			System.out.println("Nao eh possivel multiplicar as matrizes");
			return null;
		}
		
		//dimensionando a matriz resultado
		for (int i = 0; i < A.size(); i++) {
			matResultado.add(new ArrayList<Double>());
		}
		
		
		for (int i = 0; i < A.size(); i++) {
			for (int j = 0; j < B.get(0).size() ; j++) {
				sum = 0;
				for (int j2 = 0; j2 < A.get(0).size() ; j2++) {
					sum += A.get(i).get(j2)*B.get(j2).get(j);
				}
				matResultado.get(i).add(j, sum);
			}
		}
		
		return matResultado;
		
	}

	//Subtrair duas matrizes
	public static ArrayList<ArrayList<Double>> subMatrizes(ArrayList<ArrayList<Double>> A, ArrayList<ArrayList<Double>> B){
		ArrayList<ArrayList<Double>> matResultado = new ArrayList<ArrayList<Double>>();
		
		//so posso multiplicar duas matrizes se o numero de colunas da primeira for igual ao numero de linhas da segunda
		if(A.size() != B.size() || A.get(0).size() != B.get(0).size()){
			System.out.println("Nao eh possivel subtrair as matrizes");
			return null;
		}
		
		//dimensionando a matriz resultado
		for (int i = 0; i < A.size(); i++) {
			matResultado.add(new ArrayList<Double>());
		}
		
		
		for (int i = 0; i < A.size(); i++) {
			for (int j = 0; j < B.get(0).size() ; j++) {
				matResultado.get(i).add(j, A.get(i).get(j) - B.get(i).get(j));
			}
		}
		
		return matResultado;
		
	}


}
