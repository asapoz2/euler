package synchro;
import java.util.concurrent.atomic.AtomicInteger;



public class LoginMain {

	public static void main(String[] args) {
		LoginServer server=new LoginServer();
		AtomicInteger counter=new AtomicInteger(0);
		Runnable mainRunnable=new LoginRunnable("Steve",server,counter);
		/*Thread loginThread = new Thread(mainRunnable);
		Thread loginThread2 = new Thread(mainRunnable);
		loginThread.run();
		loginThread2.run();*/
		
		//loginThread3.start();
		//int i=0;
		/*while(loginThread.isAlive()&&loginThread2.isAlive()&&(i++<100)){
			Thread.yield();
		}*/
		int i=0;
		
		while(i++<160){
			new Thread(mainRunnable).start();
		}
		while(counter.get()<160){
			Thread.yield();
		}
		
		
		System.out.println(server.countName("Steve"));
	}
	


}
