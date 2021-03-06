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
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class UpdateProduct extends JPanel {
	private static final long serialVersionUID = 1L;
	private static JFrame f;
	//private boolean isAdmin;
	private JTextField tfProductID;
	private JTextField tfQuantity;
	private JTextField tfBrandName;
	private JTextField tfProductname;
	private JTextField tfPrice;
	public static JTextField tfBarcode;
	private String sname;
	
	static JLabel lblProductID;
	static JLabel lblProductname;
	static JLabel lblQuantity;
	static JLabel lblBrandName;
	static JLabel lblPrice;
	static JLabel lblBarcode;

	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
			.getDefaultScreenDevice();
	int w = gd.getDisplayMode().getWidth();
	int h = gd.getDisplayMode().getHeight();
	
	public void setTfBarcode(String data){
		tfBarcode.setText(data);
	}
	public UpdateProduct(){}
	public UpdateProduct(String shopname){
		JLabel bak=new JLabel(new ImageIcon(Login.class.getResource("/images/loginpnl.jpg")));
		bak.setBackground(Color.WHITE);
		add(bak, BorderLayout.CENTER);
		bak.setLayout(null);
		this.sname=shopname;
		
		
		lblProductID = new JLabel("ProductID ");
		lblProductID.setBounds(w/2-300, h/2-310, 300, 38);
		bak.add(lblProductID);
		lblProductID.setFont(new Font("Monotype Corsiva", Font.BOLD, 30));
		lblProductID.setForeground(Color.WHITE);
		
		tfProductID = new JTextField();
		tfProductID.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		tfProductID.setBounds(w/2, h/2-310, 152, 40);
		bak.add(tfProductID);
		tfProductID.setColumns(10);
		
		
		lblProductname = new JLabel("Product name ");
		lblProductname.setBounds(w/2-300, h/2-240, 300, 38);
		bak.add(lblProductname);
		lblProductname.setFont(new Font("Monotype Corsiva", Font.BOLD, 30));
		lblProductname.setForeground(Color.WHITE);
		
		tfProductname = new JTextField();
		tfProductname.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		tfProductname.setBounds(w/2, h/2-240, 152, 40);
		bak.add(tfProductname);
		tfProductname.setColumns(30);
		
		lblQuantity = new JLabel("Quantity ");
		lblQuantity.setBounds(w/2-300, h/2-170, 300, 38);
		bak.add(lblQuantity);
		lblQuantity.setFont(new Font("Monotype Corsiva", Font.BOLD, 30));
		lblQuantity.setForeground(Color.WHITE);
		
		tfQuantity = new JTextField();
		tfQuantity.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		tfQuantity.setBounds(w/2, h/2-170, 152, 40);
		bak.add(tfQuantity);
		tfQuantity.setColumns(10);
		
		lblBrandName = new JLabel("Company ");
		lblBrandName.setBounds(w/2-300, h/2-100, 300, 38);
		bak.add(lblBrandName);
		lblBrandName.setFont(new Font("Monotype Corsiva", Font.BOLD, 30));
		lblBrandName.setForeground(Color.WHITE);
		
		tfBrandName = new JTextField();
		tfBrandName.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		tfBrandName.setBounds(w/2, h/2-100, 152, 40);
		bak.add(tfBrandName);
		tfBrandName.setColumns(10);
		
		lblPrice = new JLabel("Price ");
		lblPrice.setBounds(w/2-300, h/2-30, 300, 38);
		bak.add(lblPrice);
		lblPrice.setFont(new Font("Monotype Corsiva", Font.BOLD, 30));
		lblPrice.setForeground(Color.WHITE);
		
		tfPrice = new JTextField();
		tfPrice.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		tfPrice.setBounds(w/2, h/2-30, 152, 40);
		bak.add(tfPrice);
		tfPrice.setColumns(10);
		
		lblBarcode = new JLabel("Barcode:");
		lblBarcode.setFont(new Font("Monotype Corsiva", Font.BOLD, 30));
		lblBarcode.setBounds(w/2-300,h/2+30, 150, 30);
		lblBarcode.setForeground(Color.WHITE);
		bak.add(lblBarcode);
		
		tfBarcode = new JTextField();
		tfBarcode.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		tfBarcode.setBounds(w/2, h/2+30, 300, 40);
		bak.add(tfBarcode);
		
		JButton brcodeScan = new JButton("");
		brcodeScan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScanBarcode.create();
			}
		});
		brcodeScan.setIcon(new ImageIcon(AddUpdateProduct.class.getResource("/images/barcode.png")));
		brcodeScan.setBounds(w/2-150, h/2+30, 100, 50);
		bak.add(brcodeScan);
		
		JButton btnSearchProduct = new JButton("Search");
		btnSearchProduct.setBounds(w/2-250,h/2+150, 100, 43);
		bak.add(btnSearchProduct);
		btnSearchProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 DB dB = null;
				 try {
						dB = (new MongoClient("localhost",27017)).getDB("smartmall");
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					DBCollection dBCollection =dB.getCollection(sname);
					BasicDBObject searchQuery = new BasicDBObject().append("ProductID",tfProductID.getText());
					DBCursor cursor = dBCollection.find(searchQuery);
					
					
					if(cursor==null){
							JOptionPane.showMessageDialog(null, "details not found");
						
						f.dispose();
					}
					else{
					while(cursor.hasNext())
					{
						DBObject dok=cursor.next();
						tfProductname.setText((String)dok.get("ProductName"));
						tfQuantity.setText((String)dok.get("Quantity"));
						tfBrandName.setText((String)dok.get("Company"));
						tfPrice.setText((String)dok.get("Price"));
						tfBarcode.setText((String)dok.get("Barcode"));
					}
					}
			}
		});
		btnSearchProduct.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));	
		
		JButton btnAddNewProduct = new JButton("Update");
		btnAddNewProduct.setBounds(w/2-50,h/2+150, 100, 43);
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
					
					
					BasicDBObject searchQuery = new BasicDBObject().append("ProductID",tfProductID.getText());
					
					
					
				
					BasicDBObject product = new BasicDBObject();
					product.append("$set",new BasicDBObject().append("ProductID", tfProductID.getText()).
							append("ProductName", tfProductname.getText()).
				            append("Quantity", tfQuantity.getText()).
				            append("Company", tfBrandName.getText()).
				            append("Price", tfPrice.getText()).
				            append("Barcode", tfBarcode.getText()));
				            
					
					dBCollection.update(searchQuery, product);
					
					JOptionPane.showMessageDialog(null, "details Update succesfully");
					
					f.dispose();
					/*int res=JOptionPane.showConfirmDialog(null, "Do you want add extra Details ?");
					if(res==0){
						ExtraDetails.create(tfProductID.getText(),sname);
						f.dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "details Added succesfully");
						
						f.dispose();	
						//System.exit(0);
						
					}*/
				}
									
			//}
			
		});
		btnAddNewProduct.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		
		
		JButton btnDeleteProduct = new JButton("Delete");
		btnDeleteProduct.setBounds(w/2+250,h/2+150, 100, 43);
		bak.add(btnDeleteProduct);
		btnDeleteProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 DB dB = null;
				 try {
						dB = (new MongoClient("localhost",27017)).getDB("smartmall");
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					DBCollection dBCollection =dB.getCollection(sname);
					BasicDBObject searchQuery = new BasicDBObject().append("ProductID",tfProductID.getText());
					DBCursor cursor = dBCollection.find(searchQuery);
					while(cursor.hasNext())
					{
						DBObject dok=cursor.next();
						tfProductname.setText("");
						tfQuantity.setText("");
						tfBrandName.setText("");
						tfPrice.setText("");
						tfBarcode.setText("");
					}
					dBCollection.remove(searchQuery);
					JOptionPane.showMessageDialog(null, "details Deleted succesfully");
					
					f.dispose();
			}
		});
		btnDeleteProduct.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));	
		

	}
	
	
	
	
	public static void main(){
		create("new shop");
	}
	public static void create(String shop){

		WindowUtil.setNimbusLook();
		
		f=new JFrame("Select Login");
		f.setResizable(true);
		f.getContentPane().add( new UpdateProduct(shop));
		WindowUtil.setToCenter(f, 1190, 650);
		//System.out.println(w+" "+h);
		f.setVisible(true);

	}

}
