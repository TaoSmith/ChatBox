import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.crypto.Cipher;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class Window_2 extends JFrame
{
	static String username2;
	private JPanel contentPane;
	static Cipher cipher;
	
	//final String secretKey = "ssshhhhhhhhhhh!!!!";
	static String secretKey = null;
	//static String getMsg2 = null;
	static String getEnc2 = null;
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
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		
		display2 = new JTextArea();
		display2.setBorder(border);
		display2.setBounds(23, 48, 336, 344);
		display2.setWrapStyleWord(true);
		display2.setLineWrap(true);
		contentPane.setLayout(null);
		contentPane.add(display2);
		
		display2_2 = new JTextArea();
		display2_2.setBorder(border);
		display2_2.setBounds(370, 48, 336, 344);
		display2_2.setWrapStyleWord(true);
		display2_2.setLineWrap(true);
		contentPane.setLayout(null);
		contentPane.add(display2_2);
		
		text2 = new JTextArea();
		text2.setBorder(border);
		text2.setBounds(23, 403, 495, 45);
		text2.setWrapStyleWord(true);
		text2.setLineWrap(true);
		contentPane.add(text2);

		//send button//
		send2 = new JButton("SEND");
		send2.setForeground(Color.GREEN);
		send2.setFont(new Font("Tahoma", Font.BOLD, 11));
		send2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0_) {
				String s = text2.getText();
				if(s.equals("")) 
				{
					return;
				}
				//Displaying me instead of username2
				display2.append("me" + " : " + s + "\n");
				display2.append("\n");
				try {
					randomStringGen();
				    String encryptedString = Server.encrypt(s, secretKey) ;
				    //String decryptedString = Server.decrypt(encryptedString, secretKey) ;
				     
					Window_1.display1.append(Window_2.username2 + " : " + encryptedString + "\n");
					Window_1.display1.append("\n");
					//Window_1.display1_1.append(Window_2.username2 + " : " + decryptedString + "\n");
					saveEncrypt(encryptedString);
				    //saveDecrypt(decryptedString);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				text2.setText("");
			}
		});
		send2.setBounds(523, 403, 89, 45);
		contentPane.add(send2);
		//decrypt button//
		send2_2 = new JButton("DECRYPT");
		send2_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		send2_2.setForeground(Color.RED);
		send2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0_) {
				String grabber = Window_1.secretKey;		
				String grabberEnc = Window_1.getEnc1;
				String deMsg = Server.decrypt(grabberEnc, grabber);
				Window_2.display2_2.append(Window_1.username1 + " : " + deMsg + "\n");
				Window_2.display2_2.append("\n");
				Window_1.getEnc1 = null;
				Window_1.secretKey = null;
			}
		});
		send2_2.setBounds(617, 403, 89, 45);
		contentPane.add(send2_2);
	
		label2 = new JLabel("Chat window for : " + username2);
		label2.setForeground(Color.BLACK);
		label2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		label2.setBounds(21, 25, 250, 14);
		contentPane.add(label2);
		
		label2_2 = new JLabel("Decrypted Messge");
		label2_2.setForeground(Color.YELLOW);
		label2_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		label2_2.setBounds(370, 25, 250, 14);
		contentPane.add(label2_2);
		
		JButton clear = new JButton("CLEAR");
		clear.setForeground(Color.BLUE);
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				display2.setText("");
				display2_2.setText("");
			}
		});
		clear.setBounds(617, 21, 89, 23);
		contentPane.add(clear);
		
	}
	public void randomStringGen() {
		byte[] array = new byte[8];
		new Random().nextBytes(array);
		String secretString = new String(array, Charset.forName("UTF-8"));
		Window_2.secretKey = secretString;
	}
	//public static void saveDecrypt(String decryptedMessage) {
	//	Window_2.getMsg2 = decryptedMessage;
	//}
	public static void saveEncrypt(String EncryptedMessage) {
		Window_2.getEnc2 = EncryptedMessage;
	}
	
	private javax.swing.JLabel label2;
	static javax.swing.JTextArea display2;
	private javax.swing.JButton send2;
	public static javax.swing.JTextArea text2;
	static javax.swing.JTextArea display2_2;
	public static javax.swing.JTextArea text2_2;
	private javax.swing.JButton send2_2;
	private javax.swing.JLabel label2_2;

}
