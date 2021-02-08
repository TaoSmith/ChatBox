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

public class Window_1 extends JFrame
{
	static String username1;
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
		setBounds(100, 100, 393, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		display1 = new JTextArea();
		display1.setBounds(23, 48, 336, 344);
		contentPane.setLayout(null);
		contentPane.add(display1);
		
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
				display1.append(username1 + ":" + s + "\n");
				Window_2.sendText();
				text1.setText("");
			}
		});
		send1.setBounds(270, 403, 89, 45);
		contentPane.add(send1);
		
		label1 = new JLabel("Chat window for : " + username1);
		label1.setBounds(21, 25, 250, 14);
		contentPane.add(label1);
		
		JButton clear = new JButton("CLEAR");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				display1.setText("");
			}
		});
		clear.setBounds(270, 21, 89, 23);
		contentPane.add(clear);
		
		//JTextArea display1 = new JTextArea();
		//display1.setBounds(23, 48, 336, 344);
		//contentPane.add(display1);
		
	}

	
	public static void sendText()
	{
		String s = Window_2.text2.getText();
		if(s.equals("")) 
		{
			return;
		}
		display1.append(Window_2.username2 + ":" + s + "\n");
	}

	
	private javax.swing.JLabel label1;
	private static javax.swing.JTextArea display1;
	private javax.swing.JButton send1;
	public static javax.swing.JTextArea text1;

}
