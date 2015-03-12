package edu.parser.QL.evaluator;

import edu.exceptions.EvaluationException;
import edu.parser.QL.nodes.AbstractNode;
import edu.parser.QL.nodes.expression.*;
import edu.parser.QL.nodes.question.Question;
import edu.parser.QL.nodes.type.Number;

import java.util.List;
import java.util.Optional;

/**
 * Created by Steven Kok on 12/03/2015.
 */
public class ComputedQuestionsRetriever implements ExpressionVisitor<AbstractNode> {
    private final List<Question> evaluatedQuestions;

    public ComputedQuestionsRetriever(List<Question> evaluatedQuestions) {
        this.evaluatedQuestions = evaluatedQuestions;
    }

    @Override
    public AbstractNode visit(Addition addition) {
        Number left = (Number) addition.getLeft().accept(this);
        Number right = (Number) addition.getRight().accept(this);
        return new Number(left.getNumber() + right.getNumber());
    }

    @Override
    public AbstractNode visit(And and) {
        return null;
    }

    @Override
    public AbstractNode visit(Equal equal) {
        return null;
    }

    @Override
    public AbstractNode visit(GreaterOrEqual greaterOrEqual) {
        return null;
    }

    @Override
    public AbstractNode visit(GreaterThan greaterThan) {
        return null;
    }

    @Override
    public AbstractNode visit(QLIdentifier qlIdentifier) {
        Question question = getQuestion(qlIdentifier);
        return question.getValue();
    }

    private Question getQuestion(QLIdentifier qlIdentifier) {
        Optional<Question> question = evaluatedQuestions.stream()
                .filter(evaluatedQuestion -> evaluatedQuestion.getQLIdentifier().equals(qlIdentifier))
                .findFirst();
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new EvaluationException("There is no question with identifier: " + qlIdentifier);
        }
    }

    @Override
    public AbstractNode visit(LessOrEqual lessOrEqual) {
        return null;
    }

    @Override
    public AbstractNode visit(LessThan lessThan) {
        return null;
    }

    @Override
    public AbstractNode visit(Multiplication multiplication) {
        return null;
    }

    @Override
    public AbstractNode visit(Not not) {
        return null;
    }

    @Override
    public AbstractNode visit(NotEqual notEqual) {
        return null;
    }

    @Override
    public AbstractNode visit(Or or) {
        return null;
    }

    @Override
    public AbstractNode visit(Division division) {
        return null;
    }

    @Override
    public AbstractNode visit(edu.parser.QL.nodes.type.Boolean aBoolean) {
        return aBoolean;
    }

    @Override
    public AbstractNode visit(Number number) {
        return number;
    }

    @Override
    public AbstractNode visit(Expression expression) {
        return expression.accept(this);
    }
}
