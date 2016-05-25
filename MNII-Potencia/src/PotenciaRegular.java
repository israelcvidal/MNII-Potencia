import java.io.IOException;
import java.util.ArrayList;

public class PotenciaRegular {
	public static void main(String[] args) {
		ArrayList< ArrayList<Double> > A = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> vk, vk1, phik; 
		double lambidak, lambidak1;
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
		System.out.println("Metodo da Potencia Regular");
		System.out.println("A Matriz utilizada foi:" + A);
		System.out.println("O vetor inicial foi: " + arquivo.getVetor());
		
		do{
			phik.removeAll(phik);
			lambidak = lambidak1;
			
			vk = vk1;
			//phik = normalizacao do vetor k
			for (int i = 0; i < vk.size(); i++) {
				phik.add(i, vk.get(i)/MatrixOperations.diseuclidiana(vk) );
			}
			
			//atualizando o vk atual
			vk1 = MatrixOperations.matvet(A, phik);
			
			//lambidak1
			lambidak1 = MatrixOperations.prodescalar(phik, vk1);
			
		}while(Math.abs( (lambidak1-lambidak)/lambidak1) > arquivo.getTolerancia());
		
		System.out.println("lambidak = " + lambidak1);
		System.out.println("phik = " + phik);
		
		
		ret = new Retorno(lambidak1, phik);
		
		try {
			ManipuladorArquivo.escritor("/Users/israelcvidal/Documents/workspace/MNII-potencia/resultado.txt", ret);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
