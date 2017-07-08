import java.util.ArrayList;

public class Main {
	public static final int CLOCK_TIME = 1000;
	
	public static void main(String[] args) {
		
		VirtualMemory vm = new VirtualMemory(8);
		PhysicalMemory ph = new PhysicalMemory(4); 
		int[] hd = {1,2,3,4,5,6,7,8}; 
		Clock c = new Clock(CLOCK_TIME);
		
		
		ArrayList <Integer> teste;
		
		MemoryManager mm = new MemoryManager(vm, ph, hd);
		c.addListener(mm);
		
		Process p = new Process("0-R,4-R,7-R,3-R,1-R", mm);
		
		Thread clockThread = new Thread(c);
		clockThread.start();
		

		Thread pro = new Thread(p);
		pro.start();
		
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
		
		/*
		[0-0, null, null, 3-3, 4-1,null, null, 7-2]
		[null, 1-0, null, 3-3, 4-1,null, null, 7-2]
		
		[1,5,8,4]
		[2,5,8,4]
		*/
	}
}
