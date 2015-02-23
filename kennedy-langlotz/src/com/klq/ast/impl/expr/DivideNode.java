package com.klq.ast.impl.expr;

import com.klq.ast.ANode;
import com.klq.ast.IVisitor;

/**
 * Created by Juriaan on 21-2-2015.
 */
public class DivideNode extends ABinaryExprNode {

    public DivideNode(ANode leftChild, ANode rightChild) {
        super(leftChild, rightChild);
    }

    @Override
    public void accept(IVisitor visitor) {
        super.accept(visitor);
        visitor.visit(this);
    }
}