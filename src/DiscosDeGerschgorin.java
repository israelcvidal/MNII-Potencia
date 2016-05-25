import java.util.ArrayList;

public class DiscosDeGerschgorin {
	public static ArrayList<Double> resolver( ArrayList<ArrayList<Double>> matriz){
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
	
	/*
	public static void main(String[] args) {
		ArrayList<ArrayList<Double>> mat = new ArrayList<ArrayList<Double>>();
		
		mat.add(new ArrayList<Double>());
		mat.add(new ArrayList<Double>());
		mat.add(new ArrayList<Double>());
		
		mat.get(0).add(0, (double)3);
		mat.get(0).add(1, (double)1);
		mat.get(0).add(2, (double)5);

		mat.get(1).add(0, (double)1);
		mat.get(1).add(1, (double)3);
		mat.get(1).add(2, (double)5);

		mat.get(2).add(0, (double)5);
		mat.get(2).add(1, (double)5);
		mat.get(2).add(2, (double)-1);

		System.out.println(DiscosDeGerschgorin(mat));	
	}
*/
}
