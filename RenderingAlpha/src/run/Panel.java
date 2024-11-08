package RenderingAlpha.src.run;

import RenderingAlpha.src.lib.Point;

import java.awt.*;
import java.awt.image.*;
import java.util.*;

import javax.swing.*;

public class Panel extends JPanel {
	public boolean running = false;
	
	//Parameters required for drawing points
	public static double camX;
	public static double camY;
	public static double camZ;
	public static double zSensitivity;
	
	public static double zLayer = 0;
	
	public static BufferedImage image;
	
	public static int width;
	public static int height;
	
	
	public static Point[][] pointsArr;
	
	
	public Panel() {
		this.setSize(new Dimension(500, 500));
		this.setBounds(new Rectangle(500, 500));
		this.width = 500;
		this.height = 500;
		this.camX = 0;
		this.camY = 0;
		this.camZ = 0;
		this.zSensitivity = 0.001;
		
		initializePanel();
	}
	
	public Panel(int width, int height) {
		this.setSize(new Dimension(width, height));
		this.setBounds(new Rectangle(width, height));
		this.width = width;
		this.height = height;
		this.camX = 0;
		this.camY = 0;
		this.camZ = 0;
		this.zSensitivity = 0.001;
		
		initializePanel();
	}
	
	public Panel(int width, int height, double camX, double camY, double camZ) {
		this.setSize(new Dimension(width, height));
		this.setBounds(new Rectangle(width, height));
		this.width = width;
		this.height = height;
		this.camX = camX;
		this.camY = camY;
		this.camZ = camZ;
		this.zSensitivity = 0.001;
		
		initializePanel();
	}
	
	public Panel(int width, int height, double camX, double camY, double camZ, double zSensitivity) {
		this.setSize(new Dimension(width, height));
		this.setBounds(new Rectangle(width, height));
		this.width = width;
		this.height = height;
		this.camX = camX;
		this.camY = camY;
		this.camZ = camZ;
		this.zSensitivity = zSensitivity;
		
		initializePanel();
	}
	
	public Panel(int width, int height, double camX, double camY, double camZ, double zSensitivity, double zLayer) {
		this.setSize(new Dimension(width, height));
		this.setBounds(new Rectangle(width, height));
		this.width = width;
		this.height = height;
		this.camX = camX;
		this.camY = camY;
		this.camZ = camZ;
		this.zSensitivity = zSensitivity;
		this.zLayer = zLayer;
		
		initializePanel();
	}
	
	
	
	public BufferedImage frameUpdate() {
		updateImage();
		clearPoints();
		return image;
	}
	
	
	public BufferedImage getImage() {
		return image;
	}
	
	
	public BufferedImage updateImage() {
		running = true;
		
		BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		
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
		
		
		
		image = bImage;
		
		running = false;
		return image;
	}
	
	
	public boolean clearPoints() {
		pointsArr = new Point[width][height];
		return true;
	}
	
	public void initializePanel() {
		this.setVisible(true);
		this.setOpaque(false);
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pointsArr = new Point[width][height];
	}
	
	
	//Adds a point to the points array
	public void addPoint(Point point) {
		addToPoints(point);
	}
	
	//Adds a point to the points array
	public void addPoints(ArrayList<Point> points) {
		for (Point point : points) {
			addToPoints(point);
		}
	}
	
	
	public void addToPoints(Point point) {
		Point temp = point.xy(this);
		
		//adding 0.5 to round to the nearest number, which should account for floating point precision errors
		temp.x += 0.5;
		temp.y += 0.5;
		
		
		int row = (int) temp.x;
		int column = (int) temp.y;
		
		//Checking if the point is out of the bounds of the screen
		if (temp.x < pointsArr[0].length && temp.y < pointsArr.length && temp.x >= 0 && temp.y >= 0) {
			try {
				if (pointsArr[row][column].z >= point.z) {
					// if (Main.points2D[(int) temp.x][(int) temp.y].z == this.z) {
						//Will implement later, I intend to average the colors of two points that land on the same pixel and have the same z-value
						// Main.points2D[(int) temp.x][(int) temp.y] = new Point(temp.x, temp.y, this.z, this.color);
					// } else {
						pointsArr[row][column] = new Point(temp.x, temp.y, point.z, point.color);
					// }
				}
			} catch (NullPointerException e) {
				pointsArr[row][column] = new Point(temp.x, temp.y, point.z, point.color);
			}
		}
	}
}