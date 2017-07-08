import java.io.File;

public class SO {
	public static final int CLOCK_TIME = 1000;
	public static final int LOG_TIME = 10000;
	public static final int TAMANHO_MEMORIA_VIRTUAL = 16;
	public static final int TAMANHO_MEMORIA_FISICA = 8;
	public static final int TEMPO_DO_PROCESSO = 1000;
	
	public static void main(String[] args) {
		
		VirtualMemory vm = new VirtualMemory(TAMANHO_MEMORIA_VIRTUAL);
		PhysicalMemory ph = new PhysicalMemory(TAMANHO_MEMORIA_FISICA); 
		File hd = new File("C:/Users/matheus/Desktop/HD.txt");
		
		//int[] hd = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16}; 
		Clock c = new Clock(CLOCK_TIME);
		Log log = new Log(vm, ph, hd, LOG_TIME);
		
		MemoryManager mm = new MemoryManager(vm, ph, hd);
		c.addListener(mm);
		
		Process p = new Process(new Entradas(TAMANHO_MEMORIA_VIRTUAL).getNewEntrada(), mm, 1, TEMPO_DO_PROCESSO);
		Process p2 = new Process(new Entradas(TAMANHO_MEMORIA_VIRTUAL).getNewEntrada(), mm, 2, TEMPO_DO_PROCESSO);
		
		
		Thread clockThread = new Thread(c);
		clockThread.start();
		

		Thread tLog = new Thread(log);
		tLog.start();

		Thread processThread = new Thread(p);
		processThread.start();
		
		Thread processThread2 = new Thread(p2);
		processThread2.start();
		
		/*
		mm.readMemory(0);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mm.readMemory(4);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mm.readMemory(7);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mm.readMemory(3);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ph.getPages());
		
		mm.readMemory(1); /*Mem cheia*/
		/*
		for(int i = 0; i < vm.getPages().size(); i++){
			System.out.print(vm.getPages().get(i).getFrame() + " ");
			System.out.print(vm.getPages().get(i).isReferenced() + " ");
			System.out.print(vm.getPages().get(i).isPresent() + " ");
		}
		
		System.out.print("\n");
		
		for(int i = 0; i < vm.getPages().size(); i++){
			System.out.print(hd[i] + " ");
		}
		
		System.out.println(ph.getPages());
		*/
		/*
		[0-0, null, null, 3-3, 4-1,null, null, 7-2]
		[null, 1-0, null, 3-3, 4-1,null, null, 7-2]
		
		[1,5,8,4]
		[2,5,8,4]
		*/
	}
}
