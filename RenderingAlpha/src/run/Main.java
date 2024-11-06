package RenderingAlpha.src.run;

import RenderingAlpha.src.lib.Point;
import RenderingAlpha.src.run.Frame;
import RenderingAlpha.src.run.Panel;

import java.awt.*;
import java.util.*;

public class Main {
	public static double z = 0;
	public static ArrayList<Point> tri = new ArrayList<Point>();
	public static void main(String[] args) {
		Frame frame = new Frame("Window", 500, 500);
		Panel panel1 = new Panel(500, 500, 250, 250, 0, 0.003, 0);
		frame.addPanel(panel1);
		
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				z += 0.1;
				Point a = new Point(100 + z, 100, 0, Color.black, null);
				Point b = new Point(243, 156 + z, z, Color.black, null);
				Point c = new Point(200, 100, 0, Color.black, null);
				if (!panel1.running) tri = tri(a, b, c, Color.black);
				
				for (int i = 0; i < tri.size(); i++) {
					Point p = tri.get(i);
					p.addToPoints(panel1);
				}
				
				frame.repaint();
			}
		}, 0, 1);
	}
	
	
	//Will move to a 3D object class later
	public static ArrayList<Point> line(Point a, Point b, Color color) {
		ArrayList<Point> result = new ArrayList<Point>();
		
		Point axy = a.xy();
		Point bxy = b.xy();
		
		double x1 = axy.x;
		double y1 = axy.y;
		double x2 = bxy.x;
		double y2 = bxy.y;
		
		double z1 = a.z;
		double z2 = b.z;
		
		double dist = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) + 1;
		
		for (int i = 0; i <= dist; i++) {
			double p = (double) i / dist;
			double z = a.z + (b.z - a.z) * p;
			
			Point fillPoint = new Point(x1 + (x2 - x1) * p, y1 + (y2 - y1) * p, 0, color, null).toXYZ(z);
			
			result.add(fillPoint);
		}
		
		return result;
	}
	
	//Will move to a 3D object class later
	public static ArrayList<Point> verticalLine(Point a, Point b, Color color) {
		ArrayList<Point> result = new ArrayList<Point>();
		
		Point a2D = a.xy();
		Point b2D = b.xy();
		
		int numPoints = (int) (Math.abs(b2D.y - a2D.y) + 1);


		if (b2D.y < a2D.y) {
			for (int i = (int) b2D.y; i <= (int) a2D.y; i++) {
				//The percent, as a decimal, to lerp from the first point to the last point
				double p = (double) (i - b2D.y) / numPoints; //Either i or numPoints (or both) must be casted to a double so that i / numPoints will not be evaluated as 0
				
				double z = b.z + (a.z - b.z) * p;
				Point add = new Point(b2D.x, i, 0, color).toXYZ(z);
				// Point addxy = add.xy();
				
				result.add(add);
			}
		}
		
		
		if (a2D.y <= b2D.y) {
			for (int i = (int) a2D.y; i <= (int) b2D.y; i++) {
				//The percent, as a decimal, to lerp from the first point to the last point
				double p = (double) (i - a2D.y) / numPoints; //Either i or numPoints (or both) must be casted to a double so that i / numPoints will not be evaluated as 0
				
				double z = a.z + (b.z - a.z) * p;
				Point add = new Point(a2D.x, i, 0, color).toXYZ(z);
				// Point addxy = add.xy();
				
				result.add(add);
			}
		}
		
		
		return result;
	}
	
	
	
	//Will move to a 3D object class later
	public static ArrayList<Point> tri(Point a, Point b, Point c, Color color) {
		ArrayList<Point> result = new ArrayList<Point>();
		
		
		Point a2D = a.xy();
		Point b2D = b.xy();
		Point c2D = c.xy();
		
		
		ArrayList<Point> r = new ArrayList<Point>();
		ArrayList<Point> rz = new ArrayList<Point>();
		r.add(a2D);
		rz.add(a);
		
		
		if (b2D.x < a2D.x) {
			r.add(0, b2D);
			rz.add(0, b);
		} else {
			r.add(b2D);
			rz.add(b);
		}
		if (c2D.x < r.get(0).x) {
			r.add(0, c2D);
			rz.add(0, c);
		} else {
			if (c2D.x < r.get(1).x) {
				r.add(1, c2D);
				rz.add(1, c);
			} else {
				r.add(c2D);
				rz.add(c);
			}
		}
		
		// ArrayList<Point> fill = lineArray2D(r.get(0), r.get(1));
		Point x = r.get(0); //furthest point
		Point y = r.get(1); //closer point 1
		Point z = r.get(2); //closer point 2
		
		//Points above in 3D
		Point xz = rz.get(0);
		Point yz = rz.get(1);
		Point zz = rz.get(2);
		
		
		for (int i = (int) (x.x + 1); i <= (int) z.x; i++) {
			double p1 = (double) (i - x.x) / (z.x - x.x);
			double p2;
			if (i <= y.x) p2 = (double) (i - x.x) / (y.x - x.x);
			else p2 = (double) (i - y.x) / (z.x - y.x);
			
			//Had to add this because p2 was getting negative when i was equal to or very close to x.x (since y.x - x.x is evaluated as 0 (or a decimal) and causes p2 to be very positive or very negative
			//There is still a single point being generated that shouldn't be, this can be fixed by adding one to the start value of i, but this also removes
			//a point that should be in the triangle
			if (p2 < 0) p2 = 0;
			
			Point add1 = new Point(i, x.y + (z.y - x.y) * (p1), 0, color).toXYZ(xz.z + (zz.z - xz.z) * (p1));
			
			Point add2;
			if (i <= y.x) {
				add2 = new Point(i, x.y + (y.y - x.y) * (p2), 0, color).toXYZ(xz.z + (yz.z - xz.z) * (p2));
			} else {
				add2 = new Point(i, y.y + (z.y - y.y) * (p2), 0, color).toXYZ(yz.z + (zz.z - yz.z) * (p2));
			}
			
			
			result.addAll(verticalLine(add1, add2, color));
		}
		
				
		//Adding black edges
		Point e1 = new Point(a.x, a.y, a.z, Color.black);
		Point e2 = new Point(b.x, b.y, b.z, Color.black);
		Point e3 = new Point(c.x, c.y, c.z, Color.black);
		
		result.addAll(line(e1, e2, Color.black));
		result.addAll(line(e2, e3, Color.black));
		result.addAll(line(e3, e1, Color.black));
		
		
		return result;
	}
	
}