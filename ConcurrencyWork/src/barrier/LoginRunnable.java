package barrier;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class LoginRunnable implements Runnable{
		private String username;
		private LoginServer server;
		private CyclicBarrier barrier;
		public LoginRunnable(String username,LoginServer server, CyclicBarrier barrier){
			super();
			this.username=username;
			this.server=server;
			this.barrier=barrier;
		}
		public void run() {
			server.login(username);
			
			server.login(username);
			server.login(username);
			try {
				barrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
		
	}
