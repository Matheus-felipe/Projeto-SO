import java.util.ArrayList;

public class Dispatcher {
	
	public ArrayList <IClockListener> listeners = new ArrayList <IClockListener>();
	
	public void addListener(IClockListener cl){
		listeners.add(cl);
	}
	
	/*Aqui ele "notifica todos os que tão associados como "ouvintes" . No nosso caso é só a MMU"*/
	public void notify(int tempo){
		
		for(IClockListener cl : listeners){
			cl.receivedEvent(tempo);
		}
	}
}
