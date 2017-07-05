import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		
		VirtualMemory vm = new VirtualMemory(16);
		PhysicalMemory ph = new PhysicalMemory(8); 
		int[] hd = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16}; 
		
		ArrayList <Integer> teste;
		
		MemoryManager mm = new MemoryManager(vm, ph, hd); 
		
		mm.writeMemory(4, 200);
		mm.writeMemory(2,300);
		mm.writeMemory(1,400);
		mm.writeMemory(0,500);
		mm.writeMemory(3,600);
		mm.writeMemory(5,0);
		System.out.println(mm.readMemory(4));
		mm.writeMemory(6,2);
		mm.writeMemory(7,4);
		System.out.println(ph.getPages());
		mm.writeMemory(7, 5);
		
		System.out.println(ph.getPages());
		
	}
}
