import java.io.IOException;
import java.util.ArrayList;



/*Requisitos: 
 * 1) Entrada e saída em arquivo; 
 * 2) Dados de entrada: Matriz A e tolerância para parada 
 * 3) O código deve usar o teorema dos discos de Gerschgorin e definir a 
 * faixa que contém os autovalores reais e, em seguida percorrer a faixa em 
 * sendido crescente, atribuindo valores de mu e tentando achar o maior número 
 * de autovalores e seus autovalores correspondentes (Por exemplo, se a faixa 
 * for de 1 a 20, divida a faixa em 5n valores discretos (mu) e procure o 
 * autovalor próximo a dada mu. */

public class PotenciaComDeslocamento {
	public static void main(String[] args) {
		ArrayList< ArrayList<Double> > A = new ArrayList<ArrayList<Double>>();
		ArrayList< ArrayList<Double> > Amodificada = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> vk, vk1, phik, intervalo; 
		double lambidak, lambidak1, tamIntervalo;
		Retorno arquivo = null;
		Retorno ret;
		
		try {
			arquivo = ManipuladorArquivo.leitor("/Users/israelcvidal/Documents/workspace/MNII-potencia/matriz.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//Criando e inicializando a matriz A
		A = arquivo.getMatriz();
		
		//Criando os vetores
		vk = new ArrayList<Double>();
		phik = new ArrayList<Double>();
		
		// criando e inicializando autovetor inicial
		vk1 = arquivo.getVetor();
		
		//autovalor inicial
		lambidak1 = 0;
		
		
		
		System.out.println("MN II - Autovalores e Autovetores \nIsrael de Castro Vidal - 370019");
		System.out.println("Metodo da Potencia com Deslocamento");
		System.out.println("A Matriz utilizada foi:" + A);
		System.out.println("O vetor inicial foi: " + arquivo.getVetor());
		
		//pegando o intervalo onde estão os autovalores
		intervalo = DiscosDeGerschgorin.resolver(A);
		//pegando o tamanho absoluto do intervalo
		tamIntervalo = Math.abs( Math.abs(intervalo.get(1)) - Math.abs(intervalo.get(0)) );
		
		System.out.println("Intervalo = " + intervalo + "\nTamanho do intervalo = " + tamIntervalo);;
	}
	
	
}
