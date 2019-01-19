import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextField;


public class noom {

	 JFrame frame;
	 JLabel lblMessage;
	 JTextArea textArea;
	 JButton btnSend_1;
	 JLabel lblport ;
	
	 
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					noom window = new noom();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public noom() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 379);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnSend = new JButton("Send");
		btnSend.setBounds(185, 240, 75, -206);
		frame.getContentPane().add(btnSend);
		
        textArea = new JTextArea();
		textArea.setBounds(265, 11, 0, 16);
		frame.getContentPane().add(textArea);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(73, 268, 269, 42);
		frame.getContentPane().add(textPane);
		
		btnSend_1 = new JButton("Send");
		btnSend_1.setBounds(142, 322, 117, 29);
		frame.getContentPane().add(btnSend_1);
		
	    lblMessage = new JLabel("Message");
		lblMessage.setBounds(123, 217, 137, 39);
		frame.getContentPane().add(lblMessage);
		
		lblport = new JLabel("New label");
		lblport.setBounds(44, 19, 353, 148);
		frame.getContentPane().add(lblport);
	}

	public void setLblport(JLabel lblport) {
		this.lblport.setText(lblport.getText()); 
		frame.revalidate();
	}

	
	
}
