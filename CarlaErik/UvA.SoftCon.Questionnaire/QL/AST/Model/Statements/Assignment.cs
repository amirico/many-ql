﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UvA.SoftCon.Questionnaire.QL.AST.Model.Expressions;

namespace UvA.SoftCon.Questionnaire.QL.AST.Model.Statements
{
    public class Assignment : Node, IStatement
    {
        public Identifier Variable
        {
            get;
            private set;
        }

        public IExpression Expression
        {
            get;
            private set;
        }

        public Assignment(Identifier variable, IExpression expression, TextPosition position)
            : base(position)
        {
            Variable = variable;
            Expression = expression;
        }

        public override void Accept(IQLVisitor visitor)
        {
            visitor.Visit(this);
        }

        public override T Accept<T>(IQLVisitor<T> visitor)
        {
            return visitor.Visit(this);
        }

        public void AppendQuestions(ICollection<Question> questions)
        {
        }
    }
}