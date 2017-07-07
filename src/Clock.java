/*Temos que arranjar uma solução pra o tempo*/
public class Clock implements Runnable{
	private int atualTime = 0;
	private Dispatcher dispatcher = new Dispatcher(); 


	public void run(){

		while(true){
			try {
				Thread.sleep(1000);
				this.dispatcher.notify(this.atualTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}





	public void addListener(IClockListener cl){
		this.dispatcher.addListener(cl);
	}	
}
