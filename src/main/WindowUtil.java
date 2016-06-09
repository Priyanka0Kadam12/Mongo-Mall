package main;


import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class WindowUtil {
	public static void setNativeLook(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	public static void setNimbusLook(){
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			//javax.swing.plaf.nimbus.NimbusLookAndFeel
		
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	public static void setToCenter(Container c,int width,int height){
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		int w = gd.getDisplayMode().getWidth();
		int h = gd.getDisplayMode().getHeight();
		
		c.setBounds(w/2-width/2,h/2-height/2,width,height);

	}
	public static int getScreenWidth(){
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		int w = gd.getDisplayMode().getWidth();
		return w;
	}
	public static int getScreenHeight(){
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		int h = gd.getDisplayMode().getHeight();
		return h;
	}
	public static ImageIcon getScaledImage(Image srcImg, int w, int h) {
		  int s=0;
		 BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	      Graphics2D g2 = resizedImg.createGraphics();
	      g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	      g2.setColor(Color.black);
	      g2.drawImage(srcImg, s, s, w-s, h-s, null);
	      for(int i=0;i<s;i++)
	    	  g2.drawRect(i, i, w-i, h-i);
	      g2.dispose();
	      return new ImageIcon(resizedImg);
	}

}

