import java.io.*; 
import java.net.*; 
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Scanner;


	
public class server {
	ServerSocket serverSocket;
	Client connect;
    ArrayList <serverConnection> clients  = new ArrayList<serverConnection>();
	int i;
	boolean ShouldRun=true;
	
	public static void main(String[] args) {
		new server();
	}

	
	
	public server(){
		try {
			serverSocket= new ServerSocket(6666);
			TwoServersConnect();
			while(ShouldRun){
				//accept this connection
				Socket socket=serverSocket.accept();
				//create a connection with a client name
				serverConnection sc=new serverConnection(socket, this);
				sc.start();
				//add the client to the list
				clients.add(sc);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void TwoServersConnect(){
		try {
		   Socket s=new Socket("Isras-MacBook-Pro.local",6600);
		   serverConnection sc=new serverConnection(s, this);
		   sc.start();
		   clients.add(sc);
		   sc.setUsrename("ServerTwo");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	



} 
	 


