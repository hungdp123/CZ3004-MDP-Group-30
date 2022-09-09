package mdp_test_own;

import java.util.ArrayList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;
import java.awt.Font;

public class MainView extends JPanel {

	public final static int PANEL_WIDTH = 600;
	public final static int PANEL_HEIGHT = 600;	
	private List<GridCell> gridCells = new ArrayList<>();
	private List<Location> obstacleLocations = new ArrayList<>();
	private int numberOfCols=20;
	private int numberOfRows=20;
	private int gridWidth;
	private int gridHeight;
	private Robot robot = new Robot();
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public MainView() {
		setSize(PANEL_WIDTH, PANEL_HEIGHT);
		calculateDimensions();
		generateRandomObstacels(5);
		robot.moveForward();
		populateGridCells();
		System.out.println("Finished Populating Grid Cells.");
	}
	
	private void generateRandomObstacels(int numberOfObstacles) {
		Random random = new Random();
		for (int i=0;i<numberOfObstacles;i++) {
			int x = random.nextInt(numberOfCols);
			int y = random.nextInt(numberOfRows);
			Location obstacleLocation = new Location(x,y);
			obstacleLocations.add(obstacleLocation);
		}
	}
	
	private void calculateDimensions() {
		gridWidth = PANEL_WIDTH/numberOfCols;
		gridHeight = PANEL_HEIGHT/numberOfRows;
	}
	
	private void populateGridCells() {
		for (int y=0; y<numberOfRows; y++) {
			for (int x=0; x<numberOfCols; x++) {
				Location location = new Location(x,y);
				boolean isObstacle = locationIsObstacle(location);
				GridCell gridCell = new GridCell(location, isObstacle);
				gridCells.add(gridCell);
			}
		}
	}
	
	private boolean locationIsObstacle(Location location) {
		for (Location obstacleLocation:obstacleLocations) {
			if (obstacleLocation.isSameLocation(location)) {
				return true;
			}
		}
		return false;
	}
	
	public void paint(Graphics g) {
		paintGridCells(g);
		paintRobot(g);
	}
	
	private void paintRobot(Graphics g) {
		g.setColor(Color.blue);
		Location robotExactLocation = robot.getExactLocation();
		if (robotExactLocation == null) {
			Location robotGridLocation = robot.getCurrentGridCell().getLocation();
			int x = robotGridLocation.getX() * gridWidth;
			int y = robotGridLocation.getY() * gridHeight;
			robotExactLocation = new Location(x,y);
		}
		int robotWidth = gridWidth*3;
		int robotHeight = gridHeight * 3;
		g.fillOval(robotExactLocation.getX(), robotExactLocation.getY(), robotWidth, robotHeight);
		g.setColor(Color.red);
		if (robot.getOrientation() == 0) {
			g.fillRect(robotExactLocation.getX()+gridWidth, robotExactLocation.getY(), gridWidth, gridHeight);
		} else if (robot.getOrientation() == 1) {
			g.fillRect(robotExactLocation.getX()+(gridWidth*2), robotExactLocation.getY()+gridHeight, gridWidth, gridHeight);
		} else if (robot.getOrientation() == 2) {
			g.fillRect(robotExactLocation.getX()+gridWidth, robotExactLocation.getY()+(gridHeight*2), gridWidth, gridHeight);
		} else {
			g.fillRect(robotExactLocation.getX(), robotExactLocation.getY()+gridHeight, gridWidth, gridHeight);
		}
//		g.fillOval(0, 0, robotWidth, robotHeight);
	}
	
	private void paintGridCells(Graphics g) {
		for (GridCell gridCell : gridCells) {
			int x = gridCell.getLocation().getX() * gridWidth;
			int y = gridCell.getLocation().getY() * gridHeight;
			if (gridCell.isObstacle()) {
				g.fillRect(x,y,gridWidth,gridHeight);
			} else {
				g.drawRect(x,y,gridWidth,gridHeight);
			}
		}
	}

}
