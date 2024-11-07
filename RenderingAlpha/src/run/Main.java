package RenderingAlpha.src.run;

import RenderingAlpha.src.lib.*;
import RenderingAlpha.src.lib.Point;
import RenderingAlpha.src.run.Frame;
import RenderingAlpha.src.run.Panel;

import java.awt.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		Frame frame = new Frame("Window", 500, 500);
		Panel panel1 = new Panel(500, 500, 250, 250, 0, 0.003, 0);
		frame.addPanel(panel1);
		
		Point a = new Point(0, 0, 0, Color.black);
		Point b = new Point(150, 100, 0, Color.black);
		
		Line line1 = new Line(a, b, Color.black);
		
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				panel1.addPoints(line1.points2D);
				line1.a.x++;
				line1.update2D();
				
				frame.repaint();
			}
		}, 0, 1);
	}
}