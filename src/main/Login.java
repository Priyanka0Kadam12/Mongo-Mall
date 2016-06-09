package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;




public class Login extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JFrame f;
	
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
			.getDefaultScreenDevice();
	int w = gd.getDisplayMode().getWidth();
	int h = gd.getDisplayMode().getHeight();

	public Login(){
		JLabel bak=new JLabel(new ImageIcon(Login.class.getResource("/images/loginpnl.jpg")));
		bak.setBackground(Color.WHITE);
		add(bak, BorderLayout.CENTER);
		bak.setLayout(null);
		
		JLabel lbladmin = new JLabel("Admin login");
		lbladmin.setForeground(new Color(0, 255, 255));
		lbladmin.setFont(new Font("Kriscen ITC", Font.BOLD, 25));
		lbladmin.setBounds(w-980, h-700, 400, 200);
		bak.add(lbladmin);

		
		JLabel lbluser = new JLabel("User Login");
		lbluser.setForeground(new Color(0, 255, 255));
		lbluser.setFont(new Font("Kriscen ITC", Font.BOLD, 25));
		lbluser.setBounds(w-580, h-700, 400, 200);
		bak.add(lbluser);
		
		
		JButton btnPur = new JButton("");
		btnPur.setRolloverIcon(new ImageIcon(Login.class.getResource("/images/admin1.png")));
		btnPur.setIcon(new ImageIcon(Login.class.getResource("/images/admin.png")));
		btnPur.setMnemonic('s');
		btnPur.setContentAreaFilled(false);
		//btnPur.setBorder(new TitledBorder(null, "login as admin", TitledBorder.LEADING, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 15), Color.white));
		bak.add(btnPur);
		btnPur.setBounds(w-1100,h-550,400,400);
		btnPur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage.create(true);
				f.dispose();
			}
		});
		
		
		JButton btnuser = new JButton("");
		btnuser.setRolloverIcon(new ImageIcon(Login.class.getResource("/images/user1.png")));
		btnuser.setIcon(new ImageIcon(Login.class.getResource("/images/user.png")));
		btnuser.setMnemonic('s');
		btnuser.setContentAreaFilled(false);
		//btnPur.setBorder(new TitledBorder(null, "login as user", TitledBorder.LEADING, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 20), Color.white));
		bak.add(btnuser);
		btnuser.setBounds(w-700,h-550,400,400);
		btnuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage.create(false);
				f.dispose();
			}
		});

		f.setSize(w,h);
		
		
	}public static void main(){
		create();
	}
	public static void create(){

		WindowUtil.setNimbusLook();
		
		f=new JFrame("Select Login");
		f.setResizable(true);
		f.getContentPane().add( new Login());
		//WindowUtil.setToCenter(f, 1288, 740);
		//System.out.println(w+" "+h);
		f.setVisible(true);

	}
}
