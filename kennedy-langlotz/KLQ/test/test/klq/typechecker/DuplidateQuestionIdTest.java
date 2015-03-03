package test.klq.typechecker;

import com.klq.ast.ANode;
import com.klq.ast.impl.*;
import com.klq.ast.impl.expr.literal.StringNode;
import com.klq.typecheker.TypeChecker;
import com.klq.typecheker.error.NotUniqueID;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Created by Juriaan on 28-2-2015.
 */
public class DuplidateQuestionIdTest {
    private QuestionnaireNode ast;
    @Before
    public void setUp() throws Exception {
        ast = new QuestionnaireNode("1");
    }

    @Test
    public void testDuplicateQuestionId(){
        ast.getChildren().add(new QuestionNode("question1", "string", "This is a test question", "1"));
        ast.getChildren().add(new QuestionNode("question2", "numeral", "This is another test question", "2"));
        TypeChecker tc = new TypeChecker(ast);
        tc.run();
        assertEquals(0, tc.getErrors().size());

        ast.getChildren().add(new QuestionNode("question1", "numeral", "This is another test question, but with a duplicate ID", "3"));
        tc = new TypeChecker(ast);
        tc.run();
        assertEquals(1,tc.getErrors().size());
        assertThat(tc.getErrors().get(0), instanceOf(NotUniqueID.class));

        ArrayList<ANode> list = new ArrayList<ANode>();
        list.add(new StringNode("test", "5"));
        ast.getChildren().add(new ComputedQuestionNode("question1", "numeral", "This is another test question, but with a duplicate ID", list, "4"));
        tc = new TypeChecker(ast);
        tc.run();
        assertEquals(2, tc.getErrors().size());
    }
}
