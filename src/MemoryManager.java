import java.util.ArrayList;

public class MemoryManager {
	private VirtualMemory virtualMemory;
	private PhysicalMemory phMemory;
	private int[] HD; 
	
	public MemoryManager(VirtualMemory vp, PhysicalMemory pPH, int[] pHd){
		this.virtualMemory = vp;
		this.phMemory = pPH;
		this.HD = pHd;
	}

	public int readMemory(int indice){

		if(virtualMemory.isPresent(indice) == false){
			System.out.println("Falta de página!!!");
			
			if(this.phMemory.isFull()){
				System.out.print("Memória cheia, chama o algoritmo");
				return 1;/*mudar retorno*/
			}
			
			int valor = this.HD[indice];/*Pega valor do HD*/
			
			int f = phMemory.setValue(valor);
			
			/*Troca os bits*/
			this.virtualMemory.setReferenced(indice, true);
			this.virtualMemory.setPresent(indice,true);
			this.virtualMemory.setFrame(indice, f);
			

			return valor; /*Retorna o valor*/
		}else{
			int valor = phMemory.getPages().get(indice);
			return valor;
		}	
	}
}