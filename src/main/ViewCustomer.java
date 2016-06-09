package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class ViewCustomer extends JPanel implements Runnable{
	private static JFrame f;
	private static JTable table;
	
	
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
			.getDefaultScreenDevice();
	int w = gd.getDisplayMode().getWidth();
	int h = gd.getDisplayMode().getHeight();
	
	public ViewCustomer(){
		JLabel bak=new JLabel(new ImageIcon(ViewSale.class.getResource("/images/loginpnl.jpg")));
		bak.setBackground(Color.WHITE);
		add(bak, BorderLayout.CENTER);
		bak.setLayout(null);
		setLayout(new BorderLayout());
		table = new JTable(){
		public Dimension getPreferredScrollableViewportSize() {
		return new Dimension(300, 150);
		}
		};
		add(new JScrollPane(table));
		
		update(ItemWindow.cus);
		Thread t=new Thread(this);
		t.start();
	}
	public void run(){}
	public void update(String un){
	DB dB = null;
	try {
		dB = (new MongoClient("localhost",27017)).getDB("smartmall");
	} catch (UnknownHostException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	
	DBCollection dBCollection =dB.getCollection(un);
	
	
	
	
	DBCursor dbCursor=dBCollection.find(); 
	int count=0;
	String[] columnNames = {"Date Time","UserName","ShopName","Productlist","product Bill"};
	DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	while(dbCursor.hasNext()) {
	DBObject obj = dbCursor.next();
	
	String pid = (String)obj.get("date");
	String pnm = (String)obj.get("UserName");
	String snm = (String)obj.get("ShopName");
	//ObjectId id = (ObjectId)obj.get("_id");
	String productlist = (String)obj.get("ProductList");
	int productbill = (int) obj.get("ProductBill");
	model.addRow(new Object[] { pid ,snm,pnm,productlist,productbill});
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
	
	public static void main(){
		create();
	}
	public static void create(){
		WindowUtil.setNimbusLook();
		
		f=new JFrame("Available product");
		f.setResizable(true);
		f.getContentPane().add(new ViewSale());
		WindowUtil.setToCenter(f, 1190, 650);
		//System.out.println(w+" "+h);
		f.setVisible(true);
		
	}
	

}
