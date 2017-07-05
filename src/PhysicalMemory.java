import java.util.ArrayList;

/*Classe que cria e manipula o array que irá representar a memória física*/
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
		int ver = 0; /*Contador que vai verificar quais páginas estão zeradas*/
		
		int tamanhoArrayMemoriaFisica =  this.pageFrames.size();
		
		for(i = 0; i < tamanhoArrayMemoriaFisica;i++){
			if(pageFrames.get(i) != null){
				ver++;
			}
		}
		
		/*Se "ver" for igual ao tamanho da memória física siginifica que todas as posições estão ocupadas
		 * Logo, a memória física está ocupada*/
		
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