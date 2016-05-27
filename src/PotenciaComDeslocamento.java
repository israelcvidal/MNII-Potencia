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
		ArrayList< ArrayList<Double> > AModificada, I;
		ArrayList<Double> vk1, phik, intervalo; 
		double lambida, lambidaBarra, tamIntervalo, mi;
		Retorno arquivo = null;
		Retorno ret;
		
		try {
			//meu notebook
			arquivo = ManipuladorArquivo.leitor("/Users/israelcvidal/Documents/workspace/MNII-potencia/matriz.txt");
			
			//lab2
			//arquivo = ManipuladorArquivo.leitor("/home/israel/workspace/MNII-Potencia/matriz.txt");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//Criando e inicializando a matriz A
		A = arquivo.getMatriz();
		
		//Criando os vetores
		phik = new ArrayList<Double>();
		
		// criando e inicializando autovetor inicial
		vk1 = arquivo.getVetor();
		
		//Criando a matriz identidade NxN
		I = MatrixOperations.criarIdentidade(A.size());
		
		
		System.out.println("MN II - Autovalores e Autovetores \nIsrael de Castro Vidal - 370019");
		System.out.println("Metodo da Potencia com Deslocamento");
		System.out.println("A Matriz utilizada foi:" + A);
		System.out.println("O vetor inicial foi: " + arquivo.getVetor());
		
		//pegando o intervalo onde estão os autovalores
		intervalo = MatrixOperations.discosDeGerschgorin(A);
		//pegando o tamanho absoluto do intervalo
		tamIntervalo = Math.abs( Math.abs(intervalo.get(1)) - Math.abs(intervalo.get(0)) );
		
		
		//dividindo o intervalo em partes iguais e calculando o mi
		for (double i = 0; i <= tamIntervalo; i+=tamIntervalo/(5*A.size())) {
			//mi = tam/4*tam
			mi = intervalo.get(0) + i*(tamIntervalo / A.size() );
			
			//Construindo AModificada
			AModificada = MatrixOperations.subMatrizes(A, MatrixOperations.matEscalar(I, mi) );
		
			ret = PotenciaInversa.resolver(AModificada, vk1, arquivo.getTolerancia());
		
			//o autovetor da matriz inversa é o autovetor da matriz normal
			//o autovalor da inversa é o da normal + mi
			lambidaBarra = ret.getAutovalor();
			phik = ret.getAutovetor();
			
			//pegando o autovalor da matriz A
			lambida = lambidaBarra + mi;
			
			System.out.println("mi("+i+") = " + mi);
			System.out.println("lambida("+i+") ="+ lambida);
			System.out.println("phik("+i+") ="+ phik);
			System.out.println(i);
			System.out.println();
		}
			
		
	}
	
	
}
