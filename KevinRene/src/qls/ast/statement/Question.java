package qls.ast.statement;

import ql.ast.QLNode;
import ql.ast.expression.Identifier;
import qls.ast.QLSStatement;
import qls.ast.visitor.QLSVisitor;

public class Question extends QLSStatement {
	public Question(Identifier identifier) {
		
	}
	
	public Question(Identifier identifier, QLNode undeterminedNode) {
		
	}
	
	@Override
	public <T> T accept(QLSVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
