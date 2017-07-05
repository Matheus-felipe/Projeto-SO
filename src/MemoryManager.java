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
			System.out.println("Falta de p�gina!!!");
			
			if(this.phMemory.isFull()){
				System.out.print("Mem�ria cheia, chama o algoritmo");
				return 1;/*mudar retorno*/
			}
			
			int valor = this.HD[indice];/*Pega valor do HD*/
			
			int f = phMemory.setValue(valor); /*Coloca valor na mem�ria f�sica e retorna o indice livre*/
			
			/*Troca os bits*/
			this.virtualMemory.setReferenced(indice, true);
			this.virtualMemory.setPresent(indice,true);
			this.virtualMemory.setFrame(indice, f);
			

			return valor; /*Retorna o valor*/
		}else{
			int i = virtualMemory.getPages().get(indice).getFrame(); /*Pega a posi��o da moldura de p�gina associada � p�gina virtual*/
			int valor = phMemory.getPages().get(i); /*Guarda o valor que est� nessa moldura*/
			this.virtualMemory.setReferenced(indice, true); /*Muda o bit de referenciado da p�gina do indice passado*/
			return valor;
		}	
	}
	
	public void writeMemory(int indice, int valor){
		
		if(virtualMemory.isPresent(indice) == false){
			System.out.println("Falta de p�gina!!!");
			
			/*Verifica se mem�ria f�sica est� cheia*/
			if(this.phMemory.isFull()){
				System.out.print("Mem�ria cheia, chama o algoritmo");
				//Quando tiver, chama 
			}
			
			int f = phMemory.setValue(valor);
			
			
			/*Troca os bits*/
			this.virtualMemory.setReferenced(indice, true);
			this.virtualMemory.setPresent(indice,true);
			this.virtualMemory.setFrame(indice, f);
			
		}else{
			int i = virtualMemory.getPages().get(indice).getFrame(); /*Valor da moldura de p�gina que a mem�ria t� associada*/
			Integer in = new Integer(valor); /*Cria um novo inteiro de acordo com o valor passado*/
			phMemory.getPages().set(i, in);/*Altera valor do array de mem�ria f�sica */
			this.virtualMemory.setModified(indice, true); /*Altera bit modificado*/
		}
	}
}