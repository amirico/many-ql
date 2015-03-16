﻿using AST.Nodes.Interfaces;
using AST.Representation;

namespace AST.Nodes.Expressions.Binary
{
    public class GreaterThan : Binary
    {
        
        public GreaterThan(Expression left, Expression right, PositionInText position)
            : base(left, right, position)
        {}

        public override T Accept<T>(ASTVisitors.IVisitor<T> visitor)
        {
            return visitor.Visit(this);
        }

        public override string ToString()
        {
            return ">";
        }
    }
}
