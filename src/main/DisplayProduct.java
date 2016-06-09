package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.net.UnknownHostException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class DisplayProduct extends JPanel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int flag=0;
	static String key1,value1;
	String key2;
	static String value2;
	String key3,value3;
	String key4,value4;
	String key5;
	static String value5;
	static String key6,value6;
	String key7,value7;
	String shopnm,proID;
	private JTextArea tdata;
	public DisplayProduct(){
		setBorder(new TitledBorder(null, "Product", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setBackground(Color.WHITE);
	
		tdata=new JTextArea("Product Details:");
		tdata.setCaretPosition(0);
		tdata.setEditable(false);
		tdata.setFont(new Font("SansSerif", Font.BOLD, 20));
		setLayout(new BorderLayout());
		add(new JScrollPane(tdata));
		
	}
	public void setData(String data){
		tdata.setText(data);
	}
	public String productInfo(String shopnm,String barcode){
		
		DB dB = null;
		try {
			dB = (new MongoClient("localhost",27017)).getDB("smartmall");
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//DBCollection dBCollection =dB.getCollection("Electronics_Goods");
		
		DBCollection dBCollection =dB.getCollection(shopnm);
		
		
		//DBObject query=dB.dBCollection.find( {  ProductID : ItemWindow.tfSearch.getText()} );
		//DBCursor result = dBCollection.find(query);
		//for( DBObject dock : shopnm.find() ){}
		
		System.out.println("Product Id :"+barcode);
		
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("Barcode", barcode);
		DBCursor cursor = dBCollection.find(whereQuery);
		//int noofcollection=cursor.count();
	
		//DBObject query=dBCollection.findOne();// {  ProductID : proID } );
		
			//int h=60;
			String ret="";
			while(cursor.hasNext()){
				DBObject doc=cursor.next();
				
				//System.out.println(doc);
				
			 key1="ProductID";value1=(String) doc.get(key1);
			 key2="ProductName";value2=(String) doc.get(key2);
			 key3="Quantity";value3=(String) doc.get(key3);
			 key4="Company";value4=(String) doc.get(key4);
			 key5="Price";value5=(String) doc.get(key5);
			 key6="Barcode";value6=(String) doc.get(key6);
			 ret=key1+" : "+value1+"\n"+
			     key6+" : "+value6+"\n"+
			     key2+" : "+value2+"\n"+
			     key3+" : "+value3+"\n"+
			     key4+" : "+value4+"\n"+
			     key5+" : "+value5+"\n";
			}
			return ret;

	}

	public DisplayProduct(String shopnm,String  proID){
		

		this.shopnm=shopnm;
		this.proID=proID;
		//setLayout(new GridLayout(2,2,5,5));
		
		DB dB = null;
		try {
			dB = (new MongoClient("localhost",27017)).getDB("smartmall");
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//DBCollection dBCollection =dB.getCollection("Electronics_Goods");
		
		DBCollection dBCollection =dB.getCollection(shopnm);
		
		
		//DBObject query=dB.dBCollection.find( {  ProductID : ItemWindow.tfSearch.getText()} );
		//DBCursor result = dBCollection.find(query);
		//for( DBObject dock : shopnm.find() ){}
		
		System.out.println("Product Id :"+proID);
		
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("ProductID", proID);
		DBCursor cursor = dBCollection.find(whereQuery);
		//int noofcollection=cursor.count();
	
		//DBObject query=dBCollection.findOne();// {  ProductID : proID } );
		
			//int h=60;
			//String ret="";
			while(cursor.hasNext()){
				DBObject doc=cursor.next();
				
				//System.out.println(doc);
				
			 key1="ProductID";value1=(String) doc.get(key1);
			 key2="ProductName";value2=(String) doc.get(key2);
			 key3="Quantity";value3=(String) doc.get(key3);
			 key4="Company";value4=(String) doc.get(key4);
			 key5="Price";value5=(String) doc.get(key5);
			 key6="Barcode";value6=(String) doc.get(key6);
			}
		
	
			int i=1;
		
			JLabel lblkey=new JLabel(key1);
			//JLabel lblkey=new JLabel((String) query.get( "ProductID" ));
			
			lblkey.setForeground(Color.BLACK);
			lblkey.setFont(new Font("Kriscen ITC", Font.BOLD, 20));
			lblkey.setBounds(22, 28, 53, 16);
			add(lblkey);
			
			JLabel lblvalue=new JLabel(value1);
			System.out.println("from display key : "+key1+" value :"+value1);
			lblvalue.setForeground(Color.BLACK);
			lblvalue.setFont(new Font("Kriscen ITC", Font.BOLD, 20));
			lblvalue.setBounds(88,28, 56, 16);
			add(lblvalue);
			
			i++;
			//AddUpdateProduct.lblProductname.getText();
			JLabel lblkey2=new JLabel(key2);
			//JLabel lblkey=new JLabel((String) query.get( "ProductID" ));
			lblkey2.setForeground(Color.BLACK);
			lblkey2.setFont(new Font("Kriscen ITC", Font.BOLD, 25));
			lblkey2.setBounds(10,10+i*20, 200, 20);
			add(lblkey2);
			JLabel lblvalue2=new JLabel(value2);
			lblvalue2.setForeground(Color.BLACK);
			lblvalue2.setFont(new Font("Kriscen ITC", Font.BOLD, 25));
			lblvalue2.setBounds(220,10+i*50, 200, 20);
			add(lblvalue2);
			
			i++;
			
			JLabel lblkey3=new JLabel(key3);
			lblkey3.setForeground(Color.BLACK);
			lblkey3.setFont(new Font("Kriscen ITC", Font.BOLD, 25));
			lblkey3.setBounds(10,10+i*20, 200, 20);
			add(lblkey3);
			JLabel lblvalue3=new JLabel(value3);
			lblvalue3.setForeground(Color.BLACK);
			lblvalue3.setFont(new Font("Kriscen ITC", Font.BOLD, 25));
			lblvalue3.setBounds(220,10+i*50, 200, 20);
			add(lblvalue3);
			
			i++;
			//AddUpdateProduct.lblBrandName.getText();
			JLabel lblkey4=new JLabel(key4);
			lblkey4.setForeground(Color.BLACK);
			lblkey4.setFont(new Font("Kriscen ITC", Font.BOLD, 25));
			lblkey4.setBounds(10,10+i*20, 200, 20);
			add(lblkey4);
			JLabel lblvalue4=new JLabel(value4);
			lblvalue4.setForeground(Color.BLACK);
			lblvalue4.setFont(new Font("Kriscen ITC", Font.BOLD, 25));
			lblvalue4.setBounds(220,10+i*50, 200, 50);
			
			i++;
			//AddUpdateProduct.lblPrice.getText();
			JLabel lblkey5=new JLabel(key5);
			lblkey5.setForeground(Color.BLACK);
			lblkey5.setFont(new Font("Kriscen ITC", Font.BOLD, 25));
			lblkey5.setBounds(10,10+i*20, 200, 20);
			add(lblkey5);
			JLabel lblvalue5=new JLabel(value5);
			lblvalue5.setForeground(Color.BLACK);
			lblvalue5.setFont(new Font("Kriscen ITC", Font.BOLD, 25));
			lblvalue5.setBounds(520,10+i*50, 200, 20);
			
			i++;
			//AddUpdateProduct.lblBarcode.getText();
			JLabel lblkey6=new JLabel(key6);
			lblkey6.setForeground(Color.BLACK);
			lblkey6.setFont(new Font("Kriscen ITC", Font.BOLD, 25));
			lblkey6.setBounds(10,10+i*20, 200, 20);
			add(lblkey6);
			JLabel lblvalue6=new JLabel(value6);
			lblvalue6.setForeground(Color.BLACK);
			lblvalue6.setFont(new Font("Kriscen ITC", Font.BOLD, 25));
			lblvalue6.setBounds(220,10+i*20, 200, 20);
			
			/*String key7=ExtraDetails.tfProductnamekey.getText();
			if(key7.equals("")==false){
				i++;flag=1;
				JLabel lblkey7=new JLabel(key7);
				lblkey7.setForeground(Color.BLACK);
				lblkey7.setFont(new Font("Kriscen ITC", Font.BOLD, 25));
				lblkey7.setBounds(10,10+i*20, 200, 20);
				add(lblkey7);
				JLabel lblvalue7=new JLabel((String) doc.get(key7));
				lblvalue7.setForeground(new Color(0, 255, 255));
				lblvalue7.setFont(new Font("Kriscen ITC", Font.BOLD, 25));
				lblvalue7.setBounds(220,10+i*20, 200, 20);
			}
			
			
			String key8=ExtraDetails.tfQuantitykey.getText();
			if(key8.equals("")==false){
				flag=2;
				JLabel lblkey8=new JLabel(key8);
				lblkey8.setForeground(Color.BLACK);
				lblkey8.setFont(new Font("Kriscen ITC", Font.BOLD, 25));
				if(flag==1){i++;
				lblkey8.setBounds(10,10+i*20, 400, 20);
				}
				else{
					lblkey8.setBounds(10,10+i*20, 400, 20);
				}
				add(lblkey8);
				JLabel lblvalue8=new JLabel((String) doc.get(key8));
				lblvalue8.setForeground(new Color(0, 255, 255));
				lblvalue8.setFont(new Font("Kriscen ITC", Font.BOLD, 25));
				
				lblvalue8.setBounds(220,10+i*20, 400, 20);
				
			}
			
			String key9=ExtraDetails.tfBrandNamekey.getText();
			if(key8.equals("")==false){
				flag=3;
				JLabel lblkey9=new JLabel(key9);
				lblkey9.setForeground(Color.BLACK);
				lblkey9.setFont(new Font("Kriscen ITC", Font.BOLD, 25));
				if(flag==2){i++;
				lblkey9.setBounds(10,10+i*20, 200, 20);
				}
				else{
					lblkey9.setBounds(10,10+i*20, 200, 20);
				}
				add(lblkey9);
				JLabel lblvalue9=new JLabel((String) doc.get(key9));
				lblvalue9.setForeground(new Color(0, 255, 255));
				lblvalue9.setFont(new Font("Kriscen ITC", Font.BOLD, 25));
				lblvalue9.setBounds(220,10+i*20, 200, 20);
				
				
			}
			
			String key10=ExtraDetails.tfPricekey.getText();
			if(key10.equals("")==false){
				flag=4;
				JLabel lblkey10=new JLabel(key10);
				lblkey10.setForeground(Color.BLACK);
				lblkey10.setFont(new Font("Kriscen ITC", Font.BOLD, 25));
				if(flag==4){i++;
					lblkey10.setBounds(10,10+i*20, 200, 20);
				}else{
					lblkey10.setBounds(10,10+i*20, 200, 20);
				}
				add(lblkey10);
				JLabel lblvalue10=new JLabel((String) doc.get(key10));
				lblvalue10.setForeground(new Color(0, 255, 255));
				lblvalue10.setFont(new Font("Kriscen ITC", Font.BOLD, 25));
				
				lblvalue10.setBounds(220,10+i*20, 200, 20);
				
				
			}
			*/
			//setVisible(true);
			
		
		}
		
		
	

}