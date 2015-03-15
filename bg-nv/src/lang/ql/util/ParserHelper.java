package lang.ql.util;

import lang.ql.ast.AstNode;
import lang.ql.gen.QLLexer;
import lang.ql.gen.QLParser;
import lang.ql.ast.AstBuilder;
import org.antlr.v4.runtime.*;

import java.io.IOException;

/**
 * Created by bore on 17/02/15.
 */
public class ParserHelper
{
    public static AstNode ParseExpression(String input)
    {
        CharStream s = createInputStream(input);
        QLParser parser = createQLParser(s);
        QLParser.ExpressionContext c = parser.expression();
        AstBuilder visitor = new AstBuilder();

        return visitor.visitExpression(c);
    }

    public static AstNode ParseQuestion(String input)
    {
        CharStream s = createInputStream(input);
        QLParser parser = createQLParser(s);
        QLParser.QuestionContext c = parser.question();
        AstBuilder visitor = new AstBuilder();

        return visitor.visitQuestion(c);
    }

    public static AstNode ParseIfCondition(String input)
    {
        CharStream s = createInputStream(input);
        QLParser parser = createQLParser(s);
        QLParser.IfConditionContext c = parser.ifCondition();
        AstBuilder visitor = new AstBuilder();

        return visitor.visitIfCondition(c);
    }

    public static AstNode ParseForm(String file)
    {
        CharStream stream = createFileStream(file);
        QLParser parser = createQLParser(stream);

        QLParser.FormContext f = parser.form();
        AstBuilder visitor = new AstBuilder();

        return f.accept(visitor);
    }

    public static CharStream createInputStream(String input)
    {
        return new ANTLRInputStream(input);
    }

    public static CharStream createFileStream(String file)
    {
        CharStream stream = null;
        try
        {
            stream = new ANTLRFileStream(file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return stream;
    }

    private static QLParser createQLParser(CharStream stream)
    {
        QLLexer lexer = new QLLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return new QLParser(tokens);
    }
}