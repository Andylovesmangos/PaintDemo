/*
 * Andy Lee
 * 
 * Paint- A GUI that lets user draw and express themselves creatively
 */
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;


public class LeeAndyPaint extends JFrame implements ActionListener{

	private int currentSize;
	private Color currentColor;
	private JButton clearButton;
	private DrawPanel draw;
	private String colorName;

	public LeeAndyPaint(){

		currentColor = Color.RED;
		currentSize = 10;
		colorName = "Red";

		setTitle("Paint- " + colorName + ":" + currentSize);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 700);
		setLocationRelativeTo(null);
		setLayout(null);

		draw = new DrawPanel();
		draw.setBounds(0, 0, 400, 700);
		draw.setLayout(null);

		//Putting clear button on the draw panel
		clearButton = new JButton("Clear");
		clearButton.setBounds(0, 600, 400, 45);
		clearButton.addActionListener(this);
		draw.add(clearButton);

		add(draw);

		JMenuBar menuBar = new JMenuBar();

		//SIZE MENU
		JMenu sizeJM = new JMenu("Size");

		//Different size menu items
		JMenuItem[] sizes = new JMenuItem[3];
		sizes[0] = new JMenuItem("10");
		sizes[1] = new JMenuItem("25");
		sizes[2] = new JMenuItem("50");

		for(JMenuItem jmi : sizes){

			sizeJM.add(jmi);
			jmi.addActionListener(this);
		}

		//COLOR MENU
		JMenu colorJM = new JMenu("Color");

		//Different color menu items
		JMenuItem[] colors = new JMenuItem[4];
		colors[0] = new JMenuItem("Red");
		colors[1] = new JMenuItem("Green");
		colors[2] = new JMenuItem("Blue");
		colors[3] = new JMenuItem("Random");

		for(JMenuItem jmi : colors){

			colorJM.add(jmi);
			jmi.addActionListener(this);
		}

		menuBar.add(sizeJM);
		menuBar.add(colorJM);

		setJMenuBar(menuBar);

		setVisible(true);
	}

	//Menu item clicks & button click
	public void actionPerformed(ActionEvent ae) {

		if(ae.getActionCommand().equals("Clear")){

			draw.points.clear();
			draw.repaint();
			return;
		}

		else if(ae.getActionCommand().equals("Red")){

			colorName = "Red";
			currentColor = Color.RED;
		}

		else if(ae.getActionCommand().equals("Blue")){

			colorName = "Blue";
			currentColor = Color.BLUE;
		}

		else if(ae.getActionCommand().equals("Green")){

			colorName = "Green";
			currentColor = Color.GREEN;
		}

		else if(ae.getActionCommand().equals("Random")){

			colorName = "Random";

			int r = (int)(Math.random()*256);
			int g = (int)(Math.random()*256);
			int b = (int)(Math.random()*256);

			currentColor = new Color(r, g, b);
		}

		else
			currentSize = Integer.parseInt(ae.getActionCommand());

		setTitle("Paint- " + colorName + ":" + currentSize);
	}

	//Represents a point in draw panel. Multiple points make the drawing
	public class Point{

		private int x;
		private int y;
		private Color ptColor;
		private int ptSize;

		public Point(int x, int y){

			this.x = x;
			this.y = y;
			ptColor = currentColor;
			ptSize = currentSize;
		}
	}

	public class DrawPanel extends JPanel implements MouseMotionListener, MouseListener{

		private ArrayList<Point> points;

		public DrawPanel(){

			this.addMouseMotionListener(this);
			this.addMouseListener(this);
			points = new ArrayList<Point>();
		}

		public void paintComponent(Graphics g){

			super.paintComponent(g);
			setBackground(Color.white);

			for(Point p : points){

				g.setColor(p.ptColor);
				g.fillOval(p.x, p.y, p.ptSize, p.ptSize);
			}
		}

		//When mouse is dragged, place a dot
		public void mouseDragged(MouseEvent me) {

			mousePressed(me);
		}

		public void mouseMoved(MouseEvent arg0) {

		}

		public void mouseClicked(MouseEvent arg0) {

		}

		public void mouseEntered(MouseEvent arg0) {

		}

		public void mouseExited(MouseEvent arg0) {

		}

		//Called when mouse first presses on the panel; also called from mouse dragged to place dot
		public void mousePressed(MouseEvent me) {

			//Places an oval that is centered on the mouse pointer
			points.add(new Point(me.getX()-currentSize/2, me.getY()-currentSize/2));
			this.repaint();
		}

		public void mouseReleased(MouseEvent arg0) {

		}
	}

	public static void main(String[] args) {

		new LeeAndyPaint();
	}


}
