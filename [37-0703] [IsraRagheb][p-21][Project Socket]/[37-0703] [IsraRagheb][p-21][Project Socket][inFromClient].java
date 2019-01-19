import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.StringTokenizer;


public class inFromClient extends Thread{
Socket s;
DataOutputStream toserver;
Client client;
boolean ShouldRun=true;
boolean notnamed=false;


public inFromClient(Client client,Socket s){
	System.out.println("Please enter your username:");
	this.client=client;
	this.s=s;	
}

//sends the join method to server
public void Join(String input){
	try {
		toserver.writeUTF(input);
	} catch (IOException e) {
		e.printStackTrace();
	}
}

//sends the message to the server
public void Chat(String Message){
	try {
		toserver.writeUTF(Message);
	} catch (IOException e) {
		e.printStackTrace();
	}
	
}

//gets the list of the clients
public void GetMemberList(){
	try {
		toserver.writeUTF("list");
	} catch (IOException e) {
		e.printStackTrace();
	}
}

//closes all the connections with the server
public void close(){
	try {
		toserver.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
}

public void quit(){
	try {
		toserver.writeUTF("quit");
	} catch (IOException e) {
		e.printStackTrace();
	}
}

//waits for the reply from the server
public void run(){
	try {
		toserver=new DataOutputStream(s.getOutputStream());
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	while(ShouldRun){
		Scanner console=new Scanner(System.in);
		String input=console.nextLine();
		StringTokenizer st=new StringTokenizer(input," ");
		
		String Token1="";
		if(st.hasMoreTokens()){
			 Token1=st.nextToken();
		}
		switch (Token1) {
		case "Join":
			Join(input);
			break;
		case "list":
			GetMemberList();
			break;
		case "quit":
			quit();
			break;
		case "chat":
		   Chat(input);
		   break;
		default:
			try {
				toserver.writeUTF("error");
			} catch (IOException e) {
				e.printStackTrace();
			}
		  break;
		}
	
	}
	
}







}
