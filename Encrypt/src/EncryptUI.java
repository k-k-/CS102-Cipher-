import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;


public class EncryptUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField shiftFactor;
	private JTextArea inputTA;
	private JTextArea outputTA;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EncryptUI frame = new EncryptUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EncryptUI() {
		  
		  setTitle("Encryption Demo");
          setVisible(true);
          setDefaultCloseOperation(3);
          setBackground(Color.BLACK);
          Container content = getContentPane();
          content.setBackground(Color.YELLOW);
          GridLayout layout = new GridLayout(3, 0, 0, 10);
          content.setLayout(layout);

          inputTA = new JTextArea("Insert text to encrypt! Yay!", 12, 40);
          inputTA.setLineWrap(true);
          inputTA.setWrapStyleWord(true);
          inputTA.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
          inputTA.setBackground(Color.black);
          inputTA.setForeground(Color.GREEN);
          JScrollPane scroller = new JScrollPane(inputTA);
          scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
          content.add(scroller);
         
          outputTA = new JTextArea("Output will appear here.",12, 40);
          outputTA.setLineWrap(true);
          outputTA.setWrapStyleWord(true);
          outputTA.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
          outputTA.setBackground(Color.black);
          outputTA.setForeground(Color.green);
          JScrollPane scroller2 = new JScrollPane(outputTA);
          scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
          content.add(scroller2);
          JLabel s2 = new JLabel("");
          s2.setForeground(Color.BLACK);
          JPanel box1 = new JPanel();
          box1.setBackground(Color.LIGHT_GRAY);
          box1.setLayout(new FlowLayout());
          JButton decryptButton = new JButton("Decrypt");
          JButton encryptButton = new JButton("Encrypt");
          decryptButton.addActionListener( this);
          encryptButton.addActionListener(this);
          box1.add(decryptButton);
          box1.add(encryptButton);
          box1.add(s2);
        
          content.add(box1);
          
         
          pack();
	}
	
	 @Override
     public void actionPerformed(ActionEvent e) {
             if(e.getActionCommand().equals("Encrypt")){
                     try{
                            String s = Encrypt.in(inputTA.getText().toString());
                            System.out.println(s);
                            outputTA.setText(s);
                     }catch(Exception e1){
                    	 System.out.println(e1);
                     }
             }
             if (e.getActionCommand().equals("Decrypt")){
                   try {
                     String s = Encrypt.out(inputTA.getText().toString());
                     outputTA.setText(s);
                   } catch (Exception e1) {
                     e1.printStackTrace();
                   }
	 }
     
	 }
	

}
