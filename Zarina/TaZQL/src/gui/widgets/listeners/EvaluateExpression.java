 package gui.widgets.listeners;

import ast.expression.Expression;
import evaluator.BooleanValue;
import evaluator.EvaluatorVisitor;
import evaluator.Value;
import evaluator.ValueRepository;
import gui.questions.IQuestionUI;

public class EvaluateExpression {

	private ValueRepository valueRepository;
	private final EvaluatorVisitor evaluatorVisitor;
	private final Expression expression;
	private final IQuestionUI setQuestion;
	
	public EvaluateExpression(ValueRepository valueRepository, Expression expression, IQuestionUI setQuestion) {
		this.valueRepository = valueRepository;
		this.expression = expression;
		this.evaluatorVisitor = new EvaluatorVisitor(this.valueRepository);
		this.setQuestion = setQuestion;
		setValueInGUI();
	}
		
	public Value evaluate() {
		Value evaluatedValue = expression.accept(this.evaluatorVisitor);
		return evaluatedValue;
	}
	
	public void setValue(String id, Value value) {
		this.valueRepository.putValue(id, value);
	}
	// TODO under construction; solving computed question setter.
	public void setValueInGUI() {  
		String trueValue = new BooleanValue(true).toString();
		String falseValue = new BooleanValue(false).toString();
		String evaluatedValue = String.valueOf(evaluate());
		String regex ="[-+]?\\d+(\\.\\d+)?";
		
		if(trueValue.equals(evaluatedValue) || falseValue.equals(evaluatedValue)) {
			this.setQuestion.setVisibilityValue(evaluate());
		}
		if (evaluatedValue.matches(regex)) {
			System.out.println("Evaluator check, match: " + evaluate().toString().matches(regex));
			System.out.println("Evaluator check, output " + evaluate().toString());
			this.setQuestion.setValue(evaluate());
		}
		
		evaluate();
		
	}
}
