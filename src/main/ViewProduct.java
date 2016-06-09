package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class ViewProduct extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
	private static JFrame f;
	static String sname,doc;
	private JTextArea tdata;
	private JTextField tfProductID;
	private static JTable table;
	
	
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
			.getDefaultScreenDevice();
	int w = gd.getDisplayMode().getWidth();
	int h = gd.getDisplayMode().getHeight();
	
	
	public ViewProduct(String shopnm){
		JLabel bak=new JLabel(new ImageIcon(ViewProduct.class.getResource("/images/loginpnl.jpg")));
		bak.setBackground(Color.WHITE);
		add(bak, BorderLayout.CENTER);
		bak.setLayout(null);
		setLayout(new BorderLayout());
		table = new JTable(){
			@Override
			public Dimension getPreferredScrollableViewportSize() {
			return new Dimension(300, 150);
			}
			};
			add(new JScrollPane(table));
			
		
		/*tdata=new JTextArea("Product Details:");
		tdata.setCaretPosition(0);
		tdata.setEditable(false);
		tdata.setFont(new Font("SansSerif", Font.BOLD, 12));
		add(new JScrollPane(tdata));*/
		this.sname=shopnm;
		doc="";
		//f.add(new JScrollPane(tdata));
		update();
		Thread t=new Thread(this);
		t.start();
	}	
		public void run(){}
		public  static void update(){
		DB dB = null;
		try {
			dB = (new MongoClient("localhost",27017)).getDB("smartmall");
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		DBCollection dBCollection =dB.getCollection(sname);
		System.out.println("adding product into: "+sname);
		
		DBCursor dbCursor=dBCollection.find(); 
		int count=0;
		String[] columnNames = {"ProductID","ProductName","Qantity","Comapny","Price","Barcode"};
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		while(dbCursor.hasNext()) {
		DBObject obj = dbCursor.next();
		
		
		//String cnt=obj.count();
		//forEach(function(obj){for (int k in obj){count++}});
		//String extra = (String)obj.get("");
		String pid = (String)obj.get("ProductID");
		String pnm = (String)obj.get("ProductName");
		String qua = (String)obj.get("Quantity");
		//ObjectId id = (ObjectId)obj.get("_id");
		String company = (String)obj.get("Company");
		String price = (String)obj.get("Price");
		String barcode=(String)obj.get("Barcode");
		model.addRow(new Object[] { pid ,pnm,qua,company,price,barcode});
		}
		table.setModel(model);
		
		/*while(dbCursor.hasNext())
		{
			doc=doc.concat("\n"+dbCursor.next().toString());
		
		}
		System.out.println(doc);*/
		//tdata.setText(doc);
		//return doc;
		
					
	}
	public void setData(String data){
		tdata.setText(data);
	}
	
	public static void main(){
		create("");
	}
	public static void create(String sname){
		WindowUtil.setNimbusLook();
		
		f=new JFrame("Available product");
		f.setResizable(true);
		f.getContentPane().add(new ViewProduct(sname));
		WindowUtil.setToCenter(f, 1190, 650);
		//System.out.println(w+" "+h);
		f.setVisible(true);
		
	}
}
