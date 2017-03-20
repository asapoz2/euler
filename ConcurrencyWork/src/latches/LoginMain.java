package latches;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;



public class LoginMain {

	public static void main(String[] args) throws InterruptedException {
		LoginServer server=new LoginServer();
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(160);
		Runnable mainRunnable=new LoginRunnable("Steve",server,startSignal,doneSignal);
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
		startSignal.countDown();
		doneSignal.await();        
		
		System.out.println(server.countName("Steve"));
	}
	


}
