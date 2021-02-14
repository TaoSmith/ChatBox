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

public class Window_2 extends JFrame
{
	static String username2;
	private JPanel contentPane;
	static Cipher cipher;
	//static Cipher cipherWin1 = Window_1.getCipher();

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
		
		text2_2 = new JTextArea();
		text2_2.setBounds(370, 403, 239, 45);
		contentPane.add(text2_2);
		
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
				} 
				text2.setText("");
			}
		});
		send2.setBounds(270, 403, 89, 45);
		contentPane.add(send2);
		
		////////////////Decrypt button///////////////////////
		send2_2	= new JButton("DECRYPT");
		send2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0_) {
				String s = text2_2.getText();
				if(s.equals(""))
				{
					return;
				}
				try {
					Window_1.decText();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				text2_2.setText("");
			}
		});
		send2_2.setBounds(617, 403, 89, 45);
		contentPane.add(send2_2);
		////////////////////////////////////////////////////

		label2 = new JLabel("Chat window for : " + username2);
		label2.setBounds(21, 25, 250, 14);
		contentPane.add(label2);
		
		//label2_2 = new JLabel("DECRYPTED MESSAGE");
		//label2_2.setBounds(476, 420, 250, 14);
		//contentPane.add(label2_2);
		
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
		String s = Window_1.text1.getText();
		if(s.equals("")) 
		{
			return;
		}
		String encMsg = encrypt(s, savedKey(), genCipher());
        //String decMsg = decrypt(encMsg, cipherWin1);
		display2.append(Window_1.username1 + " : " + encMsg + "\n");
		//This is just printing the plain text and not decrypting...
		//display2_2.append(Window_1.username1 + " : " + s + "\n");

	}
	
	public static void decText() throws Exception
	{
		String s = Window_2.text2_2.getText();
		if(s.equals("")) 
		{
			return;
		}
		else {
        String decMsg = decrypt(s, Window_1.getCipher());
		display2_2.append(Window_1.username1 + " : " + decMsg + "\n");
		}
	}
	
	
	public static Cipher genCipher() throws Exception {
		cipher = Cipher.getInstance("AES");
		return cipher;
	}
	
	public static SecretKey savedKey() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // block size is 128bits
        SecretKey secretKey = keyGenerator.generateKey();
		return secretKey;
		
	}
	
	public static String encrypt(String s, SecretKey secretKey, Cipher cipher) 
			throws Exception{
		  byte[] plainTextByte = s.getBytes();
	      cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	      byte[] encryptedByte = cipher.doFinal(plainTextByte);
	      Base64.Encoder encoder = Base64.getEncoder();
	      String encryptedText = encoder.encodeToString(encryptedByte);
	      return encryptedText;
	    }
	
	public static String decrypt(String encryptedText, Cipher cipher)
        throws Exception {
	Base64.Decoder decoder = Base64.getDecoder();
    byte[] encryptedTextByte = decoder.decode(encryptedText);
    cipher.init(Cipher.DECRYPT_MODE, (Key) cipher);
    byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
    String decryptedText = new String(decryptedByte);
    return decryptedText;
	}
	
	public static Cipher getCipher() throws Exception {
		return cipher;
	}
	
	private javax.swing.JLabel label2;
	private static javax.swing.JTextArea display2;
	private javax.swing.JButton send2;
	public static javax.swing.JTextArea text2;
	public static javax.swing.JTextArea display2_2;
	public static javax.swing.JTextArea text2_2;
	//private javax.swing.JLabel label2_2;
	private javax.swing.JButton send2_2;

}