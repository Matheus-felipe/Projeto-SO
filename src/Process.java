import java.util.Random;

public class Process implements Runnable{
	private String operations;
	private MemoryManager mm;


	public Process(String pOperations, MemoryManager pMM){
		this.operations = pOperations;
		this.mm = pMM;
	}
	public void run(){
		String[] op = operations.split(",");
		Random gerador = new Random();
			for(int i = 0; i < op.length; i++){
				if(op[i].charAt(2) == 'R'){
					mm.readMemory((Character.getNumericValue(op[i].charAt(0)))); 
				}

				if(op[i].charAt(2) == 'W'){
					mm.writeMemory((Character.getNumericValue(op[i].charAt(0))), gerador.nextInt(10));
				}

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
