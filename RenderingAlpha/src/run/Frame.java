package RenderingAlpha.src.run;

import RenderingAlpha.src.lib.Point;

import java.util.*;
// Explicitly importing java.util.Timer so that the compiler does not get confused between javax.swing.Timer and java.util.Timer 
// (Basically tells the compiler I want to use the util Timer, not the Swing timer)
import java.util.Timer;
import java.awt.*;
import java.awt.image.*;

import javax.swing.*;

public class Frame extends JFrame {
	public ArrayList<Panel> children = new ArrayList<Panel>();
	
	public BufferedImage bg;
	
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
		Graphics2D g2d = (Graphics2D) g;
		
		// Resetting the background
		g.drawImage(bg, 0, 0, this);
		
		// Sorting the children by z-layer
		Collections.sort(children, new PanelComparator());
		
		// Drawing children
		for (int i = 0; i < children.size(); i++) {
			Panel p = children.get(i);
			
			BufferedImage pImage = p.updateImage();
			g.drawImage(pImage, 0, 0, this);
			p.clearPoints();
		}
		
		
		g.dispose();
	}
	
	
	public void addPanel(Panel panel) {
		children.add(panel);
	}
	
	public void initializeWindow() {
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBG(Color.white);
	}
	
	public void setBG(Color color) {
		Dimension size = getContentPane().getSize();
		int width = (int) size.getWidth();
		int height = (int) size.getHeight();
		bg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				bg.setRGB(i, j, color.getRGB());
			}
		}
	}
	
	
	
	
	
	class PanelComparator implements Comparator<Panel> {
    public int compare(Panel a, Panel b) {
		if (a.zLayer < b.zLayer) return 1;
		if (a.zLayer > b.zLayer) return -1;
        return 0;
    }
}
}



