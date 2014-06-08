package moveset;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import wiiusej.values.Orientation;
import wiiusej.values.RawAcceleration;

public class Steady extends AbstractStep {

    private final static String prefix_icon = "pictograms/steady ";
    private final static String icon_format = ".png";
    private ImageIcon icon;
    private float pitch_upperbound;
    private float roll_upperbound;
    private float pitch_lowerbound;
    private float roll_lowerbound;

    public Steady(int span, String dir, int points) {
        this.setSpan(span);
        this.setDir(dir);
        this.setPoints(points);
        icon = new ImageIcon(prefix_icon + dir + icon_format);
        if (dir.equals("forward")) {
            pitch_upperbound = roll_upperbound = 15;
            pitch_lowerbound = roll_lowerbound = 0;
        } else if (dir.equals("up")) {
            roll_lowerbound = 100;
            roll_upperbound = 180;
            pitch_lowerbound = 80;
            pitch_upperbound = 100;
        } else if (dir.equals("left")) {
            roll_lowerbound = 60;
            roll_upperbound = 90;
            pitch_lowerbound = 10;
            pitch_upperbound = 30;
        } else if (dir.equals("down")) {
            roll_lowerbound = 0;
            roll_upperbound = 20;
            pitch_lowerbound = 60;
            pitch_upperbound = 100;
        } else if (dir.equals("right")) {
            roll_lowerbound = 20;
            roll_upperbound = 90;
            pitch_lowerbound = 10;
            pitch_upperbound = 50;
        }

    }

    public Steady(String span, String dir, String points) {
        if (span != null && span.matches("[0-9]+"));
        this.setSpan(Integer.parseInt(span));
        this.setDir(dir);
        if (points != null && points.matches("[0-9]+"));
        this.setPoints(Integer.parseInt(points));
    }

    @Override
    public ImageIcon getIcon() {

        return icon;
    }

    @Override
    public String evaluate(ArrayList<Orientation> orientation_list, ArrayList<RawAcceleration> acceleration_list) {

        float roll_average = 0;
        float pitch_average = 0;
        int begin = orientation_list.size() / 4;
        int end = orientation_list.size() * 3 / 4;
        int lenght = end - begin;
        
        
        /*for (Orientation orientation : orientation_list) {
            roll_average += Math.abs(orientation.getRoll());
            pitch_average += Math.abs(orientation.getPitch());
        }

        roll_average = roll_average / orientation_list.size();
        pitch_average = pitch_average / orientation_list.size();*/
        
        for (int i = begin; i<end; i++) {
            roll_average += Math.abs(orientation_list.get(i).getRoll());
            pitch_average += Math.abs(orientation_list.get(i).getPitch());
        }

        roll_average = roll_average / lenght;
        pitch_average = pitch_average / lenght;

        //System.out.println(roll_average + " " +  pitch_average);
        if (roll_lowerbound <= roll_average && roll_average <= roll_upperbound && pitch_lowerbound <= pitch_average && pitch_average <= pitch_upperbound) {
            
            return RES_OK;
        } else {
        	
            return RES_X;
        }
    }

}
