package nl.uva.bromance;

import nl.uva.bromance.listeners.GrammarErrorListener;
import nl.uva.bromance.listeners.QLParseTreeListener;
import nl.uva.bromance.parsers.QLLexer;
import nl.uva.bromance.parsers.QLParser;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.contentOf;

/**
 * Created by Robert on 10-2-2015.
 */
public class GrammarTest {

    private FakeGrammarListener listener;
    private ParseTreeWalker walker;

    @Before
    public void setup()
    {
        listener = new FakeGrammarListener();
        walker = new ParseTreeWalker();
    }


    private QLParser.QuestionnaireContext createTree(String content) throws IOException{
        QLLexer lexer = new QLLexer(new ANTLRInputStream(new ByteArrayInputStream(content.getBytes())));
        lexer.removeErrorListeners();
        lexer.addErrorListener(new GrammarErrorListener());
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new GrammarErrorListener());
        return parser.questionnaire();
    }

    @Test(expected = GrammarErrorListener.SyntaxError.class)
    public void contentContainsQuestionnaireWithoutForms() throws IOException{
        String content = "Name: \"Tax\" {}";

        walker.walk(listener, createTree(content));

        assertThat(listener.questionnaireCount).isEqualTo(1);
        assertThat(listener.formCount).isEqualTo(0);
    }


    @Test
    public void questionnaireHasOneFormWithoutBodyContent() throws IOException{
        String content = "Name: \"Tax\" {\n" +
                "    Form: \"default\" {\n" +
                "\n" +
                "    }}";

        walker.walk(listener, createTree(content));

        assertThat(listener.formCount).isEqualTo(1);
    }

    @Test
    public void containsMultipleFormsWithoutBodyContent() throws IOException{
        String content = "Name: \"Tax\" {\n" +
                "    Form: \"default\" {\n" +
                "\n" +
                "    }"+
                "    Form: \"default\" {\n" +
                "\n" +
                "    }}";
        walker.walk(listener, createTree(content));

        assertThat(listener.formCount).isEqualTo(2);
        assertThat(listener.questionCount).isEqualTo(0);
    }

    @Test
    public void formContainsIfStatementWithoutBodyContents() throws IOException
    {
        String content = "Name: \"Tax\" {\n" +
                "    Form: \"default\" {\n" +
                "\n     If: something{}" +
                "    }}";
        walker.walk(listener, createTree(content));

        assertThat(listener.ifStatementCount).isEqualTo(1);
    }

    @Test
    public void formContainsMulitpleIfStatementWithoutBodyContents() throws IOException
    {
        String content = "Name: \"Tax\" {\n" +
                "    Form: \"default\" {\n" +
                "\n     If: something{}" +
                "\n     If: something{}" +
                "    }}";
        walker.walk(listener, createTree(content));

        assertThat(listener.ifStatementCount).isEqualTo(2);
    }
    @Test
    public void formContainsIfStatementAndElseIfStatementWithoutBodyContents() throws IOException
    {
        String content = "Name: \"Tax\" {\n" +
                "    Form: \"default\" {\n" +
                "\n     If: something{}" +
                "\n     Else If: something{}" +
                "    }}";
        walker.walk(listener, createTree(content));

        assertThat(listener.ifStatementCount).isEqualTo(1);
        assertThat(listener.elseIfStatementCount).isEqualTo(1);
    }
    @Test
    public void formContainsIfStatementAndMultipleElseIfStatementWithoutBodyContents() throws IOException
    {
        String content = "Name: \"Tax\" {\n" +
                "    Form: \"default\" {\n" +
                "\n     If: something{}" +
                "\n     Else If: something{}" +
                "\n     Else If: something{}" +
                "    }}";
        walker.walk(listener, createTree(content));

        assertThat(listener.ifStatementCount).isEqualTo(1);
        assertThat(listener.elseIfStatementCount).isEqualTo(2);
    }

    @Test
    public void formContainsIfStatementAndElseStatementWithoutBodyContents() throws IOException {
        String content = "Name: \"Tax\" {\n" +
                "    Form: \"default\" {\n" +
                "\n     If: something{}" +
                "\n     Else:{}" +
                "    }}";
        walker.walk(listener, createTree(content));

        assertThat(listener.ifStatementCount).isEqualTo(1);
        assertThat(listener.elseStatementCount).isEqualTo(1);
    }

    @Test
    public void formContainsIfStatementAndElseIfStatementAndElseStatementWithoutBodyContents() throws IOException {
        String content = "Name: \"Tax\" {\n" +
                "    Form: \"default\" {\n" +
                "\n     If: something{}" +
                "\n     Else If: something{}" +
                "\n     Else If: something{}" +
                "\n     Else:{}" +
                "    }}";
        walker.walk(listener, createTree(content));

        assertThat(listener.ifStatementCount).isEqualTo(1);
        assertThat(listener.elseIfStatementCount).isGreaterThan(1);
        assertThat(listener.elseStatementCount).isEqualTo(1);
    }
    static class FakeGrammarListener extends QLParseTreeListener {
        public int formCount = 0;
        public int questionCount = 0;
        public int questionnaireCount = 0;
        public int ifStatementCount = 0;
        public int elseIfStatementCount = 0;
        public int elseStatementCount = 0;

        @Override
        public void exitForm(QLParser.FormContext ftx) {
            super.exitForm(ftx);
            formCount += 1;
        }

        @Override
        public void exitQuestion(QLParser.QuestionContext qtx) {
            super.exitQuestion(qtx);
            questionCount += 1;
        }

        @Override
        public void exitQuestionnaire(QLParser.QuestionnaireContext ctx) {
            super.exitQuestionnaire(ctx);
            questionnaireCount +=1;
        }

        @Override
        public void exitIfStatement(QLParser.IfStatementContext ctx) {
            super.exitIfStatement(ctx);
            ifStatementCount +=1;
        }

        @Override
        public void exitElseIfStatement(QLParser.ElseIfStatementContext ctx) {
            super.exitElseIfStatement(ctx);
            this.elseIfStatementCount +=1;
        }

        @Override
        public void exitElseStatement(QLParser.ElseStatementContext ctx) {
            super.exitElseStatement(ctx);
            this.elseStatementCount += 1;
        }
    }


}
