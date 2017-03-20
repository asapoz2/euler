package synchro;
import java.util.concurrent.atomic.AtomicInteger;

public class LoginRunnable implements Runnable{
		private String username;
		private LoginServer server;
		private AtomicInteger counter;
		public LoginRunnable(String username,LoginServer server, AtomicInteger counter){
			super();
			this.username=username;
			this.server=server;
			this.counter=counter;
		}
		public void run() {
			server.login(username);
			
			server.login(username);
			server.login(username);
			counter.incrementAndGet();
		}
		
	}
