
/*Temos que arranjar uma solução pra o tempo*/
public class Clock {
	private int atualTime;
	private Dispatcher dispatcher; 
	
	
	
	
	/*
	 * run(){
	 * 	while(true){
	 * 		/*Passou a porra do clock
	 * 		sleep(5Fodasse);
	 *		this.atual_time = this.atual_time + tempoDoClock;
	 *      this.dispatcher.notify(this.atual_time);
	 *
	 * 
	 *
	 * 
	 * 
	 * */
	
	
	public void addListener(IClockListener cl){
		this.dispatcher.addListener(cl);
	}	
}
