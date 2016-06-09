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
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;




public class ViewSale extends JPanel implements Runnable {
	private static JFrame f;
	private static JTable table;
	
	
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
			.getDefaultScreenDevice();
	int w = gd.getDisplayMode().getWidth();
	int h = gd.getDisplayMode().getHeight();
	
	public ViewSale(){
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
		JButton btnRefresh=new JButton("Refresh");
		btnRefresh.setBounds(w/2-500,h/2+200, 197, 43);
		//add(btnRefresh);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					update();
			}
		});
		btnRefresh.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		
		update();
		Thread t=new Thread(this);
		t.start();
	}
	public void run(){}
	public void update(){
	DB dB = null;
	try {
		dB = (new MongoClient("localhost",27017)).getDB("smartmall");
	} catch (UnknownHostException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	
	DBCollection dBCollection =dB.getCollection("sale");
	
	
	
	
	DBCursor dbCursor=dBCollection.find(); 
	int count=0;
	String[] columnNames = {"Last Update","UserName","ShopName","TotalSale"};
	DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	while(dbCursor.hasNext()) {
	DBObject obj = dbCursor.next();
	
	String pid = (String)obj.get("LastUpdate");
	String pnm = (String)obj.get("UserName");
	String snm = (String)obj.get("ShopName");
	//ObjectId id = (ObjectId)obj.get("_id");
	int totalsale = (int) obj.get("TotalSale");
	
	model.addRow(new Object[] { pid ,snm,pnm,totalsale});
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
