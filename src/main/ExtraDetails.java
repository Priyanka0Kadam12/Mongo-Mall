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
import javax.swing.JTextField;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class ExtraDetails extends JPanel{
	private static final long serialVersionUID = 1L;
	private static JFrame f;
	
	private JTextField tfQuantity;
	private JTextField tfBrandName;
	private JTextField tfProductname;
	private JTextField tfPrice;
	static JTextField tfProductnamekey;
	static JTextField tfQuantitykey;
	static JTextField tfBrandNamekey;
	static JTextField tfPricekey;
	String Product;
	private String sname;
	
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
			.getDefaultScreenDevice();
	int w = gd.getDisplayMode().getWidth();
	int h = gd.getDisplayMode().getHeight();
	
	public ExtraDetails(String productID,String shopname){
		this.Product=productID;
		this.sname=shopname;
		JLabel bak=new JLabel(new ImageIcon(Login.class.getResource("/images/loginpnl.jpg")));
		bak.setBackground(Color.WHITE);
		add(bak, BorderLayout.CENTER);
		bak.setLayout(null);

	
	tfProductnamekey = new JTextField();
	tfProductnamekey.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
	tfProductnamekey.setBounds(w/2-300, h/2-240, 152, 38);
	bak.add(tfProductnamekey);
	tfProductnamekey.setColumns(30);
	
	tfProductname = new JTextField();
	tfProductname.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
	tfProductname.setBounds(w/2, h/2-240, 152, 40);
	bak.add(tfProductname);
	tfProductname.setColumns(30);

	
	tfQuantitykey = new JTextField();
	tfQuantitykey.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
	tfQuantitykey.setBounds(w/2-300, h/2-170, 152, 38);
	bak.add(tfQuantitykey);
	tfQuantitykey.setColumns(10);
	
	tfQuantity = new JTextField();
	tfQuantity.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
	tfQuantity.setBounds(w/2, h/2-170, 152, 40);
	bak.add(tfQuantity);
	tfQuantity.setColumns(10);

	
	tfBrandNamekey = new JTextField();
	tfBrandNamekey.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
	tfBrandNamekey.setBounds(w/2-300, h/2-100, 152, 38);
	bak.add(tfBrandNamekey);
	tfBrandNamekey.setColumns(10);
	
	tfBrandName = new JTextField();
	tfBrandName.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
	tfBrandName.setBounds(w/2, h/2-100, 152, 40);
	bak.add(tfBrandName);
	tfBrandName.setColumns(10);
	
	
	tfPricekey = new JTextField();
	tfPricekey.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
	tfPricekey.setBounds(w/2-300, h/2-30,152, 38);
	bak.add(tfPricekey);
	tfPricekey.setColumns(10);
	
	tfPrice = new JTextField();
	tfPrice.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
	tfPrice.setBounds(w/2, h/2-30, 152, 40);
	bak.add(tfPrice);
	tfPrice.setColumns(10);
	
	
	JButton btnAddNewProduct = new JButton("Add to Product ");
	btnAddNewProduct.setBounds(w/2-20,h/2+150, 197, 43);
	bak.add(btnAddNewProduct);
	btnAddNewProduct.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			 DB dB = null;
				try {
					dB = (new MongoClient("localhost",27017)).getDB("smartmall");
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DBCollection dBCollection =dB.getCollection(sname);
				System.out.println("adding product into: "+sname);
				BasicDBObject searchQuery = new BasicDBObject();
				searchQuery.append("ProductID", Product);
				System.out.println("from extradetails: "+Product );
				if(tfProductnamekey.getText().equals("")==false && tfProductname.getText().equals("")==false){
					BasicDBObject updateQuery = new BasicDBObject();
					updateQuery.append("$set", 
						new BasicDBObject(tfProductnamekey.getText(), tfProductname.getText()));
							

					
					dBCollection.updateMulti(searchQuery, updateQuery);
				}
				if(tfQuantitykey.getText().equals("")==false && tfQuantity.getText().equals("")==false){
					BasicDBObject updateQuery = new BasicDBObject();
					updateQuery.append("$set", 
						new BasicDBObject(tfProductnamekey.getText(), tfProductname.getText()).
						   append(tfQuantitykey.getText(), tfQuantity.getText()));
					dBCollection.updateMulti(searchQuery, updateQuery);
				}
				
				if(tfBrandNamekey.getText().equals("")==false &&  tfBrandName.getText().equals("")==false){
					BasicDBObject updateQuery = new BasicDBObject();
					updateQuery.append("$set", 
						new BasicDBObject(tfProductnamekey.getText(), tfProductname.getText()).
						   append(tfBrandNamekey.getText(),  tfBrandName.getText()));
					dBCollection.updateMulti(searchQuery, updateQuery);
				}
				if(tfPricekey.getText().equals("")==false &&  tfPrice.getText().equals("")==false){
					BasicDBObject updateQuery = new BasicDBObject();
					updateQuery.append("$set", 
						new BasicDBObject(tfProductnamekey.getText(), tfProductname.getText()).
						   append(tfPricekey.getText(),  tfPrice.getText()));
					dBCollection.updateMulti(searchQuery, updateQuery);
				}
				/*BasicDBObject updateQuery = new BasicDBObject();
				updateQuery.append("$set", 
					new BasicDBObject(tfProductnamekey.getText(), tfProductname.getText()).
					   append(tfQuantitykey.getText(), tfQuantity.getText()).
			            append(tfBrandNamekey.getText(), tfBrandName.getText()).
			            append(tfPricekey.getText(), tfPrice.getText()));
			            
				BasicDBObject searchQuery = new BasicDBObject();
				searchQuery.append("ProductID", Product);
				System.out.println("from extradetails: "+Product );

				
				dBCollection.updateMulti(searchQuery, updateQuery);*/
				
				DBCursor dbCursor=dBCollection.find(); 
				while(dbCursor.hasNext())
					System.out.println(dbCursor.next().toString());	
			JOptionPane.showMessageDialog(null, "details Added succesfully");
			
				f.dispose();					
			
		}
	});
	btnAddNewProduct.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
	f.setSize(w,h);
	}
	public static void main(){
		create("","");
	}
	public static void create(String product,String shname){

		WindowUtil.setNimbusLook();
		
		f=new JFrame("Admin/User");
		f.setResizable(true);
		f.getContentPane().add( new ExtraDetails(product,shname));
		//WindowUtil.setToCenter(f, 1288, 740);
		//System.out.println(w+" "+h);
		f.setVisible(true);

	}
}
