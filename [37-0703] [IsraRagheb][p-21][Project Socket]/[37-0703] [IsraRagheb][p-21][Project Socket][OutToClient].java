import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class OutToClient extends Thread{
Socket s;
DataInputStream fromserver;
Client client;
boolean ShouldRun=true;


public OutToClient(Client client,Socket s){
	try {
		fromserver=new DataInputStream(s.getInputStream());
	} catch (IOException e) {
		e.printStackTrace();
	}
	this.client=client;
	this.s=s;
}

public void close(){
	try {
		s.close();
		fromserver.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
}

public void run(){
	while(ShouldRun){
		String reply;
		try {
			reply = fromserver.readUTF();
			if(reply.equals("quit")){
				System.out.println("Your are now offline :/");
				break;
			}
			
			System.out.println(reply);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
  this.close();
	
}

}
