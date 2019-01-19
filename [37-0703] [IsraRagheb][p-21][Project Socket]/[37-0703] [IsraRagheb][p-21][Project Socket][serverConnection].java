import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.StringTokenizer;


public class serverConnection  extends Thread{
	Socket socket;
	server Server;
	DataInputStream in;
	DataOutputStream out;
	Boolean ShouldRun=true;
	String usrename;
	


public serverConnection(Socket socket,server server){
		super("connection threads");
		this.socket=socket;
		this.Server=server;
}

//Join Response
public void JoinReresponse(String name){
	 this.setUsrename(name);
	for(int i=0;i<Server.clients.size()-1;i++){
		String sc=Server.clients.get(i).getUsrename();
		if(sc.equals(name)){
			try {
				out.writeUTF("Join Request Reject.Username already in use.Welcome to Isra's chat application :)");
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
         try {
			out.writeUTF("Join Request accepted.");
		} catch (IOException e) {
			e.printStackTrace();
		}

}


//Listing clients on my server
public void MemberListResponse(){
	String list="";
	for(int i=0;i<Server.clients.size();i++){
		list=list+" "+Server.clients.get(i).getUsrename();
	}
	try {
		out.writeUTF(list);
	} catch (IOException e) {
		e.printStackTrace();
	}
	
}

public String ListFromOtherServer(){
	String list2="";
	try {
		serverConnection sc=Server.clients.get(0);
		sc.SendStringToClient("listall");
		list2=sc.in.readUTF();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return list2;
}

public void SendStringToClient(String text){
	try {
		out.writeUTF(text);
		out.flush();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
}

//Routing the message
public void SendStringToAllClients(String text,String destination){
		for(int i=0; i< Server.clients.size();i++){
			serverConnection sc= Server.clients.get(i); 
             //check for the destination of the message
	         if(sc.getUsrename().equals(destination)){
			 sc.SendStringToClient(text);
			 return;
	            }
	         }
		
	 
		 serverConnection sc= Server.clients.get(0); 
         sc.SendStringToClient("chat"+" "+text+" "+destination+" "+"5");

	 }

		
		



	
public String getUsrename() {
	return usrename;
}

public void run(){
	 try {
		in=new DataInputStream(socket.getInputStream());
		out=new DataOutputStream(socket.getOutputStream());
		
		while(ShouldRun){
			try{
			String textin=in.readUTF();
			StringTokenizer st=new StringTokenizer(textin," ");
			String Token1="";
			String Token2="";
			String Token3="";
			String Token4="";
			if(st.hasMoreTokens()){
				 Token1=st.nextToken();
				if(st.hasMoreTokens()){
				Token2=st.nextToken();
					if(st.hasMoreTokens()){
						Token3=st.nextToken();
						if(st.hasMoreTokens()){
						Token4=st.nextToken();
						}
					}
				}
			}
				else{
					System.out.println("Please enter a vaild message");
				}


			switch (Token1) {
			case "Join":
				this.JoinReresponse(Token2);
				break;
			case "list":
				MemberListResponse();
				break;
			case"chat":
				if(Integer.parseInt(Token4)>=3){
				SendStringToAllClients(Token2, Token3);
				}
				else{
					this.out.writeUTF("The Time to live entered is to short(please enter a time to live greater than 3).");
				}
				break;
			case "error":
				this.out.writeUTF("Please enter a valid message");
				break;
			case"quit":
				this.out.writeUTF("quit");
				close();
				Server.clients.remove(this);
				break;
			default:
				break;
			}
		}catch(SocketException e){
			socket.close();
		}
			
	 }
		
		
	} catch (IOException e) {
		e.printStackTrace();
		close();
	}
	
}
	
public void setUsrename(String usrename) {
	this.usrename = usrename;
}

public void close(){
	try {
		in.close();
		out.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
}
	
	
}
