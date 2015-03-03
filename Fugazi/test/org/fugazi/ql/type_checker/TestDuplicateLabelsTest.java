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

public class TestDuplicateLabelsTest extends TypeCheckerBaseTest {

    @Before
    public void setUp() {
        this.fileName = "duplicateLabels.ql";
        super.setUp();
    }

    @Test
    public void testFormCorrect() throws Exception {
        boolean formCorrect = checker.isFormCorrect();
        assertTrue(formCorrect);
    }

    @Test
    public void testErrorCount() throws Exception {
        List<ASTNodeIssue> errors = checker.getErrors();

        assertTrue(errors.isEmpty());
    }

    @Test
    public void testWarningsCount() throws Exception {
        List<ASTNodeIssue> warnings = checker.getWarnings();
        assertFalse(warnings.isEmpty());
        assertEquals(1, warnings.size());
    }

    @Test
    public void testWarningTypes() throws Exception {
        List<ASTNodeIssue> warnings = checker.getWarnings();
        assertFalse(warnings.isEmpty());

        ASTNodeIssueType expectedType = ASTNodeIssueType.WARNING.DUPLICATE_LABEL;
        List<ASTNodeIssueType> receivedTypes = new ArrayList<>();

        for (ASTNodeIssue warning: warnings) {
            receivedTypes.add(warning.getErrorType());
        }
        // no custom arrayEquals method
        for (ASTNodeIssueType received : receivedTypes) {
            assertTrue(received.equals(expectedType));
        }
    }
}
