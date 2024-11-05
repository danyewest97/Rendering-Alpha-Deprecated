package RenderingAlpha.src.run;

import RenderingAlpha.src.lib.Point;

import java.util.*;
import java.awt.*;
import java.awt.image.*;

import javax.swing.*;

public class Frame extends JFrame {
	public ArrayList<Panel> children = new ArrayList<Panel>();
	
	public Frame() {
		super();
		
		pack();
		
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		
		initializeWindow();
	}
	
	public Frame(int width, int height) {
		super();
		
		pack();
		
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		
		initializeWindow();
	}
	
	public Frame(String title, int width, int height) {
		super(title);
		
		pack();
		
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		
		initializeWindow();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paintComponents(g);
		
		
	}
	
	public void initializeWindow() {
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}



