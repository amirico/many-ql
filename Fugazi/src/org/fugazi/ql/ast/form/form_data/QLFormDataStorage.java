package org.fugazi.ql.ast.form.form_data;

import org.fugazi.ql.ast.form.Form;
import org.fugazi.ql.ast.statement.ComputedQuestion;
import org.fugazi.ql.ast.statement.IfStatement;
import org.fugazi.ql.ast.statement.Question;
import org.fugazi.ql.ast.form.form_data.visitor.ComputedQuestionsVisitor;
import org.fugazi.ql.ast.form.form_data.visitor.IfStatementsVisitor;
import org.fugazi.ql.ast.form.form_data.visitor.QuestionsVisitor;
import org.fugazi.ql.ast.type.Type;

import java.util.*;

public class QLFormDataStorage {
    private final Form form;

    private final QuestionsVisitor questionsVisitor;
    private final ComputedQuestionsVisitor computedQuestionsVisitor;
    private final IfStatementsVisitor ifStatementsVisitor;

    public QLFormDataStorage(Form _form) {
        this.form = _form;

        this.questionsVisitor = new QuestionsVisitor(this.form);
        this.computedQuestionsVisitor = new ComputedQuestionsVisitor(this.form);
        this.ifStatementsVisitor = new IfStatementsVisitor(this.form);
    }

    /**
     * =====================
     * Public exposed getters
     * =====================
     */

    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        Iterator<Question> iterator = this.questionsVisitor.getQuestions();

        // convert back to list
        // this way internal lists will not be modified by clients
        iterator.forEachRemaining(questions::add);
        return questions;
    }

    public List<ComputedQuestion> getComputedQuestions() {
        List<ComputedQuestion> computedQuestions = new ArrayList<>();
        Iterator<ComputedQuestion> iterator = this.computedQuestionsVisitor.getComputedQuestions();

        // convert back to list
        // this way internal lists will not be modified by clients
        iterator.forEachRemaining(computedQuestions::add);
        return computedQuestions;
    }

    public List<Question> getAllQuestions() {
        List<Question> allQuestions = this.getQuestions();
        List<ComputedQuestion> computedQuestions = this.getComputedQuestions();
        allQuestions.addAll(computedQuestions);

        return allQuestions;
    }

    public List<IfStatement> getIfStatements() {
        return this.ifStatementsVisitor.getIfStatement();
    }

    public HashMap<String, Type> getallQuestionTypes() {
        List<Question> questions = this.getAllQuestions();
        HashMap<String, Type> questionTypes = new HashMap<>();

        for (Question question : questions) {
            questionTypes.put(question.getIdName(), question.getType());
        }
        return questionTypes;
    }
}