import java.io.IOException;
import java.util.Random;

public class Process implements Runnable{
	private int id;
	private int processTime;
	private String operations;
	private MemoryManager mm;


	public Process(String pOperations, MemoryManager pMM, int pID, int pProcessTime){
		this.operations = pOperations;
		this.mm = pMM;
		this.id = pID;
		this.processTime = pProcessTime;
	}
	public void run(){
			String[] op = operations.split(",");
			Random gerador = new Random();
			for(int i = 0; i < op.length; i++){
				if(op[i].charAt(2) == 'R'){
					System.out.println("Processo " + this.id + " leu do endereço " + (Character.getNumericValue(op[i].charAt(0))));
					synchronized (this.mm) {
						try {
							mm.readMemory((Character.getNumericValue(op[i].charAt(0))));
						} catch (IOException e) {
							e.printStackTrace();
						} 
					}
				}

				if(op[i].charAt(2) == 'W'){
					int valor = gerador.nextInt(10);
					System.out.println("Processo "+ this.id 
							+ " escreveu "
							+ valor
							+ " no endereço " 
							+ (Character.getNumericValue(op[i].charAt(0))));
					synchronized (this.mm) {
						try {
							mm.writeMemory((Character.getNumericValue(op[i].charAt(0))), valor);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
				}

				try {
					Thread.sleep(this.processTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		

	}

}

