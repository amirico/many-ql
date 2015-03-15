package ql.ast.type;

import ql.ast.QLType;
import ql.ast.visitor.ExpressionVisitor;

public class QLForm extends QLType {
	public QLForm() {}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public boolean equals(Object comparisonObject) {
		return comparisonObject instanceof QLForm;
	}
}
