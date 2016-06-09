package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


public class ItemWindow extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	SelectedProductList sl;
	static boolean isAdmin;
	JScrollPane jsp,jsp1;
	
	
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
			.getDefaultScreenDevice();
	int w = gd.getDisplayMode().getWidth();
	int h = gd.getDisplayMode().getHeight();
	private static JFrame f;
	private JPanel pnl1;
	
	private QRReader wpl;
	private static ItemWindow item;
	private DisplayProduct displaypnl;
	static JTextField tfSearch;
	private JPanel searchbar;
	private JPanel display;
	private JTextField tfTotal;
	public static  String cus;
	
	private ViewSale vs;
	private ViewCustomer vc;
	//final static 
	
	public  void setBarcodeText(String code){
		System.out.println(code);
		tfSearch.setText(code);
	}
	public JFrame getF() {
		return f;
	}

	public void setF(JFrame f) {
		this.f = f;
	}
	JLabel bak;
	ViewProduct vp;
	private String sname;
	static int cost=0;
	public ItemWindow(String un,boolean isAdmin, String sn){
		/*JLabel bak=new JLabel(new ImageIcon(Login.class.getResource("/images/loginpnl.jpg")));
		bak.setBackground(Color.WHITE);
		add(bak, BorderLayout.CENTER);
		bak.setLayout(null);*/
		cus=un;
		setLayout(new BorderLayout());
		pnl1=new JPanel();
		jsp =new JScrollPane(pnl1);
		
		searchbar=new JPanel();
		
		wpl = new QRReader(this,null,null);
		
		vs=new ViewSale();
		vc=new ViewCustomer();
		sl=new SelectedProductList();
		jsp1=new JScrollPane(sl);
		this.isAdmin=isAdmin;
		this.sname=sn;
		vp=new ViewProduct(sname);
		
		displaypnl=new DisplayProduct();
		
		bak=new JLabel(new ImageIcon(ItemWindow.class.getResource("/images/loginpnl.jpg")));
		add(bak);
		
		JButton btnBack = new JButton("");
		//btnuser.setRolloverIcon(new ImageIcon(Login.class.getResource("/images/user1.png")));
		btnBack.setIcon(new ImageIcon(Login.class.getResource("/images/b1.png")));
		btnBack.setMnemonic('s');
		btnBack.setContentAreaFilled(false);
		//btnPur.setBorder(new TitledBorder(null, "login as user", TitledBorder.LEADING, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 20), Color.white));
		bak.add(btnBack);
		btnBack.setBounds(0,0,100,100);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.create();
				f.dispose();
			}
		});
	

		
		
		if(isAdmin){

		JLabel lbladmin = new JLabel("by admin "+un);
		lbladmin.setForeground(new Color(0, 255, 255));
		lbladmin.setFont(new Font("Kriscen ITC", Font.BOLD, 25));
		lbladmin.setBounds(30, 10, 400, 200);
		f.add(lbladmin);

		JButton btnViewCus = new JButton("View Customers");
		btnViewCus.setBounds(w/2-500,h/2-50, 197, 43);
		//bak.add(btnViewCus);
		btnViewCus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vc.setBounds(w/2-50, h/2-250, 600, 500);
				bak.add(vc);
				//ViewCustomer.create();
			}
		});
		btnViewCus.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		
		vs.setBounds(w/2-50, h/2-250, 600, 500);
		bak.add(vs);
		
		
		
		JButton btnViewSale = new JButton("View sale");
		btnViewSale.setBounds(w/2-500,h/2, 197, 43);
		//bak.add(btnViewSale);
		btnViewSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				vs.update();
				//ViewCustomer.create();
			}
		});
		btnViewSale.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		
		JButton btnAddUser = new JButton("Add Shop");
		btnAddUser.setBounds(w/2-500,h/2+100, 197, 43);
		bak.add(btnAddUser);
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					AddUser.create(false);
					
			}
		});
		btnAddUser.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));

		
		JButton btnAddAdmin = new JButton("Add Admin");
		btnAddAdmin.setBounds(w/2-500,h/2+200, 197, 43);
		bak.add(btnAddAdmin);
		btnAddAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					AddUser.create(true);
			}
		});
		btnAddAdmin.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		
		
		
		}else{
			
			
			
			JLabel lbladmin = new JLabel("by User "+un);
			lbladmin.setForeground(new Color(0, 255, 255));
			lbladmin.setFont(new Font("Kriscen ITC", Font.BOLD, 25));
			lbladmin.setBounds(30, 0, 400, 200);
			bak.add(lbladmin);
		
			
			JButton btnViewProduct = new JButton("View Products");
			btnViewProduct.setBounds(w/2-600,h/2, 197, 43);
			bak.add(btnViewProduct);
			btnViewProduct.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ViewProduct.create(sname);	
					//vp.setData(vp.bills());
				}
			});
			btnViewProduct.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		

		searchbar=new JPanel();
		tfSearch=new JTextField();
		tfSearch.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		tfSearch.setBounds(w/2-200,h-680,w/4+250,30);
		searchbar.add(tfSearch);
		tfSearch.setColumns(20);
		
		JButton btnSearchProduct = new JButton("Search");
		btnSearchProduct.setBounds(w/2-200,h-650,w/4+150,50);
		searchbar.add(btnSearchProduct);
		btnSearchProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					//displaypnl=new DisplayProduct(sname, tfSearch.getText());
					displaypnl.setData(displaypnl.productInfo(sname, tfSearch.getText()));
					System.out.println("from itemwindow :"+sname+"prID: "+tfSearch.getText());
				
					
					
			}
		});
		btnSearchProduct.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		
		JButton btnAddToList= new JButton("Add To List");
		btnAddToList.setBounds(w/2-120,h/2+250, 197, 43);
		bak.add(btnAddToList);
		btnAddToList.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				/*JLabel product=new JLabel(DisplayProduct.key1);
				product.setBounds(22,28,56,16);
				sl.add(product);
				JLabel price=new JLabel(DisplayProduct.value1);
				price.setBounds(88,28,56,16);
				sl.add(price);*/
				sl.setData(sl.saleProductInfo(DisplayProduct.value2,DisplayProduct.value5));
				cost=cost+Integer.parseInt(DisplayProduct.value5);
				System.out.println(" itemWindow: "+ DisplayProduct.value2+" "+DisplayProduct.value5);
				
				
				//new SelectedProductList(DisplayProduct.key1,DisplayProduct.value1);
				
			}		
		});
		btnAddToList.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		
		
		
		
		JButton btnAddProduct = new JButton("Add Product");
		btnAddProduct.setBounds(w/2-600,h/2+100, 197, 43);
		bak.add(btnAddProduct);
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					AddUpdateProduct.create(false,sname);
			}
		});
		btnAddProduct.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		
		JButton btnUpdateProduct = new JButton("Update Product");
		btnUpdateProduct.setBounds(w/2-600,h/2+200, 197, 43);
		bak.add(btnUpdateProduct);
		btnUpdateProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateProduct.create(sname);
			}
		});
		btnUpdateProduct.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		

		
		JButton btnLoginAsExpert = new JButton("Print Bill");
		btnLoginAsExpert.setBounds(w/2+260,h/2+250, 197, 43);
		bak.add(btnLoginAsExpert);
		btnLoginAsExpert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String customernm=JOptionPane.showInputDialog("Enter Customer Name");
	
				System.out.println("customer name : "+customernm);
				
				DB dB = null;
				try {
					
					dB = (new MongoClient("localhost",27017)).getDB("smartmall");
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				
				DBCollection dBCollection =dB.getCollection("users");
				String shnm="";
				
				BasicDBObject saler=new BasicDBObject();
				saler.put("UserName", cus);
				DBCursor cursor=dBCollection.find(saler);
				while(cursor.hasNext()){
					DBObject obj=cursor.next();
					shnm=(String) obj.get("ShopName");
				}
				DBCollection collection =dB.getCollection("sale");
				
				BasicDBObject sale=new BasicDBObject("LastUpdate",date.toString())
						.append("ShopName", shnm)
						.append("UserName",cus)
						.append("TotalSale",cost);
				
				BasicDBObject saler1=new BasicDBObject();
				saler1.put("UserName", cus);
				DBCursor dc=collection.find(saler1);
				System.out.println("record present result: "+ dc.count());
				if(dc.count()==1){
					BasicDBObject newDocument =
							new BasicDBObject().append("$inc",
							new BasicDBObject().append("TotalSale", cost));
					BasicDBObject searchQuery = new BasicDBObject().append("UserName", cus);
					collection.update(searchQuery, newDocument);
				}
				else{
						collection.insert(sale);
				}
				DBCollection saleDetails=dB.getCollection(cus);
				System.out.println(cus);
				BasicDBObject price=new BasicDBObject("date",date.toString())
						.append("ShopName", shnm)
						.append("UserName",cus)
						.append("ProductList",SelectedProductList.str)
						.append("ProductBill",cost);
				
				saleDetails.insert(price);
				JavaPdfHelloWorld.createDocument("BILL BY " +cus+" TO "+customernm);
				if(customernm.equals("")==false){
				JOptionPane.showMessageDialog(null, "Report Successfully generated");
				}
			}
		});
		btnLoginAsExpert.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		
		JButton btnTotal = new JButton("Total");
		btnTotal.setBounds(w/2+200,h/2+190, 80, 43);
		bak.add(btnTotal);
		btnTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfTotal.setText(""+cost);
			}
		});
		btnTotal.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		
		tfTotal=new JTextField();
		tfTotal.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		tfTotal.setBounds(w/2+280,h/2+190,220,43);
		bak.add(tfTotal);
		tfTotal.setColumns(20);
		tfTotal.setEditable(false);
		
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(w/2+220, h-700, 280, 50);
		bak.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sl.setData("");
				displaypnl.setData("");
				tfSearch.setText("");
				tfTotal.setText("");
			}
		});
		btnClear.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		
		
		//middle.setBounds(w/2-200,h-700,w/4+50,h/2-100);
				wpl.setBounds(w/2-650,h-650,w/4,h/3-20);
				searchbar.setBounds(w/2-200,h-700,w/4,90);
				displaypnl.setBounds(w/2-200, h-600, w/4, 450);
				jsp1.setBounds(w/2+200, h-600, 300, 400);

				bak.setLayout(null);
				
				bak.add(searchbar);
				bak.add(displaypnl);
				bak.add(wpl);
				bak.add(jsp1);
				//f.getContentPane().add(sl);
			
		}
		
		
		
			
	}
	
	public static void addListenerForFrame(JFrame f) {
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				
				int res=JOptionPane.showConfirmDialog(null, "Do you want to Exit ?");
				if(res==0){
					//f.dispose();
						System.exit(0);	
				}
				
			}
		});
		f.addWindowFocusListener(new WindowAdapter() {
		    public void windowGainedFocus(WindowEvent e) {
		    	if(isAdmin==false){
		    	item.wpl.initDSCapture();
		    	}
		    	else{
		    		System.out.println("user login");
		    	}
		    }
		    public void windowLostFocus(WindowEvent e){
		    	if(isAdmin==false){
		    	item.wpl.getGraph().dispose();
		    	item.wpl.msg.setVisible(true);
		    	}
		    	else{
		    		item.wpl.getGraph().dispose();
			    	item.wpl.msg.setVisible(true);
		    		System.out.println("user login");
		    	}
		    }
		    
		});
	}
	protected static void create(String un,boolean isAdmin,String sn) {
		f=new JFrame("Sale To Customers");
		f.setBounds(100, 10, 1200, 700);
		item = new ItemWindow(un, isAdmin, sn);
		f.add(item);
		WindowUtil.setNimbusLook();
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		int w = gd.getDisplayMode().getWidth();
		int h = gd.getDisplayMode().getHeight();
		f.setSize(w,h);
		WindowUtil.setToCenter(f,w,h);
		f.setVisible(true);
		addListenerForFrame(f);
	
	}
}
