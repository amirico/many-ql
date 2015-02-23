package org.uva.student.calinwouter.qlqls.qls.model;

public interface IQuestionWidgetCallback {

    public void caseCheckboxWidget(Question question);

    public void caseRadioWidget(Question question);

    public void caseIntboxWidget(Question question);

    public void caseSpinboxWidget(Question question);

    public void caseTextboxWidget(Question question);

}
