package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;



public class WelcomePage extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JFrame f;
	
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
			.getDefaultScreenDevice();
	int w = gd.getDisplayMode().getWidth();
	int h = gd.getDisplayMode().getHeight();
	
	
	public WelcomePage(){
	JLabel bak=new JLabel(new ImageIcon(WelcomePage.class.getResource("/images/welcome2.png")));
	bak.setBackground(Color.WHITE);
	add(bak, BorderLayout.CENTER);
	bak.setLayout(null);
	
	
	JLabel lblWelcome = new JLabel("WELCOME TO");
	lblWelcome.setForeground(new Color(0, 255, 255));
	lblWelcome.setFont(new Font("Kriscen ITC", Font.BOLD, 30));
	lblWelcome.setBounds(w-1000, h-700, 400, 200);
	bak.add(lblWelcome);
	
	JLabel lblScore = new JLabel("Mongo-MALL");
	lblScore.setForeground(new Color(0, 255, 255));
	lblScore.setFont(new Font("Kriscen ITC", Font.BOLD, 40));
	lblScore.setBounds(w-1000, h-600, 400, 200);
	bak.add(lblScore);
	
	
	/*JPanel panel = new JPanel();
	panel.setOpaque(false);
	panel.setBackground(Color.WHITE);
	panel.setBorder(UIManager.getBorder("TitledBorder.border"));
	panel.setBounds(43, 53, 1288, 740);
	bak.add(panel);
	panel.setLayout(null);*/
	

	
	JButton btnStart = new JButton("START");
	btnStart.setBounds(500, 600, 200, 60);
	
	f.setSize(w,h);
	//panel.add(btnStart);
	bak.add(btnStart);
	btnStart.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Login.create();
			f.dispose();
		}
	});
	btnStart.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
	}
	
	public static void main(String [] args){
		create();
	}
public static void create() {
		
		WindowUtil.setNimbusLook();
		
		f=new JFrame("Welcome");
		f.setResizable(true);
		f.getContentPane().add(new WelcomePage());
		f.setVisible(true);
		
	}
}