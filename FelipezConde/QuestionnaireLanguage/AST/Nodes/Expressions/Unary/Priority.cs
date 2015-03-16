﻿using AST.Nodes.Interfaces;
using AST.Representation;

namespace AST.Nodes.Expressions.Unary
{
    public class Priority : Unary
    {
        public Priority(Expression child, PositionInText position)
            : base(child ,position)
        { }

        public override T Accept<T>(ASTVisitors.IVisitor<T> visitor)
        { return visitor.Visit(this); }

        public override string ToString()
        {
            return "()";
        }
    }
}
