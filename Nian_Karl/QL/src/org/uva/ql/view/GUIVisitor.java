package org.uva.ql.view;

import java.util.ArrayList;

import javax.swing.JPanel;

import org.uva.ql.ast.expression.association.Parenthese;
import org.uva.ql.ast.expression.binary.And;
import org.uva.ql.ast.expression.binary.Divide;
import org.uva.ql.ast.expression.binary.Equal;
import org.uva.ql.ast.expression.binary.Greater;
import org.uva.ql.ast.expression.binary.GreaterEqual;
import org.uva.ql.ast.expression.binary.Less;
import org.uva.ql.ast.expression.binary.LessEqual;
import org.uva.ql.ast.expression.binary.Minus;
import org.uva.ql.ast.expression.binary.Multiply;
import org.uva.ql.ast.expression.binary.NotEqual;
import org.uva.ql.ast.expression.binary.Or;
import org.uva.ql.ast.expression.binary.Plus;
import org.uva.ql.ast.expression.literal.BoolLiteral;
import org.uva.ql.ast.expression.literal.Identifier;
import org.uva.ql.ast.expression.literal.IntLiteral;
import org.uva.ql.ast.expression.literal.StrLiteral;
import org.uva.ql.ast.expression.unary.Negative;
import org.uva.ql.ast.expression.unary.Not;
import org.uva.ql.ast.expression.unary.Positive;
import org.uva.ql.ast.questionnaire.Form;
import org.uva.ql.ast.questionnaire.Questionnaire;
import org.uva.ql.ast.statement.Block;
import org.uva.ql.ast.statement.IfStatement;
import org.uva.ql.ast.statement.QuestionCompute;
import org.uva.ql.ast.statement.QuestionNormal;
import org.uva.ql.ast.statement.Statement;
import org.uva.ql.ast.type.BoolType;
import org.uva.ql.ast.type.IntType;
import org.uva.ql.ast.type.StrType;
import org.uva.ql.view.widgit.QLCheckBox;
import org.uva.ql.view.widgit.QLNumberTextField;
import org.uva.ql.view.widgit.QLTextField;
import org.uva.ql.view.widgit.QLWidget;
import org.uva.ql.visitor.Visitor;

public class GUIVisitor implements Visitor<Object> {

	@Override
	public Panel visit(IfStatement ifStatement) {
		
		
		System.out.println("if statement");
		return null;
	}

	@Override
	public Panel visit(QuestionNormal questionStatement) {
		System.out.println("normalquestion");
		QLWidget<?> widget = (QLWidget<?>) questionStatement.getType().accept(this);
		QuestionPanel questionView = new QuestionPanel(questionStatement,widget);
		return questionView;
	}

	@Override
	public Panel visit(QuestionCompute questionComputeStatement) {
		System.out.println("compute question");
		return null;
	}

	@Override
	public ArrayList<Panel> visit(Block blockStatement) {
		ArrayList<Panel> components = new ArrayList<Panel>();
		for (Statement statement : blockStatement.getStatements()) {
			components.add((Panel) statement.accept(this));
		}
		return components;
	}

	@Override
	public FormFrame visit(Form form) {
		FormFrame formView = new FormFrame();
		ArrayList<Panel> panelList= ((ArrayList<Panel>) form.getBlock().accept(this));
		for (Panel panel: panelList) {
			formView.add(panel);
		}
		formView.setVisible(true);
		return formView;
	}

	@Override
	public Object visit(Questionnaire questionnaire) {
		System.out.println("Visiting block");
		ArrayList<FormFrame> formViews = new ArrayList<FormFrame>();
		for (Form form : questionnaire.getForms()) {
			formViews.add((FormFrame) form.accept(this));
		}
		return formViews;
	}

	@Override
	public Object visit(Not node) {
		return null;
	}

	@Override
	public Object visit(Positive node) {
		return null;
	}

	@Override
	public Object visit(Negative node) {
		return null;
	}

	@Override
	public Object visit(Plus node) {
		return null;
	}

	@Override
	public Object visit(Minus node) {
		return null;
	}

	@Override
	public Object visit(Multiply node) {
		return null;
	}

	@Override
	public Object visit(Divide node) {
		return null;
	}

	@Override
	public Object visit(And node) {
		return null;
	}

	@Override
	public Object visit(Or node) {
		return null;
	}

	@Override
	public Object visit(Equal node) {
		return null;
	}

	@Override
	public Object visit(NotEqual node) {
		return null;
	}

	@Override
	public Object visit(Greater node) {
		return null;
	}

	@Override
	public Object visit(GreaterEqual node) {
		return null;
	}

	@Override
	public Object visit(Less node) {
		return null;
	}

	@Override
	public Object visit(LessEqual node) {
		return null;
	}

	@Override
	public Object visit(Identifier node) {
		return null;
	}

	@Override
	public Object visit(IntLiteral node) {
		return null;
	}

	@Override
	public Object visit(BoolLiteral node) {
		return null;
	}

	@Override
	public Object visit(StrLiteral node) {
		return null;
	}

	@Override
	public Object visit(Parenthese node) {
		return null;
	}

	@Override
	public QLWidget<Integer> visit(IntType node) {
		return new QLNumberTextField();
	}

	@Override
	public QLWidget<Boolean> visit(BoolType node) {
		return new QLCheckBox();
	}

	@Override
	public QLWidget<String> visit(StrType node) {
		return new QLTextField();
	}
}
