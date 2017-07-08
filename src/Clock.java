/*Temos que arranjar uma solução pra o tempo*/
public class Clock implements Runnable{
	private int atualTime = 0;
	private int clockTime;
	private Dispatcher dispatcher = new Dispatcher(); 


	public Clock(int pClockTime){
		this.clockTime = pClockTime;
	}
	public void run(){

		while(true){
			try {
				Thread.sleep(clockTime);
				this.atualTime += clockTime;
				this.dispatcher.notify(this.atualTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addListener(IClockListener cl){
		this.dispatcher.addListener(cl);
	}	
}
