package nl.uva.softwcons.validation;

import static org.assertj.core.api.Assertions.assertThat;
import helper.TestHelper;

import java.util.List;

import nl.uva.softwcons.Questionnaire;
import nl.uva.softwcons.ast.form.Form;
import nl.uva.softwcons.validation.typechecker.TypeChecker;
import nl.uva.softwcons.validation.typechecker.error.DuplicateQuestion;
import nl.uva.softwcons.validation.typechecker.error.InvalidConditionType;
import nl.uva.softwcons.validation.typechecker.error.InvalidOperatorTypes;
import nl.uva.softwcons.validation.typechecker.error.InvalidQuestionExpressionType;
import nl.uva.softwcons.validation.typechecker.error.UndefinedReference;

import org.junit.Test;

public class TypeCheckerTest {

    @Test
    public void validateNoInitialErrors() {
        final String question = "question: \"Label 1\" boolean";
        final List<Error> validationErrors = getTypeCheckerValidationErrors(question);

        assertThat(validationErrors).isEmpty();
    }

    @Test
    public void testDuplicatedQuesionIdentifiers() {
        final String question = "question: \"Label 1\" boolean";
        final List<Error> validationErrors = getTypeCheckerValidationErrors(question, question);

        assertThat(validationErrors).hasSize(1);
        assertThat(validationErrors).hasOnlyElementsOfType(DuplicateQuestion.class);
    }

    @Test
    public void testDuplicatedQuesionIdentifiersNotSuccessive() {
        final String question1 = "question: \"Label 1\" boolean";
        final String question2 = "question2: \"Label 2\" boolean";
        final List<Error> validationErrors = getTypeCheckerValidationErrors(question1, question2, question1);

        assertThat(validationErrors).hasSize(1);
        assertThat(validationErrors).hasOnlyElementsOfType(DuplicateQuestion.class);
    }

    @Test
    public void testDuplicatedQuesionIdentifiersWithConditional() {
        final String question = "question: \"Label\" boolean";
        final String ifStatement = " if (true) { question: \"Label\" boolean }";

        final List<Error> validationErrors = getTypeCheckerValidationErrors(question, ifStatement);

        assertThat(validationErrors).hasSize(1);
        assertThat(validationErrors).hasOnlyElementsOfType(DuplicateQuestion.class);
    }

    @Test
    public void testMultipleDuplicatedQuesionIdentifiersWithConditional() {
        final String question = "question: \"Label 1\" boolean";
        final String ifStatement = " if (true) { question: \"Label\" boolean }";

        final List<Error> validationErrors = getTypeCheckerValidationErrors(question, question, ifStatement);

        assertThat(validationErrors).hasSize(2);
        assertThat(validationErrors).hasOnlyElementsOfType(DuplicateQuestion.class);
    }

    @Test
    public void testDifferentDuplicatedQuesionIdentifiers() {
        final String question = "question: \"Label\" boolean";
        final String question2 = "question2: \"Label\" boolean";

        final List<Error> validationErrors = getTypeCheckerValidationErrors(question, question2, question, question2);

        assertThat(validationErrors).hasSize(2);
        assertThat(validationErrors).hasOnlyElementsOfType(DuplicateQuestion.class);
    }

    @Test
    public void testValidQuestionWithBooleanType() {
        final String question = "question: \"Label 1\" boolean (true && false)";
        final List<Error> validationErrors = getTypeCheckerValidationErrors(question);

        assertThat(validationErrors).isEmpty();
    }

    @Test
    public void testValidQuestionWithIntegerType() {
        final String question = "question: \"Label 1\" integer (6 * 5)";
        final List<Error> validationErrors = getTypeCheckerValidationErrors(question);

        assertThat(validationErrors).isEmpty();
    }

    @Test
    public void testValidQuestionWithDecimalType() {
        final String question = "question: \"Label 1\" decimal (3.0 / 3)";
        final List<Error> validationErrors = getTypeCheckerValidationErrors(question);

        assertThat(validationErrors).isEmpty();
    }

    @Test
    public void testValidQuestionWithStringType() {
        final String question = "question: \"Label 1\" string (\"plain string\")";
        final List<Error> validationErrors = getTypeCheckerValidationErrors(question);

        assertThat(validationErrors).isEmpty();
    }

    @Test
    public void testInvalidQuestionWithBooleanType() {
        final String question = "question: \"Label 1\" boolean (3 * 4)";
        final List<Error> validationErrors = getTypeCheckerValidationErrors(question);

        assertThat(validationErrors).hasSize(1);
        assertThat(validationErrors).hasOnlyElementsOfType(InvalidQuestionExpressionType.class);
    }

