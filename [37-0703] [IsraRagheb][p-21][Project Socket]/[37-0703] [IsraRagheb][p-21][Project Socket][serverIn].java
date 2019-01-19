import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class serverIn extends Thread {
	server s;
	Socket socket;
	DataInputStream in;
	boolean shouldRun;
	
public serverIn(server s,Socket sock){
	this.s=s;
	this.socket=sock;
}
	
public void run(){
	try {
		in=new DataInputStream(socket.getInputStream());
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	while(shouldRun){
		
	}
}

}
