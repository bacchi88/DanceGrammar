package moveset;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import wiiusej.values.Orientation;
import wiiusej.values.RawAcceleration;

public class Swing extends AbstractStep {


	private final static String prefix_icon = "pictograms/swing ";
	private final static String icon_format = ".png";
	private ImageIcon icon;
	private String start_dir;

	public Swing(String span, String start_dir, String dir, String points)
	{
		if(span != null && span.matches("[0-9]+"));
		this.setSpan(Integer.parseInt(span));
		this.start_dir = start_dir;
		this.setDir(dir);
		if(points != null && points.matches("[0-9]+"));
		this.setPoints(Integer.parseInt(points));
	}

	public Swing(int span, String start_dir, String dir, int points)
	{

		this.setSpan(span);
		this.start_dir = start_dir;
		this.setDir(dir);
		this.setPoints(points);
		icon = new ImageIcon(prefix_icon + start_dir + " to " + dir + icon_format);
	}

	public String getDir2() {
		return start_dir;
	}

	public void setDir2(String dir2) {
		this.start_dir = dir2;
	}

	@Override
	public ImageIcon getIcon() {
		return icon;
	}

	@Override
	public String evaluate(ArrayList<Orientation> orientation_list, ArrayList<RawAcceleration> acceleration_list) {
		// TODO Auto-generated method stub
		return RES_OK;
	}

}
