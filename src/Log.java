
public class Log implements Runnable {
	private VirtualMemory vm;
	private PhysicalMemory ph;
	private int[] hd;
	private int tempo;
	
	public Log(VirtualMemory pVM, PhysicalMemory pPH, int[] pHD, int pTempo){
		this.vm = pVM;
		this.ph = pPH;
		this.hd = pHD;
		this.tempo = pTempo;
	}
	
	public void run(){
		while(true){
			/*Log espera o tempo passado para imprimir os arrays na tela*/
			try {
				Thread.sleep(this.tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			/*Imprime memória física*/
			System.out.println(ph.getPages());
			/*Imprime HD*/
			System.out.print("HD: ");
			for(int i = 0; i < hd.length; i++){
				System.out.print(hd[i] + ",");
			}
			
			/*Imprime Memória Virtual e seus atributos*/
			for(int i = 0; i < this.vm.getPages().size(); i++){
				System.out.print("\n"+ "Pos: " + i +" [" + "F: " + vm.getPages().get(i).getFrame() + "," 
								     + "RT: " + vm.getPages().get(i).getReferencedTime() + "," 
								     + "M: " + vm.getPages().get(i).isModified() + ","
								     + "R: " + vm.getPages().get(i).isReferenced() + ","
								     + "P: " + vm.getPages().get(i).isPresent()
								     + "]" + "\n");
			}
		}
	}
	
	
}