    @Test
    public void testInvalidQuestionWithIntegerType() {
        final String question = "question: \"Label 1\" integer (true && false)";
        final List<Error> validationErrors = getTypeCheckerValidationErrors(question);

        assertThat(validationErrors).hasSize(1);
        assertThat(validationErrors).hasOnlyElementsOfType(InvalidQuestionExpressionType.class);
    }

    @Test
    public void testInvalidQuestionWithDecimalType() {
        final String question = "question: \"Label 1\" decimal (true && false)";
        final List<Error> validationErrors = getTypeCheckerValidationErrors(question);

        assertThat(validationErrors).hasSize(1);
        assertThat(validationErrors).hasOnlyElementsOfType(InvalidQuestionExpressionType.class);
    }

    @Test
    public void testInvalidQuestionWithStringType() {
        final String question = "question: \"Label 1\" string (6 * 5)";
        final List<Error> validationErrors = getTypeCheckerValidationErrors(question);

        assertThat(validationErrors).hasSize(1);
        assertThat(validationErrors).hasOnlyElementsOfType(InvalidQuestionExpressionType.class);
    }

    @Test
    public void testValidConditionalWithBooleanExpressionType() {
        final String question = "if (true) { question: \"Label 1\" boolean }";
        final List<Error> validationErrors = getTypeCheckerValidationErrors(question);

        assertThat(validationErrors).isEmpty();
    }

    @Test
    public void testInvalidConditionalWithDecimalExpressionType() {
        final String question = "if (6.0 * 5) { question: \"Label 1\" boolean }";
        final List<Error> validationErrors = getTypeCheckerValidationErrors(question);

        assertThat(validationErrors).hasSize(1);
        assertThat(validationErrors).hasOnlyElementsOfType(InvalidConditionType.class);
    }

    @Test
    public void testInvalidConditionalWithIntegerExpressionType() {
        final String question = "if (4 * 5) { question: \"Label 1\" boolean }";
        final List<Error> validationErrors = getTypeCheckerValidationErrors(question);

        assertThat(validationErrors).hasSize(1);
        assertThat(validationErrors).hasOnlyElementsOfType(InvalidConditionType.class);
    }

    @Test
    public void testInvalidConditionalWithStringExpressionType() {
        final String question = "if (\"test\") { question: \"Label 1\" boolean }";
        final List<Error> validationErrors = getTypeCheckerValidationErrors(question);

        assertThat(validationErrors).hasSize(1);
        assertThat(validationErrors).hasOnlyElementsOfType(InvalidConditionType.class);
    }

    @Test
    public void testValidExpressionOperands() {
        final String question = "question: \"Label 1\" boolean(true)";
        final List<Error> validationErrors = getTypeCheckerValidationErrors(question);

        assertThat(validationErrors).isEmpty();
    }

    /**
     * The tests for the {@link InvalidOperatorTypes} and
     * {@link UndefinedReference} errors might include getting other types of
     * errors too. This is because invalid expressions/variables "bubble up",
     * meaning that the whole expression will be resolved to UNDEFINED and not
     * matching the question's original type or triggering the expression
     * operands error.
     *
     */

    @Test
    public void testInvalidArithmeticExpressionOperands() {
        final String[] questions = { "question: \"Label 1\" integer(6 + false)",
                "question: \"Label 1\" integer(6 - false)", "question: \"Label 1\" integer(6 * false)",
                "question: \"Label 1\" integer(6 / false)" };

        for (final String question : questions) {
            final List<Error> validationErrors = getTypeCheckerValidationErrors(question);

            assertThat(validationErrors).hasSize(2);
            assertThat(validationErrors).extracting("class").contains(InvalidOperatorTypes.class,
                    InvalidQuestionExpressionType.class);
        }
    }

    @Test
    public void testInvalidComparisonExpressionOperands() {
        final String[] questions = { "question: \"Label 1\" integer(6 < false)",
                "question: \"Label 1\" integer(6 > false)", "question: \"Label 1\" integer(6 >= false)",
                "question: \"Label 1\" integer(6 <= false)" };

        for (final String question : questions) {
            final List<Error> validationErrors = getTypeCheckerValidationErrors(question);

            assertThat(validationErrors).hasSize(2);
            assertThat(validationErrors).extracting("class").contains(InvalidOperatorTypes.class,
                    InvalidQuestionExpressionType.class);
        }
    }

