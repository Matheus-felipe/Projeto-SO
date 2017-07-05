import java.util.ArrayList;

/*Classe que cria e manipula o array que ir� representar a mem�ria f�sica*/
public class PhysicalMemory {
	
	/*Array de valores inteiros*/
	ArrayList <Integer> pageFrames;
	
	/*Contrutor vai criar array de acordo com o tamanho passado*/
	public PhysicalMemory(int tamanho){
		this.pageFrames = new ArrayList<Integer>() ;
		
		/*Inicializa o vetor com os valores zerados*/
		for (int i = 0; i < tamanho; i++){
			Integer in = null;
			this.pageFrames.add(in); 
		}
	}
	
	public boolean isFull(){
		int i;
		int ver = 0; /*Contador que vai verificar quais p�ginas est�o zeradas*/
		
		int tamanhoArrayMemoriaFisica =  this.pageFrames.size();
		
		for(i = 0; i < tamanhoArrayMemoriaFisica;i++){
			if(pageFrames.get(i) != null){
				ver++;
			}
		}
		
		/*Se "ver" for igual ao tamanho da mem�ria f�sica siginifica que todas as posi��es est�o ocupadas
		 * Logo, a mem�ria f�sica est� ocupada*/
		
		if(ver == tamanhoArrayMemoriaFisica){
			return true;
		}else{
			return false;
		}		
	}
	
	public int setValue(int valor){
		int i ;
		
		for(i = 0; i < this.pageFrames.size(); i++){
			if (this.pageFrames.get(i) == null){
				Integer in = new Integer(valor);
				this.pageFrames.set(i, in);
				break;
			}
		}
		
		return i;
	}
	
	public ArrayList <Integer> getPages(){
		return this.pageFrames;
	}
}