package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class LoginPage extends JPanel{
	private static JFrame f;
	private boolean isAdmin;
	public static String  shopname;
	private JTextField tfUsername;
	private JPasswordField passwordField;
	
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
			.getDefaultScreenDevice();
	int w = gd.getDisplayMode().getWidth();
	int h = gd.getDisplayMode().getHeight();
	
	public LoginPage(boolean Admin){
		this.isAdmin=Admin;
		JLabel bak=new JLabel(new ImageIcon(Login.class.getResource("/images/loginpnl.jpg")));
		bak.setBackground(Color.WHITE);
		add(bak, BorderLayout.CENTER);
		bak.setLayout(null);
		shopname="";
		/*JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBackground(Color.WHITE);
		panel.setBorder(UIManager.getBorder("TitledBorder.border"));
		panel.setBounds(143, 153, 491, 297);
		bak.add(panel);
		panel.setLayout(null);*/
		
		JButton btnBack = new JButton("");
		//btnuser.setRolloverIcon(new ImageIcon(Login.class.getResource("/images/user1.png")));
		btnBack.setIcon(new ImageIcon(Login.class.getResource("/images/b1.png")));
		btnBack.setMnemonic('s');
		btnBack.setContentAreaFilled(false);
		//btnPur.setBorder(new TitledBorder(null, "login as user", TitledBorder.LEADING, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 20), Color.white));
		f.add(btnBack);
		btnBack.setBounds(0,0,100,100);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.create();
				f.dispose();
			}
		});
		
		
		JLabel lblUsername = new JLabel("Username ");
		lblUsername.setBounds(w/2-200, h/2-100, 150, 38);
		bak.add(lblUsername);
		lblUsername.setFont(new Font("Monotype Corsiva", Font.BOLD, 30));
		lblUsername.setForeground(Color.WHITE);
		
		tfUsername = new JTextField();
		tfUsername.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		tfUsername.setBounds(w/2, h/2-100, 152, 40);
		bak.add(tfUsername);
		tfUsername.setColumns(10);
		
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(w/2-200, h/2-30, 150, 38);
		bak.add(lblPassword);
		lblPassword.setFont(new Font("Monotype Corsiva", Font.BOLD, 30));
		lblPassword.setForeground(Color.WHITE);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		passwordField.setBounds(w/2, h/2-30, 152, 40);
		bak.add(passwordField);
		
		

		JButton btnLoginAsExpert = new JButton("Login");
		btnLoginAsExpert.setBounds(w/2-20,h/2+70, 197, 43);
		bak.add(btnLoginAsExpert);
		btnLoginAsExpert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkValidUser(isAdmin)){
					ItemWindow.create(tfUsername.getText(),isAdmin,shopname);
					f.dispose();
				}
				else{
					//new ItemWindow(tfUsername.getText(),false);
					JOptionPane.showMessageDialog(null, "Username/Password is incorrect");
				}
			}
		});
		btnLoginAsExpert.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		
		f.setSize(w,h);

	}
public boolean checkValidUser(boolean isAdmin) {
		
		
		boolean b=true;
		String username=tfUsername.getText();
		
		 DB dB = null;
			try {
				dB = (new MongoClient("localhost",27017)).getDB("smartmall");
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			DBCollection users =dB.getCollection("users");
			
		//BasicDBObject findQuery = new BasicDBObject("UserName", new BasicDBObject("$get",username));
		//DBObject docs = (DBObject) users.find(findQuery);
		//String password=(String)docs.get("Password");
		String pass=new String(passwordField.getPassword());
		
		//String myCursor =dB.users.find( { UserName: tfUsername.getText() } );
		
		for( DBObject dock : users.find() ){
			String name = (String) dock.get( "UserName" );
			
			String password = (String) dock.get( "Password" );
			
			String admin =(String) dock.get("Admin");
			if(name.equals(username)){
			if(admin.equals("true")){
			System.out.println("*"+name+"*"+password+"*"+admin);
			if(name.equals(username)&& admin.equals("true")){
				if(pass.equals(password))
				{	
					System.out.println("matched");
							b=true;					
				}				
			}
		}
		else{
				
				shopname=(String) dock.get("ShopName");
				System.out.println("*"+name+"*"+password+"*"+"*"+shopname+"*"+admin);
				if(name.equals(username)&& admin.equals("false")){
					if(pass.equals(password))
					{	
						System.out.println("matched");
								b=true;					
					}				
				}
		}
			}
		}
		
		
		
		
		return b;
		
	}
	public static String getShopName(){
		return shopname;
	}
	
	
	
	public static void create(boolean isAdmin){

		WindowUtil.setNimbusLook();
		
		f = new JFrame("Login");
		f.setResizable(true);
		f.getContentPane().add( new LoginPage(isAdmin));
		//WindowUtil.setToCenter(f, 1288, 740);
		//System.out.println(w+" "+h);
		f.setVisible(true);

	}
}
