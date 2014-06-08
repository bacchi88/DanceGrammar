package moveset;

public class StepFactory {

	public static AbstractStep createStep(String name, String dir, int span, int points)
	{
		if(name.equals("fist"))
			return new Fist(dir, points);
		else if(name.equals("shake"))
			return new Shake(dir, points);
		else if(name.equals("free"))
			return new Free(span);
		else if(name.equals("steady"))
			return new Steady(span, dir, points);
		else if(name.startsWith("swing"))
			return new Swing(span, name.substring(6).trim(),  dir, points);
		else
			return null;
	}
}
