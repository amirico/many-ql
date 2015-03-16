package com.klq.typechecker;

import com.klq.ast.ANode;
import com.klq.ast.IVisitor;
import com.klq.ast.impl.stmt.ComputedQuestionNode;
import com.klq.ast.impl.stmt.ConditionalNode;
import com.klq.ast.impl.stmt.QuestionNode;
import com.klq.ast.impl.stmt.QuestionnaireNode;
import com.klq.ast.impl.expr.literal.DateNode;
import com.klq.ast.impl.expr.literal.IdentifierNode;
import com.klq.ast.impl.expr.literal.NumberNode;
import com.klq.ast.impl.expr.literal.StringNode;
import com.klq.ast.impl.expr.bool.*;
import com.klq.ast.impl.expr.math.AddNode;
import com.klq.ast.impl.expr.math.DivideNode;
import com.klq.ast.impl.expr.math.MultiplyNode;
import com.klq.ast.impl.expr.math.SubtractNode;

/**
 * Created by Juriaan on 28-2-2015.
 */
public class QuestionMapper implements IVisitor<Void> {
    private QuestionTable table;

    public QuestionMapper(QuestionTable table) {
        this.table = table;
    }

    @Override
    public Void visit(QuestionnaireNode node) {
        for(ANode child : node.getChildren()){
            child.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(QuestionNode node) {
        table.add(node.getQuestionID(), node);
        return null;
    }

    @Override
    public Void visit(ComputedQuestionNode node) {
        table.add(node.getQuestionID(), node);
        return null;
    }

    @Override
    public Void visit(StringNode node) {
        return null;
    }

    @Override
    public Void visit(NumberNode node) {
        return null;
    }

    @Override
    public Void visit(DateNode node) {
        return null;
    }

    @Override
    public Void visit(ANode node) {
        return null;
    }

    @Override
    public Void visit(MultiplyNode node) {
        return null;
    }

    @Override
    public Void visit(DivideNode node) {
        return null;
    }

    @Override
    public Void visit(AddNode node) {
        return null;
    }

    @Override
    public Void visit(SubtractNode node) {
        return null;
    }

    @Override
    public Void visit(ConditionalNode node) {
        for(ANode child : node.getChildren()){
            child.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(GreaterThanNode node) {
        return null;
    }

    @Override
    public Void visit(GreaterEqualsNode node) {
        return null;
    }

    @Override
    public Void visit(LessThanNode node) {
        return null;
    }

    @Override
    public Void visit(LessEqualsNode node) {
        return null;
    }

    @Override
    public Void visit(EqualsNode node) {
        return null;
    }

    @Override
    public Void visit(NotEqualsNode node) {
        return null;
    }

    @Override
    public Void visit(AndNode node) {
        return null;
    }

    @Override
    public Void visit(OrNode node) {
        return null;
    }

    @Override
    public Void visit(IdentifierNode node) {
        return null;
    }
}