package org.uva.sea.ql.AST.expression.mathexpression;

import org.uva.sea.ql.AST.expression.BinaryExpression;
import org.uva.sea.ql.AST.literal.NumberLiteral;
import org.uva.sea.ql.AST.value.IntegerValue;

public class SubExpression extends BinaryExpression{
	private NumberLiteral leftLiteral = (NumberLiteral) this.leftExpression;
	private NumberLiteral rightLiteral = (NumberLiteral) this.rightExpression;
	
	@Override
	public IntegerValue interpretExpression() {
		int leftInteger = leftLiteral.interpretExpression().getValue();
		int rightInteger = rightLiteral.interpretExpression().getValue();
		return new  IntegerValue(leftInteger - rightInteger);
	}
}