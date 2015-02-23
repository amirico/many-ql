package com.form.language.ast.expression.logic;

import com.form.language.ast.expression.BinaryExpression;
import com.form.language.ast.expression.PrimitiveExpression;
import com.form.language.ast.expression.literal.IntLiteral;
import com.form.language.ast.values.BoolValue;

public class GreaterThanOrEqual extends BinaryExpression implements PrimitiveExpression {

	public GreaterThanOrEqual(PrimitiveExpression left, PrimitiveExpression right) {
		super(left, right);
	}
	
	@Override
	public BoolValue evaluate() {
		return ((IntLiteral)left).evaluate().GreaterThanOrEqual(((IntLiteral)right).evaluate());
	}

	@Override
	public Boolean typeCorrect(Error e) {
		// TODO Auto-generated method stub
		return null;
	}

}