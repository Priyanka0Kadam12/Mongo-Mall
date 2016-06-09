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

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class AddUser extends JPanel{
	
	private static JFrame f;
	//private boolean isAdmin;
	private JTextField tfShopname;
	private JTextField tfUsername;
	private JTextField tfFirstName;
	private JTextField tfName;
	private JPasswordField passwordField;
	private JPasswordField repasswordField;
	
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
			.getDefaultScreenDevice();
	int w = gd.getDisplayMode().getWidth();
	int h = gd.getDisplayMode().getHeight();
	
	public AddUser(boolean isAdmin){
		
		JLabel bak=new JLabel(new ImageIcon(Login.class.getResource("/images/loginpnl.jpg")));
		bak.setBackground(Color.WHITE);
		add(bak, BorderLayout.CENTER);
		bak.setLayout(null);
		
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
		add(btnBack);
		btnBack.setBounds(0,0,100,100);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.create();
				f.dispose();
			}
		});
		
		if(!isAdmin){
		JLabel lblShopName = new JLabel("Shop Name");
		lblShopName.setBounds(w/2-300, h/2-300, 150, 38);
		bak.add(lblShopName);
		lblShopName.setFont(new Font("Monotype Corsiva", Font.BOLD, 30));
		lblShopName.setForeground(Color.WHITE);
		
		tfShopname = new JTextField();
		tfShopname.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		tfShopname.setBounds(w/2, h/2-300, 152, 40);
		bak.add(tfShopname);
		tfShopname.setColumns(10);
		}
		
		JLabel lblUsername = new JLabel("Username ");
		lblUsername.setBounds(w/2-300, h/2-240, 150, 38);
		bak.add(lblUsername);
		lblUsername.setFont(new Font("Monotype Corsiva", Font.BOLD, 30));
		lblUsername.setForeground(Color.WHITE);
		
		tfUsername = new JTextField();
		tfUsername.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		tfUsername.setBounds(w/2, h/2-240, 152, 40);
		bak.add(tfUsername);
		tfUsername.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name ");
		lblFirstName.setBounds(w/2-300, h/2-170, 155, 38);
		bak.add(lblFirstName);
		lblFirstName.setFont(new Font("Monotype Corsiva", Font.BOLD, 30));
		lblFirstName.setForeground(Color.WHITE);
		
		tfFirstName = new JTextField();
		tfFirstName.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		tfFirstName.setBounds(w/2, h/2-170, 152, 40);
		bak.add(tfFirstName);
		tfFirstName.setColumns(10);
		
		JLabel lblName = new JLabel("Last Name ");
		lblName.setBounds(w/2-300, h/2-100, 150, 38);
		bak.add(lblName);
		lblName.setFont(new Font("Monotype Corsiva", Font.BOLD, 30));
		lblName.setForeground(Color.WHITE);
		
		tfName = new JTextField();
		tfName.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		tfName.setBounds(w/2, h/2-100, 152, 40);
		bak.add(tfName);
		tfName.setColumns(10);
		
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(w/2-300, h/2-30, 150, 38);
		bak.add(lblPassword);
		lblPassword.setFont(new Font("Monotype Corsiva", Font.BOLD, 30));
		lblPassword.setForeground(Color.WHITE);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		passwordField.setBounds(w/2, h/2-30, 152, 40);
		bak.add(passwordField);

		JLabel lblRetypePassword = new JLabel("Retype Password");
		lblRetypePassword.setBounds(w/2-300, h/2+30, 300, 38);
		bak.add(lblRetypePassword);
		lblRetypePassword.setFont(new Font("Monotype Corsiva", Font.BOLD, 30));
		lblRetypePassword.setForeground(Color.WHITE);
		
		repasswordField = new JPasswordField();
		repasswordField.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		repasswordField.setBounds(w/2, h/2+30, 152, 40);
		bak.add(repasswordField);

		
		
		if(isAdmin){
		JButton btnUpdateUser = new JButton("Add Admin");
		btnUpdateUser.setBounds(w/2-20,h/2+150, 197, 43);
		bak.add(btnUpdateUser);
		btnUpdateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(passwordField.getText().equals(repasswordField.getText())){
					 DB dB = null;
						try {
							dB = (new MongoClient("localhost",27017)).getDB("smartmall");
						} catch (UnknownHostException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						DBCollection dBCollection =dB.getCollection("users");
						
						BasicDBObject user = new BasicDBObject("UserName", tfUsername.getText()).
					            append("FirstName", tfFirstName.getText()).
					            append("LastName", tfName.getText()).
					            append("Password", passwordField.getText()).
					            append("RePassword", repasswordField.getText());
						
						user.put("Admin","true");
					

						dBCollection.insert(user);
						DBCursor dbCursor=dBCollection.find(user); 
						while(dbCursor.hasNext())
							System.out.println(dbCursor.next().toString());	
						
						f.dispose();
					
				}
				else
					{
						JOptionPane.showMessageDialog(null,  "Passward not match");
					}
					
				
				
			}
		});
		btnUpdateUser.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		}else{
			
			
			JButton btnCreateUser = new JButton("Create User");
			btnCreateUser.setBounds(w/2-20,h/2+150, 197, 43);
			bak.add(btnCreateUser);
			btnCreateUser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(passwordField.getText().equals(repasswordField.getText())){
					 DB dB = null;
						try {
							/*MongoClientURI uri  = new MongoClientURI("mongodb://priyanka:2013BCS079@ds011281.mlab.com:11281/smartmall");  
						 MongoClient client = new MongoClient(uri); 

							dB = client.getDB(uri.getDatabase());*/
							dB = (new MongoClient("localhost",27017)).getDB("smartmall");
						} catch (UnknownHostException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						dB.createCollection(tfShopname.getText(),null);
						DBCollection dBCollection =dB.getCollection("users");
						
						//BasicDBObject basicDBObject=new BasicDBObject();
						//basicDBObject.put("name","smartmall");
						BasicDBObject user = new BasicDBObject("ShopName", tfShopname.getText()).
								append("UserName", tfUsername.getText()).
					            append("FirstName", tfFirstName.getText()).
					            append("LastName", tfName.getText()).
					            append("Password", passwordField.getText()).
					            append("RePassword", repasswordField.getText());
						user.put("Admin","false");
						dBCollection.insert(user);
						dB.createCollection(tfShopname.getText(),null);
						dB.createCollection(tfUsername.getText(),null);
						
						DBCursor dbCursor=dBCollection.find(user); 
						while(dbCursor.hasNext())
							System.out.println(dbCursor.next().toString());	
						
						f.dispose();
					
				}
				else
					{
						JOptionPane.showMessageDialog(null,  "Passward not match");
					}
				}
				
			});
			btnCreateUser.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
			
		}
		
		f.setSize(w,h);
	}
	public static void main(){
		create(false);
	}
	public static void create(boolean update){

		WindowUtil.setNimbusLook();
		
		f=new JFrame("Admin/User");
		f.setResizable(true);
		f.getContentPane().add( new AddUser(update));
		WindowUtil.setToCenter(f, 1190, 650);
		//System.out.println(w+" "+h);
		f.setVisible(true);

	}
}