    @Test
    public void testInvalidLogicalExpressionOperands() {
        final String[] questions = { "question: \"Label 1\" integer(6 && false)",
                "question: \"Label 1\" integer(6 || false)", "question: \"Label 1\" integer(!6)" };

        for (final String question : questions) {
            final List<Error> validationErrors = getTypeCheckerValidationErrors(question);

            assertThat(validationErrors).hasSize(2);
            assertThat(validationErrors).extracting("class").contains(InvalidOperatorTypes.class,
                    InvalidQuestionExpressionType.class);
        }
    }

    @Test
    public void testInvalidEqualityExpressionOperands() {
        final String[] questions = { "question: \"Label 1\" integer(6 == false)",
                "question: \"Label 1\" integer(6 != false)" };

        for (final String question : questions) {
            final List<Error> validationErrors = getTypeCheckerValidationErrors(question);

            assertThat(validationErrors).hasSize(2);
            assertThat(validationErrors).extracting("class").contains(InvalidOperatorTypes.class,
                    InvalidQuestionExpressionType.class);
        }
    }

    @Test
    public void testInvalidExpressionOperandsReferringAQuestion() {
        final String question1 = "question1: \"Label 1\" boolean";
        final String question2 = "question2: \"Label 2\" integer(6 + question1)";

        final List<Error> validationErrors = getTypeCheckerValidationErrors(question1, question2);

        assertThat(validationErrors).hasSize(2);
        assertThat(validationErrors).extracting("class").contains(InvalidOperatorTypes.class,
                InvalidQuestionExpressionType.class);
    }

    @Test
    public void testUndefinedQuestionReferenceInComputedQuestion() {
        final String question = "question: \"Label 1\" boolean(notAQuestion)";
        final List<Error> validationErrors = getTypeCheckerValidationErrors(question);

        assertThat(validationErrors).hasSize(2);
        assertThat(validationErrors).extracting("class").contains(UndefinedReference.class,
                InvalidQuestionExpressionType.class);
    }

    @Test
    public void testUndefinedQuestionReferenceInConditional() {
        final String question = "if (notAQuestion) { question: \"Label 1\" boolean(true) }";
        final List<Error> validationErrors = getTypeCheckerValidationErrors(question);

        assertThat(validationErrors).hasSize(2);
        assertThat(validationErrors).extracting("class").contains(UndefinedReference.class, InvalidConditionType.class);
    }

    @Test
    public void testUndefinedQuestionReferenceNestedInConditional() {
        final String question = "if (notDefined > 4*(5 / 2)) { question: \"Label 1\" boolean(true) }";
        final List<Error> validationErrors = getTypeCheckerValidationErrors(question);

        assertThat(validationErrors).hasSize(2);
        assertThat(validationErrors).extracting("class").contains(UndefinedReference.class, InvalidOperatorTypes.class);
    }

    @Test
    public void testComplexFormWithMultipleErrors() {
        final String question1 = "question1: \"Label 1\" boolean";

        // reference to an undefined question, invalid operands in the division
        // expression, whole expression type not matching the question type
        final String question2 = "question2: \"Label 2\" integer(question11 / 2)";

        // invalid operands in the addition expression, invalid type for the
        // conditional and duplicate questions inside
        final String ifStatement = "if (question1 + (3 == 4)) { question11: \"Label 1\" boolean"
                + " question11: \"Label 1\" boolean }";

        final List<Error> validationErrors = getTypeCheckerValidationErrors(question1, question2, ifStatement);

        assertThat(validationErrors).hasSize(6);
        assertThat(validationErrors).extracting("class").contains(UndefinedReference.class, InvalidOperatorTypes.class,
                InvalidQuestionExpressionType.class, InvalidOperatorTypes.class, InvalidConditionType.class,
                DuplicateQuestion.class);
    }

    /**
     * Builds a Form called always "form1" with the given statements (separating
     * them by spaces), runs the {@link TypeChecker} checker and returns the
     * list of found errors.
     * 
     * @param formContents
     *            The statements that will be included in the form
     * @return The errors found by the {@link TypeChecker} checker
     */
    private static List<Error> getTypeCheckerValidationErrors(final String... formContents) {
        Form form = Questionnaire.build(TestHelper.buildForm("form1", formContents));
        TypeChecker checker = new TypeChecker();
        form.getBody().accept(checker);

        return checker.getErrors();

    }
}
