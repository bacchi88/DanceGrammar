package cc.visitor;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import moveset.AbstractStep;
import cc.syntaxtree.*;

public class PrinterVisitor extends DepthFirstRetVisitor<String> {

	private ArrayList<HashMap<String, Move>> variables;
	private int time;
	private int loop_level;
	private ArrayList<Integer> loop_iteration_stack;	//per ogni loop, tengo traccia del ciclo a cui si trova
	private String title_string;
	private String artist_string;
	private String audio_string;
	private String video_string;
	private String body_string;

	public static final String SUCCESS = "Coreography txt file printed.";
	public static final String THRESHOLD_EXCEEDED = "Coreography lasts too long. Check Threshold value";
	public static final String COREOGRAPHY_EMPTY = "Coreography has no moves. There should be at least one step to dance!";
	public static final int THRESHOLD = 600;

	public PrinterVisitor() {
		super();
                body_string = "";
		variables = new ArrayList<>();
		variables.add(new HashMap<String, Move>());
		time = 1;
		loop_level = -1;
		loop_iteration_stack = new ArrayList<Integer>();
	}

	public void printfile() throws IOException
	{
		PrintWriter outFile = new PrintWriter(new FileWriter("./" + artist_string.substring(artist_string.indexOf(":")+1) + " - " + title_string.substring(title_string.indexOf(":")+1) + ".txt"));
		outFile.println(title_string);
		outFile.println(artist_string);
		outFile.println(audio_string);
		outFile.println(video_string);
		outFile.print(body_string);
		outFile.close();
	}
	@Override
	public String visit(NodeToken n) {
		return n.tokenImage;
	}

	@Override
	public String visit(Scope n) {
		try {
			// f0 -> Title()
			title_string = (n.f0.accept(this));
			// f1 -> Artist()
			artist_string = (n.f1.accept(this));
			// f2 -> Audio()
			audio_string = (n.f2.accept(this));
			// f3 -> Body()
			video_string = (n.f3.accept(this));
			// f4 -> Body()
			body_string = (n.f4.accept(this));
			// f5 -> <EOF>
			return SUCCESS;
		} catch (Exception e) {
			return e.toString();
		}
	}

	@Override
	public String visit(Title n) {
		return n.f0.tokenImage + n.f1.tokenImage + n.f2.tokenImage.substring(1, n.f2.tokenImage.length() - 1);
	}

	@Override
	public String visit(Artist n) {
		return n.f0.tokenImage + n.f1.tokenImage + n.f2.tokenImage.substring(1, n.f2.tokenImage.length() - 1);
	}

	@Override
	public String visit(Audio n) {
		return n.f0.tokenImage + n.f1.tokenImage + n.f2.tokenImage.substring(1, n.f2.tokenImage.length() - 1);
	}

	@Override
	public String visit(Video n) {
		return n.f0.tokenImage + n.f1.tokenImage + n.f2.tokenImage.substring(1, n.f2.tokenImage.length() - 1);
	}

	@Override
	public String visit(Body n) {
		String res = "";

		if (n.f3.present()) {
			for (INode statement : n.f3.nodes) {
				res = res + statement.accept(this);
			}
		}

		return res;
	}

	@Override
	public String visit(Statement n) {

		return n.f0.choice.accept(this);
	}

	@Override
	public String visit(Definition n) {

		HashMap<String, Move> temp = variables.get(loop_level + 1);
		temp.put(n.f1.tokenImage, n.f2);
		variables.set(loop_level + 1, temp);

		return "";
	}

	@Override
	public String visit(Instruction n) {

		return n.f0.choice.accept(this);
	}

	@Override
	public String visit(SingleStep n) {

		String line = "";
		switch (n.f1.which) {
		case 0:
			line = getTimeAsString() + n.f1.choice.accept(this);
			break;
		case 1:

			int temp = searchVariable( ((NodeToken) n.f1.choice).tokenImage );
			//CONTROLLO: VARIABILE NON DEFINITA
			line = getTimeAsString() + variables.get(temp).get(((NodeToken) n.f1.choice).tokenImage).accept(this);
			break;
		default:  // Should never occur
			throw new Error("Unknown choice:" + n.f1.which);
		}
		return line + NodeToken.LS;
	}

	@Override
	public String visit(Move n) {
		return n.f0.accept(this);
	}

