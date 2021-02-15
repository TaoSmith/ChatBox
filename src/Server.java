import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Server extends JFrame {
	
	private JPanel contentPane;
	private JTextField name1;
	private JTextField name2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable ()
		{
			public void run()
			{
				try
				{
					Server frame = new Server();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public Server()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder( new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		name2 = new JTextField();
		name2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createRoom();
			}
		});
		name2.setBounds(104, 134, 237, 31);
		contentPane.add(name2);
		name2.setColumns(10);
		
		name1 = new JTextField();
		name1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createRoom();
			}
		});
		name1.setBounds(104, 50, 237, 31);
		contentPane.add(name1);
		name1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("1st username");
		lblNewLabel.setBounds(187, 25, 104, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("2nd username");
		lblNewLabel_1.setBounds(187, 109, 104, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("JOIN CHAT");
		btnNewButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				createRoom();
			}
		});
		btnNewButton.setBounds(152, 203, 128, 47);
		contentPane.add(btnNewButton);
	}
	
	private void createRoom()
	{
		String p1, p2;
		
		p1 = name1.getText();
		p2 = name2.getText();
		
		if(p1.equals("") || p2.equals(""))
		{
			JOptionPane.showMessageDialog(null, "Please enter a valid username");
			return;
		}
		
		Window_1.username1 = name1.getText();
		Window_2.username2 = name2.getText();
		ChatRoom.createRoom();
		
	}
	//Calling for encryption
	public String encryptedMsg1(String inputedMsg) throws Exception{
		String enc = Server.encrypt(inputedMsg, savedKey());
		Window_2.display2.append(Window_1.username1 + " : " + enc + "\n");
		return enc;
	}
	//\\******//Something isn't working here? Need to figure out how to get a global variable of key to use//******//\\
	public String decryptedMsg1(String encrypteddMsg1) throws Exception{
		String enc = Server.encrypt(encrypteddMsg1, savedKey());
		String dec = Server.decrypt(enc, savedKey());
		Window_1.display1_1.append(Window_2.username2 + " : " + dec + "\n");
		return dec;
	}
	public String encryptedMsg2(String inputedMsg) throws Exception{
		String enc = Server.encrypt(inputedMsg, savedKey());
		Window_1.display1.append(Window_2.username2 + " : " + enc + "\n");
		return enc;
	}
	//\\******//Something isn't working here? Need to figure out how to get a global variable of key to use//******//\\
	public String decryptedMsg2(String encrypteddMsg1) throws Exception{
		String enc = Server.encrypt(encrypteddMsg1, savedKey());
		String dec = Server.decrypt(enc, savedKey());
		Window_2.display2_2.append(Window_1.username1 + " : " + dec + "\n");
		return dec;
	}
	//Cipher Style
	//public static Cipher genCipher() throws Exception {
	//	Cipher cipher = Cipher.getInstance("AES");
	//	return cipher;
	//}
	//Secret Key Generator
	public static SecretKey savedKey() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // block size is 128bits
        SecretKey secretKey = keyGenerator.generateKey();
		return secretKey;
		
	}
	//Encryption Algorithm
	public static String encrypt(String s, SecretKey secretKey) 
			throws Exception{
		  byte[] plainTextByte = s.getBytes();
		  Cipher cipher = Cipher.getInstance("AES");
	      cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	      byte[] encryptedByte = cipher.doFinal(plainTextByte);
	      Base64.Encoder encoder = Base64.getEncoder();
	      String encryptedText = encoder.encodeToString(encryptedByte);
	      return encryptedText;
	    }
	//Decryption Algorithm
	public static String decrypt(String encryptedText, SecretKey secretKey)
        throws Exception {
	Base64.Decoder decoder = Base64.getDecoder();
    byte[] encryptedTextByte = decoder.decode(encryptedText);
	Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.DECRYPT_MODE, secretKey);
    //\\******//The cipher.update will grab something and display garble. compared to doFinal//******//\\
    byte[] decryptedByte = cipher.update(encryptedTextByte);
    String decryptedText = new String(decryptedByte);
    return decryptedText;
	}
}
