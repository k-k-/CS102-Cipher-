import java.awt.BorderLayout;
import java.sql.Statement;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Future;

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
	public int colno = 1;
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
          JButton decryptButton = new JButton("Pull and Decrypt");
          JButton encryptButton = new JButton("Encrypt and Send");
          decryptButton.addActionListener( this);
          encryptButton.addActionListener(this);
          box1.add(decryptButton);
          box1.add(encryptButton);
          box1.add(s2);
        
          content.add(box1);
          
         
          pack();
	}
	
	public void connectToAndWriteDatabase(String tosend) throws SQLException {

	    Connection con;
		try {
			String uname = "User2";
			String pass = "Passcode347";
			String url = "jdbc:sqlserver://WIN-CHTQ8NAD1NL\\SQLEXPRESS;databaseName=Chat";
			Class.forName ("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("1");
			con = DriverManager.getConnection(url,uname,pass);
			System.out.println("2");
		    Statement stmt = con.createStatement();
		    stmt.execute("INSERT INTO dbo.MSG VALUES ('" + tosend + "')");
		    colno++;
		 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public String connectToAndReadDatabase() throws SQLException{
		Connection con;
		String s = "";
		ArrayList<String> s2 = new ArrayList<String>();
		try{
			String uname = "User2";
			String pass = "Passcode347";
			String url = "jdbc:sqlserver://WIN-CHTQ8NAD1NL\\SQLEXPRESS;databaseName=Chat";
			Class.forName ("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("1");
			con = DriverManager.getConnection(url,uname,pass);
			System.out.println("2");
		    Statement stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery("SELECT * FROM dbo.MSG");
		    
		    while (rs.next()){
		    	s2.add(rs.getString("Messages"));
		    }
		    

		}
		catch (SQLException e){
			e.printStackTrace();
		}
		 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int ww = s2.size();
		return s2.get(ww-1);
		
	}
	
	 @Override
     public void actionPerformed(ActionEvent e) {
             if(e.getActionCommand().equals("Encrypt and Send")){
                     try{
                    	 String s = Encrypt.in(inputTA.getText().toString());
                    	 connectToAndWriteDatabase(s);
                     }catch(Exception e1){
                    	 System.out.println(e1);
                     }
             }
             if (e.getActionCommand().equals("Pull and Decrypt")){
                   try {
                     String s = connectToAndReadDatabase();
                     outputTA.setText(Encrypt.out(s));
                   } catch (Exception e1) {
                     e1.printStackTrace();
                   }
	 }
     
	 }
	

}