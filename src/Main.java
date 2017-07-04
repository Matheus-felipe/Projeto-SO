import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		VirtualMemory vm = new VirtualMemory(16);
		PhysicalMemory ph = new PhysicalMemory(8); 
		int[] hd = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16}; 
		
		ArrayList <Integer> teste;
		
		MemoryManager mm = new MemoryManager(vm, ph, hd); 
		
		System.out.println(mm.readMemory(0));
		System.out.println(mm.readMemory(1));
		System.out.println(mm.readMemory(2));
		System.out.println(mm.readMemory(3));
		System.out.println(mm.readMemory(4));
		System.out.println(mm.readMemory(5));
		System.out.println(mm.readMemory(6));
		System.out.println(mm.readMemory(7));
		System.out.println(mm.readMemory(7));
		
		System.out.println(ph.getPages());
		
	}
}
