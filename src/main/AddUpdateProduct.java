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
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import java.lang.Object;



public class AddUpdateProduct extends JPanel {
	
	/**
	 * 
	 */
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
	private ScanBarcode addproduct;
	
	static JLabel lblProductID;
	static JLabel lblProductname;
	static JLabel lblQuantity;
	static JLabel lblBrandName;
	static JLabel lblPrice;
	static JLabel lblBarcode;
	//JLabel lblProductID ;
	
	//WebcamQRCodepnl wc=new WebcamQRCodepnl();
	
	
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
			.getDefaultScreenDevice();
	int w = gd.getDisplayMode().getWidth();
	int h = gd.getDisplayMode().getHeight();
	public AddUpdateProduct(){
		
	}
	public AddUpdateProduct(boolean isupdate,String shopname){
		
		JLabel bak=new JLabel(new ImageIcon(Login.class.getResource("/images/loginpnl.jpg")));
		bak.setBackground(Color.WHITE);
		add(bak, BorderLayout.CENTER);
		bak.setLayout(null);
		this.sname=shopname;
		

		
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
		
		bak.add(btnBack);
		btnBack.setBounds(0,0,100,100);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.create();
				f.dispose();
			}
		});
		
		
		
		
		if(isupdate){
			for (int i=0;i<5;i++){
			lblProductID = new JLabel("ProductID ");
			lblProductID.setBounds(w/2-300, h/2-310, 300, 38);
			bak.add(lblProductID);
			lblProductID.setFont(new Font("Monotype Corsiva", Font.BOLD, 30));
			lblProductID.setForeground(Color.WHITE);
			//JTextField String s="tfProduct"+i;
			 
			tfProductID = new JTextField();
			tfProductID.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
			tfProductID.setBounds(w/2, h/2-310, 152, 40);
			bak.add(tfProductID);
			tfProductID.setColumns(10);
			}
			
		JButton btnUpdateUser = new JButton("Update Product Details");
		btnUpdateUser.setBounds(w/2-20,h/2+150, 207, 43);
		bak.add(btnUpdateUser);
		btnUpdateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				f.dispose();
					
				
				
			}
		});
		btnUpdateUser.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		}
		else{
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
		

			
			
			JButton btnAddNewProduct = new JButton("Add New Product ");
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
						 //DBObject query = new BasicDBObject("productID", new BasicDBObject("$exists", true));
						BasicDBObject searchQuery = new BasicDBObject();
						searchQuery.append("ProductID",tfProductID.getText());
						System.out.println("serchquery : "+searchQuery);
						
						
						
						// DBObject query=dB.dBCollection.find( {  ProductID : tfProductID.getText()} );
						//DBCursor result = dBCollection.find(query);
						//System.out.println("searchQuery result: "+result);
						
						
						
						// if(result){
						BasicDBObject product = new BasicDBObject("ProductID", tfProductID.getText()).
								append("ProductName", tfProductname.getText()).
					            append("Quantity", tfQuantity.getText()).
					            append("Company", tfBrandName.getText()).
					            append("Price", tfPrice.getText()).
					            append("Barcode", tfBarcode.getText());
					            
					     
						dBCollection.insert(product);
						
						DBCursor dbCursor=dBCollection.find(product); 
						while(dbCursor.hasNext())
							System.out.println(dbCursor.next().toString());	
						
						int res=JOptionPane.showConfirmDialog(null, "Do you want add extra Details ?");
						if(res==0){
							ExtraDetails.create(tfProductID.getText(),sname);
							f.dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "details Added succesfully");
							
							f.dispose();	
							//System.exit(0);
							
						}
					}
										
				//}
				
			});
			btnAddNewProduct.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
			

			
		/*	JButton btnAddExtraDetails = new JButton("Add Extra details ");
			btnAddExtraDetails.setBounds(w/2-400,h/2+150, 197, 43);
			bak.add(btnAddExtraDetails);
			btnAddExtraDetails.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					new ExtraDetails(tfProductID.getText());
					
					
				}
			});
			btnAddExtraDetails.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
			*/
		}
		
		//f.setSize(w,h);
	}
	public void setproBarcodeText(String code){
		tfBarcode.setText(code);
		addproduct.close();
	}
	
	public static void main(){
		create(false,"new shop");
	}
	public static void create(boolean update,String shop){

		WindowUtil.setNimbusLook();
		
		f=new JFrame("Select Login");
		f.setResizable(true);
		f.getContentPane().add( new AddUpdateProduct(update,shop));
		WindowUtil.setToCenter(f, 1190, 650);
		//System.out.println(w+" "+h);
		f.setVisible(true);

	}



}
