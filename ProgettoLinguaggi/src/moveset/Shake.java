package moveset;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import wiiusej.values.Orientation;
import wiiusej.values.RawAcceleration;

public class Shake extends AbstractStep {

	private final static String prefix_icon = "pictograms/shake ";
	private final static String icon_format = ".png";
	private ImageIcon icon;
	private float pitch_upperbound;
	private float roll_upperbound;
	private float pitch_lowerbound;
	private float roll_lowerbound;
	private float x_lowerbound;
	private float x_upperbound;

	public Shake(String dir, int points)
	{
		this.setDir(dir);
		this.setPoints(points);
		this.setSpan(1);
		icon = new ImageIcon(prefix_icon + dir + icon_format);
		if (dir.equals("forward")) {
			roll_lowerbound = -30;
			roll_upperbound = 50;
			pitch_lowerbound = -10;
			pitch_upperbound = 60;
			x_lowerbound = 70;
			x_upperbound = 180;
		} else if (dir.equals("up")) {
			roll_lowerbound = -170;
			roll_upperbound = 170;
			pitch_lowerbound = -170;
			pitch_upperbound = -170;
			x_lowerbound = 80;
			x_upperbound = 180;
		} else if (dir.equals("left")) {
			roll_lowerbound = -50;
			roll_upperbound = 60;
			pitch_lowerbound = -20;
			pitch_upperbound = 60;
			x_lowerbound = 50;
			x_upperbound = 150;
		} else if (dir.equals("down")) {
			roll_lowerbound = -60;
			roll_upperbound = 50;
			pitch_lowerbound = 40;
			pitch_upperbound = 70;
			x_lowerbound = 90;
			x_upperbound = 150;
		} else if (dir.equals("right")) {
			roll_lowerbound = -70;
			roll_upperbound = 90;
			pitch_lowerbound = -20;
			pitch_upperbound = 90;
			x_lowerbound = 50;
			x_upperbound = 140;
		}
	}

	public Shake(String dir, String points)
	{
		this.setDir(dir);
		if(points != null && points.matches("[0-9]+"))
			this.setPoints(Integer.parseInt(points));
		this.setSpan(1);
	}

	@Override
	public ImageIcon getIcon() {
		return icon;
	}

	@Override
	public String evaluate(ArrayList<Orientation> orientation_list, ArrayList<RawAcceleration> acceleration_list) {

		float roll_average = 0;
		float pitch_average = 0;
		float x_average = 0;
		for (Orientation orientation : orientation_list) {
			roll_average += orientation.getARoll();
			//System.out.println(orientation.getARoll());
			pitch_average += orientation.getAPitch();
		}
		
		for (RawAcceleration acceleration : acceleration_list) {
			x_average += acceleration.getX();
		}

		//System.out.println(orientation_list.size());
		roll_average = roll_average / orientation_list.size();
		pitch_average = pitch_average / orientation_list.size();

		//System.out.println(roll_average + " " +  pitch_average);
		if (new Random().nextInt(5) < 3 || (roll_lowerbound <= roll_average && roll_average <= roll_upperbound 
				&& pitch_lowerbound <= pitch_average && pitch_average <= pitch_upperbound
				&& x_lowerbound <= x_average && x_average <= x_upperbound) ) {
			
			return RES_OK;
		} else {
			
			return RES_X;
		}
	}


}
