package moveset;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import wiiusej.values.Orientation;
import wiiusej.values.RawAcceleration;

public class Fist extends AbstractStep{

	private final static String prefix_icon = "pictograms/fist ";
	private final static String icon_format = ".png";
	private ImageIcon icon;

	private float pitch_max, pitch_min;
	private float roll_max, roll_min;

	public Fist(String dir, int points)
	{
		this.setDir(dir);
		this.setPoints(points);
		this.setSpan(1);
		icon = new ImageIcon(prefix_icon + dir + icon_format);
		if (dir.equals("forward") || dir.equals("left") || dir.equals("up") )
		{
			pitch_max = roll_max = 170;
			pitch_min = roll_min = -170;
		} 
		else if (dir.equals("down")) {
			roll_max = 170;
			roll_min = -170;
			pitch_min= 90;
			pitch_max= 140;
		} 
		else if (dir.equals("right")) {
			roll_min = -90;
			roll_max = 60;
			pitch_max = 170;
			pitch_min = -170;
		}
	}

	public Fist(String dir, String points)
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
		// TODO Auto-generated method stub
		float roll_average = 0;
		float pitch_average = 0;
		float r_min = 0;
		float r_max = 0;
		float p_min = 0;
		float p_max  = 0;

		if (this.getDir().equals("forward") || this.getDir().equals("left") || this.getDir().equals("up") )
		{
			//glitch +-170 per roll e pitch
			for (Orientation orientation : orientation_list) {
				float temp = orientation.getRoll();
				if(temp > r_max)
					r_max = temp;
				else if (temp < r_min)
					r_min = temp;
				temp = orientation.getPitch();
				if(temp > p_max)
					p_max = temp;
				else if (temp < p_min)
					p_min = temp;
			}

			if(r_max >= roll_max && r_min <= roll_min && p_max >= pitch_max && p_min <= pitch_min)
				return RES_OK;
			else 
				return RES_X;
		} 
		else if (this.getDir().equals("down")) { 
			//glitch +-170 roll e 90 < pitch < 140
			for (Orientation orientation : orientation_list) {
				float temp = orientation.getRoll();

				if(temp > r_max)
					r_max = temp;
				else if (temp < r_min)
					r_min = temp;
				temp = orientation.getPitch();
				pitch_average += temp;
			}

			if(r_max >= roll_max && r_min <= roll_min && pitch_average >= pitch_min&& pitch_average <= pitch_max)
				return RES_OK;
			else 
				return RES_X;
		} 
		else if (this.getDir().equals("right")) {
			//glitch +-170 pitch e -90 < roll < 60
			for (Orientation orientation : orientation_list) {
				float temp = orientation.getPitch();

				if(temp > p_max)
					p_max = temp;
				else if (temp < p_min)
					p_min = temp;
				temp = orientation.getRoll();
				roll_average += temp;
			}

			if(p_max >= pitch_max && p_min <= pitch_min && roll_average >= roll_min && roll_average <= roll_max)
				return RES_OK;
			else 
				return RES_X;
		}
		
		else 
			return RES_X;
	}

}
