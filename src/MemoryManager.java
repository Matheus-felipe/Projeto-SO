import java.util.ArrayList;

public class MemoryManager {
	private VirtualMemory virtualMemory;
	private PhysicalMemory phMemory;

	public MemoryManager(VirtualMemory vp, PhysicalMemory pPH){
		this.virtualMemory = vp;
		this.phMemory = pPH;
	}

	public int readMemory(int indice){

		int i = 0;

		if(virtualMemory.isPresent(indice) == false){
			System.out.println("Fata de página!!!");
			this.virtualMemory.setReferenced(indice, true);
			this.virtualMemory.setPresent(indice,true);


			if(this.phMemory.isFull()){
				System.out.print("Memória cheia, chama o algoritmo");
				return 1;/*mudar retorno*/
			}



			return 0; /*Mudar depois*/
		}
		return 2; /*Mudar o retorno quando a gente já tiver mapeado*/
	}
}