﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UvA.SoftCon.Questionnaire.QL.AST.Model.Expressions;
using UvA.SoftCon.Questionnaire.QL.AST.Model.Statements;
using UvA.SoftCon.Questionnaire.QL.Grammar;
using UvA.SoftCon.Questionnaire.Utilities;
using UvA.SoftCon.Questionnaire.Utilities.AST;

namespace UvA.SoftCon.Questionnaire.QL.AST.Building
{
    /// <summary>
    /// Represents a visitor for the <c>stat</c> parser rule.
    /// </summary>
    internal class StatementVisitor : QLBaseVisitor<IStatement>
    {
        public override IStatement VisitIfStatement(QLParser.IfStatementContext context)
        {
            IExpression condition = context.expr().Accept(new ExpressionVisitor());

            var thenStatements = new List<IStatement>();
            var elseStatements = new List<IStatement>();
           
            foreach (var statement in context._then) 
            {
                thenStatements.Add(statement.Accept(this));
            }
            foreach (var statement in context._else)
            {
                elseStatements.Add(statement.Accept(this));
            }

            return new IfStatement(condition, thenStatements, elseStatements, context.GetTextPosition());
        }

        public override IStatement VisitQuestion(QLParser.QuestionContext context)
        {
            DataType type = StringEnum.GetEnumerationValue<DataType>(context.TYPE().GetText());
            Identifier id = new Identifier(context.ID().GetText(), context.GetTextPosition());
            string label = context.STRING().GetText();

            // Remove the leading and trailing '"' characters from the string literal.
            label = label.Trim('"');

            if (context.expr() != null)
            {
                IExpression expression = context.expr().Accept(new ExpressionVisitor());
                return new Question(type, id, label, expression, context.GetTextPosition());
            }
            else
            {
                return new Question(type, id, label, context.GetTextPosition());
            }
        }

        public override IStatement VisitDefinition(QLParser.DefinitionContext context)
        {
            DataType dataType = StringEnum.GetEnumerationValue<DataType>(context.TYPE().GetText());
            Identifier id = new Identifier(context.ID().GetText(), context.GetTextPosition());
            IExpression expression = context.expr().Accept(new ExpressionVisitor());

            return new Definition(dataType, id, expression, context.GetTextPosition());
        }
    }
}