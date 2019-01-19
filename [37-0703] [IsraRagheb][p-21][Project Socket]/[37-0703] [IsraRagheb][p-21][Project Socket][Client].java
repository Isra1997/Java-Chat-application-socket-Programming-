import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.sampled.ReverbType;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Client {
	

Socket s;
inFromClient in;
OutToClient out;
static boolean flag= true ;
boolean createdOnce=false;
static noom x;
public static void main(String[] args) {

//	 x =new noom();
//	
//	while(flag){
//	JLabel portnum= new JLabel("please enter the port number of the server you wish to connect to:");
//	x.setLblport(portnum);
//	x.frame.setVisible(true);
//	x.btnSend_1.addActionListener(new ActionListener() {
//		
//		
//		public void actionPerformed(ActionEvent e) {
//			String port=x.textArea.getText();
//
//			if(port.equals(6666) ||port.equals(6600)){
//				int portn=Integer.parseInt(port);
//				new Client(portn);
//				flag=false;
//				}
//				else
//				{
//					JLabel mess=new JLabel("Please enter a valid port number(6666 or 6600):");
//					x.setLblport(mess);
//					
//				}
//				}
//		
//	     });
//	
//	
//     }}
	while(flag){
		System.out.println("please enter the port number of the server you wish to connect to:");
		Scanner sc=new Scanner(System.in);
		int port=Integer.parseInt(sc.nextLine());
		if(port==6666 ||port==6600){
		new Client(port);
		flag=false;
		}
		else
		{
			JLabel text=new JLabel("Please enter a valid port number(6666 or 6600):");
			System.out.println();
		}
		}
}




public Client(int port){
	try {
		s=new Socket("Isras-MacBook-Pro.local", port);
	    in=new inFromClient(this,s);
	    out=new OutToClient(this, s);
		in.start();
		out.start();
	} catch (IOException e) {
		e.printStackTrace();
	}

}


}
	
	
	




