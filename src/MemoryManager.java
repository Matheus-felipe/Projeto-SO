import java.util.ArrayList;

public class MemoryManager implements IClockListener {
	private VirtualMemory virtualMemory;
	private PhysicalMemory phMemory;
	private int atual_time;
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

			int f = phMemory.setValue(valor); /*Coloca valor na memória física e retorna o indice livre*/

			/*Troca os bits*/
			this.virtualMemory.setReferenced(indice, true);
			this.virtualMemory.setPresent(indice,true);
			this.virtualMemory.setFrame(indice, f);


			return valor; /*Retorna o valor*/
		}else{
			int i = virtualMemory.getPages().get(indice).getFrame(); /*Pega a posição da moldura de página associada à página virtual*/
			int valor = phMemory.getPages().get(i); /*Guarda o valor que está nessa moldura*/
			this.virtualMemory.setReferenced(indice, true); /*Muda o bit de referenciado da página do indice passado*/
			return valor;
		}	
	}

	public void writeMemory(int indice, int valor){

		if(virtualMemory.isPresent(indice) == false){
			System.out.println("Falta de página!!!");

			/*Verifica se memória física está cheia*/
			if(this.phMemory.isFull()){
				System.out.print("Memória cheia, chama o algoritmo");
				//Quando tiver, chama 
			}

			int f = phMemory.setValue(valor);


			/*Troca os bits*/
			this.virtualMemory.setReferenced(indice, true);
			this.virtualMemory.setPresent(indice,true);
			this.virtualMemory.setFrame(indice, f);

		}else{
			int i = virtualMemory.getPages().get(indice).getFrame(); /*Valor da moldura de página que a memória tá associada*/
			Integer in = new Integer(valor); /*Cria um novo inteiro de acordo com o valor passado*/
			phMemory.getPages().set(i, in);/*Altera valor do array de memória física */
			this.virtualMemory.setModified(indice, true); /*Altera bit modificado*/
		}
	}

	public void WSClock(){

		int tempo = 10;
		int i = 0;
		int value = 0;
		int temp = 0;
		boolean valid = false;
		boolean valid2 = false;
		
		for(i = 0; i < this.virtualMemory.getPages().size();i++){
			if(virtualMemory.getPages().get(i).isPresent() == false){
				continue;
			}

			if(virtualMemory.getPages().get(i).isReferenced() == true){
				virtualMemory.getPages().get(i).setReferencedTime(this.atual_time); /*tempo do clock*/
			}

			if(virtualMemory.getPages().get(i).isReferenced() == false && (this.atual_time - virtualMemory.getPages().get(i).getReferencedTime()) > tempo){
				value = phMemory.getPages().get(virtualMemory.getPages().get(i).getFrame());
				HD[i] = value;
				phMemory.getPages().set(virtualMemory.getPages().get(i).getFrame(),null);
				valid = false;
				break;
			}

			if(virtualMemory.getPages().get(i).isReferenced() == false && (this.atual_time - virtualMemory.getPages().get(i).getReferencedTime()) <= tempo){
				
				int idade_atual = (this.atual_time - virtualMemory.getPages().get(i).getReferencedTime());
				int idade_temp = (this.atual_time - virtualMemory.getPages().get(temp).getReferencedTime());
				if(idade_atual >= idade_temp){

					temp = i;
					valid = true;
					
				}
			}
		}

		if(valid == true){
			HD[temp] = value;
			phMemory.getPages().set(virtualMemory.getPages().get(temp).getFrame(),null);
		}
	}
	
	/*Método só é chamado quando o clock bater o tempo dele e pá*/
	
	public void receivedEvent(int tempo){
		/*Recebe o sinal do clock e zera os bits*/
		
		System.out.println("ROLA");
		for(VirtualPage vp : this.virtualMemory.getPages()){
			vp.setReferenced(false);
		}
		/*Pega o tempo atual que vem do clock
		 * */
		this.atual_time = tempo;
	}	
}