import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Log implements Runnable {
	private VirtualMemory vm;
	private PhysicalMemory ph;
	private File HD;
	private int tempo;

	public Log(VirtualMemory pVM, PhysicalMemory pPH, File pHD, int pTempo){
		this.vm = pVM;
		this.ph = pPH;
		this.HD = pHD;
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
			InputStream is;
			try {
				is = new FileInputStream(HD.getAbsolutePath());
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String s;
				try {
					s = br.readLine();
					System.out.println(s);
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}


			} catch (FileNotFoundException e) {
				e.printStackTrace();
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
