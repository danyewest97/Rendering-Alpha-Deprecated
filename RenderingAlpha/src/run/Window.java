package RenderingAlpha.src.run;

import RenderingAlpha.src.lib.Point;
import RenderingAlpha.src.run.Frame;
import RenderingAlpha.src.run.Panel;

import java.awt.*;
import java.util.*;

public class Window {
	public Frame frame;
	public int fps = 60;
	public Timer fpsTimer = new Timer();
	
	public boolean running = false;
	
	public Window() {
		frame = new Frame();
	}
	
	public Window(Frame frame) {
		this.frame = frame;
	}
	
	public void start() {
		running = true;
		fpsTimer.schedule(new TimerTask() {
			public void run() {
				draw();
				frame.repaint();
			}
		}, 0, 1000/fps);
	}
	
	//Meant to be overridden when created
	public void draw() {
		
	}
	
	public void stop() {
		if (running) {
			fpsTimer.cancel();
		}
	}
	
	public void drawPoint(Point p, int index) {
		frame.children.get(index).addPoint(p);
	}
}