import java.util.ArrayList;

public class PhysicalMemory {
	ArrayList <PageFrames> frames;

	public PhysicalMemory(int tamanho){
		this.frames = new ArrayList<PageFrames>() ;

		for (int i = 0; i < tamanho; i++){
			PageFrames pf = new PageFrames();
			this.frames.add(pf);
		}
	}

	public void set
	}
}
