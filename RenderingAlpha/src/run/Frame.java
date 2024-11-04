package RenderingAlpha.src.run;

import RenderingAlpha.src.lib.Point;

import java.util.*;
import java.awt.*;
import java.awt.image.*;

import javax.swing.*;

public class Frame extends JFrame {
	public Panel panel;
	
	//Parameters required for drawing points
	public static double camX;
	public static double camY;
	public static double camZ;
	public static double zSensitivity;
	
	//Starting width and height, are not updated when the frame is resized
	public static int startWidth;
	public static int startHeight;
	
	public static Point[][] pointsArr;
	
	
	public Frame() {
		super();
		this.setSize(new Dimension(500, 500));
		this.startWidth = 500;
		this.startHeight = 500;
		this.camX = 0;
		this.camY = 0;
		this.camZ = 0;
		this.zSensitivity = 0.001;
		
		pack();
		initializeWindow();
	}
	
	public Frame(int startWidth, int startHeight) {
		super();
		this.setSize(new Dimension(width, height));
		this.startWidth = width;
		this.startHeight = startHeight;
		this.camX = 0;
		this.camY = 0;
		this.camZ = 0;
		this.zSensitivity = 0.001;
		
		pack();
		initializeWindow();
	}
	
	public Frame(int startWidth, int height, double camX, double camY, double camZ) {
		super();
		this.setSize(new Dimension(width, height));
		this.startWidth = width;
		this.height = height;
		this.camX = camX;
		this.camY = camY;
		this.camZ = camZ;
		this.zSensitivity = 0.001;
		
		pack();
		initializeWindow();
	}
	
	public Frame(int startWidth, int height, double camX, double camY, double camZ, double zSensitivity) {
		super();
		this.setSize(new Dimension(width, height));
		this.startWidth = width;
		this.height = height;
		this.camX = camX;
		this.camY = camY;
		this.camZ = camZ;
		this.zSensitivity = zSensitivity;
		
		pack();
		initializeWindow();
	}
	
	// @Override
	// public void paint(Graphics g) {
		// super.paintComponents(g);
	// }
	
	public void initializeWindow() {
		this.setTitle("Window");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.setSize(new Dimension(width, height));
		this.setLocationRelativeTo(null);
		
		
		pointsArr = new Point[width][height];
		panel = new Panel();
		add(panel);
	}
	
	class Panel extends JPanel {
		public boolean running = false;
		
		public Panel() {
			initializePanel();
		}
		
		@Override
		public void paintComponent(Graphics g) {
			running = true;
			super.paintComponent(g);
			
			
			Frame frame = (Frame) (JFrame) SwingUtilities.getWindowAncestor(this);
			
			
			Dimension size = frame.getContentPane().getSize();
			int width = (int) size.getWidth();
			int height = (int) size.getHeight();
			
			
			pointsArr = frame.pointsArr;
			
			
			BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			
			
			Graphics2D g2D = (Graphics2D) g;
			
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					if (pointsArr[i][j] == null) {
						bImage.setRGB(i, j, Color.white.getRGB());
					}
				}
			}
			
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					Point p = pointsArr[i][j];
					if (p != null) {
						bImage.setRGB((int) p.x, (int) p.y, p.color.getRGB());
					}
				}
			}
			
			
			
			g.drawImage(bImage, 0, 0, this);
			g.dispose();
			
			
			pointsArr = new Point[width][height];
			running = false;
		}
		
		
		public void initializePanel() {
			Frame frame = (Frame) (JFrame) SwingUtilities.getWindowAncestor(this);
			Dimension size = frame.getContentPane().getSize();
			int width = (int) size.getWidth();
			int height = (int) size.getHeight();
			
			this.setVisible(true);
			this.setOpaque(false);
			this.setBounds(new Rectangle(width, height));
		}
	}
}



