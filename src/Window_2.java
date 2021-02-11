import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Window_2 extends JFrame
{
	static String username2;
	private JPanel contentPane;
	static Cipher cipher;
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
					Window_2 frame = new Window_2();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
				
		});
	}
	
	
	public Window_2()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 747, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		display2 = new JTextArea();
		display2.setBounds(23, 48, 336, 344);
		contentPane.setLayout(null);
		contentPane.add(display2);
		
		display2_2 = new JTextArea();
		display2_2.setBounds(370, 48, 336, 344);
		contentPane.setLayout(null);
		contentPane.add(display2_2);
		
		text2 = new JTextArea();
		text2.setBounds(21, 403, 239, 45);
		contentPane.add(text2);
		
		send2 = new JButton("SEND");
		send2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0_) {
				String s = text2.getText();
				if(s.equals("")) 
				{
					return;
				}
				//Displaying me instead of username2
				display2.append("me" + " : " + s + "\n");
				try {
					Window_1.sendText();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //save this line
				text2.setText("");
			}
		});
		send2.setBounds(270, 403, 89, 45);
		contentPane.add(send2);
		////////////////Decrypt button///////////////////////
		send2_1	= new JButton("DECRYPT");
		send2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0_) {
				String s = text2.getText();
				if(s.equals(" "));
				{
					return;
				}
			}
		});
		send2_1.setBounds(500, 403, 89, 45);
		contentPane.add(send2_1);
		////////////////////////////////////////////////////

		label2 = new JLabel("Chat window for : " + username2);
		label2.setBounds(21, 25, 250, 14);
		contentPane.add(label2);
		
		JButton clear = new JButton("CLEAR");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				display2.setText("");
				display2_2.setText("");
			}
		});
		clear.setBounds(270, 21, 89, 23);
		contentPane.add(clear);
		
	}

	public static void sendText() throws Exception
	{
		//////////////////////key gen ////////////////////////////////////
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128); // block size is 128bits
		SecretKey secretKey = keyGenerator.generateKey();
		cipher = Cipher.getInstance("AES");
		/////////////////////////////////////////////////////////////////////

		String s = Window_1.text1.getText();
		
		//////////////////////for encryption////////////////////////////////
		byte[] plainTextByte = s.getBytes();
		try {
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] encryptedByte = null;
		try {
			encryptedByte = cipher.doFinal(plainTextByte);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Base64.Encoder encoder = Base64.getEncoder();
		String encryptedText = encoder.encodeToString(encryptedByte);
		///////////////////////////////////////////////////////////////////
		
		if(s.equals("")) 
		{
			return;
		}
		//Saves what the is being typed in Window 2 and appends it to savedmsg.txt.
		//File fileName = new File("savedmsg.txt");
		//FileWriter fileWriter = new FileWriter(fileName, true);
		//BufferedWriter buffer = new BufferedWriter(fileWriter);
		//PrintWriter printWriter = new PrintWriter(buffer);
		display2.append(Window_1.username1 + " : " + encryptedText + "\n");
		display2_2.append(Window_1.username1 +  " : " + s + "\n");
		//printWriter.println(Window_1.username1+ " : " + s);
		//printWriter.close();
	}
	
	
	private javax.swing.JLabel label2;
	private static javax.swing.JTextArea display2;
	private javax.swing.JButton send2;
	public static javax.swing.JTextArea text2;
	public static javax.swing.JTextArea display2_2;
	private javax.swing.JButton send2_1;

	
}