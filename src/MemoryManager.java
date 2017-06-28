import java.util.ArrayList;

public class MemoryManager {
	private VirtualMemory virtualMemory;
	private PageFrames [] pageFrames;

	public MemoryManager(VirtualMemory vp, PageFrames[] pf){
		this.virtualMemory = vp;
		this.pageFrames = pf;
	}

	public int readMemory(int indice){

		int i = 0;
	
		if(virtualMemory.isPresent(indice) == false){
			System.out.println("Fata de página!!!");
			this.virtualMemory.setReferenced(indice, true);
			this.virtualMemory.setPresent(indice,true);
			
			
			if(memoriaFisica.isFull()){
				System.out.print("Memória cheia, chama o algoritmo");
			}
			
			for(i = 0; i < pageFrames.length;i++){
				if(pageFrames[i] == null){
					vp.setMoldura(i);
					PageFrames pf = new PageFrames();
					pageFrames[i] = pf;
					return pageFrames[i].getValor();
				}	
			}

			return 0; /*Mudar depois*/
		}else{
			int mapIndice = virtualPages[indice].getMoldura();
			return pageFrames[mapIndice].getValor();
			
		}
	}
}