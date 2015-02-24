package lang.ql.ast.expression;

import lang.ql.semantics.Visitor;

/**
 * Created by bore on 09/02/15.
 */
public class Add extends BinaryExpr
{
    public Add(Expr left, Expr right, int lineNumber)
    {
        super(left, right, lineNumber);
    }

    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}