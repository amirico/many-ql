package org.fugazi.ql.type_checker;

import org.fugazi.ql.type_checker.issue.ASTNodeIssue;
import org.fugazi.ql.type_checker.issue.ASTNodeIssueType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestCheckerInvalidOperandTypesTest extends TypeCheckerBaseTest {

    @Before
    public void setUp() {
        this.fileName = "invalidOperandTypes.ql";
        super.setUp();
    }

    @Test
    public void testFormCorrect() throws Exception {
        boolean formCorrect = checker.isFormCorrect();
        assertFalse(formCorrect);
    }

    @Test
    public void testErrorCount() throws Exception {
        List<ASTNodeIssue> errors = checker.getErrors();

        assertFalse(errors.isEmpty());
        assertEquals(17, errors.size());
    }

    @Test
    public void testErrorTypes() throws Exception {
        List<ASTNodeIssue> errors = checker.getErrors();

        ASTNodeIssueType expectedType = ASTNodeIssueType.ERROR.TYPE_MISMATCH;
        List<ASTNodeIssueType> receivedTypes = new ArrayList<>();

        for (ASTNodeIssue error: errors) {
            System.out.println(error.getMessage() +
            " " + (error.getLine()) + " " + error.getNode());
            receivedTypes.add(error.getErrorType());
        }
        // no custom arrayEquals method
        for (ASTNodeIssueType received : receivedTypes) {
            assertTrue(received.equals(expectedType));
        }
    }

    @Test
    public void testNoWarnings() throws Exception {
        List<ASTNodeIssue> warnings = checker.getWarnings();
        assertTrue(warnings.isEmpty());
    }
}
