package RenderingAlpha.src.lib;

import RenderingAlpha.src.lib.*;

import java.awt.*;
import java.util.*;

public class Line {
	public ArrayList<Point> points = new ArrayList<Point>();
	public ArrayList<Point> points2D = new ArrayList<Point>();
	public Point a;
	public Point b;
	public Color color;
	public Line(Point a, Point b) {
		this.a = a;
		this.b = b;
		this.color = Color.black;
		update();
	}
	
	public Line(Point a, Point b, Color color) {
		this.a = a;
		this.b = b;
		this.color = color;
		update();
	}
	
	public void update() {
		// points = line(a, b, color);
		points2D = line2D(a, b, color);
	}
	
	public void update3D() {
		// points = line(a, b, color);
	}
	
	public void update2D() {
		points2D = line2D(a, b, color);
	}
	
	// public static ArrayList<Point> line(Point a, Point b, Color color) {
		
	// }
	
	public static ArrayList<Point> line2D(Point a, Point b, Color color) {
		ArrayList<Point> result = new ArrayList<Point>();
		Point axy = a.xy();
		Point bxy = b.xy();
		
		double x1 = axy.x;
		double x2 = bxy.x;
		double y1 = axy.y;
		double y2 = bxy.y;
		
		double z1 = a.z;
		double z2 = b.z;
		
		int numPoints = (int) (Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) + 1);
		
		for (int i = 0; i <= numPoints; i++) {
			double p = (double) i / numPoints;
			double z = z1 + (z2 - z1) * p;
			
			Point fillPoint = new Point(x1 + (x2 - x1) * p, y1 + (y2 - y1) * p, 0, color).toXYZ(z);
			result.add(fillPoint);
		}
		
		return result;
	}
}