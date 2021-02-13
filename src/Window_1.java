import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Window_1 extends JFrame
{
	static String username1;
	private JPanel contentPane;
	static Cipher cipher;
	//static Cipher cipherWin2 = Window_2.getCipher();
	/**
	 * Launch the application.
	 * @throws NoSuchAlgorithmException 
	 */		
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Window_1 frame = new Window_1();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			
		});
	}
	
	
	public Window_1()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 747, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		display1 = new JTextArea();
		display1.setBounds(23, 48, 336, 344);
		contentPane.setLayout(null);
		contentPane.add(display1);
		
		display1_1 = new JTextArea();
		display1_1.setBounds(370, 48, 336, 344);
		contentPane.setLayout(null);
		contentPane.add(display1_1);
		
		text1 = new JTextArea();
		text1.setBounds(21, 403, 495, 45);
		contentPane.add(text1);
		
		send1 = new JButton("SEND");
		send1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0_) {
				String s = text1.getText();
				if(s.equals("")) 
				{
					return;
				}
				//Displaying me instead of username1
				display1.append("me" + " : " + s + "\n");
				try {
					Window_2.sendText();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				text1.setText("");
			}
		});
		send1.setBounds(523, 403, 89, 45);
		contentPane.add(send1);
	
		////////////////Decrypt button///////////////////////
		send1_1	= new JButton("DECRYPT");
		send1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0_) {
		        String s = Window_2.display2.getText();
				if(s.equals(""))
				{
					return;
				}
				try {
					Window_2.decText();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				text1_1.setText("");
			}
		});
		send1_1.setBounds(617, 403, 89, 45);
		contentPane.add(send1_1);
		////////////////////////////////////////////////////
	
		label1 = new JLabel("Chat window for : " + username1);
		label1.setBounds(21, 25, 250, 14);
		contentPane.add(label1);
		
		//label1_1 = new JLabel("DECRYPTED MESSAGE");
		//label1_1.setBounds(476, 420, 250, 14);
		//contentPane.add(label1_1);
		
		JButton clear = new JButton("CLEAR");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				display1.setText("");
				display1_1.setText("");
			}
		});
		clear.setBounds(617, 21, 89, 23);
		contentPane.add(clear);
		
	}

	public static void sendText() throws Exception
	{
        String s = Window_2.display2.getText();
        if(s.equals("")) 
		{
			return;
		}
        try {
        	Server input = new Server();
        	input.encryptedMsg2(s);
        } catch (Exception e) {
        	e.printStackTrace();
        }

	}
	
	public static void decText() throws Exception
	{
        String s = Window_2.display2.getText();
        if(s.equals("")) 
		{
			return;
		}
        try {
        	Server output = new Server();
        	output.decryptedMsg2(s);
        } catch (Exception e) {
        	e.printStackTrace();
        }

	}
	
	private javax.swing.JLabel label1;
	static javax.swing.JTextArea display1;
	private javax.swing.JButton send1;
	public static javax.swing.JTextArea text1;
	static javax.swing.JTextArea display1_1;
	public static javax.swing.JTextArea text1_1;
	//private javax.swing.JLabel label1_1;
	private javax.swing.JButton send1_1;

}