
public class PageFrames {
	private boolean referenciada = false;
	private boolean modificada = false;
	private float tempoReferenciado;
	private int value;
	
	public void setValue(int pValue) {
		this.value = pValue;
	}

	public int getValor() {
		return this.value;
	}

	public void setTime(float tempoAtual){
		this.tempoReferenciado = tempoAtual;
	}
	
	public boolean isReferenciada() {
		return referenciada;
	}
	
	public boolean isModificada() {
		return modificada;
	}
	
	public float getTempoReferenciado() {
		return tempoReferenciado;
	}
}

