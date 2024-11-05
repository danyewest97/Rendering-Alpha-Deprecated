package RenderingAlpha.src.run;

import RenderingAlpha.src.lib.Point;
import RenderingAlpha.src.run.Frame;
import RenderingAlpha.src.run.Panel;

import java.awt.*;

public class Main {
	public static void main(String[] args) {
		Frame frame = new Frame("Window", 500, 500);
		Panel panel1 = new Panel(500, 500, 250, 250, 0, 0.003, 0);
		frame.add(panel1);
		panel1.addPoint(new Point(250, 250, 0, Color.black, panel1));
	}
}