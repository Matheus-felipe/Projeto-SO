import java.util.ArrayList;

public class VirtualMemory {
	
	private ArrayList <VirtualPage> virtualPages;
	
	public VirtualMemory(int tamanho){
		this.virtualPages = new ArrayList<VirtualPage>() ;
		
		for (int i = 0; i < tamanho; i++){
			VirtualPage vp = new VirtualPage();
			this.virtualPages.add(vp);
		}
	}
	
	public boolean isPresent(int indice){
		if(this.virtualPages.get(indice).isPresent()){
			return true;
		}else{
			return false;
		}
	}
	
	public void setReferenced(int indice, boolean pReferenced) {
		this.virtualPages.get(indice).setReferenced(pReferenced);
	}
	
	public void setPresent(int indice, boolean pPresent) {
		this.virtualPages.get(indice).setReferenced(pPresent);
	}
}