package moveset;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import wiiusej.values.Orientation;
import wiiusej.values.RawAcceleration;

public class Free extends AbstractStep {

	private final static String prefix_icon = "pictograms/free";
	private final static String icon_format = ".png";
	private ImageIcon icon;
	
	public Free(int span)
	{
		this.setSpan(span);
		this.setDir(null);
		this.setPoints(0);
		icon = new ImageIcon(prefix_icon + icon_format);
	}
	
	public Free()
	{
		this.setSpan(1);
		this.setDir(null);
		this.setPoints(0);
	}

	@Override
	public ImageIcon getIcon() {
		 return icon;
	}

	@Override
	public String evaluate(ArrayList<Orientation> orientation_list, ArrayList<RawAcceleration> acceleration_list) {
		// TODO Auto-generated method stub
		return RES_EMPTY;
	}
}
