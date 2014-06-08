package cc.visitor;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import cc.syntaxtree.Artist;
import cc.syntaxtree.Audio;
import cc.syntaxtree.Body;
import cc.syntaxtree.Cycle;
import cc.syntaxtree.Definition;
import cc.syntaxtree.Details;
import cc.syntaxtree.Instruction;
import cc.syntaxtree.LongMove;
import cc.syntaxtree.LongMoveInfo;
import cc.syntaxtree.Move;
import cc.syntaxtree.NodeToken;
import cc.syntaxtree.OnIteration;
import cc.syntaxtree.OnIterationElement;
import cc.syntaxtree.PointsExp;
import cc.syntaxtree.Scope;
import cc.syntaxtree.ShortMove;
import cc.syntaxtree.SingleStep;
import cc.syntaxtree.SpanExp;
import cc.syntaxtree.Statement;
import cc.syntaxtree.Title;
import cc.syntaxtree.Video;

public class TreeVisitor extends DepthFirstVoidArguVisitor<DefaultMutableTreeNode> {

	JTree tree;

	@Override
	public void visit(NodeToken n, DefaultMutableTreeNode argu) {

		DefaultMutableTreeNode child = new DefaultMutableTreeNode(n.tokenImage);
		argu.add(child);
	}

	@Override
	public void visit(Scope n, DefaultMutableTreeNode argu) {
		
		argu = new DefaultMutableTreeNode(n.getClass().getSimpleName());
		n.f0.accept(this, argu);
		n.f1.accept(this, argu);
		n.f2.accept(this, argu);
		n.f3.accept(this, argu);
		n.f4.accept(this, argu);
                //n.f5.accept(this, argu);
		tree = new JTree(argu);
	}

	@Override
	public void visit(Title n, DefaultMutableTreeNode argu) {
		
		DefaultMutableTreeNode child = new DefaultMutableTreeNode(n.getClass().getSimpleName());
		argu.add(child);
		super.visit(n, child);
	}

	@Override
	public void visit(Artist n, DefaultMutableTreeNode argu) {
		
		DefaultMutableTreeNode child = new DefaultMutableTreeNode(n.getClass().getSimpleName());
		argu.add(child);
		super.visit(n, child);
	}

	@Override
	public void visit(Audio n, DefaultMutableTreeNode argu) {

		DefaultMutableTreeNode child = new DefaultMutableTreeNode(n.getClass().getSimpleName());
		argu.add(child);
		super.visit(n, child);
	}
	
	@Override
	public void visit(Video n, DefaultMutableTreeNode argu) {

		DefaultMutableTreeNode child = new DefaultMutableTreeNode(n.getClass().getSimpleName());
		argu.add(child);
		super.visit(n, child);
	}

	@Override
	public void visit(Body n, DefaultMutableTreeNode argu) {

		DefaultMutableTreeNode child = new DefaultMutableTreeNode(n.getClass().getSimpleName());
		argu.add(child);
		super.visit(n, child);
	}

	@Override
	public void visit(Statement n, DefaultMutableTreeNode argu) {

		DefaultMutableTreeNode child = new DefaultMutableTreeNode(n.getClass().getSimpleName());
		argu.add(child);
		super.visit(n, child);
	}

	@Override
	public void visit(Definition n, DefaultMutableTreeNode argu) {

		DefaultMutableTreeNode child = new DefaultMutableTreeNode(n.getClass().getSimpleName());
		argu.add(child);
		super.visit(n, child);
	}

	@Override
	public void visit(Instruction n, DefaultMutableTreeNode argu) {

		DefaultMutableTreeNode child = new DefaultMutableTreeNode(n.getClass().getSimpleName());
		argu.add(child);
		super.visit(n, child);
	}

	@Override
	public void visit(SingleStep n, DefaultMutableTreeNode argu) {

		DefaultMutableTreeNode child = new DefaultMutableTreeNode(n.getClass().getSimpleName());
		argu.add(child);
		super.visit(n, child);
	}

	@Override
	public void visit(Move n, DefaultMutableTreeNode argu) {

		DefaultMutableTreeNode child = new DefaultMutableTreeNode(n.getClass().getSimpleName());
		argu.add(child);
		super.visit(n, child);
	}

	@Override
	public void visit(LongMove n, DefaultMutableTreeNode argu) {

		DefaultMutableTreeNode child = new DefaultMutableTreeNode(n.getClass().getSimpleName());
		argu.add(child);
		super.visit(n, child);
	}

	@Override
	public void visit(LongMoveInfo n, DefaultMutableTreeNode argu) {

		DefaultMutableTreeNode child = new DefaultMutableTreeNode(n.getClass().getSimpleName());
		argu.add(child);
		super.visit(n, child);
	}

	@Override
	public void visit(ShortMove n, DefaultMutableTreeNode argu) {

		DefaultMutableTreeNode child = new DefaultMutableTreeNode(n.getClass().getSimpleName());
		argu.add(child);
		super.visit(n, child);
	}

	@Override
	public void visit(Details n, DefaultMutableTreeNode argu) {

		DefaultMutableTreeNode child = new DefaultMutableTreeNode(n.getClass().getSimpleName());
		argu.add(child);
		super.visit(n, child);
	}

	@Override
	public void visit(Cycle n, DefaultMutableTreeNode argu) {

		DefaultMutableTreeNode child = new DefaultMutableTreeNode(n.getClass().getSimpleName());
		argu.add(child);
		super.visit(n, child);;
	}

	@Override
	public void visit(OnIteration n, DefaultMutableTreeNode argu) {

		DefaultMutableTreeNode child = new DefaultMutableTreeNode(n.getClass().getSimpleName());
		argu.add(child);
		super.visit(n, child);
	}

	@Override
	public void visit(OnIterationElement n, DefaultMutableTreeNode argu) {

		DefaultMutableTreeNode child = new DefaultMutableTreeNode(n.getClass().getSimpleName());
		argu.add(child);
		super.visit(n, child);
	}

	@Override
	public void visit(PointsExp n, DefaultMutableTreeNode argu) {

		DefaultMutableTreeNode child = new DefaultMutableTreeNode(n.getClass().getSimpleName());
		argu.add(child);
		super.visit(n, child);
	}

	@Override
	public void visit(SpanExp n, DefaultMutableTreeNode argu) {

		DefaultMutableTreeNode child = new DefaultMutableTreeNode(n.getClass().getSimpleName());
		argu.add(child);
		super.visit(n, child);
	}
	public JTree getTree() {
		return tree;
	}

}
