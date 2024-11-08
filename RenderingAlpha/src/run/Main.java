package RenderingAlpha.src.run;

import RenderingAlpha.src.lib.Point;
import RenderingAlpha.src.run.Frame;
import RenderingAlpha.src.run.Panel;
import RenderingAlpha.src.run.Window;

import java.awt.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		Frame frame = new Frame("Window", 500, 500);
		final Panel panel1 = new Panel(500, 500, 250, 250, 0, 0.003, 0);
		frame.addPanel(panel1);
		
		Window window = new Window(frame) {
			public void draw() {
				drawPoint(new Point(250, 250, 0, Color.black), 0);
			}
		};
		
		
	}
}