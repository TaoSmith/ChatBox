import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	/**
	 * Launch the application.
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
		setBounds(100, 100, 393, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		display2 = new JTextArea();
		display2.setBounds(23, 48, 336, 344);
		contentPane.setLayout(null);
		contentPane.add(display2);
		
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
				display2.append(username2 + ":" + s + "\n");
				Window_1.sendText();
				text2.setText("");
			}
		});
		send2.setBounds(270, 403, 89, 45);
		contentPane.add(send2);
		
		label2 = new JLabel("Chat window for : " + username2);
		label2.setBounds(21, 25, 250, 14);
		contentPane.add(label2);
		
		JButton clear = new JButton("CLEAR");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				display2.setText("");
			}
		});
		clear.setBounds(270, 21, 89, 23);
		contentPane.add(clear);
		
	}

	public static void sendText()
	{
		String s = Window_1.text1.getText();
		if(s.equals("")) 
		{
			return;
		}
		display2.append(Window_1.username1 + ":" + s + "\n");
	}
	
	
	private javax.swing.JLabel label2;
	private static javax.swing.JTextArea display2;
	private javax.swing.JButton send2;
	public static javax.swing.JTextArea text2;
	
}