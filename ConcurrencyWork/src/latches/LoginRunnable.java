package latches;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class LoginRunnable implements Runnable{
		private String username;
		private LoginServer server;
		CountDownLatch startSignal;
		CountDownLatch doneSignal;
		public LoginRunnable(String username,LoginServer server, CountDownLatch startSignal, CountDownLatch doneSignal){
			super();
			this.username=username;
			this.server=server;
			this.startSignal=startSignal;
			this.doneSignal=doneSignal;
		}
		public void run() {
			 try {
				startSignal.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			server.login(username);
			
			server.login(username);
			server.login(username);
			doneSignal.countDown();
		}
		
	}
