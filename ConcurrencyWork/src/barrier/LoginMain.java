package barrier;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;



public class LoginMain {

	public static void main(String[] args) throws InterruptedException {
		LoginServer server=new LoginServer();
		/*CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(160);*/
		CyclicBarrier barrier = new CyclicBarrier(160);
		Runnable mainRunnable=new LoginRunnable("Steve",server,barrier);
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
		     
		
		System.out.println(server.countName("Steve"));
	}
	


}
