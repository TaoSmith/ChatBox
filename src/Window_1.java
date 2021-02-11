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
import java.security.Key;
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

public class Window_1 extends JFrame
{
	static String username1;
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
		text1.setBounds(21, 403, 239, 45);
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
		
		send1.setBounds(270, 403, 89, 45);
		contentPane.add(send1);
		////////////////Decrypt button///////////////////////
		send1_1	= new JButton("DECRYPT");
		send1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0_) {
				String s = text1.getText();
				if(s.equals(" "));
				{
					return;
				}
			}
		});
		send1_1.setBounds(500, 403, 89, 45);
		contentPane.add(send1_1);
		////////////////////////////////////////////////////
		
		label1 = new JLabel("Chat window for : " + username1);
		label1.setBounds(21, 25, 250, 14);
		contentPane.add(label1);
		
		JButton clear = new JButton("CLEAR");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				display1.setText("");
				display1_1.setText("");
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
        
		String s = Window_2.text2.getText();
		
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
		//Saves what the is being typed in Window 1 and appends it to savedmsg.txt.
		//File fileName = new File("savedmsg.txt");
		//FileWriter fileWriter = new FileWriter(fileName, true);
		//BufferedWriter buffer = new BufferedWriter(fileWriter);
		//PrintWriter printWriter = new PrintWriter(buffer);
		display1.append(Window_2.username2 + " : " + encryptedText + "\n");
		display1_1.append(Window_2.username2 +  " : " + s + "\n");
		//printWriter.println(Window_2.username2+ " : "  + s);
		//printWriter.close();
	}

	
	private javax.swing.JLabel label1;
	private static javax.swing.JTextArea display1;
	private javax.swing.JButton send1;
	public static javax.swing.JTextArea text1;
	private static javax.swing.JTextArea display1_1;
	private javax.swing.JButton send1_1;
}
