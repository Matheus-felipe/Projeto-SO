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
				this.WSClock();
			}

			int valor = this.HD[indice];/*Pega valor do HD*/

			int f = phMemory.setValue(valor); /*Coloca valor na memória física e retorna o indice livre*/

			/*Troca os bits*/
			this.virtualMemory.setReferenced(indice, true);
			this.virtualMemory.setPresent(indice,true);
			this.virtualMemory.setFrame(indice, f);
			this.virtualMemory.setReferencedTime(indice, this.atual_time); /*Adiciona o tempo de referenciado da página para o tempo atual*/


			return valor; /*Retorna o valor*/
		}else{
			int i = virtualMemory.getPages().get(indice).getFrame(); /*Pega a posição da moldura de página associada à página virtual*/
			int valor = phMemory.getPages().get(i); /*Guarda o valor que está nessa moldura*/
			this.virtualMemory.setReferenced(indice, true); /*Muda o bit de referenciado da página do indice passado*/
			this.virtualMemory.setReferencedTime(indice, this.atual_time);
			return valor;
		}	
	}

	public void writeMemory(int indice, int valor){

		if(virtualMemory.isPresent(indice) == false){
			System.out.println("Falta de página!!!");

			/*Verifica se memória física está cheia*/
			if(this.phMemory.isFull()){
				System.out.print("Memória cheia, chama o algoritmo");
				this.WSClock();
			}

			int f = phMemory.setValue(valor);


			/*Troca os valores da memória física*/
			this.virtualMemory.setReferenced(indice, true);
			this.virtualMemory.setPresent(indice,true);
			this.virtualMemory.setFrame(indice, f);
			this.virtualMemory.setReferencedTime(indice, this.atual_time);
			this.virtualMemory.setModified(indice, true);

		}else{
			int i = virtualMemory.getPages().get(indice).getFrame(); /*Valor da moldura de página que a memória tá associada*/
			Integer in = new Integer(valor); /*Cria um novo inteiro de acordo com o valor passado*/
			phMemory.getPages().set(i, in);/*Altera valor do array de memória física */
			this.virtualMemory.setReferenced(indice, true); /*Setar o referenciado*/
			this.virtualMemory.setModified(indice, true); /*Altera bit modificado*/
			this.virtualMemory.setReferencedTime(indice, this.atual_time);
		}
	}

	public void WSClock(){

		int tempo = 10;
		int idadeAtual = 0; /*Vai guardar tempo de referenciado da página que estiver sendo "apontada"*/
		int idadeTemp = 0; /*Guarda o tempo da página mais antiga*/
		int i = 0;
		int value = 0;
		int temp = 0;
		boolean valid = false;

		for(i = 0; i < this.virtualMemory.getPages().size();i++){
			
			/*Se a página que estiver sendo apontada no percorrimento não estiver presente ele vai para próxima*/
			if(virtualMemory.getPages().get(i).isPresent() == false){
				continue;
			}	

			/*Se ela estiver presente e tiver o bit referenciado em 1, então ela seta o tempo de referenciado da página para o tempo atual
			 * e pega a posição dessa página como sendo temp 
			 * */
			if(virtualMemory.getPages().get(i).isReferenced() == true){
				virtualMemory.getPages().get(i).setReferencedTime(this.atual_time); /*tempo do clock*/
				idadeAtual = (this.atual_time - virtualMemory.getPages().get(i).getReferencedTime());
				idadeTemp = (this.atual_time - virtualMemory.getPages().get(temp).getReferencedTime());
				if(idadeAtual >= idadeTemp){
					temp = i;
					valid = true;
					continue;
				}
			}
			
			/*Se o bit referenciado da página for 0 e sua idade for maior que o tempo definido no nosso algoritmo, então ela é a escolhida pra sair*/
			if(virtualMemory.getPages().get(i).isReferenced() == false && (this.atual_time - virtualMemory.getPages().get(i).getReferencedTime()) > tempo){
				value = phMemory.getPages().get(virtualMemory.getPages().get(i).getFrame());
				if(virtualMemory.getPages().get(i).isModified()){
					HD[i] = value;
				}
			
				phMemory.getPages().set(virtualMemory.getPages().get(i).getFrame(),null);
				this.virtualMemory.clearPage(i);
				valid = false;
				break;
			}
			
			/*Se o bit referenciado for 0 e sua idade for menor ou igual ao tempo, ela é candidata a sair*/
			if(virtualMemory.getPages().get(i).isReferenced() == false && (this.atual_time - virtualMemory.getPages().get(i).getReferencedTime()) <= tempo){

				idadeAtual = (this.atual_time - virtualMemory.getPages().get(i).getReferencedTime());
				idadeTemp = (this.atual_time - virtualMemory.getPages().get(temp).getReferencedTime());
				if(idadeAtual >= idadeTemp){

					temp = i;
					valid = true;
					continue;

				}
			}
		}

		if(valid == true){
			if(virtualMemory.getPages().get(i).isModified()){
				HD[temp] = value;
			}
			phMemory.getPages().set(virtualMemory.getPages().get(temp).getFrame(),null);
			this.virtualMemory.clearPage(temp);
		}
	}

/*Método só é chamado quando o clock bater o tempo dele e pá*/

public void receivedEvent(int tempo){
	/*Recebe o sinal do clock e zera os bits*/

	System.out.println("\n - Tick de Clock - \n");
	for(VirtualPage vp : this.virtualMemory.getPages()){
		vp.setReferenced(false);
	}
	/*Pega o tempo atual que vem do clock
	 * */
	this.atual_time = tempo;
}	
}