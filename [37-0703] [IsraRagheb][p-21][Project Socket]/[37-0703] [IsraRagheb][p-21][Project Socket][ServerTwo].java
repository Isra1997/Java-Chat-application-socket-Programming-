	import java.io.*; 
import java.net.*; 
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
public class ServerTwo {
		ServerSocket serverSocket;
		Client connect;
		ArrayList <serverConnection2> clients  = new ArrayList<serverConnection2>();
		boolean ShouldRun=true;
		Socket x;
		DataOutputStream outToServer;
		public static void main(String[] args) {
			new ServerTwo();

		}


		public ServerTwo(){
			try {
				serverSocket= new ServerSocket(6600);
				while(ShouldRun){
					//accept this connection
					Socket socketc=serverSocket.accept();
					//create a connection with a client name
					serverConnection2 sc=new serverConnection2(socketc, this);
					sc.start();
					//add the client to the list
					clients.add(sc);
					//if statement for client port number connecting the servers
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
	}
		 