	@Override
	public String visit(LongMove n) {
		String res = n.f0.choice.accept(this);

		if (n.f1.present()) {
			n.f1.accept(this);
		} else {
			time = time + AbstractStep.DEFAULT_SPAN;
		}

		return res;
	}

	@Override
	public String visit(LongMoveInfo n) {

		String move = "";

		//CONTROLLO SEMANTICO DIREZIONE A DIREZIONE
		switch (n.f0.which) {
		case 0:
			NodeSequence s = (NodeSequence) n.f0.choice;
			String dir = s.elementAt(1).accept(this);

			move = s.elementAt(0).accept(this) + " " + dir + " " + s.elementAt(2).accept(this);
			break;
		case 1:
			move = n.f0.choice.accept(this);
			break;
		default:  // Should never occur
			throw new Error("Unknown choice:" + n.f0.which);
		}
		String details = n.f1.accept(this);
		return move + " " + details;
	}

	@Override
	public String visit(Details n) {

		String det = n.f0.tokenImage;
		if (n.f1.present()) {
			det = det + " " + n.f1.accept(this);
		} else {
			det = det + " " + "points: " + AbstractStep.DEFAULT_POINTS;
		}
		return det;
	}

	@Override
	public String visit(ShortMove n) {

		time++;
		String result = n.f0.choice.accept(this) + " " + n.f1.accept(this);
		return result;
	}

	@Override
	public String visit(Cycle n) {

		//CONTROLLO SEMANTICO CICLO 
		String result = "";
		int lenght = Integer.parseInt(n.f1.accept(this));


		loop_iteration_stack.add(1);

		for (int i = 0; i < lenght; i++) {
			loop_level++;
			variables.add(new HashMap<String, Move>());

			for (INode node : n.f4.nodes) {
				result = result + node.accept(this);
			}
			//System.out.println(variables.size());
			//variables.set(variables.size() -1, new HashMap<String, Move>());
			variables.remove(variables.size() -1);
			//System.out.println(variables.size());
			loop_iteration_stack.set(loop_level, loop_iteration_stack.get(loop_level) + 1);
			loop_level--;
		}

		loop_iteration_stack.remove(loop_iteration_stack.size() - 1);

		return result;
	}

	@Override
	public String visit(OnIteration n) {

		String result = "";
		for (INode iteration_element : n.f2.nodes) {
			result = result + iteration_element.accept(this);
		}
		return result;
	}

	@Override
	public String visit(OnIterationElement n) {

		// CONTROLLO SEMANTICO NUMERO ITERAZIONE
		if (loop_iteration_stack.get(loop_level) == Integer.parseInt(n.f0.tokenImage)) {
			switch (n.f2.which) {
			case 0:
				return getTimeAsString() + n.f2.choice.accept(this) + NodeToken.LS;
			case 1:
				int temp = searchVariable( ((NodeToken) n.f2.choice).tokenImage );
				return getTimeAsString() + variables.get(temp).get(((NodeToken) n.f2.choice).tokenImage).accept(this) + NodeToken.LS;
			default:  // Should never occur
				throw new Error("Unknown choice:" + n.f2.which);
			}
		} else {
			return "";
		}
	}

	@Override
	public String visit(PointsExp n) {

		//EFFETTUARE CONTROLLO SEMANTICO PUNTEGGIO
		return n.f0.tokenImage + n.f1.tokenImage + " " + n.f2.tokenImage;
	}

	@Override
	public String visit(SpanExp n) {

		//EFFETTUARE CONTROLLO SEMANTICO SPAN
		time = time + Integer.parseInt(n.f2.tokenImage);
		return "";
	}

	private String getTimeAsString() {
		int seconds = time % 60;
		int minutes = time / 60;
		String sec = (seconds > 9 ? seconds + "" : "0" + seconds);
		String min = (minutes > 9 ? minutes + "" : "0" + minutes);
		return "<" + min + ":" + sec + ">";
	}

	private int searchVariable(String name)
	{
		for(int i = loop_level + 1; i >-1 ; i--)
		{
			if(variables.get(i).containsKey(name))
				return i;
		}

		//CONTROLLARE VARIABILE NON PRESENTE
		return -1;
	}
	public boolean isTimeOutOfThreshold()
	{
		return time > THRESHOLD;
	}

	public boolean isCoreographyEmpty()
	{
		return body_string.isEmpty();
	}
}
