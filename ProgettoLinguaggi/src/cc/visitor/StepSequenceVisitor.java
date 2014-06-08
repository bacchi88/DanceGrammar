package cc.visitor;

import java.util.ArrayList;
import java.util.HashMap;

import moveset.AbstractStep;
import moveset.StepFactory;
import cc.syntaxtree.Artist;
import cc.syntaxtree.Audio;
import cc.syntaxtree.Cycle;
import cc.syntaxtree.Definition;
import cc.syntaxtree.Details;
import cc.syntaxtree.INode;
import cc.syntaxtree.LongMove;
import cc.syntaxtree.LongMoveInfo;
import cc.syntaxtree.Move;
import cc.syntaxtree.NodeSequence;
import cc.syntaxtree.NodeToken;
import cc.syntaxtree.OnIteration;
import cc.syntaxtree.OnIterationElement;
import cc.syntaxtree.PointsExp;
import cc.syntaxtree.ShortMove;
import cc.syntaxtree.SingleStep;
import cc.syntaxtree.SpanExp;
import cc.syntaxtree.Title;
import cc.syntaxtree.Video;

public class StepSequenceVisitor extends DepthFirstVoidVisitor{

	private ArrayList<AbstractStep> sequence;
	private ArrayList<HashMap<String, Move>> variables;

	private int loop_level;
	private ArrayList<Integer> loop_iteration_stack;

	private String coreography_name;
	private String mp3_filename;
	private String video_filename;
	private String move_name;
	private String move_dir;
	private int move_span;
	private int move_points;

	public StepSequenceVisitor() {
		super();
		variables = new ArrayList<>();
		variables.add(new HashMap<String, Move>());
		loop_level = -1;
		loop_iteration_stack = new ArrayList<Integer>();
		coreography_name = "";
		mp3_filename = "";
		video_filename = "";
		move_dir = "";
		move_name = "";
		move_points = AbstractStep.DEFAULT_POINTS;
		move_span = AbstractStep.DEFAULT_SPAN;
		sequence = new ArrayList<AbstractStep>();
	}


	@Override
	public void visit(Title n) {
		coreography_name = n.f2.tokenImage.replace("\"", "");
	}

	@Override
	public void visit(Artist n) {

		coreography_name  = coreography_name  + " - " + n.f2.tokenImage.replace("\"", "");
	}

	@Override
	public void visit(Audio n) {

		mp3_filename = n.f2.tokenImage.replace("\"", "");
	}

	@Override
	public void visit(Video n) {

		video_filename = n.f2.tokenImage.replace("\"", "");
	}

	@Override
	public void visit(Definition n) {

		HashMap<String, Move> temp = variables.get(loop_level + 1);
		temp.put(n.f1.tokenImage, n.f2);
		variables.set(loop_level + 1, temp);

	}

	@Override
	public void visit(SingleStep n) {

		switch (n.f1.which) {
		case 0:
			n.f1.choice.accept(this);
			break;
		case 1:
			//CONTROLLO: VARIABILE NON DEFINITA

			int temp = searchVariable( ((NodeToken) n.f1.choice).tokenImage );
			//CONTROLLO: VARIABILE NON DEFINITA
			variables.get(temp).get(((NodeToken) n.f1.choice).tokenImage).accept(this);

			break;
		default:  // Should never occur
			throw new Error("Unknown choice:" + n.f1.which);
		}

	}

	@Override
	public void visit(Move n) {
		// TODO Auto-generated method stub
		super.visit(n);

		sequence.add(StepFactory.createStep(move_name, move_dir, move_span, move_points));
		//resetMoveInfo();
	}

	@Override
	public void visit(LongMove n) {

		switch (n.f0.which) {
		case 0:
			n.f0.choice.accept(this);
			break;
		case 1:
			move_name = ((NodeToken) n.f0.choice).tokenImage ;
			break;
		default:  // Should never occur
			throw new Error("Unknown choice:" + n.f0.which);
		}

		if (n.f1.present()) {
			n.f1.accept(this);
		} else {

			move_span = AbstractStep.DEFAULT_SPAN;
		}
	}

	@Override
	public void visit(LongMoveInfo n) {

		switch (n.f0.which) {
		case 0:

			NodeSequence s = (NodeSequence) n.f0.choice;

			move_name = ((NodeToken)s.elementAt(0)).tokenImage + " " + ((NodeToken)s.elementAt(1)).tokenImage;
			break;
		case 1:
			move_name = ((NodeToken) n.f0.choice).tokenImage ;
			break;
		default:  // Should never occur
			throw new Error("Unknown choice:" + n.f0.which);
		}
		n.f1.accept(this);
	}

	@Override
	public void visit(ShortMove n) {

		move_span = 1;
		move_name = ((NodeToken)n.f0.choice).tokenImage;
		n.f1.accept(this);
	}

	@Override
	public void visit(Details n) {

		move_dir = n.f0.tokenImage;
		if (n.f1.present()) {
			n.f1.accept(this);
		} else {
			move_points = AbstractStep.DEFAULT_POINTS;
		}
	}

	@Override
	public void visit(Cycle n) {

		int lenght = Integer.parseInt(n.f1.tokenImage);

		
		loop_iteration_stack.add(1);

		for (int i = 0; i < lenght; i++) {
			loop_level++;
			variables.add(new HashMap<String, Move>());
			
			for (INode node : n.f4.nodes) {
				node.accept(this);
			}

			variables.remove(variables.size() -1);
			loop_iteration_stack.set(loop_level, loop_iteration_stack.get(loop_level) + 1);
			loop_level--;
		}

		loop_iteration_stack.remove(loop_iteration_stack.size() - 1);
		
	}

	@Override
	public void visit(OnIteration n) {
		// TODO Auto-generated method stub
		super.visit(n);
	}

	@Override
	public void visit(OnIterationElement n) {

		if (loop_iteration_stack.get(loop_level) == Integer.parseInt(n.f0.tokenImage)) {
			switch (n.f2.which) {
			case 0:
				n.f2.choice.accept(this);
				break;
			case 1:
				int temp = searchVariable( ((NodeToken) n.f2.choice).tokenImage );
				variables.get(temp).get(((NodeToken) n.f2.choice).tokenImage).accept(this);

				break;
			default:  // Should never occur
				throw new Error("Unknown choice:" + n.f2.which);
			}
		}
	}

	@Override
	public void visit(PointsExp n) {
		move_points = Integer.parseInt(n.f2.tokenImage);
	}

	@Override
	public void visit(SpanExp n) {

		move_span = Integer.parseInt(n.f2.tokenImage);
	}

	public ArrayList<AbstractStep> getSequence()
	{
		return sequence;
	}

	public String getCoreography_name()	{
		return coreography_name;
	}
	public String getMp3_filename() {
		return mp3_filename;
	}

	public String getVideo_filename() {
		return video_filename;
	}

	public void print()
	{
		for (AbstractStep step : sequence) {
			System.out.println(step.getClass().getSimpleName() + " " + step.getDir() + " " + step.getPoints() + " " + step.getSpan());
		}
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

}
