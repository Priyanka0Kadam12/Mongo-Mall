package main;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ScanBarcode extends JPanel{
	private QRReader qr;
	private JTextField tfQRcode;
	private static JFrame f;
	private static ScanBarcode code;
	
	public ScanBarcode(){
		AddUpdateProduct aup=new  AddUpdateProduct();
		UpdateProduct up=new UpdateProduct();
		qr=new QRReader(null,aup,up);
		setLayout(null);
		qr.setBounds(10, 10, 600, 600);
		add(qr);
		tfQRcode=new JTextField();
		tfQRcode.setFont(new Font("Monotype Corsiva",Font.BOLD,20));
		tfQRcode.setBounds(100,610,600,50);
		tfQRcode.setColumns(40);
		//add(tfQRcode);
		f.setSize(600,600);
	}
	public void close(){
		f.dispose();
	}
	public static void main(String [] args){
		create();
	}
	public static void create(){
		f=new JFrame("scan barcode");
		f.setResizable(true);
		code=new ScanBarcode();
		f.getContentPane().add(code);
		f.setVisible(true);
		f.addWindowFocusListener(new WindowAdapter() {
		    public void windowGainedFocus(WindowEvent e) {
		  
		    	code.qr.initDSCapture();
		    	
		    }
		    public void windowLostFocus(WindowEvent e){
		    	code.qr.getGraph().dispose();
		    	code.qr.msg.setVisible(true);
		    }
		    
		});
		
	}
	
	
	

}
