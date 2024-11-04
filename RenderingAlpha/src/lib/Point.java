package RenderingAlpha.src.lib;

import RenderingAlpha.src.run.Frame;

import java.util.*;
import java.awt.*;

import javax.swing.*;

public class Point {
	public double x;
	public double y;
	public double z;
	public Color color;
	
	public static Frame frame;
	
	public Point() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
		this.color = Color.white;
		this.frame = null;
	}
	
	public Point(double x, double y, double z, Frame frame) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.color = Color.black;
		this.frame = frame;
	}
	
	public Point(double x, double y, double z, Color color, Frame frame) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.color = color;
		this.frame = frame;
	}
	
	public double dist(Point p) {
		return Math.sqrt(Math.pow(p.x - x, 2) + Math.pow(p.y - y, 2) + Math.pow(p.z - z, 2));
	}
	
	public void addToPoints() {
		Point temp = this.xy();
		
		//adding 0.5 to round to the nearest number, which should account for floating point precision errors
		// temp.x += 0.5;
		// temp.y += 0.5;
		//FIX THIS IN THE TRI AND LINE ARRAY METHODS
		
		int row = (int) temp.x;
		int column = (int) temp.y;
		
		if (frame != null) {
			Point[][] pointsArr = frame.pointsArr;
			
			//Checking if the point is out of the bounds of the screen
			if (temp.x < pointsArr[0].length && temp.y < pointsArr.length && temp.x >= 0 && temp.y >= 0) {
				try {
					if (pointsArr[row][column].z >= this.z) {
						// if (Main.points2D[(int) temp.x][(int) temp.y].z == this.z) {
							//Will implement later, I intend to average the colors of two points that land on the same pixel and have the same z-value
							// Main.points2D[(int) temp.x][(int) temp.y] = new Point(temp.x, temp.y, this.z, this.color);
						// } else {
							pointsArr[row][column] = new Point(temp.x, temp.y, this.z, this.color, frame);
						// }
					}
				} catch (NullPointerException e) {
					pointsArr[row][column] = new Point(temp.x, temp.y, this.z, this.color, frame);
				}
			}
		}
	}
	
	
	public Point xy() {
		double camX = frame.camX;
		double camY = frame.camY;
		double zSensitivity = frame.zSensitivity;
		
		double rx = camX;
		double ry = camY;
		
		
		double p = Math.exp(-(z * zSensitivity));
		if (z == 0) p = 1;
		
		rx += (x - camX) * p;
		ry += (y - camY) * p;
		
		Point result = new Point(rx, ry, 0, color, frame);
		
		return result;
	}
	
	public Point toXYZ(double z) {
		double camX = frame.camX;
		double camY = frame.camY;
		double zSensitivity = frame.zSensitivity;
		
		double p = Math.exp(-(z * zSensitivity));
		
		double rx = x;
		double ry = y;
		
		
		rx += p * camX;
		ry += p * camY;

		
		rx -= (camX);
		ry -= (camY);
		
		rx /= p;
		ry /= p;
		
		
		Point result = new Point(rx, ry, z, color, frame);
		
		return result;
	}
	
	@Override
	public Point clone() {
		return new Point(x, y, z, color, frame);
	}
	
	@Override
	public String toString() {
		return "" + x + ", " + y + ", " + z;
	}
}