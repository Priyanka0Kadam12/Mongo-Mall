package main;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;

import main.ItemWindow;

public class SelectedProductList extends 	JPanel 
 {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Instance attributes used in this example
	private JTextArea tdata;
	static String str;


	// Constructor of main frame
	public SelectedProductList()
	{
		setBorder(new TitledBorder(null, "Bill", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setBackground(Color.WHITE);
		tdata=new JTextArea("Sale Product Details:");
		tdata.setCaretPosition(0);
		tdata.setEditable(false);
		tdata.setFont(new Font("SansSerif", Font.BOLD, 20));
		setLayout(new BorderLayout());
		add(new JScrollPane(tdata));
		str="";
	
		
		
	}
	public void setData(String data){
		tdata.setText(data);
	}
	public String saleProductInfo(String product,String price){
		str=str.concat("\n"+product+" : "+price);
		System.out.println("String :"+str);
		return str;
	}
}
