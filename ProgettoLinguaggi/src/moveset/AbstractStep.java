package moveset;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import wiiusej.values.Orientation;
import wiiusej.values.RawAcceleration;

public abstract class AbstractStep {

	public final static int DEFAULT_POINTS = 100;
	public final static int DEFAULT_SPAN = 1;
	public final static String DIR_UP = "up";
	public final static String DIR_DOWN = "down";
	public final static String DIR_LEFT = "left";
	public final static String DIR_RIGHT = "right";
	public final static String DIR_FORWARD = "forward";
	public final static String RES_OK = "OK!";
	public final static String RES_X = "X";
	public final static String RES_EMPTY = "";
	
	
	private int span;
	private String dir;
	private int points;
	
	public int getSpan() {
		return span;
	}
	public void setSpan(int span) {
		this.span = span;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	
	abstract public ImageIcon getIcon();
	abstract public String evaluate(ArrayList<Orientation> orientation_list, ArrayList<RawAcceleration> acceleration_list);
}
