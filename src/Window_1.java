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

public class Window_1 extends JFrame
{
	static String username1;
	private JPanel contentPane;
	static Cipher cipher;
	
	//final String secretKey = "ssshhhhhhhhhhh!!!!";
	static String secretKey = null;
	//static String getMsg1 = null;
	static String getEnc1 = null;
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
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		Border border = BorderFactory.createLineBorder(Color.BLACK);

		display1 = new JTextArea();
		display1.setBorder(border);
		display1.setBounds(23, 48, 336, 344);
		display1.setWrapStyleWord(true);
		display1.setLineWrap(true);
		contentPane.setLayout(null);
		contentPane.add(display1);
		
		display1_1 = new JTextArea();
		display1_1.setBorder(border);
		display1_1.setBounds(370, 48, 336, 344);
		display1_1.setWrapStyleWord(true);
		display1_1.setLineWrap(true);
		contentPane.setLayout(null);
		contentPane.add(display1_1);
		
		text1 = new JTextArea();
		text1.setBorder(border);
		text1.setBounds(23, 403, 495, 45);
		text1.setWrapStyleWord(true);
		text1.setLineWrap(true);
		contentPane.add(text1);
		
		//Send Button//
		send1 = new JButton("SEND");
		send1.setForeground(Color.GREEN);
		send1.setFont(new Font("Tahoma", Font.BOLD, 11));
		send1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0_) {
				String s = text1.getText();
				if(s.equals("")) 
				{
					return;
				}
				//Displaying me instead of username1
				display1.append("me" + " : " + s + "\n");
				display1.append("\n");
				try {
					randomStringGen();
				    String encryptedString = Server.encrypt(s, secretKey) ;
				    //String decryptedString = Server.decrypt(encryptedString, secretKey) ;
				     
					Window_2.display2.append(Window_1.username1 + " : " + encryptedString + "\n");
					Window_2.display2.append("\n");
					//Window_2.display2_2.append(Window_1.username1 + " : " + decryptedString + "\n");
					saveEncrypt(encryptedString);
				    //saveDecrypt(decryptedString);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				text1.setText("");
			}
		});
		send1.setBounds(523, 403, 89, 45);
		contentPane.add(send1);
		//decrypt button//
		send1_1 = new JButton("DECRYPT");
		send1_1.setForeground(Color.RED);
		send1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		send1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0_) {
				String grabber = Window_2.secretKey;		
				String grabberEnc = Window_2.getEnc2;
				String deMsg = Server.decrypt(grabberEnc, grabber);
				Window_1.display1_1.append(Window_2.username2 + " : " + deMsg + "\n");
				Window_1.display1_1.append("\n");	
				Window_2.getEnc2 = null;
				Window_2.secretKey = null;
			}
		});
		send1_1.setBounds(617, 403, 89, 45);
		contentPane.add(send1_1);
		
		label1 = new JLabel("Chat window for : " + username1);
		label1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		label1.setBounds(21, 25, 250, 14);
		contentPane.add(label1);
		
		label1_1 = new JLabel("Decrypted Messge");
		label1_1.setForeground(Color.YELLOW);
		label1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		label1_1.setBounds(370, 25, 250, 14);
		contentPane.add(label1_1);
		
		JButton clear = new JButton("CLEAR");
		clear.setForeground(Color.BLUE);
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
	public void randomStringGen() {
		byte[] array = new byte[8];
		new Random().nextBytes(array);
		String secretString = new String(array, Charset.forName("UTF-8"));
		Window_1.secretKey = secretString;
	}
	//public static void saveDecrypt(String decryptedMessage) {
	//	Window_1.getMsg1 = decryptedMessage;
	//}
	public static void saveEncrypt(String EncryptedMessage) {
		Window_1.getEnc1 = EncryptedMessage;
	}

	private javax.swing.JLabel label1;
	static javax.swing.JTextArea display1;
	private javax.swing.JButton send1;
	public static javax.swing.JTextArea text1;
	static javax.swing.JTextArea display1_1;
	public static javax.swing.JTextArea text1_1;
	private javax.swing.JButton send1_1;
	private javax.swing.JLabel label1_1;

}
