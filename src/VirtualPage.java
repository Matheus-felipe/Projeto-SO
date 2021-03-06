public class VirtualPage {
	private boolean referenced;
	private boolean modified;
	private boolean present;
	private int referencedTime;
	private int frame;
	
	public boolean isReferenced() {
		return referenced;
	}
	
	public void clearPage(){
		this.referenced = false;
		this.modified = false;
		this.present = false;
		this.referencedTime = 0;
		this.frame = 0;
	}
	
	public void setReferenced(boolean pReferenced) {
		this.referenced = pReferenced;
	}
	public void setModified(boolean pModified) {
		this.modified = pModified;
	}
	public void setPresent(boolean pPresent) {
		this.present = pPresent;
	}
	public void setFrame(int pFrame) {
		this.frame = pFrame;
	}
	public boolean isModified() {
		return this.modified;
	}
	public boolean isPresent() {
		return this.present;
	}
	public int getFrame() {
		return this.frame;
	}
	
	public void setReferencedTime(int pTime){
		this.referencedTime = pTime;
	}
	
	public int getReferencedTime(){
		return this.referencedTime;
	}
}
