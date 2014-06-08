package cc.visitor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import cc.syntaxtree.Cycle;
import cc.syntaxtree.Definition;
import cc.syntaxtree.Details;
import cc.syntaxtree.INode;
import cc.syntaxtree.LongMove;
import cc.syntaxtree.LongMoveInfo;
import cc.syntaxtree.NodeSequence;
import cc.syntaxtree.NodeToken;
import cc.syntaxtree.OnIteration;
import cc.syntaxtree.OnIterationElement;
import cc.syntaxtree.PointsExp;
import cc.syntaxtree.SingleStep;
import cc.syntaxtree.SpanExp;

public class SemanticsVisitor extends DepthFirstVoidVisitor{

	private ArrayList<String> warnings;
	private ArrayList<String> errors;

	private ArrayList<Set<String>> variables;
	private int loop_level;
	private ArrayList<Integer> loop_iteration_stack;

	private int visiting; //0 -> mossa 1-> definizione ->2 mossa nell'onIteration 4->ciclo
	private String what_visiting[];
	private int count_visiting[];

	private boolean isSwing;
	private String swing_dir1;

	public SemanticsVisitor()
	{
		warnings = new ArrayList<String>();
		errors = new ArrayList<String>();

		visiting = 0;
		what_visiting = new String[4];
		what_visiting[0]= "move";
		what_visiting[1]= "definition";
		what_visiting[2]= "move inside 'On Iteration'";
		what_visiting[3]= "cycle";

		count_visiting = new int[4];
		for (int i = 0; i< count_visiting.length; i++)
			count_visiting[i] = 0;

		loop_level = -1;
		variables = new ArrayList<>();
		variables.add(new HashSet<String>() );
		loop_iteration_stack = new ArrayList<Integer>();

		isSwing = false;
		swing_dir1 = "";
	}

	@Override
	public void visit(Definition n) {

		visiting = 1;
		count_visiting[visiting]++;
		Set<String> temp = variables.get(loop_level + 1);
		temp.add(n.f1.tokenImage);
		variables.set(loop_level + 1, temp);

		//variables.add(n.f1.tokenImage);
		n.f2.accept(this);
	}

	@Override
	public void visit(SingleStep n) {

		visiting = 0;
		count_visiting[visiting]++;

		switch (n.f1.which)
		{
		case 0:
			n.f1.accept(this);
			break;
		case 1:

			int temp = searchVariable( ((NodeToken) n.f1.choice).tokenImage );
			
			if ( temp < 0) {
				errors.add("Error at move " + count_visiting[visiting] + ": undefined variable " + ((NodeToken)n.f1.choice).tokenImage);
			}
			break;
		default:  // Should never occur
			throw new Error("Unknown choice:" + n.f1.which );
		}
	}
        
        @Override
        public void visit(LongMove n)
        {
            if(n.f0.which == 0)
                n.f0.choice.accept(this);
            
            if(n.f1.present())
                n.f1.accept(this);
        }
        
	@Override
	public void visit(LongMoveInfo n) {
      
		if(n.f0.which == 0)
		{
			isSwing = true; 
			NodeToken dir = (NodeToken) ((NodeSequence)n.f0.choice).nodes.get(1);
			swing_dir1 = dir.tokenImage;
		}
		else 
			isSwing = false;
		n.f1.accept(this);
	}

	@Override
	public void visit(Details n) {
            
		if(isSwing && swing_dir1.equals(n.f0.tokenImage.toString()))
		{
			errors.add("Error at " + what_visiting[visiting] + " " + count_visiting[visiting] +": swing first and last directions must not be the same");
			
		}
		
                isSwing = false;
                if(n.f1.present())
                    n.f1.accept(this);
	}

	@Override
	public void visit(Cycle n) {

		count_visiting[3]++;

		int lenght = Integer.parseInt(n.f1.tokenImage);
		if(lenght == 0)
			errors.add("Loop" + " " + count_visiting[3] + ": no repetition?");
		else if(lenght == 1)
			errors.add("Loop" + " " + count_visiting[3] + ": useless.");
		else if(lenght > 5)
			warnings.add("Warning! Loop" + " " + count_visiting[3] + " requires more than 5 iterations");

		loop_level++;
		loop_iteration_stack.add(lenght);
		variables.add(new HashSet<String>());

		for (INode node : n.f4.nodes) {
			node.accept(this);
		}
		
		variables.remove(variables.size() -1);
		loop_iteration_stack.remove(loop_iteration_stack.size()-1);
		loop_level--;
	}

	@Override
	public void visit(OnIteration n) {

		visiting = 2;
		count_visiting[visiting]++;
		n.f2.accept(this);
	}

	@Override
	public void visit(OnIterationElement n) {

		if(Integer.parseInt(n.f0.tokenImage) > loop_iteration_stack.get(loop_level))
			errors.add("Error: " + what_visiting[visiting] + " " + count_visiting[visiting] + ": iteration number " + Integer.parseInt(n.f0.tokenImage) 
					+ " not possibile, as loop lasts " + loop_iteration_stack.get(loop_level));
		if(n.f2.which == 1)
		{
			if(searchVariable(((NodeToken)n.f2.choice).tokenImage) < 0 )
				errors.add("Error: " + what_visiting[visiting] + " " + count_visiting[visiting] + ": undefined variable" + ((NodeToken)n.f2.choice).tokenImage);
		}
		
		n.f2.choice.accept(this);
	}

	@Override
	public void visit(PointsExp n) {

		int res = Integer.parseInt(n.f2.tokenImage);
		if (res < 10 || res > 100)
			errors.add("Error: " + what_visiting[visiting] + " " + count_visiting[visiting] + ": score out of bounds [10, 100]");
		//super.visit(n);
	}

	@Override
	public void visit(SpanExp n) {

		int res = Integer.parseInt(n.f2.tokenImage);
		if (res < 1 || res > 5)
			errors.add("Error: " + what_visiting[visiting] + " " + count_visiting[visiting] +": span out of bounds [1, 5]");
		else if ( res >= 4)
			warnings.add("Warning! " + what_visiting[visiting] + " " + count_visiting[visiting] + ": too slow");
		//super.visit(n);
	}

	public ArrayList<String> getWarnings() {
		return warnings;
	}

	public ArrayList<String> getErrors() {
		return errors;
	}

	private int searchVariable(String name)
	{
		for(int i = loop_level + 1; i >-1 ; i--)
		{
			if(variables.get(i).contains(name))
				return i;
		}
		//CONTROLLARE VARIABILE NON PRESENTE
		return -1;
	}
}
