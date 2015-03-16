package org.uva.sea.ql.encoders.ast.expression.literal;

import org.uva.sea.ql.encoders.ast.TextLocation;
import org.uva.sea.ql.encoders.ast.expression.Expression;
import org.uva.sea.ql.encoders.visitor.ExpressionVisitor;

public class StringLiteral extends Expression {

	private final String value;

	public StringLiteral(TextLocation textLocation, String value) {
		super(textLocation);
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
