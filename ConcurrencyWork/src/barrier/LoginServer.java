package barrier;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class LoginServer {
	private Map<String, Integer> logins;
	public LoginServer(){
		logins=new HashMap<String,Integer>();
		//logins=new ConcurrentHashMap<String,Integer>();
	}
	public synchronized void login(String username){
		
		Integer currlogs=logins.get(username);
		if(currlogs==null){
			logins.put(username, 1);
		}else{
			logins.put(username, currlogs+1);
		}
	}
	public Integer countName(String username){
		return logins.get(username);
	}
}
